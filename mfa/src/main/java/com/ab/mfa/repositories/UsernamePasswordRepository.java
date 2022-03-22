package com.ab.mfa.repositories;

import com.ab.mfa.dao.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UsernamePasswordRepository extends JpaRepository<Users,Integer> {
    Optional<Users>  findUsersByUsername(String username);
}
