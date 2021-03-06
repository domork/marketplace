package com.domork.demo.service;

import com.domork.demo.enpoint.dto.CompanyExtendedDto;
import com.domork.demo.entity.Company;
import com.domork.demo.entity.CompanyExtended;
import com.domork.demo.entity.Product;
import com.domork.demo.exception.ValidationException;
import com.domork.demo.persistance.CompanyDAO;

import java.util.List;

public interface CompanyService {

    /**
     * Searches the company with ID
     * @param id of company to be given.
     * @return company with the given ID
     * @throws ValidationException when ID doesn't
     * fill the requirements (check them in the Validator)
     */
    CompanyExtended getOneById (Long id);

    /**
     * The new company is to be added. The ID
     * (positive Long number) will be generated in DB.
     * @param company should only contain the name.
     *                The ID will be ignored.
     * @return the company name and ID, which is stored
     *                in DB.
     * @throws ValidationException when company doesn't
     * fill the requirements (check them in the Validator)
     */
    CompanyExtended putNewCompany(CompanyExtended company);

    /**
     * Removes from DB the company with given ID.
     * All tables, where this company was included,
     * will delete their expressions as well
     * @param id of company to be removed
     * @throws ValidationException when ID doesn't
     * fill the requirements (check them in the Validator)
     */
    void deleteById (Long id);

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
     * @throws ValidationException when name doesn't
     * fill the requirements (check them in the Validator)
     */
    Company getCompanyByName(String name);

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
     * @throws ValidationException when name doesn't
     * fill the requirements (check them in the Validator)
     */
    List<CompanyExtended> getAllCompaniesWithGivenName(String name);

    /**
     * Overwrites the company with the given ID inside of param
     * to the values the given param.
     * @param company with all values to be changed
     * @return the company with changed values
     * @throws ValidationException when company doesn't
     * fill the requirements (check them in the Validator)
     */
    CompanyExtended updateCompany(CompanyExtended company);

}
