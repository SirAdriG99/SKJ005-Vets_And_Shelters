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
    public Customer getById(Integer id) {
        return new Customer(
                0, "01234567A", "Test", "Testez Testerino",
                "-_XxX-Test69420-XxX_-", LocalDate.now(),false,
                "test@test-mail.com", "+34000000000", "+234123456789",
                "Calle falsa, 1234"
        );
    }

    @Override
    public CustomerCollection getBy(CustomerCriteria criteria) {
        return null;
    }

    // @Override
    // public AnimalCollection getBy(Criteria criteria) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'getBy'");
    // }

    @Override
    public int add(Customer customer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public int update(Customer customer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    // TODO: The function.
    private String getPasswordBy(final int customerId) {
        return null;
    }

    // TODO: Check if it's correct.
    private boolean checkPassword(final int customerId,  final String writtenPassword){
        String dbPassword = getPasswordBy(customerId);
        // TODO: if it's necessary to do something to the password received of the DB, do it here.
        //  if not, change dbPassword to "final".
        return (dbPassword != null) && (dbPassword.equals(writtenPassword));
    }

}
