package org.tuo.game;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/game")
@Produces(MediaType.APPLICATION_JSON)
public class GameResource {
    private final GameService gameService;

    public GameResource(GameService gameService) {
        this.gameService = gameService;
    }

    @GET
    @Path("/start")
    @RolesAllowed("USER")
    public GameSession startGame(){
        return gameService.startNewSession();
    }
}
