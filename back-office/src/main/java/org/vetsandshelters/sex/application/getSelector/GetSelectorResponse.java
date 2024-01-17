package org.vetsandshelters.sex.application.getSelector;

import org.vetsandshelters.sex.domain.Sex;

public class GetSelectorResponse {
    private Sex[] sexSelector;

    public GetSelectorResponse(Sex[] sexSelector) {
        this.sexSelector = sexSelector;
    }

    public Sex[] getSexSelector() {
        return sexSelector;
    }
}
