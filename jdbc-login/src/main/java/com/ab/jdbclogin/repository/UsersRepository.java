package com.ab.jdbclogin.repository;

import com.ab.jdbclogin.dao.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Integer> {

    Optional<Users> findUsersByUsername(String username);

}
