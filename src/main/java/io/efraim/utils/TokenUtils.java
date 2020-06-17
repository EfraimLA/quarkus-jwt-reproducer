package io.efraim.utils;

import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;

import java.util.Set;

/**
 * Utilities for generating a JWT for testing
 */
public class TokenUtils {

    private static final String ISSUER = "https://quarkus.io";
    private static final String AUDIENCE = "auth";

    private TokenUtils() {
    }

    public static String generateToken(String subject, Object name, String... roles) {
        try {
            JwtClaimsBuilder claims = Jwt.claims();

            claims.issuer(ISSUER);
            claims.subject(subject);
            claims.upn(subject);
            claims.preferredUserName(String.valueOf(name));
            claims.groups(Set.of(roles));
            claims.audience(AUDIENCE);
            claims.expiresAt(currentTimeInSecs() + 600);

            return claims.sign();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return the current time in seconds since epoch
     */
    private static int currentTimeInSecs() {
        long currentTimeMS = System.currentTimeMillis();
        return (int) (currentTimeMS / 1000);
    }

}