package org.vetsandshelters.animal.application.updateAnimal;

public class UpdateAnimalCommand {
    private int id;
    private String name;
    private String color;
    private int sexId;
    private int breedId;
    private int procedenceTypeId;

    public UpdateAnimalCommand(int id, String name, String color, int sexId, int breedId, int procedenceTypeId) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.sexId = sexId;
        this.breedId = breedId;
        this.procedenceTypeId = procedenceTypeId;
    }

    public int getId() {
        return id;
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

}
