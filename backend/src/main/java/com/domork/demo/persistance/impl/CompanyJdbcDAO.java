package com.domork.demo.persistance.impl;

import com.domork.demo.entity.Company;
import com.domork.demo.exception.NotFoundException;
import com.domork.demo.exception.PersistenceException;
import com.domork.demo.persistance.CompanyDAO;
import org.h2.jdbc.JdbcSQLException;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public CompanyJdbcDAO(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public List<Company> getAllCompanies() {
        LOGGER.trace("get all companies");
        final String sql = "SELECT * FROM COMPANY";
        List<Company> companies = jdbcTemplate.query(sql, new Object[]{}, this::mapRow);
        if (companies.isEmpty()) throw new NotFoundException("Could not find any company");
        return companies;
    }

    @Override
    public Company putNewCompany(Company company) {
        LOGGER.trace("PUT NEW COMPANY({})", company);
        final String sql = "INSERT INTO company (name) VALUES (?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int paramIndex = 1;
            statement.setString(paramIndex, company.getName());
            return statement;
        }, keyHolder);
        company.setId(((Number) Objects.requireNonNull(keyHolder.getKeys()).get("id")).longValue());
        return company;
    }

    @Override
    public Company getOneById(Long id) {
        LOGGER.trace("getOneById({})", id);
        final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
        List<Company> companies = jdbcTemplate.query(sql, new Object[]{id}, this::mapRow);
        if (companies.isEmpty()) throw new NotFoundException("There is no company with id: " + id);

        return companies.get(0);
    }

    @Override
    public void deleteById(Long id) {
        LOGGER.trace("deleteById({})", id);
        jdbcTemplate.update("DELETE FROM company WHERE ID=?", id);
    }

    @Override
    public List<Company> getAllCompaniesWithGivenName(String name) {
        final String sql = "SELECT * FROM company WHERE name LIKE '"+name+"%'";
        List <Company> companies = jdbcTemplate.query(sql,new Object[]{},this::mapRow);
        if (companies.isEmpty())
            throw new NotFoundException ("No company with given name template was found");
        return companies;
    }

    @Override
    public Company getCompanyByName(String name) {
        final String sql = "SELECT * FROM company WHERE name ='"+name+"'";
        Company company = jdbcTemplate.query(sql,new Object[]{},this::mapRow).get(0);
        if (company == null)
            throw new NotFoundException ("No company with given name template was found");
        return company;
    }

    private Company mapRow(ResultSet resultSet, int i) throws SQLException {
        final Company company = new Company();
        company.setId(resultSet.getLong("id"));
        company.setName(resultSet.getString("name"));
        return company;
    }
}
