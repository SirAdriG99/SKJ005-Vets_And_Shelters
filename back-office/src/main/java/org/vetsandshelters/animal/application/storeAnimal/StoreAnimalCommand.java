package org.vetsandshelters.animal.application.storeAnimal;

public class StoreAnimalCommand {
    private String name;
    private String color;
    private int sexId;
    private int breedId;
    private int procedenceTypeId;
    private int animalStatusId;

    public StoreAnimalCommand(String name, String color, int sexId, int breedId, int procedenceTypeId,
            int animalStatusId) {
        this.name = name;
        this.color = color;
        this.sexId = sexId;
        this.breedId = breedId;
        this.procedenceTypeId = procedenceTypeId;
        this.animalStatusId = animalStatusId;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getSexId() {
        return sexId;
    }

    public int getBreedId() {
        return breedId;
    }

    public int getProcedenceTypeId() {
        return procedenceTypeId;
    }

    public int getAnimalStatusId() {
        return animalStatusId;
    }

}
