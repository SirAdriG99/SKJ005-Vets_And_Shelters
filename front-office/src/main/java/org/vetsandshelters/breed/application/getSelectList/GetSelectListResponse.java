package org.vetsandshelters.breed.application.getSelectList;

public class GetSelectListResponse {
    private String breeds;

    public GetSelectListResponse(String breeds) {
        this.breeds = breeds;
    }

    public String getBreeds() {
        return breeds;
    }
}
