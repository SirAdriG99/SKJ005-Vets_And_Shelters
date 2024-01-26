package org.vetsandshelters.customer.infraestructure.Controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.vetsandshelters.customer.application.createCustomer.CreateCustomerCommand;
import org.vetsandshelters.customer.application.createCustomer.CreateCustomerCommandHandler;
import org.vetsandshelters.customer.application.createCustomer.CreateCustomerResponse;

@Path("/customer")
public class CreateCustomerController {
    @Inject
    CreateCustomerCommandHandler createCustomerCommandHandler;

    @POST
    @Path("/store")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response storeCustomer(CreateCustomerCommand body) {
        CreateCustomerResponse response= this.createCustomerCommandHandler.handle(body);
        return Response.ok("{\"id\":" + response.getId() + "}").build();
    }


}
