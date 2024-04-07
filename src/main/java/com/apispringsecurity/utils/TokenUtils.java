package com.apispringsecurity.utils;

import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.ParseException;

public class TokenUtils {

    public static TokenAttributes parseToken(String tokenString) throws ParseException {
        SignedJWT jwt = (SignedJWT) JWTParser.parse(tokenString);
        return new TokenAttributes(
                jwt.getJWTClaimsSet().getIssuer(),
                jwt.getJWTClaimsSet().getSubject(),
                jwt.getJWTClaimsSet().getIssueTime(),
                jwt.getJWTClaimsSet().getExpirationTime(),
                jwt.getJWTClaimsSet().getStringClaim("scope")
        );
    }

    public static class TokenAttributes {
        private final String issuer;
        private final String subject;
        private final java.util.Date issueTime;
        private final java.util.Date expirationTime;
        private final String scope;

        public TokenAttributes(String issuer, String subject, java.util.Date issueTime, java.util.Date expirationTime, String scope) {
            this.issuer = issuer;
            this.subject = subject;
            this.issueTime = issueTime;
            this.expirationTime = expirationTime;
            this.scope = scope;
        }

        // Getters para os atributos do token
        public String getIssuer() {
            return issuer;
        }

        public String getSubject() {
            return subject;
        }

        public java.util.Date getIssueTime() {
            return issueTime;
        }

        public java.util.Date getExpirationTime() {
            return expirationTime;
        }

        public String getScope() {
            return scope;
        }
    }

    public static String recoverToken(String token){
        if(token == null) return null;
        return token.replace("Bearer ", "");
    }


    public static boolean senhaValida(String usuarioSenha, String requestSenha) {
        return (new BCryptPasswordEncoder()).matches(
                usuarioSenha,
                requestSenha
        );
    }
}
