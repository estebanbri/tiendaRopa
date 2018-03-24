/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import ejb.ProductDao;
import entities.Product;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author esteb
 */
@Named(value = "productDetailsBean")
@RequestScoped
public class ProductDetailsBean {
    
    private Product product;
    private long pid;
    @Inject
    ProductDao productDao;

    public ProductDetailsBean() {
    }
    
    public void loadProduct(){
        product = productDao.findProduct(pid);
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
