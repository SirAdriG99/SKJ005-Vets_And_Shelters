package org.vetsandshelters.back_office.animal;

import org.vetsandshelters.back_office.animal.application.getAnimal.GetAnimalQueryHandler;
import org.vetsandshelters.back_office.animal.application.getAnimal.GetAnimalResponse;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;

@Path("/animal")
@Produces(MediaType.APPLICATION_JSON)
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APLICATION_XML })
public class GetAnimalController {
    private GetAnimalQueryHandler getAnimalQueryHandler;

    public GetAnimalController(GetAnimalQueryHandler getAnimalQueryHandler) {
        this.getAnimalQueryHandler = getAnimalQueryHandler;
    }

    @GET
    @Path("/{id}")
    public Animal getAnimal(@PathParam("id") Integer id) {

        GetAnimalQuery query = new GetAnimalQuery(id);

        GetAnimalResponse response = this.getAnimalQueryHandler.handle(query);

        return response.getAnimal();
    }
}
