package ro.ubb.catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.Category;
import ro.ubb.catalog.core.repository.CategoryRepository;

import java.util.List;
@Service
public class CategoryService implements GenericService<Category> {

   private static final Logger log = LoggerFactory.getLogger(CategoryService.class);

   @Autowired
   private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllEntities() {
        log.trace("getAllCategories --- method started");

        List<Category> allCategories = categoryRepository.findAll();

        log.trace("getAllCategories: categories={}", allCategories);
        return allCategories;
    }

    @Override
    public Category findOneEntity(Integer id) {
        log.trace("getOneCategory --- method started");
        log.trace("category's id={}", id);
        Category category = categoryRepository.findById(id).orElseThrow();
        log.trace("category found={}", category);
        return category;
    }

    @Override
    public Category addEntity(Category entity) {
        log.trace("addCategory --- method started");
        Category category = categoryRepository.save(entity);
        log.trace("category to add={}", category);
        return category;
    }

    @Transactional
    @Override
    public Category updateEntity(Integer id, Category entity) {
        log.trace("updateCategory --- method started");
        Category categoryToUpdate = categoryRepository.findById(id).orElseThrow();
        log.trace("category before update={}", categoryToUpdate);
        categoryToUpdate.setName(entity.getName());
        categoryToUpdate.setDescription(entity.getDescription());
        log.trace("category updated={}", categoryToUpdate);
        return categoryToUpdate;
    }

    @Override
    public void deleteById(Integer id) {
        log.trace("delete category --- method started");
        log.trace("category's id={}", id);
        categoryRepository.deleteById(id);

    }
}
