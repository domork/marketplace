package com.domork.demo.persistance.impl;

import com.domork.demo.entity.Company;
import com.domork.demo.entity.CompanyExtended;
import com.domork.demo.exception.NotFoundException;
import com.domork.demo.exception.PersistenceException;
import com.domork.demo.persistance.CompanyDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.lang.invoke.MethodHandles;
import java.sql.*;
import java.util.List;
import java.util.Objects;

@Repository
public class CompanyJdbcDAO implements CompanyDAO {
    private static final String TABLE_NAME = "Company";
    private static final String ADDITIONAL_TABLE_NAME = "Companydetailinformation";
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public CompanyJdbcDAO(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public CompanyExtended putNewCompany(CompanyExtended company) {
        LOGGER.trace("PUT NEW COMPANY({})", company);
        final String sql = "INSERT INTO company (name) VALUES (?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement =
                    connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int paramIndex = 1;
            statement.setString(paramIndex, company.getName());
            return statement;
        }, keyHolder);
        company.setId(((Number) Objects.requireNonNull(keyHolder.getKeys()).get("id")).longValue());

        final String sqlExtended = "INSERT INTO companyDetailInformation " +
                "(id,website,description,basedIn) VALUES(?,?,?,?)";
        keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sqlExtended, Statement.RETURN_GENERATED_KEYS);
            int paramIndex = 1;
            statement.setLong(paramIndex++, company.getId());
            statement.setString(paramIndex++, company.getWebsite());
            statement.setString(paramIndex++, company.getDescription());
            statement.setString(paramIndex, company.getBasedIn());
            return statement;
        }, keyHolder);
        return company;
    }

    @Override
    public CompanyExtended getOneById(Long id) {
        LOGGER.trace("getOneById({})", id);
        final String sql = "SELECT * FROM " + TABLE_NAME + " natural join " + ADDITIONAL_TABLE_NAME + " WHERE id=?";
        List<CompanyExtended> companies = jdbcTemplate.query(sql, new Object[]{id}, this::mapRowExtended);
        if (companies.isEmpty()) throw new NotFoundException("There is no company with id: " + id);

        return companies.get(0);
    }

    @Override
    public void deleteById(Long id) {
        LOGGER.trace("deleteById({})", id);
        jdbcTemplate.update("DELETE FROM company WHERE ID=?", id);
        jdbcTemplate.update("DELETE FROM companyDetailInformation WHERE ID=?", id);
    }

    @Override
    public List<CompanyExtended> getAllCompaniesWithGivenName(String name) {
        final String sql = "SELECT * FROM " + TABLE_NAME + " natural join " + ADDITIONAL_TABLE_NAME
                + " WHERE name LIKE '" + name + "%' ORDER BY NAME ASC";
        List<CompanyExtended> companies = jdbcTemplate.query(sql, new Object[]{}, this::mapRowExtended);
        if (companies.isEmpty())
            throw new NotFoundException("No company with given name template was found");
        return companies;
    }

    @Override
    public Company getCompanyByName(String name) {
        final String sql = "SELECT * FROM company WHERE name ='" + name + "'";

        List<Company> company = jdbcTemplate.query(sql, new Object[]{}, this::mapRow);
        if (company.isEmpty())
            throw new NotFoundException("No company with given name template was found");
        return company.get(0);
    }

    @Override
    public CompanyExtended updateCompany(CompanyExtended company) {
        LOGGER.info("UPDATE COMPANY ({})", company);

        final String sql = "UPDATE company " +
                "SET name=? WHERE id=?";
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int paramIndex = 1;
            statement.setString(paramIndex++, company.getName());
            statement.setLong(paramIndex, company.getId());
            return statement;
        });
        final String sqlExtended = "UPDATE companyDetailInformation SET website=?, description=?, basedIn=? " +
                "WHERE id=?";
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sqlExtended, Statement.RETURN_GENERATED_KEYS);
            int paramIndex = 1;
            statement.setString(paramIndex++, company.getWebsite());
            statement.setString(paramIndex++, company.getDescription());
            statement.setString(paramIndex++, company.getBasedIn());
            statement.setLong(paramIndex, company.getId());
            return statement;
        });
        return company;


    }

    private Company mapRow(ResultSet resultSet, int i) throws SQLException {
        final Company company = new Company();
        company.setId(resultSet.getLong("id"));
        company.setName(resultSet.getString("name"));
        return company;
    }

    private CompanyExtended mapRowExtended(ResultSet resultSet, int i) throws SQLException {
        final CompanyExtended company = new CompanyExtended();
        company.setId(resultSet.getLong("id"));
        company.setName(resultSet.getString("name"));
        company.setDescription(resultSet.getString("description"));
        company.setBasedIn(resultSet.getString("basedIn"));
        company.setWebsite(resultSet.getString("website"));
        return company;
    }
}
