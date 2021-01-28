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
    private Validator validator;

    @Autowired
    public CompanyServiceImpl(CompanyDAO companyDAO, Validator validator) {
        this.companyDAO = companyDAO;
        this.validator = validator;
    }

    @Override
    public Company getOneById(Long id) {
        LOGGER.trace("getOneById({})",id);
        return companyDAO.getOneById(id);
    }

    @Override
    public List<Company> getAllCompanies() {
        LOGGER.trace("GetAllCompanies");
        return companyDAO.getAllCompanies();
    }

    @Override
    public Company putNewCompany(Company company) {
        validator.checkCompanyOnNullValues(company);
        validator.nameText(company.getName());
        return companyDAO.putNewCompany(company);
    }
}
