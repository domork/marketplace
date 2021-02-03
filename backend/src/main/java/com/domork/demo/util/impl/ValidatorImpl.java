package com.domork.demo.util.impl;

import com.domork.demo.entity.CompanyExtended;
import com.domork.demo.entity.Product;
import com.domork.demo.exception.ValidationException;
import com.domork.demo.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Pattern;

@Service
public class ValidatorImpl implements Validator {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final static Pattern namePattern = Pattern.compile("[a-zA-Z- ]*");
    private final static Pattern textPattern = Pattern.compile("[a-zA-Z0-9-.,:/ ]*");

    public ValidatorImpl() {
    }


    @Override
    public boolean nameText(String s) {
        if (textPattern.matcher(s).matches()) {
            if (s.length() >= 1 && s.length() <= 63) {
                return true;
            } else throw new ValidationException("name length needs to be between 1 and 63 ");
        } else throw new ValidationException("name contains only letters from a to z or from A to Z, " +
                "as well as ',', '.', ':', '/', ' '");
    }

    @Override
    public boolean checkCompanyNameOnNullValues(CompanyExtended company) {
        if (company.getName() != null)
            return true;
        else throw new ValidationException("Name of company is null!");
    }

    @Override
    public boolean idCheck(Long ID) {
        if (ID == null)
            throw new ValidationException("ID is null. Type in the real value!");
        else if (ID < 0 || ID > Long.MAX_VALUE - 1)
            throw new ValidationException("ID is must be positive or not that big!");
        else return true;

    }

    @Override
    public boolean checkProduct(Product product) {
        if (product.getName() == null)
            throw new ValidationException("Name was not given");
        nameText(product.getName());

        if (product.getDescription() != null)
            nameText(product.getDescription());
        if (product.getCategory() != null)
            nameText(product.getCategory());

       /* Currency money = product.getPrice();
        if (money != null){
        }
        */
        BigDecimal price = product.getPrice();
        if (price!=null&& price.compareTo(BigDecimal.ZERO) < 0)
            throw new ValidationException("price must be 0 or positive");
        if (product.getQuantity() < 0)
            throw new ValidationException("Quantity cannot be negative!");
        String condition = product.getCondition();
        if (condition != null) {
            if (!(condition.equals("new") ||
                    condition.equals("like new") ||
                    condition.equals("used") ||
                    condition.equals("acceptable")))
                throw new ValidationException("Quality should be the right typ");
        }
        return true;
    }

    @Override
    public boolean checkCompany(CompanyExtended companyExtended) {
        checkCompanyNameOnNullValues(companyExtended);
        nameText(companyExtended.getName());

        if (companyExtended.getBasedIn() != null)
            nameText(companyExtended.getBasedIn());

        if (companyExtended.getDescription() != null)
            nameText(companyExtended.getDescription());

        if (companyExtended.getWebsite() != null)
            nameText(companyExtended.getWebsite());
        return true;
    }


}
