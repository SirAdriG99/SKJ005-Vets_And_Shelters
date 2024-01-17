package org.vetsandshelters.animalStatus.infraestructure.Controller;

import org.vetsandshelters.animalStatus.application.getSelector.GetSelectorQueryHandler;
import org.vetsandshelters.animalStatus.application.getSelector.GetSelectorResponse;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/animalStatus")
public class AnimalStatusGetSelectorController {

    @Inject
    private GetSelectorQueryHandler handler;

    @GET
    @Path("/select")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSelector() {
        GetSelectorResponse response = this.handler.handle();

        return Response.ok(response.getSelector()).build();
    }
}
