/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Product;
import entities.WebOrder;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
/**
 *
 * @author esteban
 */
@Named(value = "checkoutBean")
@RequestScoped
public class CheckoutBean {
    
    @Inject
    Basket basket;

    /**
     * Creates a new instance of CheckoutBean
     */
    public CheckoutBean() {
    }
    
    public void generarReporte() throws JRException{
        Map<String,Object> parametro = new HashMap();
        Collection<Product> listaProduct = basket.getProductsInBasket();
        
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listaProduct);
        
        parametro.put("tituloParam", "Resumen de la compra");
        parametro.put("emailParam", basket.getCustomer().getEmail());
        parametro.put("totalParam", basket.getTotal());
        
        String rutaReporteJRXML = "C:\\Users\\esteban\\Documents\\NetBeansProjects\\tiendaRopa\\reportes\\checkoutReport";
        String rutaReportePDF = "C:\\Users\\esteban\\Documents\\NetBeansProjects\\tiendaRopa\\reportes\\checkoutReport.pdf";
        JasperReport report = JasperCompileManager.compileReport(rutaReporteJRXML.concat(".jrxml"));
        JasperPrint jasperprint = JasperFillManager.fillReport(report, parametro, beanColDataSource);
        JasperExportManager.exportReportToPdfFile(jasperprint, rutaReportePDF);
    }
    
    
    
}
