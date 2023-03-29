package pol.edu.py.primerparcialbackend.utils;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public  class EMailService {

    public static void enviarMail(String destinatario, String asunto, String cuerpo) {
        new Thread(() -> {
            EMailService.enviarMailHilo(destinatario, asunto, cuerpo);
        }).start();
    }

    public static void enviarMailHilo(String destinatario, String asunto, String cuerpo) {

        // Configuración para el correo
        String remitente = "andresssshk@fpuna.edu.py";
        String clave = "5574180AH";
//        String destinatario = "correo_destino@dominio.com";
//        String asunto = "Correo de prueba";
//        String cuerpo = "<h1>Este es un correo de prueba</h1><p>¡Hola!</p>";

        // Configuración de propiedades
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        // Autenticación de cuenta
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, clave);
            }
        });

        try {
            // Creación del mensaje
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setContent(cuerpo, "text/html");
            message.setHeader("X-Priority", "1"); // Prioridad alta

            // Envío del mensaje
            Transport.send(message);

            System.out.println("Mensaje enviado correctamente");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
