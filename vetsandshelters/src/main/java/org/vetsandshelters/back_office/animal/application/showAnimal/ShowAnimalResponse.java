package org.vetsandshelters.back_office.animal.application.showAnimal;

import org.vetsandshelters.back_office.animal.domain.Animal;

public class ShowAnimalResponse {
    public Animal animal;

    public ShowAnimalResponse(Animal animal) {
        this.animal = animal;
    }

    public Animal getAnimal() {
        return animal;
    }
}
