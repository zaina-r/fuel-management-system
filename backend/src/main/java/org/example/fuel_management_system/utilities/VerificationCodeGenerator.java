package org.example.fuel_management_system.utilities;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class VerificationCodeGenerator {
    private static final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final int length = 6;

    public String generateVerificationCode(){
        List<Character> availableCharacters = new ArrayList<>();
        for (char c : characters.toCharArray()) {
            availableCharacters.add(c);
        }

        SecureRandom randomValues = new SecureRandom();
        Collections.shuffle(availableCharacters, randomValues);

        StringBuilder verificationCode = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            verificationCode.append(availableCharacters.get(i));
        }

        System.out.println(verificationCode.toString());

        return verificationCode.toString();
    }
}
