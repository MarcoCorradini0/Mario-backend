package org.tuo.player;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PlayerService {
    @Inject
    PlayerRepository playerRepo;
    @Transactional
    public void updateStatsAfterGame(Long playerId, int score){
        Player player = playerRepo.findByIdOptional(playerId)
                          .orElseThrow(() -> new RuntimeException("Player not found"));
        player.totalGames+=1;
        player.totalPoints+=score;
        if (score>player.bestScore) {
            player.bestScore=score;
        }
    }
}
