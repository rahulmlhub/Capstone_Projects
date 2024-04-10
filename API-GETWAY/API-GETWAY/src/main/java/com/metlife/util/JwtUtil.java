//package com.metlife.util;
//
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//
//@Component
//public class JwtUtil {
//
//    //    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
//    private final static String SECRET = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";
//
//
//    public void validateToken(final String token) {
//        try {
//            Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
//        } catch (JwtException e) {
//            throw new RuntimeException("Invalid token: " + e.getMessage());
//        }
//    }
//
//
//    private Key getSignKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//}
