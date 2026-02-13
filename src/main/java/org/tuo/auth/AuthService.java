package org.tuo.auth;

import java.time.Instant;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.tuo.dto.RegisterRequestDTO;
import org.tuo.player.Player;
import org.tuo.player.PlayerRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;

@ApplicationScoped
public class AuthService {
    private final PlayerRepository playerRepository;

    public AuthService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Transactional
    public Player register(RegisterRequestDTO dto)throws IllegalArgumentException{
        //Controllo username
        Optional<Player> byUsername = playerRepository.findByUsername(dto.username);
        if (byUsername.isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        //Controllo email
        Optional<Player> byEmail = playerRepository.findByEmail(dto.email);
        if (byEmail.isPresent()) {
            throw new IllegalArgumentException("Email already exists");            
        }
        //Hash password
        String hashed = BCrypt.hashpw(dto.password, BCrypt.gensalt(12));
        //Creo player
        Player player = new Player();
        player.username=dto.username;
        player.email=dto.email;
        player.passwordHash=hashed;
        player.role=player.Role.USER;
        player.createdAt=Instant.now();
        player.updatedAt=Instant.now();
        //Salva a db
        playerRepository.persist(player);
        return player;
    }
}
