package ro.ubb.catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.Customer;
import ro.ubb.catalog.core.model.Product;
import ro.ubb.catalog.core.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService implements GenericService<Product> {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllEntities() {
        log.trace("getAllProducts --- method started");

        List<Product> products = productRepository.findAll();

        log.trace("getAllProducts: products={}", products);
        return products;
    }

    @Override
    public Product findOneEntity(Integer id) {
        log.trace("getOneProduct --- method started");
        log.trace("product's id={}", id);
        Product product = productRepository.findById(id).orElseThrow();
        log.trace("product found={}", product);
        return product;
    }

    @Override
    public Product addEntity(Product entity) {
        log.trace("addProduct --- method started");
        Product product = productRepository.save(entity);
        log.trace("product to add={}", product);
        return product;
    }

    @Transactional
    @Override
    public Product updateEntity(Integer id, Product entity) {
        log.trace("updateProduct --- method started");
        Product productToUpdate = productRepository.findById(id).orElseThrow();
        log.trace("product before update={}", productToUpdate);
        productToUpdate.setProductName(entity.getProductName());
        productToUpdate.setPrice(entity.getPrice());
        productToUpdate.setComment(entity.getComment());
        log.trace("product updated={}", productToUpdate);
        return productToUpdate;
    }

    @Override
    public void deleteById(Integer id) {
        log.trace("delete product --- method started");
        log.trace("product's id={}", id);
        productRepository.deleteById(id);
    }
}
