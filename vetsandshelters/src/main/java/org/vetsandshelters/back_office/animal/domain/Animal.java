package org.vetsandshelters.back_office.animal.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

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
