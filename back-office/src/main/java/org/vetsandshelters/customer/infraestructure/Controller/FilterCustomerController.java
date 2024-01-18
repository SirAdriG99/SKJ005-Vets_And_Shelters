package org.vetsandshelters.customer.infraestructure.Controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.vetsandshelters.customer.application.filterCustomer.FilterCustomerQuery;
import org.vetsandshelters.customer.application.filterCustomer.FilterCustomerQueryHandler;
import org.vetsandshelters.customer.application.filterCustomer.FilterCustomerResponse;

@Path("/customer")
public class FilterCustomerController {
    @Inject
    private FilterCustomerQueryHandler handler;

    @GET
    @Path("/filter")
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterCustomer(
            @QueryParam("pageNum") Integer pageNumber,
            @QueryParam("size") Integer size,
            @QueryParam("order") String order, // Ignore by now
            @QueryParam("sort") String sort, // Ignore by now
            @QueryParam("name") String name,
            @QueryParam("surname") String surname,
            @QueryParam("userAlias") String userAlias,
            @QueryParam("docNumber") String docNumber,
            @QueryParam("email") String email,
            @QueryParam("phone1") String phone1
    ) {
        FilterCustomerQuery query = new FilterCustomerQuery(
                pageNumber,
                size,
                order,
                sort,
                name,
                surname,
                userAlias,
                docNumber,
                email,
                phone1);
        FilterCustomerResponse response = this.handler.handle(query);
        return Response.ok(response).build();
    }
}
