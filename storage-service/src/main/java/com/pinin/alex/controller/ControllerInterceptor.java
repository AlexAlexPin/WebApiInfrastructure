package com.pinin.alex.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
class ControllerInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) {
        handleNoContent(response);
    }

    private void handleNoContent(HttpServletResponse response) {
        if (response.getStatus() == HttpServletResponse.SC_OK && response.getContentType() == null) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }
}
