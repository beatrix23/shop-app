package ro.ubb.catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Category;
import ro.ubb.catalog.web.dto.CategoryDto;
@Component
public class CategoryConverter extends BaseConverter<Category, CategoryDto> {
    @Override
    public Category convertDtoToModel(CategoryDto dto) {
        Category category = Category.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
        category.setId(dto.getId());
        return category;
    }

    @Override
    public CategoryDto convertModelToDto(Category category) {
        CategoryDto categoryDto = CategoryDto.builder()
                .name(category.getName())
                .description(category.getDescription())
                .build();
        categoryDto.setId(category.getId());
        return categoryDto;
    }
}
