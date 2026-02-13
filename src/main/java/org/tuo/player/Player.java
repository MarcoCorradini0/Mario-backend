package org.tuo.player;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(unique = true, nullable = false, length = 50)
    public String username;
    @Column(unique = true, nullable = false, length = 100)
    public String email;
    @Column(name = "password_hash", nullable = false, length = 255)    
    public String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public Role role = Role.USER;
    @Column(nullable = false)
    public Integer bestScore=0;
    @Column(nullable = false)
    public Integer totalGames=0;
    @Column(nullable = false)
    public Integer totalPoints=0;
    @Column(nullable = false)
    public boolean isActive = true;
    @Column(nullable = false)
    public boolean isBanned = false;
    @Column(nullable = false)
    public Instant createdAt=Instant.now();
    @Column(nullable = false)
    public Instant updatedAt=Instant.now();
    
    public enum Role{
        USER,
        ADMIN
    }
}
