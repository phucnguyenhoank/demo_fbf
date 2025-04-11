package com.example_fbf.demo_fbf.util;

import java.util.Random;

public class OtpGenerator {
    public static String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // Generate a 6-digit number
        return String.valueOf(otp);
    }
}
