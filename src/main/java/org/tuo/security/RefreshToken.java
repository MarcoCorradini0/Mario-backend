package org.tuo.security;

import java.time.Instant;
import java.util.UUID;

import org.tuo.player.Player;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class RefreshToken {
    @Id
    public String token;
    @ManyToOne
    public Player player;
    public Instant expiresAt;

    public RefreshToken(){} //JPA

    public RefreshToken(Player player){
        this.token=UUID.randomUUID().toString();
        this.player=player;
        this.expiresAt=Instant.now().plusSeconds(7*24*3600);
    }
}
