package org.tuo.leaderboard;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

import org.tuo.game.GameSession;
import org.tuo.dto.GameSessionDTO;
import org.tuo.game.GameSessionMapper;

@Path("/leaderboard")
@Produces(MediaType.APPLICATION_JSON)
public class LeaderboardResource {
    @Inject
    LeaderboardService leaderboardService;
    @GET
    public List<GameSessionDTO> getTopScores() {
        List<GameSession> topSessions = leaderboardService.getTopSessions(10);
        return topSessions.stream()
                          .map(GameSessionMapper::toDTO)
                          .toList();
    }
}
