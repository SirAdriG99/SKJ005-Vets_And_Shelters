package org.vetsandshelters.animal.infraestructure.Controller;

import jakarta.ws.rs.*;
import org.vetsandshelters.animal.application.updateAnimal.UpdateAnimalCommand;
import org.vetsandshelters.animal.application.updateAnimal.UpdateAnimalCommandHandler;
import org.vetsandshelters.animal.application.updateAnimal.UpdateAnimalResponse;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/animal")
public class UpdateAnimalController {

    @Inject
    private UpdateAnimalCommandHandler handler;

    @PUT
    @Path("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAnimal(
            @PathParam("id") Integer id,
            @QueryParam("name") String name,
            @QueryParam("color") String color,
            @QueryParam("sexId") Integer sexId,
            @QueryParam("breedId") Integer breedId,
            @QueryParam("procedenceTypeId") Integer procedenceTypeId,
            @QueryParam("animalStatusId") Integer animalStatusId) {
        UpdateAnimalCommand command = new UpdateAnimalCommand(id, name, color, sexId, breedId, procedenceTypeId,
                animalStatusId);

        UpdateAnimalResponse response = this.handler.handle(command);

        return Response.ok("{\"id\":" + response.getId() + "}").build();
    }
}
