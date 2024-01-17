package org.vetsandshelters.animalStatus.application.getSelector;

import org.vetsandshelters.animalStatus.domain.AnimalStatus;

public class GetSelectorResponse {
    private AnimalStatus[] selector;

    public GetSelectorResponse(AnimalStatus[] selector) {
        this.selector = selector;
    }

    public AnimalStatus[] getSelector() {
        return selector;
    }
}
