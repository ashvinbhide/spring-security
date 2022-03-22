package com.ab.hashedpasswords.services;

import com.ab.hashedpasswords.dao.SecurityUser;
import com.ab.hashedpasswords.dao.Users;
import com.ab.hashedpasswords.repositories.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JPAUserDetailsService implements UserDetailsManager {

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

    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }
}
