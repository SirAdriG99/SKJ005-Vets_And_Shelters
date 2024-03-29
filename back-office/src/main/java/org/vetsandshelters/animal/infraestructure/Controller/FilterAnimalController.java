package org.vetsandshelters.animal.infraestructure.Controller;

import org.vetsandshelters.animal.application.filterAnimal.FilterAnimalQuery;
import org.vetsandshelters.animal.application.filterAnimal.FilterAnimalQueryHandler;
import org.vetsandshelters.animal.application.filterAnimal.FilterAnimalResponse;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/animal")
public class FilterAnimalController {

    @Inject
    private FilterAnimalQueryHandler handler;

    @GET
    @Path("/filter")
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterAnimal(
            @QueryParam("pageNum") Integer pageNumber,
            @QueryParam("size") Integer size,
            @QueryParam("order") String order, // Ignore by now
            @QueryParam("sort") String sort, // Ignore by now
            @QueryParam("name") String name,
            @QueryParam("color") String color,
            @QueryParam("sexId") Integer sexId,
            @QueryParam("breedId") Integer breedId,
            @QueryParam("procedenceTypeId") Integer procedenceTypeId,
            @QueryParam("animalStatusId") Integer animalStatusId) {
        FilterAnimalQuery query = new FilterAnimalQuery(
                pageNumber,
                size,
                order,
                sort,
                name,
                color,
                sexId,
                breedId,
                procedenceTypeId,
                animalStatusId);
        FilterAnimalResponse response = this.handler.handle(query);



        return Response.ok(response).build();
    }
}
