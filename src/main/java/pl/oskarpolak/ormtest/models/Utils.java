package pl.oskarpolak.ormtest.models;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
    public static String hash256SHA(String stringToHash){
//        MessageDigest digest = null;
//        StringBuilder stringBuilder = new StringBuilder();
//        try {
//            digest = MessageDigest.getInstance("SHA-256");
//            byte[] hash = digest.digest(stringToHash.getBytes(StandardCharsets.UTF_8));
//
//            for (byte b : hash) {
//                stringBuilder.append(Integer.toHexString(0xff & b));
//            }
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//       return stringBuilder.toString();
        ShaPasswordEncoder encoder  = new ShaPasswordEncoder();
        return encoder.encodePassword(stringToHash, "");
    }
}
