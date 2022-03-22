package com.ab.mfa.services.impl;

import com.ab.mfa.repositories.OtpRepository;
import com.ab.mfa.dao.Otp;
import com.ab.mfa.dao.SecurityOtp;
import com.ab.mfa.services.OtpService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Optional;

@Service
public class OtpServiceImpl implements OtpService {

    final
    OtpRepository repository;

    public OtpServiceImpl(OtpRepository repository) {
        this.repository = repository;
    }

    @Override
    public SecurityOtp getOtpDetails(String username) {
        Optional<Otp> optional = repository.findByUsername(username);

        Otp otp = optional.orElseThrow(()-> new BadCredentialsException("Invalid credentials"));

        return new SecurityOtp(otp);
    }

    @Override
    public void saveOtp(Otp otp) {
        repository.save(otp);
    }

    @Override
    public String generateOtp() throws NoSuchAlgorithmException {
        return  String.valueOf(SecureRandom.getInstance("SHA1PRNG").nextInt(9999));
    }

    @Override
    @Transactional
    public void deleteOtp(String otp) {
        repository.deleteByOtp(otp);
    }
}
