package org.tuo.security;

import java.time.Duration;
import java.util.Set;

import org.tuo.player.Player;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JwtService {
    public String generateToken(Player player) {
        return Jwt.issuer("tuo-app")
                  .subject(player.username)
                  .groups(Set.of(player.role.name())) 
                  .expiresIn(Duration.ofHours(24))    
                  .sign();
    }
}
