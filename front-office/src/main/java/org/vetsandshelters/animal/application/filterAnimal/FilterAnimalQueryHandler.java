package org.vetsandshelters.animal.application.filterAnimal;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.vetsandshelters.animal.domain.AnimalCollection;
import org.vetsandshelters.animal.domain.AnimalCriteria;
import org.vetsandshelters.animal.domain.AnimalRepository;
import org.vetsandshelters.shared.DomainUtils.Criteria.Filter;
import org.vetsandshelters.shared.DomainUtils.Criteria.FilterCollection;
import org.vetsandshelters.shared.DomainUtils.Criteria.FilterOperator;
import org.vetsandshelters.shared.DomainUtils.Pagination.Pagination;

@ApplicationScoped
public class FilterAnimalQueryHandler {
    @Inject
    private AnimalRepository repository;

    public FilterAnimalResponse handle(FilterAnimalQuery query) {
        AnimalCriteria criteria = this.setCriteria(query);

        AnimalCollection animals = this.repository.getBy(criteria);

        return new FilterAnimalResponse(animals.getCollection(), animals.getTotalElements());
    }

    private AnimalCriteria setCriteria(FilterAnimalQuery query) {
        /**
         * TODO: Sort and order. Not for MVP
         */
        /**
         * If at least exists the pagination size,
         * by default generate
         */
        Pagination pagination = query.getSize() != null ? new Pagination(
                query.getSize(),
                query.getPageNumber() != null ? query.getPageNumber() : 0, null // Ignore sort by now
        ) : null;

        FilterCollection filters = new FilterCollection();
        if (query.getName() != null) {
            Filter nameFilter = new Filter("name", FilterOperator.LIKE, query.getName());
            filters.add(nameFilter);
        }
        if (query.getColor() != null) {
            Filter colorFilter = new Filter("color", FilterOperator.LIKE, query.getColor());
            filters.add(colorFilter);
        }
        if (query.getSexId() != null) {
            Filter sexFilter = new Filter("sexId", FilterOperator.EQUALS, query.getSexId());
            filters.add(sexFilter);
        }
        if (query.getBreedId() != null) {
            Filter breedFilter = new Filter("breedId", FilterOperator.EQUALS, query.getBreedId());
            filters.add(breedFilter);
        }
        if (query.getProcedenceTypeId() != null) {
            Filter procedenceTypeFilter = new Filter("procedenceTypeId", FilterOperator.EQUALS,
                    query.getProcedenceTypeId());
            filters.add(procedenceTypeFilter);
        }
        if (query.getAnimalStatusId() != null) {
            Filter animalStatusFilter = new Filter("animalStatusId", FilterOperator.EQUALS,
                    query.getAnimalStatusId());
            filters.add(animalStatusFilter);
        }

        return new AnimalCriteria(filters, pagination);
    }
}
