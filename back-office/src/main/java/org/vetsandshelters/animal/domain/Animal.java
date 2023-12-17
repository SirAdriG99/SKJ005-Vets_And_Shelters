package org.vetsandshelters.animal.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;

/*
 * In the domain we should only have the data that is relevant for the business logic and the UI.
 * The only methods that should be here are the getters, the constructor and the methods that
 * implement the business logic (p. e. calculate the price of a product with a discount if you receive the full price and the discount as parameters).
 * The setters should only be used if the class is to big and there are some specific use cases that only modify a few fields (p.e. a use case that only modifies 
 * one attribute from a class with 20 attributes).
 */

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
public class Animal {
    @Id
    private Integer id;
    private String name;
    private String color;
    @OneToOne
    private Sex sex;
    @OneToOne
    private Breed breed;
    @OneToOne
    private ProcedenceType procedenceType;

    @Transient
    public static final Animal NOT_FOUND = new Animal(-1, "Not found", "", Sex.NOT_FOUND, Breed.NOT_FOUND,
            ProcedenceType.NOT_FOUND);

    public Animal(
            Integer id,
            String name,
            String color,
            Sex sex,
            Breed breed,
            ProcedenceType procedenceType) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.sex = sex;
        this.breed = breed;
        this.procedenceType = procedenceType;
    }

    public Animal() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public Sex getSex() {
        return sex;
    }

    public Breed getBreed() {
        return breed;
    }

    public ProcedenceType getProcedenceType() {
        return procedenceType;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id + '\'' +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", " + sex.toString() + '\'' +
                ", " + breed.toString() + '\'' +
                ", " + procedenceType.toString() +
                '}';
    }

}
