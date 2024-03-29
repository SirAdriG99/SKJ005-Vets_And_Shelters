package org.vetsandshelters.animal.application.updateAnimal;

public class UpdateAnimalCommand {
    private int id;
    private String name;
    private String color;
    private Integer sexId;
    private Integer breedId;
    private Integer procedenceTypeId;
    private Integer animalStatusId;

    public UpdateAnimalCommand(int id, String name, String color, Integer sexId, Integer breedId,
            Integer procedenceTypeId, Integer animalStatusId) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.sexId = sexId;
        this.breedId = breedId;
        this.procedenceTypeId = procedenceTypeId;
        this.animalStatusId = animalStatusId;
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

    public Integer getSexId() {
        return sexId;
    }

    public Integer getBreedId() {
        return breedId;
    }

    public Integer getProcedenceTypeId() {
        return procedenceTypeId;
    }

    public Integer getAnimalStatusId() {
        return animalStatusId;
    }

}
