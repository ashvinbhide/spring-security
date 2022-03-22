package com.ab.mfa.security.filters;

import com.ab.mfa.dao.Otp;
import com.ab.mfa.security.authentications.OtpAuthentication;
import com.ab.mfa.security.authentications.UsernamePasswordAuthentication;
import com.ab.mfa.security.managers.TokenManager;
import com.ab.mfa.services.EmailService;
import com.ab.mfa.services.OtpService;
import com.ab.mfa.services.UsernamePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class UsernamePasswordFilter extends OncePerRequestFilter {

    @Autowired
    AuthenticationManager manager;

    @Autowired
    TokenManager tokenManager;

    @Autowired
    OtpService service;

    @Autowired
    EmailService emailService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String username = request.getHeader("username");
        String password = request.getHeader("password");
        String otp = request.getHeader("otp");

       if(null == otp) {
           Authentication authentication = new UsernamePasswordAuthentication(username, password);
           manager.authenticate(authentication);

           try {
               String randomOtp = service.generateOtp();
               Otp saveOtp = new Otp(username, randomOtp);
               service.saveOtp(saveOtp);
               emailService.sendSimpleMessage("bhide.ashvin@mailhog.com","ashvinbhide@mailhog.com",randomOtp);
           } catch (NoSuchAlgorithmException e) {
               e.printStackTrace();
           }
       }
       else {
           Authentication authentication = new OtpAuthentication(username,otp);
           manager.authenticate(authentication);
            service.deleteOtp(otp);
           var token = UUID.randomUUID().toString();
           tokenManager.add(token);
           response.setHeader("token", token);
       }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login");
    }
}
