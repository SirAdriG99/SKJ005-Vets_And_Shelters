package org.vetsandshelters.animal.infraestructure.Controller;

import org.vetsandshelters.animal.application.getAnimal.GetAnimalQuery;
import org.vetsandshelters.animal.application.getAnimal.GetAnimalQueryHandler;
import org.vetsandshelters.animal.application.getAnimal.GetAnimalResponse;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/animal")
public class GetAnimalController {

    @Inject
    private GetAnimalQueryHandler getAnimalQueryHandler;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAnimal(@PathParam("id") Integer id) {

        GetAnimalQuery query = new GetAnimalQuery(id);

        GetAnimalResponse response = this.getAnimalQueryHandler.handle(query);

        return Response.ok(response.getAnimal()).build();
    }
}
