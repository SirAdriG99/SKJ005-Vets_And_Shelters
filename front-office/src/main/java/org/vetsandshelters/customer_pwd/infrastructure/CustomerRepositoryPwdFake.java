package org.vetsandshelters.front_office.customer.infraestructurre;

import org.vetsandshelters.front_office.customer.domain.Customer;
import org.vetsandshelters.front_office.customer.domain.CustomerCollection;
import org.vetsandshelters.front_office.customer.domain.CustomerCriteria;
import org.vetsandshelters.front_office.customer.domain.CustomerRepository;

import java.time.LocalDate;
// import org.vetsandshelters.back_office.animal.domain.AnimalCollection;
// import org.vetsandshelters.back_office.animal.domain.Criteria;

public class CustomerPwdRepositoryFake implements CustomerRepository {

    @Override
    public CustomerPwd getById(Integer id) {
        return new CustomerPwd(
                0, 0, "CONTRASEÑA_INSEGURA"
        );
    }

    @Override
    public CustomerPwdCollection getBy(CustomerPwdCriteria criteria) {
        return null;
    }

    // @Override
    // public AnimalCollection getBy(Criteria criteria) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'getBy'");
    // }

    @Override
    public int add(CustomerPwd customerPwd) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public int update(CustomerPwd customerPwd) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }


}
