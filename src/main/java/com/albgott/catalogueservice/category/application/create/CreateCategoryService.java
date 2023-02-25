package com.albgott.catalogueservice.category.application.create;

import com.albgott.catalogueservice.category.domain.event.CategoryCreatedDomainEvent;
import com.albgott.catalogueservice.category.domain.exception.CategoryNameAlreadyUseInsideBusiness;
import com.albgott.catalogueservice.category.domain.model.Category;
import com.albgott.catalogueservice.category.domain.repository.CategoryRepository;
import com.albgott.catalogueservice.shared.application.CommandUseCase;
import com.albgott.catalogueservice.shared.domain.event.EventBus;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CreateCategoryService implements CommandUseCase<CreateCategoryCommand> {
    private final CategoryRepository categoryRepository;
    private final EventBus eventBus;

    public CreateCategoryService(CategoryRepository categoryRepository, EventBus eventBus) {
        this.categoryRepository = categoryRepository;
        this.eventBus = eventBus;
    }

    @Override
    public void exec(CreateCategoryCommand command) {
        Category category = getCategoryFromCommand(command);

        categoryRepository.save(category);
        eventBus.publish(List.of(
                new CategoryCreatedDomainEvent(category)
        ));
    }

    private Category getCategoryFromCommand(CreateCategoryCommand command) {
        Category category = new Category(
                command.businessId(),
                command.id(),
                command.name(),
                command.description()
        );

        if(categoryRepository.isNameUsedOnBusiness(category.name(), category.businessId()))
            throw new CategoryNameAlreadyUseInsideBusiness(category.businessId().toString(), category.name());

        return category;
    }
}
