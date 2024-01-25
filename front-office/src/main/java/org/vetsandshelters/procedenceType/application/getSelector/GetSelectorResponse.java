package org.vetsandshelters.procedenceType.application.getSelector;

import org.vetsandshelters.procedenceType.domain.ProcedenceType;

public class GetSelectorResponse {
    private ProcedenceType[] selector;

    public GetSelectorResponse(ProcedenceType[] selector) {
        this.selector = selector;
    }

    public ProcedenceType[] getSelector() {
        return selector;
    }
}
