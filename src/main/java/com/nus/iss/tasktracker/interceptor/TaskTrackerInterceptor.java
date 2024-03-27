package com.nus.iss.tasktracker.interceptor;

import com.nus.iss.tasktracker.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Enumeration;

@Component
public class TaskTrackerInterceptor implements HandlerInterceptor{

    private final JWTUtil jwtUtil;
    private static final ThreadLocal<String> userNameHolder = new ThreadLocal<>();
    private static final ThreadLocal<String> userRoleHolder = new ThreadLocal<>();


    @Autowired
    public TaskTrackerInterceptor(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public static String getLoggedInUserName() {
        return userNameHolder.get();
    }
    public static String getLoggedInUserRole() {
        return userRoleHolder.get();
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
        if(!StringUtils.hasText(token)){
            System.out.println("TOKEN IS EMPTY");
            // FIXME
            //throw new Exception("No Token");
        } else{
            System.out.println("TOKEN IS "+token);
            String[] subjectRoleValues = jwtUtil.validateJWT(token);
            if((subjectRoleValues==null) || (subjectRoleValues.length!=2) ||
                    (!StringUtils.hasText(subjectRoleValues[0])) || (!StringUtils.hasText(subjectRoleValues[1]))){
                isTokenValid = false;
            } else{
                // Set a value in the thread-local variable
                userNameHolder.set(subjectRoleValues[0]);
                userRoleHolder.set(subjectRoleValues[1]);
            }
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
        userNameHolder.remove();
    }
}
