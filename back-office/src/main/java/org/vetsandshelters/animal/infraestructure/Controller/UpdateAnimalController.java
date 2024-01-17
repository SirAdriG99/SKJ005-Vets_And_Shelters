package org.vetsandshelters.animal.infraestructure.Controller;

import org.vetsandshelters.animal.application.updateAnimal.UpdateAnimalCommand;
import org.vetsandshelters.animal.application.updateAnimal.UpdateAnimalCommandHandler;
import org.vetsandshelters.animal.application.updateAnimal.UpdateAnimalResponse;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/animal")
public class UpdateAnimalController {

    @Inject
    private UpdateAnimalCommandHandler handler;

    @POST
    @Path("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAnimal(
            @PathParam("id") Integer id,
            @QueryParam("name") String name,
            @QueryParam("color") String color,
            @QueryParam("sexId") int sexId,
            @QueryParam("breedId") int breedId,
            @QueryParam("procedenceTypeId") int procedenceTypeId) {
        UpdateAnimalCommand command = new UpdateAnimalCommand(id, name, color, sexId, breedId, procedenceTypeId);

        UpdateAnimalResponse response = this.handler.handle(command);

        return Response.ok("{\"id\":" + response.getId() + "}").build();
    }
}
