package com.diabunity.diabunityapi.auth;

import com.diabunity.diabunityapi.exceptions.ApiError;
import com.diabunity.diabunityapi.exceptions.InvalidUserTokenException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    private final Logger logger = LogManager.getLogger(AuthenticationInterceptor.class);

    @Autowired
    private UserAuthService userAuthService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws InvalidUserTokenException {
        logger.info("[" + request.getMethod() + "]" + "[" + request.getRequestURI()  + "]");

        String authToken = request.getHeader("auth-token");
        String uid = userAuthService.getUserIDFromAuthToken(authToken);

        request.getSession().setAttribute("authorized_user", uid);
        return true;
    }


}