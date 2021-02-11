package com.domork.demo.enpoint;

import com.domork.demo.enpoint.dto.CompanyDto;
import com.domork.demo.enpoint.dto.CompanyExtendedDto;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto product) {
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
            LOGGER.warn("PUT PRODUCT THROWS VALIDATION EXCEPTION ({})", product + " . The error message: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), e);
        }
        //otherwise bad request will be sent back
        LOGGER.warn("PUT PRODUCT PROVIDES ALREADY EXISTING COMPANY ({})", product);
        throw new ResponseStatusException(HttpStatus.CONFLICT, "This product already exists");
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProductsByName(@RequestParam(value = "name",
            required = false, defaultValue = "") String name) {
        LOGGER.info("getProductsByName: ({})", name);
        try {
            List<Product> products = productService.getProductsByName(name);
            List<ProductDto> productDtos = new ArrayList<>();
            for (Product product : products) {
                productDtos.add(productMapper.entityToDto(product));
            }
            return new ResponseEntity<List<ProductDto>>(productDtos, HttpStatus.OK);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during finding the companies." +
                    " Possible reason: there is no stored product with given name", e);

        }

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDto> getOneById(@PathVariable("id") Long id) {
        LOGGER.info("GET " + BASE_URL + "/{}", id);
        try {
            return new ResponseEntity<ProductDto>(productMapper.entityToDto(productService.getProductById(id)), HttpStatus.OK);
        } catch (NotFoundException e) {
            LOGGER.warn("GET PRODUCT WITH ID: " + id + " THROWS 404");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during reading the product", e);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProductDto> deleteOneById(@PathVariable("id") Long id) {
        LOGGER.info("DELETE " + BASE_URL + "/{}", id);
        try {
            ProductDto productDto = productMapper.entityToDto(productService.getProductById(id));
            productService.deleteProductById(id);
            return new ResponseEntity<ProductDto>(productDto, HttpStatus.OK);
        } catch (NotFoundException e) {
            LOGGER.warn("DELETE PRODUCT WITH ID: " + id + " THROWS 404");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during deleting the product", e);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto) {
        LOGGER.info("UPDATE " + BASE_URL + "/{}", id);
        try {
            String name = productDto.getName();
            if (name != null) {
                Product product = productService.getOneProductByName(name);
                if (!product.getID().equals(productDto.getId()))
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "This name was already taken!");
            } else throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Name must have a value!");

        } catch (NotFoundException ignored) {
        }
        try {


            return new ResponseEntity<ProductDto>(productMapper.entityToDto
                    (productService.updateProduct(productMapper.dtoToEntity(productDto))), HttpStatus.OK);
        } catch (ValidationException e) {
            LOGGER.warn("UPDATE PRODUCT WITH ID: ({}) THROWS VALIDATION EXCEPTION", id);
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), e);

        }
    }

}
