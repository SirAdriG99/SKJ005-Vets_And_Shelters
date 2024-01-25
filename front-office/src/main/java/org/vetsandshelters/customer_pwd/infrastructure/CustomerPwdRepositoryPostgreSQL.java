package org.vetsandshelters.customer_pwd.infrastructure;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.vetsandshelters.customer_pwd.domain.CustomerPwd;
import org.vetsandshelters.customer_pwd.domain.CustomerPwdRepository;

@Priority(5)
@ApplicationScoped
public class CustomerPwdRepositoryPostgreSQL implements CustomerPwdRepository {
    @Inject
    EntityManager em;

    @Override
    public CustomerPwd getById(int id) {
        CustomerPwd customerPwd = em.find(CustomerPwd.class, id);
        if (customerPwd == null) {
            return CustomerPwd.NOT_FOUND;
        }
        return customerPwd;
    }

    @Override
    public CustomerPwd getByCustomerId(int customer_id) {
        try {
            CustomerPwd customerPwd = em.createQuery("SELECT c FROM CustomerPwd c WHERE c.customer_id = :customer_id", CustomerPwd.class)
                    .setParameter("customer_id", customer_id)
                    .getSingleResult();
            return customerPwd;
        } catch (NoResultException e) {
            return CustomerPwd.NOT_FOUND;
        }
    }

    @Override
    @Transactional
    public int store(CustomerPwd customerPwd) {
//        TODO: encrypt password
        em.persist(customerPwd);
        return customerPwd.getId();
    }

    @Override
    @Transactional
    public int update(CustomerPwd customerPwd) {
//        TODO: encrypt password
        em.merge(customerPwd);
        return customerPwd.getId();
    }
}
