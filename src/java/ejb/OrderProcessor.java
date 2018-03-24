/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.WebOrder;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author esteb
 */
@Stateless
public class OrderProcessor {

    @PersistenceContext
    EntityManager em;

    public void saveOrder(@Observes WebOrder webOrder) {
        em.persist(webOrder);
    }
}
