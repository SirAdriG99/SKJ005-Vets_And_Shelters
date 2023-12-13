package org.vetsandshelters.back_office.animal.domain;

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
    public Integer id;
    public String name;
    public String description;
    public Race race;

    public Animal(Integer id, String name, String description, Race race) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.race = race;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

}
