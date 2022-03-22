package com.ab.mfa.security.providers;

import com.ab.mfa.dao.SecurityOtp;
import com.ab.mfa.security.authentications.OtpAuthentication;
import com.ab.mfa.security.authentications.UsernamePasswordAuthentication;
import com.ab.mfa.services.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class OtpProvider implements AuthenticationProvider {

    final
    OtpService service;

   // final PasswordEncoder passwordEncoder;

    public OtpProvider(OtpService service) {
        this.service = service;
        //this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String otp = (String)authentication.getCredentials();

        UserDetails otpDetails = service.getOtpDetails(username);

       /* if(passwordEncoder.matches(otp, otpDetails.getPassword())) {
            return new UsernamePasswordAuthentication(otpDetails.getUsername(),
                    otpDetails.getPassword(),otpDetails.getAuthorities());
        }*/

        if(null != otpDetails){
            return new UsernamePasswordAuthentication(otpDetails.getUsername(),
                    otpDetails.getPassword(),otpDetails.getAuthorities());
        }
        throw new BadCredentialsException("Invalid otp credentials");

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OtpAuthentication.class.equals(authentication);
    }
}
