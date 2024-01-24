package org.vetsandshelters.appointment.application.filterAppointment;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.vetsandshelters.appointment.domain.AppointmentCollection;
import org.vetsandshelters.appointment.domain.AppointmentCriteria;
import org.vetsandshelters.appointment.domain.AppointmentRepository;
import org.vetsandshelters.shared.DomainUtils.Criteria.Filter;
import org.vetsandshelters.shared.DomainUtils.Criteria.FilterCollection;
import org.vetsandshelters.shared.DomainUtils.Criteria.FilterOperator;
import org.vetsandshelters.shared.DomainUtils.Pagination.Pagination;

@ApplicationScoped
public class FilterAppointmentQueryHandler {
    @Inject
    private AppointmentRepository repository;

    public FilterAppointmentResponse handle(FilterAppointmentQuery query) {
        AppointmentCriteria criteria = this.setCriteria(query);

        AppointmentCollection appointmentCollection = this.repository.getBy(criteria);

        return new FilterAppointmentResponse(appointmentCollection.getCollection(), appointmentCollection.getTotalElements());
    }

    private AppointmentCriteria setCriteria(FilterAppointmentQuery query) {
        /**
         * TODO.todo: Sort and order. Not for MVP
         */
        /**
         * If at least exists the pagination size,
         * by default generate
         */
        Pagination pagination = query.getSize() != null ? new Pagination(
                query.getSize(),
                query.getPageNumber() != null ? query.getPageNumber() : 0, null // Ignore sort by now
        ) : null;

        FilterCollection filters = new FilterCollection();
        if (query.getAnimalId() != null) {
            Filter nameFilter = new Filter("animalId", FilterOperator.EQUALS, query.getAnimalId());
            filters.add(nameFilter);
        }
        if (query.getCustomerId() != null) {
            Filter colorFilter = new Filter("customerId", FilterOperator.EQUALS, query.getCustomerId());
            filters.add(colorFilter);
        }
        if (query.getAppointmentDate() != null) {
            Filter sexFilter = new Filter("appointmentDate", FilterOperator.EQUALS, query.getAppointmentDate());
            filters.add(sexFilter);
        }

        return new AppointmentCriteria(filters, pagination);
    }
}
