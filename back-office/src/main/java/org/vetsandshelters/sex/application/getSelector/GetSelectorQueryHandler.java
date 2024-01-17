package org.vetsandshelters.sex.application.getSelector;

import org.vetsandshelters.sex.domain.SexCollection;
import org.vetsandshelters.sex.domain.SexRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetSelectorQueryHandler {
    @Inject
    private SexRepository repository;

    public GetSelectorResponse handle() {
        SexCollection sexCollection = this.repository.getAll();

        return new GetSelectorResponse(sexCollection.getCollection());

    }
}
