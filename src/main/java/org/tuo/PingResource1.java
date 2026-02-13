package org.tuo;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/")
public class PingResource1 {

    @GET
    public String home() {
        return "Backend online";
    }
}

