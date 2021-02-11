package com.domork.demo.util;

import com.domork.demo.entity.Company;
import com.domork.demo.entity.CompanyExtended;
import com.domork.demo.entity.Product;

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
     * Checks, if the ID is not null, bigger than 0
     * and smaller than MAXVALUE.
     * @param ID
     * @return
     */
    boolean idCheck(Long ID);

    /**
     * Checks if product has follow requirements:
     * (Except name it is possible to contain null values)
     *
     * - name, category,description has only legit characters
     * - price not negative and not too high,
     * - quantity must be positive natural number (starting with 0)
     * - condition must be one of the follow words:
     *      'new','like new','used','acceptable'
     *
     * @param product to be checked
     * @return true, if all requirements are not violated.
     */
    boolean checkProduct(Product product);

    /**
     * Checks if product has follow requirements:
     * (Except name it is possible to contain null values)
     *
     * - name, website, basedIn, description has only legit characters
     * @param companyExtended to be inspected
     * @return true, if all requirements are not violated.
     */
    boolean checkCompany (CompanyExtended companyExtended);
}
