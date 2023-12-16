package org.vetsandshelters.back_office.animal.application.getAnimal;

import org.vetsandshelters.back_office.animal.domain.Animal;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class GetAnimalResponse {
    public Animal animal;

    public GetAnimalResponse(Animal animal) {
        this.animal = animal;
    }

    public Animal getAnimal() {
        return animal;
    }
}
