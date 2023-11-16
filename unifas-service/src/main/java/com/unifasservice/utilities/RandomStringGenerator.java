package com.unifasservice.utilities;

import java.security.SecureRandom;

public class RandomStringGenerator {
    public static String generateRandomString(int length) {
        String allowedChars = "0123456789";

        SecureRandom random = new SecureRandom();

        StringBuilder randomStringBuilder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allowedChars.length());
            char randomChar = allowedChars.charAt(randomIndex);
            randomStringBuilder.append(randomChar);
        }

        return randomStringBuilder.toString();
    }
}
