package org.vetsandshelters.animal.domain;

import org.vetsandshelters.shared.DomainUtils.Collection;

/*
 * The collections represent a group of objects of the same type.
 * They're all extensions of the Collection class.
 * The collection class has all the methods that we're going to need
 */
public class AnimalCollection extends Collection<Animal> {
    public AnimalCollection(Animal[] animals) {
        super(animals);
    }
    public AnimalCollection(Animal[] animals, int totalElements) {
        super(animals, totalElements);
    }
}
