/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import ejb.ProductDao;
import entities.Product;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author esteb
 */

@Path("products")
public class ProductResource {

    @Inject
    ProductDao productDao;

    @GET
    @Produces("application/json")
   public List<Product> getProducts() {
       return productDao.listProducts();
   }


}
