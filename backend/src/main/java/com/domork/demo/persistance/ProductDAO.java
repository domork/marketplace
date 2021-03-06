package com.domork.demo.persistance;

import com.domork.demo.entity.Product;
import com.domork.demo.exception.NotFoundException;
import java.util.List;

public interface ProductDAO {
    /**
     * Adds a new product. ID will be ignored.
     *
     * product.name must not be null -> It is not obligatory
     * to have other product params.
     *
     * price will be truncated and
     * have only 2 positions on the right:
     *    *,..
     * E.x. 313,5973 -> 313,59
     *
     *
     * @param product to be added
     * @return product with generated ID
     */
    Product addNewProduct (Product product);

    /**
     * Finds the ascending ordered list of products,
     * which start with follow name:
     *
     * E.x follow values are saved in DB:
     *  p, p2, p1, p1a
     * Calling getProductByName ("p") will
     * return this List:
     * (showing the order, not the list implementation)
     * {p,p1,p1a,p2}
     *
     * @param name of product(s) to be found
     * @return the list of name-filtered products
     */
    List<Product> getProductsByName (String name);

    /**
     * Finds the product with the given ID
     * @param ID of product to be found
     * @return the product with given ID
     * @throws NotFoundException when no product with
     * given ID exists
     */
    Product getProductById (Long ID);

    /**
     * Deletes product with the given ID
     * @param ID of product to be deleted
     */
    void deleteProductById (Long ID);

    /**
     * Finds only one specific product with such a name
     * @param name of product to be found
     * @return the product with the name in param
     * @throws NotFoundException when no product with
     * given ID exists
     */
    Product getOneProductByName(String name);

    /**
     * Updates all product values except of ID
     * @param product contains these non-null values,
     *                which will be updated
     *                for the given ID
     * @return the product with updated values
     */
    Product updateProduct (Product product);
}
