/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashPassword;

import java.io.File;
import java.nio.file.Files;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import static javax.crypto.Cipher.ENCRYPT_MODE;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author 2dam
 */
public class PruebasCifrado {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String hasheado = hashearTexto(new String("Hola mundo!").getBytes());
        System.out.println("\n\nhasheado  -  " + hasheado);
        System.out.println("\n\ndesHexadecimal  -  " + desHexadecimal(hasheado));

    }

    public static String hashearTexto(byte[] texto) {
        MessageDigest messageDigest;
        String encriptacion = "SHA";
        String mensaje = null;

        try {
            // Obtén una instancia de MessageDigest que usa SHA
            messageDigest = MessageDigest.getInstance(encriptacion);
            // Actualiza el MessageDigest con el array de bytes             
            messageDigest.update(texto);
            // Calcula el resumen (función digest)
            byte[] digest = messageDigest.digest();
            System.out.println("\n\ndigest  -  " + digest);
            mensaje = hexadecimal(digest);
            System.out.println("\n\nmensaje  -  " + mensaje);
        } catch (NoSuchAlgorithmException e) {
            Logger.getLogger(PruebasCifrado.class.getName()).log(Level.SEVERE, null, e);
        }
        return mensaje;
    }

    // Convierte Array de Bytes en hexadecimal
    static String hexadecimal(byte[] resumen) {

        String hexString = DatatypeConverter.printHexBinary(resumen);
        return hexString;
    }

    public static String desHexadecimal(String texto) {
        byte[] bytes = DatatypeConverter.parseHexBinary(texto);
        String result = new String(bytes);

        return result;
    }

    public static byte[] cifrarTexto(PublicKey clave, String mensaje) {

        Cipher cipher = null;
        String mensajeCifrado = null;
        byte[] encodedMessage = null;
        try {
            // Obtenemos una instancide de Cipher con el algoritmos que vamos a usar "RSA/ECB/PKCS1Padding"
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

            // Iniciamos el Cipher en ENCRYPT_MODE y le pasamos la clave privada
            cipher.init(ENCRYPT_MODE, clave);
            // Le decimos que cifre (método doFinal())
            encodedMessage = cipher.doFinal(mensaje.getBytes());

        } catch (Exception e) {
            Logger.getLogger(PruebasCifrado.class.getName()).log(Level.SEVERE, null, e);
        }
        return encodedMessage;
    }

    private static byte[] leerFichero(String path) {
        byte[] ret = null;

        File file = new File(path);

        try {
            ret = Files.readAllBytes(file.toPath());

        } catch (IOException ex) {
            Logger.getLogger(PruebasCifrado.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    private static void escribirFichero(String privadadat, byte[] privateKey) {
        try (FileOutputStream fos = new FileOutputStream(privadadat)) {
            fos.write(privateKey);
        } catch (IOException e) {
            Logger.getLogger(PruebasCifrado.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    private static void generarClaves() {
        try {

            byte fileKey[] = leerFichero("Public.key");
            //Generamos una instancia de KeyFactory para el algoritmo RSA
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.genKeyPair();

            byte[] privateKey = keyPair.getPrivate().getEncoded();
            escribirFichero("Privada.dat", privateKey);
            byte[] publicKey = keyPair.getPublic().getEncoded();
            escribirFichero("Publica.dat", publicKey);

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PruebasCifrado.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
