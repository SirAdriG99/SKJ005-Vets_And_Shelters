package org.vetsandshelters.sex.application.getSelector;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.vetsandshelters.sex.domain.SexCollection;
import org.vetsandshelters.sex.domain.SexRepository;

@ApplicationScoped
public class GetSelectorQueryHandler {
    @Inject
    private SexRepository repository;

    public GetSelectorResponse handle() {
        SexCollection sexCollection = this.repository.getAll();

        return new GetSelectorResponse(sexCollection.getCollection());

    }
}
