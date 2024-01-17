package org.vetsandshelters.animal.application.filterAnimal;

import org.vetsandshelters.animal.domain.Animal;

public class FilterAnimalResponse {
    private Animal[] animals;

    public FilterAnimalResponse(Animal[] animals) {
        this.animals = animals;
    }

    public Animal[] getAnimals() {
        return animals;
    }
}
