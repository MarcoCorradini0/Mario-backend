package org.tuo.leaderboard;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

import org.tuo.game.GameSession;
import org.tuo.game.GameSessionRepository;

@ApplicationScoped
public class LeaderboardService {

    @Inject
    GameSessionRepository sessionRepo;

    public List<GameSession> getTopSessions(int limit){
        return sessionRepo.findTopScores(limit);
    }
}
