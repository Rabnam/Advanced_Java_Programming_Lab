package SwingEmailSender;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;


public class EmailService {

    // SMTP server configuration
    private final String smtpHost = "smtp.gmail.com";
    private final String smtpPort = "587";
    private final String smtpUser = "m9800564411@gmail.com"; // Replace with your email
    private final String smtpPassword = "sivy hlef vcrw xazs"; // Replace with your password or app password

    public boolean sendEmail(Email email) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", smtpHost);
            props.put("mail.smtp.port", smtpPort);

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(smtpUser, smtpPassword);
                }
            });

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(email.getSender()));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getReceiver()));
            msg.setSubject(email.getSubject());
            msg.setText(email.getMessage());

            Transport.send(msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
