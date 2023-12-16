package org.vetsandshelters.animal.infraestructure;

import org.vetsandshelters.animal.application.getAnimal.GetAnimalQuery;
import org.vetsandshelters.animal.application.getAnimal.GetAnimalQueryHandler;
import org.vetsandshelters.animal.application.getAnimal.GetAnimalResponse;
import org.vetsandshelters.animal.domain.Animal;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/animal")
@Produces(MediaType.APPLICATION_JSON)
@Consumes({ MediaType.APPLICATION_JSON })
public class GetAnimalController {

    @Inject
    private GetAnimalQueryHandler getAnimalQueryHandler;

    @GET
    @Path("/{id}")
    public Animal getAnimal(@PathParam("id") Integer id) {

        GetAnimalQuery query = new GetAnimalQuery(id);

        GetAnimalResponse response = this.getAnimalQueryHandler.handle(query);

        return response.getAnimal().tJson();
    }
}
