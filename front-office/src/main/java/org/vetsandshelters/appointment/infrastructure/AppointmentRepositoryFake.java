package org.vetsandshelters.front_office.customer.infraestructurre;

import org.vetsandshelters.front_office.customer.domain.Customer;
import org.vetsandshelters.front_office.customer.domain.CustomerCollection;
import org.vetsandshelters.front_office.customer.domain.CustomerCriteria;
import org.vetsandshelters.front_office.customer.domain.CustomerRepository;

import java.time.LocalDate;
// import org.vetsandshelters.back_office.animal.domain.AnimalCollection;
// import org.vetsandshelters.back_office.animal.domain.Criteria;

public class CustomerRepositoryFake implements CustomerRepository {

    @Override
    public Appointment getById(Integer id) {
        return new Appointment( 0, LocalDate.now(), 0);
    }

    @Override
    public AppointmentCollection getBy(AppointmentCollection criteria) {
        return null;
    }

    // @Override
    // public AnimalCollection getBy(Criteria criteria) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'getBy'");
    // }

    @Override
    public int add(Appointment appointment) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public int update(Appointment appointment) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
