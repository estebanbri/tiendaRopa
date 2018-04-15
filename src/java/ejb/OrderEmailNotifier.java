/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.WebOrder;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author esteban
 */
@Stateless
public class OrderEmailNotifier {

    @Resource() public  String host;
    @Resource() public  String port;
    @Resource() public  String mail;
    @Resource() public  String pass;
    
     public Session getSession(){
     Properties props = new Properties();
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.socketFactory.port", port);
    props.put("mail.smtp.socketFactory.class",
            "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", port); 
     props.put("mail.smtp.starttls.enable","true"); 
    props.put("mail.smtp.EnableSSL.enable","true");
    Session session = Session.getDefaultInstance(props,
        new javax.mail.Authenticator() {
                            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail,pass);
            }
        });

    return session;
    }   
    
    public void enviar(@Observes WebOrder webOrder){
 
        System.out.print("Creando email...");
          Message mensaje = new MimeMessage(getSession());
        try {
            mensaje.setSubject("Orden de Compra en Tienda Online");
            mensaje.setText(    "Datos de Orden " + 
                                webOrder.getOrderDate().toString() +" "+
                                webOrder.getProducts().toString());
            mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(webOrder.getCustomer().getEmail()));
            Transport.send(mensaje);
            System.out.print("Email Enviado...");
        } catch (MessagingException ex) {
            Logger.getLogger(OrderEmailNotifier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
