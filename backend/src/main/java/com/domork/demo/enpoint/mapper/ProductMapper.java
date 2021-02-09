package com.domork.demo.enpoint.mapper;

import com.domork.demo.enpoint.dto.ProductDto;
import com.domork.demo.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getID(),
                product.getName(), product.getCategory(),
                product.getDescription(), product.getPrice(),
                product.getQuantity(), product.getCondition());
    }

    public Product dtoToEntity(ProductDto productDto) {
        return new Product(productDto.getId(),
                productDto.getName(), productDto.getCategory(),
                productDto.getDescription(),
                productDto.getPrice(), productDto.getQuantity(),
                productDto.getCondition());
    }
}
