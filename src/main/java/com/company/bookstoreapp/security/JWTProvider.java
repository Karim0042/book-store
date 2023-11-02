package com.company.bookstoreapp.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.company.bookstoreapp.constants.SecurityConstants;
import com.company.bookstoreapp.model.jwt.JwtToken;
import com.company.bookstoreapp.util.DateHelper;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class JWTProvider {

    private final JWTVerifier jwtVerifier;
    private final SecurityConstants securityConstants;
    private final String SECRET_KEY= "seceretseceretseceretseceretseceretseceretseceretseceretseceretseceretseceret";
    public JWTProvider(SecurityConstants securityConstants) {
        this.securityConstants = securityConstants;
        this.jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .withSubject("Course Management")
                .withIssuer("Course-Management-System")
                .build();
    }

    public JwtToken getJWTToken(String userId) {
        Date createDate = DateHelper.now();
        Date expirationDate = getExpirationDate();
        String jwtToken = JWT
                .create()
                .withSubject("Course Management")
                .withExpiresAt(expirationDate)
                .withIssuedAt(createDate)
                .withClaim("userId", userId)
                .withIssuer("Course-Management-System")
                .sign(Algorithm.HMAC256(SECRET_KEY));

        return JwtToken.builder()
                .token(jwtToken)
                .createDate(createDate.getTime())
                .expirationDate(expirationDate.getTime())
                .build();
    }

    public String extractUserId(String token) {
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT.getClaim("userId").asString();
    }



    private Date getExpirationDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 3);
        return calendar.getTime();
    }

}
