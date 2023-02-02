/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailRecovery;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
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

    static String sSalt = "Mensaje super secreto"; //      = ".....";
    private static byte[] salt = sSalt.getBytes();
    String user = null;
    String emailKey = null;

    /**
     *
     * @param emailUser
     */
    public String sendEmail(String emailUser) {
        final ResourceBundle bundle = ResourceBundle.getBundle("emailRecovery.emailCredentials");

        final String cyperuser = bundle.getString("CYPEREMAIL");
        final String cyperemailKey = bundle.getString("CYPEREMAILKEY");

        try {
            user = descifrarTexto("Clave", "user");
        } catch (IOException ex) {
            Logger.getLogger(AetherEmailRecovery.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            emailKey = descifrarTexto("Clave", "key");
        } catch (IOException ex) {
            Logger.getLogger(AetherEmailRecovery.class.getName()).log(Level.SEVERE, null, ex);
        }

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

    public String descifrarTexto(String clave, String mensajeCyper) throws IOException {
        String ret = null;
        byte[] fileContent = null;

        if (mensajeCyper.equals("user")) {

            InputStream is = getClass().getResourceAsStream("email.dat");
            fileContent = new byte[is.available()];
            is.read(fileContent, 0, is.available());
        } else {
            InputStream is = getClass().getResourceAsStream("key.dat");
            fileContent = new byte[is.available()];
            is.read(fileContent, 0, is.available());
        }

        // Fichero leído
        KeySpec keySpec = null;
        SecretKeyFactory secretKeyFactory = null;

        try {
            // Obtenemos el keySpec
            keySpec = new PBEKeySpec(clave.toCharArray(), salt, 65536, 128); // AES-128

            // Obtenemos una instancide de SecretKeyFactory con el algoritmo "PBKDF2WithHmacSHA1"
            secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            // Generamos la clave
            byte[] key = secretKeyFactory.generateSecret(keySpec).getEncoded();

            // Creamos un SecretKey usando la clave + salt
            SecretKey privateKey = new SecretKeySpec(key, 0, key.length, "AES");// AES;

            // Obtenemos una instancide de Cipher con el algoritmos que vamos a usar "AES/CBC/PKCS5Padding"
            // Iniciamos el Cipher en ENCRYPT_MODE y le pasamos la clave privada
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            // Leemos el fichero codificado 
            IvParameterSpec ivParam = new IvParameterSpec(Arrays.copyOfRange(fileContent, 0, 16));

            // Iniciamos el Cipher en ENCRYPT_MODE y le pasamos la clave privada y el ivParam
            cipher.init(Cipher.DECRYPT_MODE, privateKey, ivParam);
            // Le decimos que descifre
            byte[] decodedMessage = cipher.doFinal(Arrays.copyOfRange(fileContent, 16, fileContent.length));

            // Texto descifrado
            ret = new String(decodedMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    private static byte[] fileReader(String path) {
        byte ret[] = null;
        File file = new File(path);
        try {
            ret = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

}
