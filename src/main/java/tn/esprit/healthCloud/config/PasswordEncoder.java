package tn.esprit.healthCloud.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
    public static  void main(String[] args){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "12345678";
        String encodedPaswword = encoder.encode(rawPassword);

        System.out.println(encodedPaswword);
    }
}
