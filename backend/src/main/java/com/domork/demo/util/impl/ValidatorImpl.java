package com.domork.demo.util.impl;

import com.domork.demo.entity.Company;
import com.domork.demo.exception.ValidationException;
import com.domork.demo.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.regex.Pattern;

@Service
public class ValidatorImpl implements Validator {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final static Pattern namePattern = Pattern.compile("[a-zA-Z- ]*");
    private final static Pattern textPattern = Pattern.compile("[a-zA-Z0-9-., ]*");

    public ValidatorImpl() {
    }


    @Override
    public boolean nameText(String s) {
        if (namePattern.matcher(s).matches()) {
            if (s.length() >= 1 && s.length() <= 63) {
                return true;
            } else throw new ValidationException("name length needs to be between 1 and 63 ");
        } else throw new ValidationException("name contains only letters from a to z or from A to Z");
    }

    @Override
    public boolean checkCompanyOnNullValues(Company company) {
        if (company.getName() != null)
            return true;
        else throw new ValidationException("Name of company is null!");
    }

    @Override
    public boolean idCheck(Long ID) {
        if (ID == null)
            throw new ValidationException("ID is null. Type in the real value!");
        else if (ID < 0||ID> Long.MAX_VALUE-1)
            throw new ValidationException("ID is must be positive or that big!");
        else return true;

    }


}
