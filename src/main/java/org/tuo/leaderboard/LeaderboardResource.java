package org.tuo.leaderboard;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

import org.tuo.game.GameSession;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.tuo.dto.GameSessionDTO;
import org.tuo.dto.PlayerLeaderboardResponse;
import org.tuo.game.GameSessionMapper;

import com.arjuna.ats.arjuna.common.recoveryPropertyManager;

import io.vertx.codegen.annotations.GenIgnore;

@Path("/leaderboard")
@Produces(MediaType.APPLICATION_JSON)
public class LeaderboardResource {
    @Inject
    LeaderboardService leaderboardService;
    @Inject
    JsonWebToken jwt;

    @GET
    public List<GameSessionDTO> getTopScores() {
        List<GameSession> topSessions = leaderboardService.getTopSessions(10);
        return topSessions.stream()
                          .map(GameSessionMapper::toDTO)
                          .toList();
    }

    @GET
    @Path("/me")
    @RolesAllowed("USER")
    public PlayerLeaderboardResponse getMyLeaderBoard(){
        Long playerId=Long.parseLong(jwt.getSubject());
        List<GameSession> sessions=leaderboardService.getPlayerSessions(playerId);
        int rank=leaderboardService.getPlayerRank(playerId);
        return new PlayerLeaderboardResponse(rank, sessions);
    }

    @GET
    @Path("/global")
    public List<PlayerLeaderboardResponse> getGlobalLeaderboard(){
        return leaderboardService.getGlobalLeaderboard();
    }
}
