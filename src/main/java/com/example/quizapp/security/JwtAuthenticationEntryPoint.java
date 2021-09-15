package com.example.quizapp.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpRequest;

@Component
public class JwtAuthenticationEntryPoint {

    public static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException ex) throws IOException {
        logger.error("Responding with unauthorized error");
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
    }
}
