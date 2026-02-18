package org.tuo.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import org.hibernate.annotations.NotFound;

import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.NotAllowedException;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {

    public static class ErrorResponse {
        public String message;
        public ErrorResponse(String message) {
            this.message = message;
        }
    }

    @Override
    public Response toResponse(Throwable exception) {
        if (exception instanceof IllegalArgumentException) {
            return Response.status(Response.Status.BAD_REQUEST)  // 400
                    .entity(new ErrorResponse(exception.getMessage()))
                    .build();
        } else if (exception instanceof NotAuthorizedException) {
            return Response.status(Response.Status.UNAUTHORIZED) // 401
                    .entity(new ErrorResponse(exception.getMessage()))
                    .build();
        } else if (exception instanceof ForbiddenException) {
            return Response.status(Response.Status.FORBIDDEN)    // 403
                    .entity(new ErrorResponse(exception.getMessage()))
                    .build();
        } else if (exception instanceof NotFound) {
            return Response.status(Response.Status.NOT_FOUND)    // 404
                    .entity(new ErrorResponse(exception.getMessage()))
                    .build();
        } else if (exception instanceof NotAllowedException) {   // 405
            return Response.status(Response.Status.METHOD_NOT_ALLOWED)
                    .entity(new ErrorResponse(exception.getMessage()))
                    .build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR) // 500
                    .entity(new ErrorResponse("Internal server error: " + exception.getMessage()))
                    .build();
        }
    }
}
