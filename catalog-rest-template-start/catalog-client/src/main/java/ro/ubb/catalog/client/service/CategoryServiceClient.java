package ro.ubb.catalog.client.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.ubb.catalog.web.dto.CategoryDto;
import ro.ubb.catalog.web.dto.Dtos;
import java.util.Set;

@Component
public class CategoryServiceClient {
    private static final String URL = "http://localhost:8080/api/categories";
    private static final Logger log = LoggerFactory.getLogger(CategoryServiceClient.class);
    @Autowired
    private RestTemplate restTemplate;

    public Set<CategoryDto> getAllCategories() {
        log.trace("getAllCategories --- method started");
        Dtos<CategoryDto> categoryDtos = restTemplate.exchange(URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Dtos<CategoryDto>>() {}).getBody();;
        log.trace("category dtos={}", categoryDtos);
        return categoryDtos.getDtos();
    }

    public CategoryDto addCategory(CategoryDto newCategory) {
        log.trace("addCategory --- method started");
        CategoryDto categoryDto = restTemplate.postForObject(URL, newCategory, CategoryDto.class);
        log.trace("category dto={}", categoryDto);
        return categoryDto;
    }

    public void updateCategory(Integer id, CategoryDto updated) {
        log.trace("updateCategory --- method started");
        restTemplate.put(URL + "/{id}", updated, id);
    }

    public void deleteCategory(Integer id) {
        log.trace("deleteCategory --- method started");
        restTemplate.delete(URL + "/{id}", id);
    }

    public CategoryDto findOneCategory(Integer id) {
        log.trace("category's id={}", id);
        CategoryDto categoryDto = restTemplate.getForObject(URL + "/{id}", CategoryDto.class, id);
        log.trace("categoryDto={}", categoryDto);
        return categoryDto;
    }
}
