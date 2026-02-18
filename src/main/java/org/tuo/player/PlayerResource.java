package org.tuo.player;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.tuo.dto.ChangePasswordDTO;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/player")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PlayerResource {
    @Inject
    PlayerService playerService;
    @Inject
    JsonWebToken jwt;

    @POST
    @Path("/change-password")
    @RolesAllowed("USER")
    public Response changePassword(ChangePasswordDTO dto){
        Long playerId=Long.parseLong(jwt.getSubject());
        playerService.changePassword(playerId, dto.oldPassword, dto.newPassword);
        return Response.ok().build();
    }
}
