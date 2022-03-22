package com.ab.mfa.security.providers;

import com.ab.mfa.security.authentications.UsernamePasswordAuthentication;
import com.ab.mfa.services.UsernamePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsernamePasswordProvider implements AuthenticationProvider {

    final
    UsernamePasswordService service;

   // final PasswordEncoder passwordEncoder;

    public UsernamePasswordProvider(UsernamePasswordService service) {
        this.service = service;
       // this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String)authentication.getCredentials();

        UserDetails userDetails = service.getUserDetails(username);
        /*if(passwordEncoder.matches(password,userDetails.getPassword()) ) {
            return new UsernamePasswordAuthentication(userDetails.getUsername(),
                    userDetails.getPassword(),userDetails.getAuthorities());
        }*/
        if(null!=userDetails) {
            return new UsernamePasswordAuthentication(userDetails.getUsername(),
                    userDetails.getPassword(),userDetails.getAuthorities());
        }
        throw new BadCredentialsException("Invalid Credentials");

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthentication.class.equals(authentication);
    }
}
