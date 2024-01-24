package org.vetsandshelters.appointment.infraestructure;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.vetsandshelters.appointment.domain.Appointment;
import org.vetsandshelters.appointment.domain.AppointmentCollection;
import org.vetsandshelters.appointment.domain.AppointmentCriteria;
import org.vetsandshelters.appointment.domain.AppointmentRepository;
import org.vetsandshelters.shared.DomainUtils.Criteria.Filter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Priority(5)
@ApplicationScoped
public class AppointmentRepositoryPostgreSQL implements AppointmentRepository {

    @Inject
    EntityManager em;

    @Override
    public Appointment getById(int id) {
        Appointment appointment = em.find(Appointment.class, id);
        if (appointment == null) {
            return Appointment.NOT_FOUND;
        }
        return appointment;
    }

    @Override
    public AppointmentCollection getBy(AppointmentCriteria criteria) {
        return null;
    }

    private List<Appointment> getAppointmentListPaginated(AppointmentCriteria criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Appointment> cq = cb.createQuery(Appointment.class);
        Root<Appointment> appointmentRoot = cq.from(Appointment.class);

        Map<String, Predicate> predicates = new HashMap<>();
        for (Filter filter : criteria.getFilterCollection().getFilters()) {
            switch (filter.getOperator()) {
                case EQUALS:
                    predicates.put(filter.getField(), cb.equal(appointmentRoot.get(filter.getField()), filter.getValue()));
                    break;
                case GREATER_THAN:
                    predicates.put(filter.getField(),
                            cb.greaterThan(appointmentRoot.get(filter.getField()), (Comparable) filter.getValue()));
                    break;
                case GREATER_THAN_OR_EQUAL_TO:
                    predicates.put(filter.getField(),
                            cb.greaterThanOrEqualTo(appointmentRoot.get(filter.getField()), (Comparable) filter.getValue()));
                    break;
                case LESS_THAN:
                    predicates.put(filter.getField(),
                            cb.lessThan(appointmentRoot.get(filter.getField()), (Comparable) filter.getValue()));
                    break;
                case LESS_THAN_OR_EQUAL_TO:
                    predicates.put(filter.getField(),
                            cb.lessThanOrEqualTo(appointmentRoot.get(filter.getField()), (Comparable) filter.getValue()));
                    break;
                case NOT_EQUALS:
                    predicates.put(filter.getField(), cb.notEqual(appointmentRoot.get(filter.getField()), filter.getValue()));
                    break;
                case LIKE, CONTAINS:
                    predicates.put(filter.getField(), cb.like(cb.upper(appointmentRoot.get(filter.getField())),
                            "%" + filter.getValue().toString().toUpperCase() + "%"));
                    break;
                case IN:
                    predicates.put(filter.getField(), appointmentRoot.get(filter.getField()).in(filter.getValue()));
                    break;
                case NOT_IN:
                    predicates.put(filter.getField(), cb.not(appointmentRoot.get(filter.getField()).in(filter.getValue())));
                    break;
                default:
                    break;
            }
        }

        cq.where(predicates.values().toArray(new Predicate[0]));

        TypedQuery<Appointment> query = em.createQuery(cq);
        if (criteria.getPagination() != null) {
            query.setFirstResult((criteria.getPagination().getPageNumber() - 1) * criteria.getPagination().getSize());
            query.setMaxResults(criteria.getPagination().getSize());
        }

        List<Appointment> appointmentList = query.getResultList();
        em.clear();
        return appointmentList;
    }
}
