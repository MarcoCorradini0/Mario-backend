package org.tuo.leaderboard;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.tuo.dto.PlayerLeaderboardResponse;
import org.tuo.game.GameSession;
import org.tuo.game.GameSessionRepository;

@ApplicationScoped
public class LeaderboardService {
    @Inject
    GameSessionRepository sessionRepo;

    public List<GameSession> getTopSessions(int limit){
        return sessionRepo.findTopScores(limit);
    }
    public int getPlayerRank(Long playerId){
        List<GameSession> all=sessionRepo.findTopScores(1000);
        int rank=1;
        for (GameSession gs:all){
            if (gs.player.id.equals(playerId)) {
                return rank;
            }
            rank++;
        }
        return rank;
    }
    public List<GameSession> getPlayerSessions(Long playerId) {
        return sessionRepo.findByPlayerId(playerId);
    }
    public List<PlayerLeaderboardResponse> getGlobalLeaderboard() {
        List<GameSession> allSessions=sessionRepo.findTopScores(100);
        // Convertiamo in PlayerLeaderboardResponse raggruppando per giocatore
        Map<Long, List<GameSession>> grouped=allSessions.stream()
                .collect(Collectors.groupingBy(gs->gs.player.id));
        List<PlayerLeaderboardResponse> leaderboard=new ArrayList<>();
        int rank=1;
        for (Map.Entry<Long, List<GameSession>> entry:grouped.entrySet()){
            leaderboard.add(new PlayerLeaderboardResponse(rank, entry.getValue()));
            rank++;
        }
        return leaderboard;
    }
}
