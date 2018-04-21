/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
/**
 *
 * @author esteban
 */
@Named(value = "checkoutBean")
@RequestScoped
public class CheckoutBean {
    
    

    /**
     * Creates a new instance of CheckoutBean
     */
    public CheckoutBean() {
    }
    
    public void generarReporte() throws JRException{
        String rutaReporte = "C:\\Users\\esteban\\Documents\\NetBeansProjects\\tiendaRopa\\reportes\\checkoutReport.jasper";
        String rutaReportePDF = "C:\\Users\\esteban\\Documents\\NetBeansProjects\\tiendaRopa\\reportes\\checkoutReport.pdf";
        JasperPrint jasperprint = JasperFillManager.fillReport(rutaReporte, null, new JREmptyDataSource());
        JasperExportManager.exportReportToPdfFile(jasperprint, rutaReportePDF);
    }
    
    
    
}
