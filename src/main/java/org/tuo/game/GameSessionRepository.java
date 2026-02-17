package org.tuo.game;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GameSessionRepository implements PanacheRepository<GameSession> {
    public List<GameSession> findByPlayerId(Long playerId){
        return list("player.id", playerId); 
    }
    public List<GameSession> findTopScores(int limit){
        return findAll(Sort.by("score").descending())  
                .page(0, limit)              
                .list();                     
    }
}
