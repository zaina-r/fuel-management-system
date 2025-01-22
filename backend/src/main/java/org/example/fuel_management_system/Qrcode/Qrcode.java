package org.example.fuel_management_system.Qrcode;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public  class Qrcode {
    public static  String generateQrCode() {

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            int randomIndex = random.nextInt(characters.length());
            sb.append(characters.charAt(randomIndex));
        }
        return sb.toString();
    }
}
