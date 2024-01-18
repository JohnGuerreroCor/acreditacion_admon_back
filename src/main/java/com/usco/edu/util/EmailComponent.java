package com.usco.edu.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.usco.edu.dto.Email;

@Component
public class EmailComponent {
    
    Logger logger = LoggerFactory.getLogger(EmailComponent.class);
    
    // MÉTODO PARA ENVIAR CORREOS ELECTRÓNICOS
    public void enviar(Email email) {
        this.enviar(email, false);
    }
    
    // MÉTODO SOBRECARGADO QUE PERMITE ESPECIFICAR SI SE DEBE LANZAR UNA EXCEPCIÓN EN CASO DE ERROR
    public void enviar(Email email, boolean lanzarError) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(email.getDestinatario());
            messageHelper.setSubject(email.getAsunto());
            
            // CONSTRUIR EL CONTENIDO DEL CORREO A PARTIR DE UNA PLANTILLA Y LOS DATOS PROPORCIONADOS
            String mje = this.build(email);
            messageHelper.setText(mje, true);
        };

        try {
            JavaMailSender emailSender = this.crearJavaMailSender();
            emailSender.send(messagePreparator);
        } catch (MailException e) {
            logger.error(email.toString());
            e.printStackTrace();
            
            if (lanzarError) {
                throw e;
            }
        }
    }
    
    // MÉTODO PARA CONSTRUIR EL CONTENIDO DEL CORREO A PARTIR DE UNA PLANTILLA Y DATOS PROPORCIONADOS
    private String build(Email email) throws Exception {
        String plantillaCorreo = this.obtenerTextoDeArchivo(new ClassPathResource("static/plantilla_email/notificacion_programa.html").getFile());
        
        // REEMPLAZAR MARCADORES DE POSICIÓN EN LA PLANTILLA CON DATOS ESPECÍFICOS DEL CORREO
        plantillaCorreo = plantillaCorreo.replaceAll(":nombrePersona", email.getNombreDestinatario());
        plantillaCorreo = plantillaCorreo.replaceAll(":ruta", email.getHash());
        plantillaCorreo = plantillaCorreo.replaceAll(":textoUno", email.getTextoUno());
        plantillaCorreo = plantillaCorreo.replaceAll(":textoDos", email.getTextoDos());
        plantillaCorreo = plantillaCorreo.replaceAll(":confidencial", email.getConfidencial());
        plantillaCorreo = plantillaCorreo.replaceAll(":titulo", email.getTitulo());
        plantillaCorreo = plantillaCorreo.replaceAll(":textoTres", email.getTextoTres());
        plantillaCorreo = plantillaCorreo.replaceAll(":footer", email.getFooter());
        plantillaCorreo = plantillaCorreo.replaceAll(":programa", email.getPrograma());
        
        return plantillaCorreo;
    }
    
    // MÉTODO PARA OBTENER EL CONTENIDO DE UN ARCHIVO DE TEXTO
    private String obtenerTextoDeArchivo(File file) {
        FileReader fr = null;
        BufferedReader br = null;
        String contenido = "";
        try {
            fr = new FileReader(file.getPath());
            br = new BufferedReader(fr);
            String linea = "";
            while ((linea = br.readLine()) != null) {
                contenido += linea;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return contenido;
    }
    
    // MÉTODO PARA CREAR Y CONFIGURAR UN OBJETO JAVAMAILSENDER PARA EL ENVÍO DE CORREOS
    private JavaMailSender crearJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        UserPass email = this.getEmailRandom();
        
        // CONFIGURACIÓN PARA EL SERVIDOR DE CORREO (EN ESTE CASO, GMAIL)
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(email.user);
        mailSender.setPassword(email.pass);
        mailSender.setDefaultEncoding("UTF-8");
        
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mailSender.setJavaMailProperties(javaMailProperties);
        
        return mailSender;
    }
    
    // MÉTODO PARA SELECCIONAR UN CORREO DE FORMA ALEATORIA
    private UserPass getEmailRandom() {
        UserPass emails[] = { 
                new UserPass("CORREO", "CLAVE"),
                new UserPass("CORREO", "CLAVE"),
                new UserPass("CORREO", "CLAVE"),
                new UserPass("CORREO", "CLAVE"),
                new UserPass("CORREO", "CLAVE"),
        };
        
        Random random = new Random();
        int n = random.nextInt(emails.length);
        
        UserPass email = emails[n];
        
        return email;
    }
    
    // CLASE INTERNA PARA REPRESENTAR UN USUARIO Y CONTRASEÑA DE CORREO
    private class UserPass {
        String user;
        String pass;
        
        public UserPass(String user, String pass) {
            this.user = user;
            this.pass = pass;
        }
    }
}
