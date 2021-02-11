package com.domork.demo.service.impl;

import com.domork.demo.entity.Product;
import com.domork.demo.exception.ValidationException;
import com.domork.demo.persistance.CompanyDAO;
import com.domork.demo.persistance.ProductDAO;
import com.domork.demo.service.ProductService;
import com.domork.demo.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final Validator validator;
    private final ProductDAO productDAO;
    public ProductServiceImpl(Validator validator, ProductDAO productDAO) {
        this.validator = validator;
        this.productDAO=productDAO;
    }

    @Override
    public Product addNewProduct(Product product) {
        LOGGER.trace("addNewProduct({})",product);
        String name = product.getName();
        if (name== null)
            throw new ValidationException("Name was not given");
        validator.nameText(product.getName());

        validator.checkProduct(product);
        return productDAO.addNewProduct(product);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        validator.nameText(name);
        return productDAO.getProductsByName(name);
    }

    @Override
    public Product getProductById(Long ID) {
        validator.idCheck(ID);
        return productDAO.getProductById(ID);
    }

    @Override
    public void deleteProductById(Long ID) {
    validator.idCheck(ID);
    productDAO.deleteProductById(ID);
    }

    @Override
    public Product getOneProductByName(String name) {
        validator.nameText(name);
        return productDAO.getOneProductByName(name);
    }

    @Override
    public Product updateProduct(Product product) {
        if (product.getID()==null)
            throw new ValidationException("ID was not given");
        validator.checkProduct(product);
        return productDAO.updateProduct(product);
    }
}
