package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.model.Product;
import ro.ubb.catalog.core.service.GenericService;
import ro.ubb.catalog.web.converter.ProductConverter;
import ro.ubb.catalog.web.dto.Dtos;
import ro.ubb.catalog.web.dto.ProductDto;

import java.util.List;
import java.util.Set;

@RestController
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private GenericService<Product> productService;

    @Autowired
    private ProductConverter productConverter;

    @RequestMapping(value = "products/{id}", method = RequestMethod.GET)
    ProductDto findOneProduct(@PathVariable Integer id) {
        log.trace("product's id={}", id);
        Product product = productService.findOneEntity(id);
        log.trace("product found={}", product);
        ProductDto productDto = productConverter.convertModelToDto(product);
        log.trace("productDto = {}", productDto);
        return productDto;
    }

    @RequestMapping(value = "/products")
    Dtos<ProductDto> getAllProducts() {
        log.trace("getAllProducts --- method started");
        List<Product> products = productService.getAllEntities();
        log.trace("products from service={}", products);
        Set<ProductDto> productDtos = productConverter.convertModelsToDtos(products);
        log.trace("products dtos={}", productDtos);
        return new Dtos<>(productDtos);
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    ProductDto saveProduct(@RequestBody ProductDto productDto) {
        log.trace("save product: dto={}", productDto);
        return productConverter.convertModelToDto(productService.addEntity(productConverter.convertDtoToModel(productDto)));
    }

    @RequestMapping(value = "products/{id}", method = RequestMethod.PUT)
    ProductDto updateProduct(@PathVariable Integer id, @RequestBody ProductDto dto) {
        log.trace("update product dto = {}", dto);
        log.trace("product's id = {}", id);
        Product product = productConverter.convertDtoToModel(dto);
        Product productUpdated = productService.updateEntity(id, product);
        ProductDto dtoToReturn = productConverter.convertModelToDto(productUpdated);
        log.trace("product dto to return = {}", dtoToReturn);
        return dtoToReturn;
    }

    @RequestMapping(value = "products/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        log.trace("product's id={}", id);
        productService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
