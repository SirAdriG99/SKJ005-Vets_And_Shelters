package org.vetsandshelters.animal.application.filterAnimal;

public class FilterAnimalQuery {
    private Integer pageNumber;
    private Integer size;
    private String order;
    private String sort;
    private String name;
    private String color;
    private Integer sexId;
    private Integer breedId;
    private Integer procedenceTypeId;
    private Integer animalStatusId;

    public FilterAnimalQuery(Integer offset, Integer size, String order, String sort, String name, String color,
            Integer sexId, Integer breedId, Integer procedenceTypeId, Integer animalStatusId) {
        this.pageNumber = offset;
        this.size = size;
        this.order = order;
        this.sort = sort;
        this.name = name;
        this.color = color;
        this.sexId = sexId;
        this.breedId = breedId;
        this.procedenceTypeId = procedenceTypeId;
        this.animalStatusId = animalStatusId;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getSize() {
        return size;
    }

    public String getOrder() {
        return order;
    }

    public String getSort() {
        return sort;
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
