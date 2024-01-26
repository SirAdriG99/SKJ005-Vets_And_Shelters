package org.vetsandshelters.appointment.infraestructure.Controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.vetsandshelters.appointment.application.storeAppointment.StoreAppointmentComand;
import org.vetsandshelters.appointment.application.storeAppointment.StoreAppointmentComandHandler;
import org.vetsandshelters.appointment.application.storeAppointment.StoreAppointmentResponse;

@Path("/appointment")
public class StoreAppointmentController {
    @Inject
    StoreAppointmentComandHandler storeAppointmentCommandHandler;

    @POST
    @Path("/store")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response storeAppointment(StoreAppointmentComand command) {
        StoreAppointmentResponse response = this.storeAppointmentCommandHandler.handle(command);
        return Response.ok("{\"id\":" + response.getId() + "}").build();
    }

}
