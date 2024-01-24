package org.vetsandshelters.breed.domain;

import org.vetsandshelters.shared.DomainUtils.Collection;

public class BreedCollection extends Collection<Breed> {
    public BreedCollection(Breed[] collection, int totalElements) {
        super(collection, totalElements);
    }

    public BreedCollection(Breed[] collection) {
        super(collection);
    }
}
