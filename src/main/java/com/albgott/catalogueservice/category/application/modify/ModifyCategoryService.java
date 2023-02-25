package com.albgott.catalogueservice.category.application.modify;

import com.albgott.catalogueservice.category.domain.exception.CategoryNotFound;
import com.albgott.catalogueservice.category.domain.model.Category;
import com.albgott.catalogueservice.category.domain.repository.CategoryRepository;
import com.albgott.catalogueservice.shared.application.CommandUseCase;
import com.albgott.catalogueservice.shared.domain.event.EventBus;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ModifyCategoryService implements CommandUseCase<ModifyCategoryCommand> {

    private final CategoryRepository categoryRepository;
    private final EventBus eventBus;

    public ModifyCategoryService(CategoryRepository categoryRepository, EventBus eventBus) {
        this.categoryRepository = categoryRepository;
        this.eventBus = eventBus;
    }

    @Override
    public void exec(ModifyCategoryCommand command) {
        Category category = getCategoryFromCommand(command);

        category.modifyDescription(command.description());
        category.modifyName(command.name());

        eventBus.publish(category.pullDomainEvents());
    }

    private Category getCategoryFromCommand(ModifyCategoryCommand command) {
        return categoryRepository.findById(command.id())
                .orElseThrow(() -> new CategoryNotFound(command.id().toString()));
    }
}
