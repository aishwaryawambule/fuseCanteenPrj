package com.example.fuseCanteen.Service.security.jwt;

import com.example.fuseCanteen.bean.JwtUserRole;
import com.example.fuseCanteen.constant.Parameters;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * This class defines methods for coping with JWT.
 *
 */
@Service
public class JWTService {
 
    @Value(Parameters.KEY_JWT_EXPIRE_HOURS)
    private Long expireHours;

    @Value(Parameters.KEY_JWT_SECRET)
    private String plainSecret;

    private String encodedSecret;
 
    public Authentication getAuthorization(String token) {
        return getUser(this.encodedSecret, token);
    }
 
    public String getToken(JwtUserRole jwtUser) {
        return getToken(this.encodedSecret, jwtUser);
    }
    
    @PostConstruct
    protected void init() {
        this.encodedSecret = generateEncodedSecret(this.plainSecret);
    }
 
    private String generateEncodedSecret(String plainSecret) {

        if (StringUtils.isEmpty(plainSecret)) {
            throw new IllegalArgumentException("JWT secret cannot be null or empty.");
        }
        return Base64
                .getEncoder()
                .encodeToString(this.plainSecret.getBytes());
    }
 
    private Date getExpirationTime() {
    	
        Date now = new Date();
        Long expireInMilis = TimeUnit.HOURS.toMillis(expireHours);
        return new Date(expireInMilis + now.getTime());
    }
 
    private Authentication getUser(String encodedSecret, String token) {
    	
        Claims claims = Jwts.parser()
                .setSigningKey(encodedSecret)
                .parseClaimsJws(token)
                .getBody();
        
        String username = claims.getSubject();
        @SuppressWarnings("unchecked")
		List<String> roleList = (List<String>) claims.get("roles");
        
        Collection<? extends GrantedAuthority> authorities = roleList
        		.stream()
        		.map(authority -> new SimpleGrantedAuthority(authority))
        		.collect(Collectors.toList());
                
        return new UsernamePasswordAuthenticationToken(username, "", authorities);
    }
    
    private String getToken(String encodedSecret, JwtUserRole jwtUser) {
    	
        Date now = new Date();
        
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(jwtUser.getUsername())
                .claim("roles", jwtUser.getRoles())
                .setIssuedAt(now)
                .setExpiration(getExpirationTime())
                .signWith(SignatureAlgorithm.HS512, encodedSecret)
                .compact();
    }
}
