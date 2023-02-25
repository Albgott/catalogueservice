package com.albgott.catalogueservice.category.application.delete;

import com.albgott.catalogueservice.category.domain.exception.CategoryNotFound;
import com.albgott.catalogueservice.category.domain.model.Category;
import com.albgott.catalogueservice.category.domain.repository.CategoryRepository;
import com.albgott.catalogueservice.shared.application.CommandUseCase;
import com.albgott.catalogueservice.shared.domain.event.EventBus;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DeleteCategoryService implements CommandUseCase<DeleteCategoryCommand> {

    private final CategoryRepository categoryRepository;
    private final EventBus eventBus;

    public DeleteCategoryService(CategoryRepository categoryRepository, EventBus eventBus) {
        this.categoryRepository = categoryRepository;
        this.eventBus = eventBus;
    }

    @Override
    public void exec(DeleteCategoryCommand command) {
        Category category = categoryRepository.findById(command.id())
                .orElseThrow(() -> new CategoryNotFound(command.id().toString()));
        category.delete();
        categoryRepository.delete(category);
        eventBus.publish(category.pullDomainEvents());
    }
}
