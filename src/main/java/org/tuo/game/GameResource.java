package org.tuo.game;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.tuo.dto.EndGameRequestDTO;
import org.tuo.dto.GameSessionDTO;
import org.tuo.service.GameSessionService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/game")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GameResource {
    @Inject
    GameSessionService gameSessionService;
    @Inject
    JsonWebToken jwt;

    @GET
    @Path("/start")
    @RolesAllowed("USER")
    public GameSessionDTO startGame() {
        String username = jwt.getSubject();
        GameSession session = gameSessionService.startNewSession(username);
        return GameSessionMapper.toDTO(session);
    }

    @POST
    @Path("/end/{id}")
    @RolesAllowed("USER")
    public GameSessionDTO endGame(@PathParam("id") Long id, EndGameRequestDTO request) {
        GameSession session = gameSessionService.endSession(
                id,
                request.score,
                request.levelReached,
                request.durationSeconds);
        return GameSessionMapper.toDTO(session);
    }
}
