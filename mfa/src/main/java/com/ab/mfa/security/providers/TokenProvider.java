package com.ab.mfa.security.providers;

import com.ab.mfa.security.authentications.TokenAuthentication;
import com.ab.mfa.security.managers.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class TokenProvider implements AuthenticationProvider {

    @Autowired
    TokenManager tokenManager;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String)authentication.getCredentials();
        if(tokenManager.contains(token)) {
            tokenManager.remove(token);
            return  new TokenAuthentication(authentication.getName(),authentication.getCredentials(),
                    authentication.getAuthorities());
        }

        throw new BadCredentialsException("token not valid");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }
}
