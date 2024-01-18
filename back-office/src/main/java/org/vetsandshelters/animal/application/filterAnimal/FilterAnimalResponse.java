package org.vetsandshelters.animal.application.filterAnimal;

import org.vetsandshelters.animal.domain.Animal;

public class FilterAnimalResponse {
    private Animal[] animals;
    private int totalElements;

    public FilterAnimalResponse(Animal[] animals, int totalElements) {
        this.animals = animals;
        this.totalElements = totalElements;
    }

    public Animal[] getAnimals() {
        return animals;
    }

    public int getTotalElements() {
        return totalElements;
    }
}
