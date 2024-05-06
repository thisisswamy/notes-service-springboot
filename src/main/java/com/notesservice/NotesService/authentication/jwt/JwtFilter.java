package com.notesservice.NotesService.authentication.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notesservice.NotesService.exceptions.ApplicationException;
import com.notesservice.NotesService.users.entities.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    private final JwtUtilService jwtUtilService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        try{

            if(request.getServletPath().contains("/auth") || request.getServletPath().contains("/users/register") ){
                filterChain.doFilter(request,response);
                return;
            }
            final String jwtToken;
            final String authHeader = request.getHeader("Authorization");
            if(authHeader == null || !authHeader.startsWith("Bearer ")){
//                filterChain.doFilter(request,response);
                throw new ApplicationException("Unauthorized", HttpStatus.UNAUTHORIZED);
//                return;
            }
            jwtToken = authHeader.substring(7);
            String userEmail = jwtUtilService.extractUsername(jwtToken);
            User user = (User) userDetailsService.loadUserByUsername(userEmail);
            if(userEmail != null && jwtUtilService.isTokenValid(jwtToken,user) &&  !user.isTokenExpired()){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        user, null, user.getAuthorities()
                );
                authToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);

            }
            filterChain.doFilter(request,response);
        }catch (Exception e){
           unauthorizedRequests(response);
        }



    }
    private void unauthorizedRequests(HttpServletResponse response) throws IOException {
        Map<String ,String> errorResponse = new HashMap<>();
       ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json");
        errorResponse.put("timestamp", String.valueOf(Calendar.getInstance().getTime()));
        errorResponse.put("message", "Unauthorized");
        errorResponse.put("status", HttpStatus.UNAUTHORIZED.toString());
        response.getOutputStream().println(objectMapper.writeValueAsString(errorResponse));
        response.setStatus(401);
    }
}
