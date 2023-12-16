package org.vetsandshelters.animal.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/*
 * In the domain we should only have the data that is relevant for the business logic and the UI.
 * The only methods that should be here are the getters, the constructor and the methods that
 * implement the business logic (p. e. calculate the price of a product with a discount if you receive the full price and the discount as parameters).
 * The setters should only be used if the class is to big and there are some specific use cases that only modify a few fields (p.e. a use case that only modifies 
 * one attribute from a class with 20 attributes).
 */

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Animal {
    private Integer id;
    private String name;
    private String description;
    private Race race;

    public Animal(final Integer id, final String name, final String description, final Race race) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.race = race;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }
}
