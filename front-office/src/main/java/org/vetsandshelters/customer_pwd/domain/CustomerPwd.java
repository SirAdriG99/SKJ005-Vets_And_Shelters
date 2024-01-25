package org.vetsandshelters.customer_pwd.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.time.LocalDate;

/*
 * In the domain we should only have the data that is relevant for the business logic and the UI.
 * The only methods that should be here are the getters, the constructor and the methods that
 * implement the business logic (p. e. calculate the price of a product with a discount if you receive the full price and the discount as parameters).
 * The setters should only be used if the class is to big and there are some specific use cases that only modify a few fields (p.e. a use case that only modifies 
 * one attribute from a class with 20 attributes).
 */

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
@Table(name = "customer_pwd")
public class CustomerPwd {
    @Id
    private Integer id;
    private int customer_id;
    private String pwd;
    public CustomerPwd(final Integer id, final int customer_id, final String pwd){
        this.id = id;
        this.customer_id = customer_id;
        this.pwd = pwd;
    }

    public CustomerPwd() {
        super();
    }

    @Transient
    public static final CustomerPwd NOT_FOUND = new CustomerPwd(-1, -1, "Not found");

    public Integer getId() {
        return id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public String getPwd() {
        return pwd;
    }
}

