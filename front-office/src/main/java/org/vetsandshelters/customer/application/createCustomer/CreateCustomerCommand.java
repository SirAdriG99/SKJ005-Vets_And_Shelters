package org.vetsandshelters.customer.application.createCustomer;

import java.util.Date;

public class CreateCustomerCommand {
    private String doc_number;
    private String name;
    private String surname;
    private String user_alias;
    private Date date_birth;
    private String email;
    private String phone1;
    private String phone2;
    private String address;
    private String password;

    public CreateCustomerCommand(String doc_number, String name, String surname, String user_alias, Date date_birth, boolean banned, String email, String phone1, String phone2, String address, String password) {
        this.doc_number = doc_number;
        this.name = name;
        this.surname = surname;
        this.user_alias = user_alias;
        this.date_birth = date_birth;
        this.email = email;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.address = address;
        this.password = password;

    }

    public String getDoc_number() {
        return doc_number;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUser_alias() {
        return user_alias;
    }

    public Date getDate_birth() {
        return date_birth;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone1() {
        return phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }
}
