/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pqrs;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author ANCIZAR
 */
public class Correos {
  public static void enviarCorreo(String respuesta) {

        //credenciales del correo que va a enviar las respuestas
        final String correoRemitente = "75035e001@smtp-brevo.com";
        final String contraseniaCorreo = "43nzUC7RXD6PvFcZ";

        //contenido del correo a enviar
        final String asuntoCorreo = "SU PQRS HA SIDO RESPONDIDO POR MEDINUCLEAR";

        //definicion de las propiedades del servidor SMTP 
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp-relay.brevo.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        //crear la sesion del servidor SMTP con el correo del remitente
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(correoRemitente, contraseniaCorreo);
            }
        });

        try {
            //crea el objeto con la sesion del servidor SMTP 
            Message message = new MimeMessage(session);
            
            //asigna el correo del remitente 
            message.setFrom(new InternetAddress(correoRemitente));

            //asigna el correo del receptor
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("isalop887@gmail.com"));

            // asigna el asunto del correo
            message.setSubject(asuntoCorreo);

            //asigna la respuesta que llega como parametro de la ventana modal que responde la PQR
            message.setText(respuesta);

            // envia el correo
            Transport.send(message);

            System.out.println("Correo enviado correctamente.");

        } catch (MessagingException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }  
}
