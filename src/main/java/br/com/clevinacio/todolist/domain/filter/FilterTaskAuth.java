package br.com.clevinacio.todolist.domain.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.clevinacio.todolist.domain.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var servletPath = request.getServletPath();

        if (servletPath.contains("/tasks/")){
            var auth = request.getHeader("Authorization");

            var authEncoded = auth.substring("Basic".length()).trim();

            byte[] decodedPassword = Base64.getDecoder().decode(authEncoded);
            var authString = new String(decodedPassword);

            String[] credentials = authString.split(":");

            String username = credentials[0];
            String password = credentials[1];

            var userReq = userRepository.findByUsername(username);
            if(userReq == null){
                response.sendError(401);
            }else {
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), userReq.getPassword());
                if(passwordVerify.verified){
                    request.setAttribute("idUser", userReq.getId());
                    filterChain.doFilter(request,response);
                }else {
                    response.sendError(401);
                }
            }
        }else {
            filterChain.doFilter(request,response);
        }

    }
}
