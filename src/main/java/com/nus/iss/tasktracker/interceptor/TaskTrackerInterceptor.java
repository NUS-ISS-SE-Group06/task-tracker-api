package com.nus.iss.tasktracker.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Enumeration;
import java.util.List;

public class TaskTrackerInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = null;
        // This method is called before the controller method is invoked.
        // You can perform pre-processing here.
        System.out.println("Pre-handle method is called - for URL: "+ request.getRequestURI()+"; Method: "+request.getMethod());
        for (Enumeration en = request.getHeaderNames(); en.hasMoreElements();) {
            String key = (String) en.nextElement();
            String val = request.getHeader(key);
            System.out.println("header: " + key + "=" + val);
        }
        if(request.getHeader("Authorization")!=null){
            token = request.getHeader("Authorization");
            System.out.println("Authorization header value: " + token);
            token = token.replaceAll("Bearer ", "");
        }

        System.out.println("Token Value: "+token);

        // FIXME
        // DO TOKEN VALIDATION
        // THROW ERROR IF THE TOKEN IS EMPTY OR THE VALIDATION FAILS
//        if(token == null){
//            throw new Exception("No Token");
//        }
        return true; // Return true to continue with the execution chain, or false to stop it.
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // This method is called after the controller method is invoked, but before the view is rendered.
        // You can perform post-processing here.
        System.out.println("Post-handle method is called; No custom implementation available");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        // This method is called after the view is rendered.
        // You can perform cleanup activities here.
        System.out.println("After-completion method is called; No custom implementation available");
    }
}
