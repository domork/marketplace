package com.domork.demo.enpoint;

import com.domork.demo.enpoint.dto.ProductDto;
import com.domork.demo.enpoint.mapper.CompanyExtendedMapper;
import com.domork.demo.enpoint.mapper.CompanyMapper;
import com.domork.demo.enpoint.mapper.ProductMapper;
import com.domork.demo.entity.Product;
import com.domork.demo.exception.NotFoundException;
import com.domork.demo.exception.ValidationException;
import com.domork.demo.service.CompanyService;
import com.domork.demo.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.lang.invoke.MethodHandles;

@RequestMapping(ProductEndpoint.BASE_URL)
@RestController
public class ProductEndpoint {
    static final String BASE_URL = "/product";
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final ProductService productService;
    private final ProductMapper productMapper;

    @Autowired
    public ProductEndpoint(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }
    @PutMapping
    public ResponseEntity<ProductDto> put(@RequestBody ProductDto product) {
        LOGGER.info("PUT " + BASE_URL + "/{}", product);
        try {

            //check if the product already exists
            try {
                productService.getOneProductByName(product.getName());
            } catch (NotFoundException e) {


                //if not -> the new product will be saved
                return new ResponseEntity<ProductDto>(
                        (productMapper.entityToDto
                                (productService.addNewProduct
                                        (productMapper.dtoToEntity(product)))), HttpStatus.CREATED);
            }
        } catch (ValidationException e) {
            LOGGER.warn("PUT PRODUCT THROWS VALIDATION EXCEPTION ({})", product);
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), e);
        }
        //otherwise bad request will be sent back
        LOGGER.warn("PUT PRODUCT PROVIDES ALREADY EXISTING COMPANY ({})", product);
        throw new ResponseStatusException(HttpStatus.CONFLICT, "This product already exists");
    }
}
