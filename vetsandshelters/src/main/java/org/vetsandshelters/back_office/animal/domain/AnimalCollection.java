package org.vetsandshelters.back_office.animal.domain;

import org.vetsandshelters.shared.DomainUtils.Collection;

public class AnimalCollection extends Collection<Animal> {
    public AnimalCollection(Animal[] animals) {
        super(animals);
    }
}
