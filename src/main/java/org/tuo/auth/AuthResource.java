package org.tuo.auth;

import org.tuo.dto.RegisterRequestDTO;
import org.tuo.player.Player;

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {
    private final AuthService authService;

    public AuthResource(AuthService authService){
        this.authService=authService;
    }

    @POST
    @Path("/register")
    public Response register(@Valid RegisterRequestDTO dto){
        try{
            Player player = authService.register(dto);

            return Response.status(Response.Status.CREATED)
                .entity(new PlayerResponse(player.id, player.username, player.email))
                .build();
        } catch(IllegalArgumentException e){
            return Response.status(Response.Status.CONFLICT)
                .entity(new ErrorResponse(e.getMessage()))
                .build();
        } catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorResponse("Internal error"))
                .build();
        }
    }

    @POST
    @Path("/login")
    public Response login(@Valid AuthRequestDTO dto){
        AuthResponseDTO response = authService.login(dto);
        return Response.ok(response).build();
    }

    //DTO risposta
    public static class PlayerResponse{
        public final Long id;
        public final String username;
        public final String email;

        public PlayerResponse(Long id, String username, String email){
            this.id=id;
            this.username=username;
            this.email=email;
        }
    }
    //DTO errori
    public static class ErrorResponse {
        public final String error;
        public ErrorResponse(String error){
            this.error=error;
        }
    }
}
