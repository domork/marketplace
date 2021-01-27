package com.domork.demo.service;

import com.domork.demo.entity.Company;
import com.domork.demo.persistance.CompanyDAO;

import java.util.List;

public interface CompanyService {

    /**
     * @return all companies from the DB
     */
    List<Company> getAllCompanies();

    /**
     * The new company is to be added.
     * @param company should only contain the name.
     *                The ID will be ignored.
     * @return the company name and ID, which is stored
     *                in DB.
     */
    Company putNewCompany(Company company);
}
