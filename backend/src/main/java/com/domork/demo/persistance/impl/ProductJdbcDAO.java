package com.domork.demo.persistance.impl;

import com.domork.demo.entity.CompanyExtended;
import com.domork.demo.entity.Product;
import com.domork.demo.exception.NotFoundException;
import com.domork.demo.persistance.ProductDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.lang.invoke.MethodHandles;
import java.sql.*;
import java.util.List;
import java.util.Objects;

@Repository
public class ProductJdbcDAO implements ProductDAO {
    private static final String TABLE_NAME = "product";
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final JdbcTemplate jdbcTemplate;

    public ProductJdbcDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product addNewProduct(Product product) {
        LOGGER.trace("PUT NEW PRODUCT({})", product);
        final String sql = "INSERT INTO " + TABLE_NAME +
                "(name, description, category,price,quantity,condition)" +
                " VALUES (?,?,?,?,?,?::productCondition);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement =
                    connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int paramIndex = 1;
            statement.setString(paramIndex++, product.getName());

            statement.setString(paramIndex++, product.getDescription());
            statement.setString(paramIndex++, product.getCategory());
            statement.setBigDecimal(paramIndex++, product.getPrice());
            statement.setInt(paramIndex++, product.getQuantity());
            statement.setString(paramIndex, product.getCondition() == null ? "new" : product.getCondition());
            return statement;
        }, keyHolder);
        product.setID(((Number) Objects.requireNonNull(keyHolder.getKeys()).get("id")).longValue());
        product.setCondition((Objects.requireNonNull(keyHolder.getKeys()).get("condition")).toString());
        return product;
    }

    @Override
    public List<Product> getProductsByName(String name) {
        LOGGER.trace("getProductsByName({})", name);
        final String sql = "SELECT * FROM " + TABLE_NAME
                + " WHERE name LIKE '" + name + "%' ORDER BY NAME ASC";
        List<Product> products = jdbcTemplate.query(sql, new Object[]{}, this::mapRow);
        if (products.isEmpty())
            throw new NotFoundException("No product with given name template was found");
        return products;
    }

    @Override
    public Product getProductById(Long ID) {
        LOGGER.trace("getProductById({})", ID);
        final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
        List<Product> products = jdbcTemplate.query(sql, new Object[]{ID}, this::mapRow);
        if (products.isEmpty()) throw new NotFoundException("There is no product with id: " + ID);
        return products.get(0);
    }

    @Override
    public void deleteProductById(Long ID) {
        LOGGER.trace("deleteProductById({})", ID);
        jdbcTemplate.update("DELETE FROM product WHERE ID=?", ID);
    }

    @Override
    public Product getOneProductByName(String name) {
        final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE name='" + name + "'";
        List<Product> product = jdbcTemplate.query(sql, new Object[]{}, this::mapRow);
        if (product.isEmpty())
            throw new NotFoundException("No product with given name template was found");
        return product.get(0);
    }

    @Override
    public Product updateProduct(Product product) {
        LOGGER.info("UPDATE PRODUCT ({})", product);
        final String sql = "UPDATE product " +
                "SET name=?, description=?, price=?, quantity=?, condition=?::productCondition" +
                " WHERE id=?";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int paramIndex = 1;
            statement.setString(paramIndex++, product.getName());

            statement.setString(paramIndex++, product.getDescription());
            statement.setBigDecimal(paramIndex++, product.getPrice());
            statement.setInt(paramIndex++, product.getQuantity());
            statement.setString(paramIndex++, product.getCondition() == null ? "new" : product.getCondition());
            statement.setLong(paramIndex, product.getID());
            return statement;
        }, keyHolder);
        return product;
    }

    private Product mapRow(ResultSet resultSet, int i) throws SQLException {
        final Product product = new Product();
        product.setCategory(resultSet.getString("category"));
        product.setDescription(resultSet.getString("description"));
        product.setName(resultSet.getString("name"));
        product.setCondition(resultSet.getString("condition"));
        product.setPrice(resultSet.getBigDecimal("price"));
        product.setQuantity(resultSet.getInt("quantity"));
        product.setID(resultSet.getLong("id"));
        return product;
    }
}
