package org.vetsandshelters.front_office.customer.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.vetsandshelters.front_office.customer.infraestructurre.Session;

import java.time.LocalDate;

/*
 * In the domain we should only have the data that is relevant for the business logic and the UI.
 * The only methods that should be here are the getters, the constructor and the methods that
 * implement the business logic (p. e. calculate the price of a product with a discount if you receive the full price and the discount as parameters).
 * The setters should only be used if the class is to big and there are some specific use cases that only modify a few fields (p.e. a use case that only modifies 
 * one attribute from a class with 20 attributes).
 */

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Customer {
    private int id;
    private String doc_number;
    private String name;
    private String surname;
    private String user_alias;
    private LocalDate date_birth;
    private boolean banned;
    private String email;
    private String phone1;
    private String phone2;
    private String address;
    // private String password; // It make no sense to save it after the check.

    private Session session;

    public Customer(final int id, final String doc_number, final String name,
                    final String surname, final String user_alias, final LocalDate date_birth, final boolean banned,
                    final String email, final String phone1, final String phone2, final String address)
    {
        this.id = id;
        this.doc_number = doc_number;
        this.name = name;
        this.surname = surname;
        this.user_alias = user_alias;
        this.date_birth = date_birth;
        this.banned = banned;
        this.email = email;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.address = address;
        this.session = new Session();
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getDoc_number() {
        return doc_number;
    }

    public void setDoc_number(final String doc_number) {
        this.doc_number = doc_number;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public String getUser_alias() {
        return user_alias;
    }

    public void setUser_alias(final String user_alias) {
        this.user_alias = user_alias;
    }

    public LocalDate getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(final LocalDate date_birth) {
        this.date_birth = date_birth;
    }

    public boolean getBanned() {
        return banned;
    }

    public void setBanned(final boolean banned) {
        this.banned = banned;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(final String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(final String phone2) {
        this.phone2 = phone2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public boolean isBanned() {
        return banned;
    }

    public Session getSession() {
        return session;
    }

    public void updateSessionLastAction(){
        this.session.setDateTimeLastAction();
    }

    public boolean isSessionActive(){
        return this.session.isSessionActive();
    }
}

