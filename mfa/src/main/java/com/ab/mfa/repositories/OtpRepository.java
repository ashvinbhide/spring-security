package com.ab.mfa.repositories;

import com.ab.mfa.dao.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface OtpRepository extends JpaRepository<Otp,Integer> {

    Optional<Otp> findByUsername(String username);

    void deleteByOtp(String otp);
}
