package com.domork.demo.persistance;

import com.domork.demo.entity.Company;

import java.util.List;

public interface CompanyDAO {

    /**All companies
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

    /**
     * Searches the company with ID
     * @param id of company to be given
     * @return company with the given ID
     */
    Company getOneById (Long id);
}
