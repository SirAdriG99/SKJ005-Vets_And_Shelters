package org.vetsandshelters.customer.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    private Integer id;
    private String name;
    private String surname;
    @Column(name = "user_alias")
    private String userAlias;
    @Column(name = "doc_number")
    private String docNumber;
    private String email;
    private String phone1;

    public Customer(
            Integer id,
            String name,
            String surname,
            String userAlias,
            String docNumber,
            String email,
            String phone1) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.userAlias = userAlias;
        this.docNumber = docNumber;
        this.email = email;
        this.phone1 = phone1;
    }

    public Customer() {
        super();
    }

    @Transient
    public static final Customer NOT_FOUND = new Customer(-1, "", "", "", "", "", "");

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUserAlias() {
        return userAlias;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone1() {
        return phone1;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", userAlias='" + userAlias + '\'' +
                ", docNumber='" + docNumber + '\'' +
                ", email='" + email + '\'' +
                ", phone1='" + phone1 + '\'' +
                '}';
    }

}
