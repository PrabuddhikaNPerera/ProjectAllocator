import javax.mail.Session;

/**
 * Created by DHPLAKMAL on 11/2/2015.
 */
public class MailMain {
    public static void main(String[] args) {
        String to = args[0];
        String[] tos = to.split(";");

        String from = args[1];
        String subject = args[2];
        String body = args[3];
        String status = args[4].trim();
        String authUsername = args[5];
        String AuthPassword = args[6];
        String domain = args[7];
        String port = args[8];


        Session session = MailServer.setSMTPConfig(authUsername, AuthPassword, domain, port);

        MailServer.sendMail(tos, from, subject, body, status, session);
    }
}
