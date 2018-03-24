/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author esteb
 */
@Stateless
public class ProductDao {

    @PersistenceContext
    EntityManager em;
    
    public List<Product> listProducts(String filter, boolean asc){
	CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> product = query.from(Product.class);
        query.select(product);
        
        //esta es la parte que le aplicamos los parametros a la query usando Criteria
        if (asc) {
            query.orderBy(cb.asc(product.get("price")));
        } else {
            query.orderBy(cb.desc(product.get("price")));
        }
        
        //Si mi filtro no es null, voy a agregar mi filtro
        if(filter != null){
            query.where(cb.like(product.<String>get("name"), filter + "%"));
        }

        
        return em.createQuery(query).getResultList();
    }
    
    public List<Product> listProducts(){
        return listProducts(null, true);
    }

    public Product findProduct(long pid) {
        return em.find(Product.class, pid); 
    }

}
