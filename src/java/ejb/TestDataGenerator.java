/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Product;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author esteb
 */
@Singleton
@Startup
public class TestDataGenerator{

    @PersistenceContext
    EntityManager em;

    
    @PostConstruct
    public void setupTestData(){
        em.persist(new Product("Pantalones", 10.53));
        em.persist(new Product("Zapatillas", 20.73));
        em.persist(new Product("Campera", 23.67));
        em.persist(new Product("Short", 26.31));
        em.persist(new Product("Remera", 77.83));
    }
}

