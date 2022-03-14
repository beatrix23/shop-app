package ro.ubb.catalog.client.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.ubb.catalog.web.dto.Dtos;
import ro.ubb.catalog.web.dto.ProductDto;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
@Component
public class ProductServiceClient {

    private static final String URL = "http://localhost:8080/api/products";
    private static final Logger log = LoggerFactory.getLogger(ProductServiceClient.class);

    @Autowired
    private RestTemplate restTemplate;

    public Set<ProductDto> getAllProducts() {
        log.trace("getAllProducts --- method started");
        Dtos<ProductDto> productsDto = restTemplate.exchange(URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Dtos<ProductDto>>() {}).getBody();
        log.trace("product dtos ={}", productsDto);
        return productsDto.getDtos();
    }

    public ProductDto addProduct(ProductDto productDto) {
        log.trace("add product --- method started");
        ProductDto productDtoToAdd = restTemplate.postForObject(URL, productDto, ProductDto.class);
        log.trace("productDto={}", productDto);
        return productDtoToAdd;
    }

    public void updateProduct(Integer id, ProductDto update) {
       log.trace("update product --- method started");
       restTemplate.put(URL + "/{id}", update, id);
    }

    public void deleteProduct(Integer id) {
        restTemplate.delete(URL + "/{id}", id);
    }

    public ProductDto findOneProduct(Integer id) {
        log.trace("product's id={}", id);
        ProductDto productDto = restTemplate.getForObject(URL + "/{id}", ProductDto.class, id);
        log.trace("productDto={}", productDto);
        return productDto;
    }
}
