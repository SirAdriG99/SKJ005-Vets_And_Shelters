package org.vetsandshelters.breed.application.filterBreed;

public class FilterBreedQuery {
    private Integer pageNumber;
    private Integer size;
    private String order;
    private String sort;
    private String name;
    private Boolean dangerous;
    private Boolean exotic;
    private String activityNeed;
    private String spaceNeed;
    private String alimentationNeed;
    private String timeDedicationNeed;

    public FilterBreedQuery(Integer pageNumber, Integer size, String order, String sort, String name, Boolean dangerous, Boolean exotic, String activityNeed, String spaceNeed, String alimentationNeed, String timeDedicationNeed) {
        this.pageNumber = pageNumber;
        this.size = size;
        this.order = order;
        this.sort = sort;
        this.name = name;
        this.dangerous = dangerous;
        this.exotic = exotic;
        this.activityNeed = activityNeed;
        this.spaceNeed = spaceNeed;
        this.alimentationNeed = alimentationNeed;
        this.timeDedicationNeed = timeDedicationNeed;
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

    public Boolean getDangerous() {
        return dangerous;
    }

    public Boolean getExotic() {
        return exotic;
    }

    public String getActivityNeed() {
        return activityNeed;
    }

    public String getSpaceNeed() {
        return spaceNeed;
    }

    public String getAlimentationNeed() {
        return alimentationNeed;
    }

    public String getTimeDedicationNeed() {
        return timeDedicationNeed;
    }
}
