package Model;

/**
 * Created by ppnperera on 7/16/2016.
 */

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class MailServer {

    public static void sendMail(String[] to, String from, String subject, String body, Session session){

        try {
            Message message = new MimeMessage(session);
            MimeMultipart content= new MimeMultipart("related");
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            message.setFrom(new InternetAddress(from));

            if (!to[0].equals("")) {
                for (String tos : to) {
                    message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(tos));
                }
            }else {
                System.out.println("No Recipient Emails were found");
                System.exit(1);
            }

            message.setSubject(subject);
            messageBodyPart = new MimeBodyPart();
            content.addBodyPart(messageBodyPart);
            message.setContent(content);
            Transport.send(message);
            System.out.println("Notification Mail Sent");

        } catch( javax.mail.SendFailedException  mx) {

            StringBuilder errorSB = null;

            if(mx.getInvalidAddresses() != null) {
                errorSB = new StringBuilder();
                for(Address email: mx.getInvalidAddresses()) {
                    errorSB.append(email.toString());
                    errorSB.append(", ");
                }
                System.out.println("Invalid Address Found: "+ errorSB);
            }

            if(mx.getValidSentAddresses() != null) {
                errorSB = new StringBuilder();
                for(Address email: mx.getValidSentAddresses()) {
                    errorSB.append(email.toString());
                    errorSB.append(", ");
                }
                System.out.println("Email sent to valid address: "+ errorSB);
            }

            if(mx.getValidUnsentAddresses() != null) {
                errorSB = new StringBuilder();
                for(Address email: mx.getValidUnsentAddresses()) {
                    errorSB.append(email.toString());
                    errorSB.append(", ");
                }
                System.out.println("Email not sent to valid address: "+ errorSB);
            }
            System.exit(1);
            System.out.println("Please enter recipient addresses each separated by \";\" ");

        } catch(javax.mail.MessagingException mx) {

            System.out.println(mx.getMessage());
            System.exit(1);

        } catch (Exception ex) {

            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }

    public static Session setSMTPConfig(String authUsername, String AuthPassword, String domain, String port) {

        final String username = authUsername;
        final String password = AuthPassword;

        Properties props = new Properties();


        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "false");
//            props.put("mail.smtp.ssl.trust", "mail.tempo.com");
        props.put("mail.smtp.host", domain);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.ssl.trust", domain);
        props.put("mail.smtp.sendpartial", "true");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        return session;
    }
}
