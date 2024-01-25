package org.vetsandshelters.breed.infraestructure.Controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.vetsandshelters.breed.application.filterBreed.FilterBreedQuery;
import org.vetsandshelters.breed.application.filterBreed.FilterBreedQueryHandler;
import org.vetsandshelters.breed.application.filterBreed.FilterBreedResponse;

@Path("/breed")
public class FilterBreedController {
    @Inject
    FilterBreedQueryHandler handler;

    @GET
    @Path("/filter")
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterBreed(
            @QueryParam("pageNum") Integer pageNumber,
            @QueryParam("size") Integer size,
            @QueryParam("order") String order, // Ignore by now
            @QueryParam("sort") String sort, // Ignore by now
            @QueryParam("name") String name,
            @QueryParam("dangerous") Boolean dangerous,
            @QueryParam("exotic") Boolean exotic,
            @QueryParam("activityNeed") String activityNeed,
            @QueryParam("spaceNeed") String spaceNeed,
            @QueryParam("alimentationNeed") String alimentationNeed,
            @QueryParam("timeDedicationNeed") String timeDedicationNeed
    ) {
        FilterBreedQuery query = new FilterBreedQuery(
                pageNumber,
                size,
                order,
                sort,
                name,
                dangerous,
                exotic,
                activityNeed,
                spaceNeed,
                alimentationNeed,
                timeDedicationNeed
        );
        FilterBreedResponse response = this.handler.handle(query);

        return Response.ok(response).build();
    }

}
