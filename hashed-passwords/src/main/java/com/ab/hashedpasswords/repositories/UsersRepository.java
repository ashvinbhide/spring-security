package com.ab.hashedpasswords.repositories;


import com.ab.hashedpasswords.dao.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Integer> {

    Optional<Users> findUsersByUsername(String username);

}
