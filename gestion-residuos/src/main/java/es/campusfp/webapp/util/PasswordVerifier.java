package es.campusfp.webapp.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Utilidad para verificar contraseñas BCrypt
 */
public class PasswordVerifier {
    
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        String password = "password123";
        String hash = "$2a$10$zLOu2edV9R5HURZUJRp4iOW9nc.KFfLK8awjwcrjNUPSChuHTDVSS";
        
        boolean matches = encoder.matches(password, hash);
        
        System.out.println("Password: " + password);
        System.out.println("Hash: " + hash);
        System.out.println("¿Coincide?: " + matches);
    }
}
