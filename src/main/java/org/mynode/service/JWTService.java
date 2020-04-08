package org.mynode.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.mynode.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.security.Signature;
import java.util.Date;

@Service
public class JWTService {
    private static final String SECURITY_KEY = "LiamZhao";
    private final String ISSUER = "org.mynode";
    private final long EXPIREATION_TIME = 86400 * 1000;
    private static Logger logger = LoggerFactory.getLogger(JWTService.class);
    public String generateToken(Customer c){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECURITY_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Claims claims = Jwts.claims();
        claims.setId(String.valueOf(c.getId()));
        claims.setSubject(c.getName());
        claims.setIssuedAt(new Date(System.currentTimeMillis()));
        claims.setIssuer(ISSUER);
        claims.setExpiration(new Date(System.currentTimeMillis() + EXPIREATION_TIME));

        JwtBuilder builder = Jwts.builder().setClaims(claims).signWith(signatureAlgorithm, signingKey);
        return builder.compact();
    }

    public static Claims decodeJwtToken(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECURITY_KEY))
                .parseClaimsJws(token).getBody();

        logger.debug("Claims: " + claims.toString());
        // String username = claims.getSubject();
        // Customer c = customerService.findByName(username);
        //c.getRoles();
        return claims;
    }
}
