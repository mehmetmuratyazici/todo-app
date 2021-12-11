package io.mmy.todoapp.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    private TokenManagerImpl tokenManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        if(authHeader != null && authHeader.contains("Bearer")){
            token = authHeader.substring(7);
            try{
                username = tokenManager.getUsernameByToken(token);
            }
            catch (Exception ex){
                logger.debug(ex.getMessage());
            }
        }

        if(username != null && token != null
            && SecurityContextHolder.getContext().getAuthentication() == null){

            if(tokenManager.tokenValidate(token)){
                UsernamePasswordAuthenticationToken upToken =
                        new UsernamePasswordAuthenticationToken(username, null ,
                                new ArrayList<>());
                upToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(upToken);
            }

        }

        filterChain.doFilter(request, response);

    }
}
