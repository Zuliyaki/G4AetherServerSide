/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailRecovery;

import java.security.SecureRandom;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author unaib
 */
public class AetherEmailRecovery {

    /**
     *
     * @param emailUser
     */
    public static String sendEmail(String emailUser) {
        final ResourceBundle bundle = ResourceBundle.getBundle("emailRecovery.emailCredentials");

        final String user = bundle.getString("EMAIL");
        final String emailKey = bundle.getString("EMAILKEY");

        final String receiver = emailUser;
        final String newPassword = generateRandomPassword(Integer.parseInt(bundle.getString("PASSWORDCHARACTERS")));

        final String emailBody
                = "<body style=\"font-size:16px;padding-top:80px;color:rgb(51,51,51);\">"
                + "<table style=\"width:100%;max-width:600px;background-color:#c5e5e5;font-size:3px;line-height:3px\" align=\"center\"><tr><td></td></tr></table><br/>"
                + "<table style=\"width:100%;max-width:600px\" align=\"center\"><tr><td><br/>"
                + "<b style=\"font-size: 20px;\">Aether</b><br/>"
                + "<br/>"
                + "Recently, we received a request to reset the account password associated with this email address. "
                + "We understand from time to time passwords are lost or forgotten and we are here to assist you.<br/>"
                + "<br/>"
                + "We recommend that you update your password as soon as possible. When choosing a new password, we suggest you increase your account security by selecting a unique password that you have not used on other websites. "
                + "Strong passwords contain more than eight characters and include small and capital letters, special characters and numbers.<br/>"
                + "<br/>"
                + "Your new generated login password is: <i><b>" + newPassword + "</b></i><br/>"
                + "<br/>"
                + "<br/>"
                + "<b>ADDITIONAL ASSISTANCE</b><br/>"
                + "<br/>"
                + "If you did not request a password reset, please notify Customer Support.<br/>"
                + "<br/>"
                + "Thank you for choosing Aether.<br/>"
                + "<br/>"
                + "<br/>"
                + "<br/>"
                + "<b>NOTE</b>: <i>Do not respond to this email. It does not accept incoming emails!</i>"
                + "</td></tr></table><br/>"
                + "<br/>"
                + "<br/>"
                + "<br/>"
                + "<table style=\"width:100%;max-width:600px;background-color: #e8e9ec;font-size:3px;line-height:3px\" align=\"center\"><tr><td></td></tr></table><br/>"
                + "<footer style=\"font-size:12px;line-height:150%;margin:0;text-align:center;color:rgb(51,51,51);\">This email was sent by:<br/>"
                + "<b>Aether Customer Service</b><br/>"
                + "Calle Fanderia, 48901<br/>"
                + "Barakaldo, Bizkaia, Spain</footer><br/>"
                + "</body>";

        Properties properties = new Properties();
        properties.put(
                "mail.smtp.auth", true);
        properties.put(
                "mail.smtp.starttls.enable", "true");
        properties.put(
                "mail.smtp.host", "smtp.gmail.com");
        properties.put(
                "mail.smtp.port", "587");
        properties.put(
                "mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put(
                "mail.smtp.partialfetch", false);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, emailKey);
            }
        });

        Message message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(user));
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
            message.setSubject("Password recovery");

            Multipart multipart = new MimeMultipart();
            MimeBodyPart mimebodyPart = new MimeBodyPart();
            mimebodyPart.setContent(emailBody, "text/html");
            multipart.addBodyPart(mimebodyPart);

            message.setContent(multipart);

            Transport t = session.getTransport("smtp");
            t.connect((String) properties.get(user), emailKey);
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        } catch (AddressException ex) {
            Logger.getLogger(AetherEmailRecovery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(AetherEmailRecovery.class.getName()).log(Level.SEVERE, null, ex);
        }

        return newPassword;
    }

    private static String generateRandomPassword(int len) {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        return sb.toString();
    }

}
