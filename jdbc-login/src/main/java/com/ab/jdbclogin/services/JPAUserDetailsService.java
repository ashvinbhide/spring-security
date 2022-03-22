package com.ab.jdbclogin.services;

import com.ab.jdbclogin.dao.SecurityUser;
import com.ab.jdbclogin.dao.Users;
import com.ab.jdbclogin.repository.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JPAUserDetailsService implements UserDetailsService {

    private final UsersRepository userJpaRepository;

    public JPAUserDetailsService(UsersRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = userJpaRepository.findUsersByUsername(username);
        Users u  = user.orElseThrow(()->new UsernameNotFoundException("user not present!"));
        return new SecurityUser(u);
    }
}
