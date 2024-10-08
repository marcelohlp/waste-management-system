package br.com.fiap.wastemanagementsystem.config.security;

import br.com.fiap.wastemanagementsystem.exception.TokenException;
import br.com.fiap.wastemanagementsystem.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${my.secret.key}")
    private String secretyWord;

    public String getToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretyWord);
            String token = JWT
                    .create()
                    .withIssuer("api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new TokenException("Failed to generate token");
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretyWord);
            return JWT.require(algorithm).withIssuer("api").build().verify(token).getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }
    private Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }


}
