package org.vetsandshelters.appointment.infraestructure.Controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.vetsandshelters.appointment.application.getAppointment.GetAppointmentQuery;
import org.vetsandshelters.appointment.application.getAppointment.GetAppointmentQueryHandler;
import org.vetsandshelters.appointment.application.getAppointment.GetAppointmentResponse;

@Path("/appointment")
public class GetAppointmentController {
    @Inject
    private GetAppointmentQueryHandler getAppointmentQueryHandler;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppointment(@PathParam("id") Integer id) {
        GetAppointmentQuery query = new GetAppointmentQuery(id);
        GetAppointmentResponse response = this.getAppointmentQueryHandler.handle(query);
        return Response.ok(response.getAppointment()).build();
    }
}
