package com.ab.mfa.services.impl;

import com.ab.mfa.repositories.UsernamePasswordRepository;
import com.ab.mfa.dao.SecurityUser;
import com.ab.mfa.dao.Users;
import com.ab.mfa.services.UsernamePasswordService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class usernamePasswordServiceImpl implements UsernamePasswordService {

    final
    UsernamePasswordRepository repository;

    public usernamePasswordServiceImpl(UsernamePasswordRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails getUserDetails(String username){
        Optional<Users> optional = repository.findUsersByUsername(username);
        Users user = optional.orElseThrow(() -> new BadCredentialsException("Invalid credentials"));

        return  new SecurityUser(user);
    }

}
