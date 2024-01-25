package org.vetsandshelters.breed.infraestructure.Controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.vetsandshelters.breed.application.getBreed.GetBreedQuery;
import org.vetsandshelters.breed.application.getBreed.GetBreedQueryHandler;
import org.vetsandshelters.breed.application.getBreed.GetBreedResponse;

@Path("/breed")
public class GetBreedController {
    @Inject
    GetBreedQueryHandler getBreedQueryHandler;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBreed(@PathParam("id") Integer id) {
        GetBreedQuery query = new GetBreedQuery(id);
        GetBreedResponse response = this.getBreedQueryHandler.handle(query);
        return Response.ok(response.getBreed()).build();
    }
}
