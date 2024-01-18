package org.vetsandshelters.animal.infraestructure.Controller;

import org.vetsandshelters.animal.application.storeAnimal.StoreAnimalCommand;
import org.vetsandshelters.animal.application.storeAnimal.StoreAnimalCommandHandler;
import org.vetsandshelters.animal.application.storeAnimal.StoreAnimalResponse;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/animal")
public class StoreAnimalController {

    @Inject
    private StoreAnimalCommandHandler handler;

    @POST
    @Path("/store")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response storeAnimal(
            StoreAnimalCommand command) {
        // StoreAnimalCommand command = new StoreAnimalCommand(name, color, sexId,
        // breedId, procedenceTypeId,
        // animalStatusId);
        // System.out.println("Generated command: " + command.getName());
        StoreAnimalResponse response = this.handler.handle(command);

        return Response.ok("{\"id\":" + response.getId() + "}").build();
    }
}
