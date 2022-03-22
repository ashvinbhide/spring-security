package com.ab.mfa.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface UsernamePasswordService {
    public UserDetails getUserDetails(String username);
}
