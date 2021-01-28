package com.domork.demo.util;

import com.domork.demo.entity.Company;

public interface Validator {

    /**
     * Checks, if the text contain only allowed
     * characters, such as [a-zA-Z] AND has at least 1 char.
     * Maximum length of text is 63 chars.
     *
     * @param s is the String to check
     * @return true if the s has no restricted chars.
     */
    boolean nameText(String s);

    /**
     * Checks, if the company object contains null values,
     * which must not be null (e.x. name).
     *
     * @param company to be checked
     * @return true, if name of company is not null.
     */
    boolean checkCompanyOnNullValues(Company company);
}
