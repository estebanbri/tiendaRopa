/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import entities.WebOrder;
import javax.enterprise.event.Observes;

/**
 *
 * @author esteb
 */
public class OrderSMSNotifier {
    public void sendSomething(@Observes WebOrder webOrder){
        System.out.println("Datos de Orden " + 
                webOrder.getOrderDate() +" "+
                webOrder.getCustomer().getName()+" "+  
                webOrder.getProducts());
    }
}
