package org.vetsandshelters.breed.application.filterBreed;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.vetsandshelters.breed.domain.BreedCollection;
import org.vetsandshelters.breed.domain.BreedCriteria;
import org.vetsandshelters.breed.domain.BreedRepository;
import org.vetsandshelters.shared.DomainUtils.Criteria.Filter;
import org.vetsandshelters.shared.DomainUtils.Criteria.FilterCollection;
import org.vetsandshelters.shared.DomainUtils.Criteria.FilterOperator;
import org.vetsandshelters.shared.DomainUtils.Pagination.Pagination;

@ApplicationScoped
public class FilterBreedQueryHandler {
    @Inject
    BreedRepository breedRepository;

    public FilterBreedResponse handle(FilterBreedQuery query) {
        BreedCriteria criteria = this.setCriteria(query);

        BreedCollection breeds = this.breedRepository.getBy(criteria);

        return new FilterBreedResponse(breeds.getCollection(), breeds.getTotalElements());
    }

    private BreedCriteria setCriteria(FilterBreedQuery query) {
        Pagination pagination = query.getSize() != null ? new Pagination(
                query.getSize(),
                query.getPageNumber() != null ? query.getPageNumber() : 0, null // Ignore sort by now
        ) : null;

        FilterCollection filters = new FilterCollection();
        if (query.getName() != null) {
            Filter nameFilter = new Filter("name", FilterOperator.LIKE, query.getName());
            filters.add(nameFilter);
        }
        if (query.getName() != null) {
            Filter nameFilter = new Filter("exotic", FilterOperator.EQUALS, query.getExotic());
            filters.add(nameFilter);
        }
        if (query.getName() != null) {
            Filter nameFilter = new Filter("dangerous", FilterOperator.EQUALS, query.getDangerous());
            filters.add(nameFilter);
        }
        if (query.getName() != null) {
            Filter nameFilter = new Filter("activityNeed", FilterOperator.LIKE, query.getActivityNeed());
            filters.add(nameFilter);
        }
        if (query.getName() != null) {
            Filter nameFilter = new Filter("spaceNeed", FilterOperator.LIKE, query.getSpaceNeed());
            filters.add(nameFilter);
        }
        if (query.getName() != null) {
            Filter nameFilter = new Filter("alimenationNeed", FilterOperator.LIKE, query.getAlimentationNeed());
            filters.add(nameFilter);
        }
        if (query.getName() != null) {
            Filter nameFilter = new Filter("timeDedicationNeed", FilterOperator.LIKE, query.getAlimentationNeed());
            filters.add(nameFilter);
        }


        return new BreedCriteria(filters, pagination);
    }

}
