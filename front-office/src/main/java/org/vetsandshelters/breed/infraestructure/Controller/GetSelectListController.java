package org.vetsandshelters.breed.infraestructure.Controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.vetsandshelters.breed.application.getSelectList.GetSelectListQueryHandler;
import org.vetsandshelters.breed.application.getSelectList.GetSelectListResponse;

@Path("/breed")
public class GetSelectListController {
    @Inject
    GetSelectListQueryHandler getSelectListQueryHandler;

    @GET
    @Path("/selectList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSelectList() {
        GetSelectListResponse response = this.getSelectListQueryHandler.handle();
        return Response.ok(response.getBreeds()).build();
    }

}
