package com.domork.demo.service.impl;

import com.domork.demo.entity.Company;
import com.domork.demo.persistance.CompanyDAO;
import com.domork.demo.service.CompanyService;
import com.domork.demo.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final CompanyDAO companyDAO;
    private final Validator validator;

    @Autowired
    public CompanyServiceImpl(CompanyDAO companyDAO, Validator validator) {
        this.companyDAO = companyDAO;
        this.validator = validator;
    }

    @Override
    public Company getOneById(Long id) {
        LOGGER.trace("getOneById({})",id);
        validator.idCheck(id);
        return companyDAO.getOneById(id);
    }

    @Override
    public Company putNewCompany(Company company) {
        LOGGER.trace("putNewCompany({})",company);
        validator.checkCompanyOnNullValues(company);
        validator.nameText(company.getName());
        return companyDAO.putNewCompany(company);
    }

    @Override
    public void deleteById(Long id) {
        LOGGER.trace("deleteById({})", id);
        validator.idCheck(id);

        //check, if company with given ID exists
        companyDAO.getOneById(id);

        companyDAO.deleteById(id);
    }

    @Override
    public Company getCompanyByName(String name) {
        return companyDAO.getCompanyByName(name);
    }

    @Override
    public List<Company> getAllCompaniesWithGivenName(String name) {
        if (!name.equals(""))
        validator.nameText(name);
        return companyDAO.getAllCompaniesWithGivenName(name);
    }
}
