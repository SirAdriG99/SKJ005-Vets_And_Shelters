package org.vetsandshelters.animal.application.getAnimal;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.vetsandshelters.animal.domain.Animal;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class GetAnimalResponse {
    private Animal animal;

    public GetAnimalResponse(Animal animal) {
        this.animal = animal;
    }

    public Animal getAnimal() {
        return animal;
    }
}
