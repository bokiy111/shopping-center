package com.nwpu.shopping.api.common.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author: feoyang
 * @date: 2024/1/12 13:16
 * @description: TODO
 */
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {
    private final UserAuthProvider userAuthProvider;
    private static final String JWT_KEY_IN_HEADER = "authorization";
    private static final String IP_IN_HEADER = "x-forwarded-for";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String jwt = request.getHeader(JWT_KEY_IN_HEADER);
        String ip = request.getHeader(IP_IN_HEADER);
        if (jwt != null) {
            String[] elements = jwt.split(" ");
            if (elements.length == 2 && "Bearer".equals(elements[0])) {
                try {
                    SecurityContextHolder.getContext().setAuthentication(
                            userAuthProvider.validateToken(elements[1])
                    );
                } catch (RuntimeException e) {
                    SecurityContextHolder.clearContext();
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
