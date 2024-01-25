package org.vetsandshelters.breed.application.getSelectList;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.vetsandshelters.breed.domain.BreedCollection;
import org.vetsandshelters.breed.domain.BreedRepository;

@ApplicationScoped
public class GetSelectListQueryHandler {
    @Inject
    private BreedRepository repository;

    public GetSelectListResponse handle() {
        BreedCollection collection = this.repository.getAll();

        String breeds = "[";
        for (int i = 0; i < collection.getCollection().length; i++) {
            breeds += "{\"id\": " + collection.getCollection()[i].getId() + ", \"name\": \"" + collection.getCollection()[i].getName() + "\"}";
            if (i < collection.getCollection().length - 1) {
                breeds += ", ";
            }
        }
        breeds += "]";


        return new GetSelectListResponse(breeds);
    }
}
