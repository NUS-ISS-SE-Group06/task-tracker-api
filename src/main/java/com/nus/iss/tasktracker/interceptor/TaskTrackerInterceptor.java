package com.nus.iss.tasktracker.interceptor;

import com.nus.iss.tasktracker.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class TaskTrackerInterceptor implements HandlerInterceptor{

    private final JWTUtil jwtUtil;

    @Autowired
    public TaskTrackerInterceptor(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        boolean isTokenValid = true;
        String token = null;
        // This method is called before the controller method is invoked.
        // You can perform pre-processing here.
        System.out.println("Pre-handle method is called - for URL: "+ request.getRequestURI()+"; Method: "+request.getMethod());

        if(request.getHeader("Authorization")!=null){
            token = request.getHeader("Authorization");
            System.out.println("Authorization header value: " + token);
            token = token.replaceAll("Bearer ", "");
        }

        // DO TOKEN VALIDATION
        // THROW ERROR IF THE TOKEN IS EMPTY OR THE VALIDATION FAILS
        if( token == null){
            System.out.println("TOKEN IS EMPTY");
            // FIXME
            //throw new Exception("No Token");
        } else{
            System.out.println("TOKEN IS "+token);
            isTokenValid = jwtUtil.validateJWT(token);
        }

        System.out.println("Token valid: "+isTokenValid);

        if(!isTokenValid){
            throw new RuntimeException("Auth Token Validation Failed");
        }

        return true; // Return true to continue with the execution chain, or false to stop it.
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // This method is called after the controller method is invoked, but before the view is rendered.
        // You can perform post-processing here.
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        // This method is called after the view is rendered.
        // You can perform cleanup activities here.
    }
}
