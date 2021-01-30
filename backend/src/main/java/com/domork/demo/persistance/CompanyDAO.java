package com.domork.demo.persistance;

import com.domork.demo.entity.Company;

import java.util.List;

public interface CompanyDAO {

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

    /**
     * Removes from DB the company with given ID.
     * All tables, where this company was included,
     * will delete their expressions as well
     * @param id of company to be removed
     */
    void deleteById (Long id);

    /**
     * Finds all companies, which start with
     * the follow name.
     * E.x. There are follow
     * companies:
     * "AMA", "AAM", "AMWQL".
     * The param name is "AM" ->
     * "AMA" and "AMWQL" will be returned
     * @param name of companies to be find
     * @return the list of companies with given name
     */
    List<Company> getAllCompaniesWithGivenName(String name);


    /**
     * Finds the company, which has exactly
     * the follow name.
     * E.x. There are follow
     * companies:
     * "AM", "AMD", "ABC".
     * The param name is "AM" ->
     * "AMA" will be returned
     * @param name of company to be find
     * @return the company with given name
     */
    Company getCompanyByName(String name);
}
