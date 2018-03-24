/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Customer;
import entities.Product;
import entities.WebOrder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.event.Event;


/**
 *
 * @author esteb
 */
@Named(value = "basket")
@ConversationScoped
public class Basket implements Serializable {
    
    @Inject
    Conversation conversation;
    
    private List<Product> productsInBasket = new ArrayList();
    
    private Customer customer = new Customer();
    
    public Basket() {
    }
    
    public void addProduct(Product product) {
        if(conversation.isTransient()) {
            conversation.begin();
        }
        productsInBasket.add(product);
    }

    public List<Product> getProductsInBasket() {
        return productsInBasket;
    }
   
    public double getTotal() {
        double total = 0.0;
        for (Product product : productsInBasket) {
            total = total +  product.getPrice();
        }

        return total;
    }
    
    @Inject 
    Event<WebOrder> webOrderEvent;
    
    public void checkout(){
        webOrderEvent.fire(new WebOrder(new Date(), productsInBasket, customer));
        if(!conversation.isTransient()) {
            conversation.end();
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
}
