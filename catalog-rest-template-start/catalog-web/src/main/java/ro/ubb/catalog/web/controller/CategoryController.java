package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.model.Category;

import ro.ubb.catalog.core.service.GenericService;
import ro.ubb.catalog.web.converter.CategoryConverter;
import ro.ubb.catalog.web.dto.CategoryDto;
import ro.ubb.catalog.web.dto.Dtos;


import java.util.List;
import java.util.Set;

@RestController
public class CategoryController {
    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private GenericService<Category> categoryService;

    @Autowired
    private CategoryConverter categoryConverter;

    @RequestMapping(value = "/categories")
    Dtos<CategoryDto> getAllCategories() {
        log.trace("getAllCategories --- method started");
        List<Category> categoryList = categoryService.getAllEntities();
        log.trace("categories ={}", categoryList);
        Set<CategoryDto> categoryDtos = categoryConverter.convertModelsToDtos(categoryList);
        log.trace("category dtos ={}", categoryDtos);
        return new Dtos<>(categoryDtos);
    }

    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    CategoryDto addCategory(@RequestBody CategoryDto categoryDto) {
        log.trace("category dto to save={}", categoryDto);
        Category categoryToAdd = categoryConverter.convertDtoToModel(categoryDto);
        log.trace("category model to save={}", categoryToAdd);
        Category added = categoryService.addEntity(categoryToAdd);
        log.trace("category after add={}", added);
        CategoryDto dto = categoryConverter.convertModelToDto(added);
        log.trace("category dto to return ={}", dto);
        return dto;
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.PUT)
    CategoryDto updateCategory(@RequestBody CategoryDto dto, @PathVariable Integer id) {
        log.trace("category dto={}", dto);
        log.trace("category id={}", id);
        Category converted = categoryConverter.convertDtoToModel(dto);
        log.trace("category converted to model={}", converted);
        Category updated = categoryService.updateEntity(id, converted);
        log.trace("category updated={}", updated);
        CategoryDto toReturn = categoryConverter.convertModelToDto(updated);
        log.trace("category dto to return={}", toReturn);
        return toReturn;
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
        log.trace("delete category --- method started");
        categoryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "categories/{id}", method = RequestMethod.GET)
    CategoryDto findOneCategory(@PathVariable Integer id) {
        log.trace("category's id={}", id);
        Category category = categoryService.findOneEntity(id);
        log.trace("category found={}", category);
        CategoryDto categoryDto = categoryConverter.convertModelToDto(category);
        log.trace("categorySto = {}", categoryDto);
        return categoryDto;
    }
}
