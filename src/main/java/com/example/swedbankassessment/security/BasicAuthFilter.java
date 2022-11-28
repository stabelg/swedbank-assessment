package com.example.swedbankassessment.security;

import com.example.swedbankassessment.entity.ApplicationUser;
import com.example.swedbankassessment.service.ApplicationUserService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
@AllArgsConstructor
public class BasicAuthFilter implements Filter {

    private PasswordEncoder passwordEncoder;
    private ApplicationUserService applicationUserService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse)response;

        String authHeader = req.getHeader("Authorization");

        if (authHeader != null) {
            byte[] e = Base64.decode(authHeader.substring(6).getBytes());
            String userAndPass = new String(e);
            String username = userAndPass.substring(0, userAndPass.indexOf(":"));
            String password = userAndPass.substring(userAndPass.indexOf(":") + 1);
            try {
                ApplicationUser applicationUser = applicationUserService.findApplicationUserByUsername(username);
                if (passwordEncoder.matches(password, applicationUser.getPassword())) {
                    chain.doFilter(request, response);
                } else {
                    res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Wrong user/password");
                }
            } catch (RuntimeException runtimeException){
                res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Wrong user/password");
            }
        } else {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing Auth Header");
        }
    }
}
