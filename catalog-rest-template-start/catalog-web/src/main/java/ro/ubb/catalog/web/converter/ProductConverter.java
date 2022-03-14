package ro.ubb.catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Product;
import ro.ubb.catalog.web.dto.ProductDto;

@Component
public class ProductConverter extends BaseConverter<Product, ProductDto> {
    @Override
    public Product convertDtoToModel(ProductDto dto) {
        Product product = Product.builder().
                productName(dto.getName()).
                price(dto.getPrice()).
                comment(dto.getComment()).
                build();
        product.setId(dto.getId());
        return product;
    }

    @Override
    public ProductDto convertModelToDto(Product product) {
        ProductDto dto = ProductDto.builder()
                .name(product.getProductName())
                .price(product.getPrice())
                .comment(product.getComment())
                .build();
        dto.setId(product.getId());

        return dto;
    }
}
