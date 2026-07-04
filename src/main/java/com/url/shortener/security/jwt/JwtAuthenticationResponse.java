package com.url.shortener.security.jwt;



/**
 * Response returned after successful authentication.
 */
public record JwtAuthenticationResponse(
        String token
) {
}