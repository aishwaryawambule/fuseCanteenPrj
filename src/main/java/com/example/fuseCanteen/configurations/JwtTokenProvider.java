//package com.example.fuseCanteen.configurations;
//
//import io.jsonwebtoken.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import com.example.fuseCanteen.Service.modelService.userDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
///**
// * Created by aishwarya on 9/8/20.
// */
//
//@Component
//public class JwtTokenProvider {
//
//    @Autowired
//    userDetails userDetails;
//
//    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
//
//    @Value("${app.jwtSecret}")
//    private String jwtSecret;
//
//    @Value("${app.jwtRefreshSecret}")
//    private String jwtRefreshSecret;
//
//    @Value("${app.jwtExpirationInMs}")
//    private int jwtExpirationInMs;
//
//    @Value("${app.jwtRefreshTokenExpirationInMs}")
//    private int jwtRefreshTokenExpirationInMs;
//
//    @Value("${app.jwtIssuer}")
//    private String jwtIssuer;
//
//    public String generateToken(Authentication authentication) {
//        Claims claims = Jwts.claims();
//        claims.put("roles",userDetails.getUsername());
//        userDetails userDetails1 = (userDetails) authentication.getPrincipal();
//        return Jwts.builder()
//                .setClaims(claims)
//                .setIssuer(jwtIssuer)
//                .setSubject(userDetails1.getUsername())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 1*1000))
//                .signWith(SignatureAlgorithm.HS512, jwtSecret)
//                .compact();
//    }
//
//    public Integer getUserIdFromJWT(String token) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(jwtSecret)
//                .parseClaimsJws(token)
//                .getBody();
//
//        return Integer.parseInt(claims.getSubject().toString());
//    }
//
//
//    public boolean validateToken(String authToken) {
//        try {
//            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
//            return true;
//        } catch (SignatureException ex) {
//            logger.error("Invalid JWT signature");
//        } catch (MalformedJwtException ex) {
//            logger.error("Invalid JWT token");
//        } catch (ExpiredJwtException ex) {
//            logger.error("Expired JWT token");
//        } catch (UnsupportedJwtException ex) {
//            logger.error("Unsupported JWT token");
//        } catch (IllegalArgumentException ex) {
//            logger.error("JWT claims string is empty.");
//        }
//        return false;
//    }
//}
