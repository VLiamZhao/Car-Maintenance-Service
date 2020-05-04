package org.mynode.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.mynode.model.Customer;
import org.mynode.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JWTService {
    // TODO change to vm option - done
    // Further question: why cannot use @Value?
    private static String SECURITY_KEY = System.getProperty("jwt.securitykey");
    private static String ISSUER = System.getProperty("jwt.issuer");
    private final long EXPIREATION_TIME = 86400 * 1000;
    private static Logger logger = LoggerFactory.getLogger(JWTService.class);
    public String generateToken(Customer c){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECURITY_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        Claims claims = Jwts.claims();
        claims.setId(String.valueOf(c.getId()));
        claims.setSubject(c.getName());
        claims.setIssuedAt(new Date(System.currentTimeMillis()));
        claims.setIssuer(ISSUER);
        claims.setExpiration(new Date(System.currentTimeMillis() + EXPIREATION_TIME));

        List<Role> roles = c.getRoleList();
        String allowedReadResources = "";
        String allowedCreateResources = "";
        String allowedUpdateResources = "";
        String allowedDeleteResources = "";
        String allowedResource = roles.stream().map(role -> role.getAllowedResource()).collect(Collectors.joining(","));
        claims.put("allowedResource", allowedResource);
        for (Role role : roles) {
            if (role.isAllowedRead()) allowedReadResources = String.join(role.getAllowedResource(), allowedReadResources, ",");
            if (role.isAllowedCreate()) allowedCreateResources = String.join(role.getAllowedResource(), allowedCreateResources, ",");
            if (role.isAllowedUpdate()) allowedUpdateResources = String.join(role.getAllowedResource(), allowedUpdateResources, ",");
            if (role.isAllowedDelete()) allowedDeleteResources = String.join(role.getAllowedResource(), allowedDeleteResources, ",");
        }
        claims.put("allowedReadResources", allowedReadResources.replaceAll(".$", ""));
        claims.put("allowedCreateResources", allowedCreateResources.replaceAll(".$", ""));
        claims.put("allowedUpdateResources", allowedUpdateResources.replaceAll(".$", ""));
        claims.put("allowedDeleteResources", allowedDeleteResources.replaceAll(".$", ""));

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
