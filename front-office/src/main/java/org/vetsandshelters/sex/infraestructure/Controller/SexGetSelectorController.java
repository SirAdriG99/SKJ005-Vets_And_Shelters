package org.vetsandshelters.sex.infraestructure.Controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.vetsandshelters.sex.application.getSelector.GetSelectorQueryHandler;
import org.vetsandshelters.sex.application.getSelector.GetSelectorResponse;

@Path("/sex")
public class SexGetSelectorController {

    @Inject
    private GetSelectorQueryHandler handler;

    @GET
    @Path("/select")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSelector() {
        GetSelectorResponse response = this.handler.handle();

        return Response.ok(response.getSexSelector()).build();
    }
}
