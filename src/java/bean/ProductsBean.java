/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import ejb.ProductDao;
import entities.Product;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author esteb
 */
@Named(value = "productsBean")
@RequestScoped
public class ProductsBean {
    
    List<Product> products;
    
    @Inject
    ProductDao productDao;
    
    private boolean order = true;
    private String filter;
   
    public List<Product> getProducts() {
        
        if(products == null){
            products = productDao.listProducts(filter, order);
        }
        
       return products;
   }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
        products = null;
    }

    public boolean isOrder() {
        return order;
    }

    public void setOrder(boolean order) {
        this.order = order;
    }


    
}
