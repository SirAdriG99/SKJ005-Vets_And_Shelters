package org.vetsandshelters.animalPhotos.domain;

import org.vetsandshelters.shared.DomainUtils.Collection;

public class AnimalPhotoCollection extends Collection<AnimalPhoto> {

    public AnimalPhotoCollection(AnimalPhoto[] collection) {
        super(collection);
    }
    public AnimalPhotoCollection(AnimalPhoto[] collection, int totalElements) {
        super(collection, totalElements);
    }
}
