package org.tuo.player;

import java.util.Optional;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlayerRepository implements PanacheRepository<Player> {
    public Optional<Player> findByUsername(String username){
        return find("username", username).firstResultOptional();
    }
    public Optional<Player> findByEmail(String email){
        return find("email", email).firstResultOptional();
    }
}
