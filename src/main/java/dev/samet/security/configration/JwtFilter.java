package dev.samet.security.configration;

import dev.samet.security.service.AppUserDetailsService;
import dev.samet.security.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final ApplicationContext applicationContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        String jwtToken = null;
        String username = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwtToken = authorizationHeader.substring(7);
        }
        if (jwtToken != null) {
            username = jwtService.extractUserName(jwtToken);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = applicationContext.getBean(AppUserDetailsService.class).loadUserByUsername(username);

            if(jwtService.validateToken(jwtToken, userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
