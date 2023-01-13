/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Leire
 */
public class MentalDiseaseException extends Exception {

    /**
     * Creates a new instance of <code>MentalDiseaseException</code> without
     * detail message.
     */
    public MentalDiseaseException() {
    }

    /**
     * Constructs an instance of <code>MentalDiseaseException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public MentalDiseaseException(String msg) {
        super(msg);
    }

}
