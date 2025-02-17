package dev.Zerphyis.auth.segurança;

import dev.Zerphyis.auth.repositorios.RepositoryLogin;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    ServiceToken tokenService;
    @Autowired
    RepositoryLogin repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      String requestURI = request.getRequestURI();

        // Não aplica o filtro nas rotas de cadastro e login
        if (requestURI.equals("/auth/cadastro") || requestURI.equals("/auth/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Recupera o token
        var token = recoverToken(request);
        if (token != null) {
            // Valida o token e recupera o login (e.g., email)
            String login = tokenService.validateToken(token);

            if (login != null) {
                // Recupera o usuário com base no email (login)
                UserDetails user = repository.findByEmail(login);
                if (user != null) {
                    // Cria um token de autenticação e define no SecurityContextHolder
                    var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        // Continua o filtro
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
