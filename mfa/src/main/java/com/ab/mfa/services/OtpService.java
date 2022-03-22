package com.ab.mfa.services;

import com.ab.mfa.dao.Otp;
import com.ab.mfa.dao.SecurityOtp;
import com.ab.mfa.dao.SecurityUser;

import java.security.NoSuchAlgorithmException;

public interface OtpService {

    public SecurityOtp getOtpDetails(String username);

    public void saveOtp(Otp otp);

    public String generateOtp() throws NoSuchAlgorithmException;

    public void deleteOtp(String otp);
}
