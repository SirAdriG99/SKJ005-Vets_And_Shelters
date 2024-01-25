package org.vetsandshelters.procedenceType.application.getSelector;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.vetsandshelters.procedenceType.domain.ProcedenceTypeCollection;
import org.vetsandshelters.procedenceType.domain.ProcedenceTypeRepository;

@ApplicationScoped
public class GetSelectorQueryHandler {
    @Inject
    private ProcedenceTypeRepository repository;

    public GetSelectorResponse handle() {
        ProcedenceTypeCollection collection = this.repository.getAll();

        return new GetSelectorResponse(collection.getCollection());

    }
}
