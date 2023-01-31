/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author unaib
 */
public class PatientException extends Exception {

    /**
     * Creates a new instance of <code>PatientException</code> without detail
     * message.
     */
    public PatientException() {
    }

    /**
     * Constructs an instance of <code>PatientException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PatientException(String msg) {
        super(msg);
    }
}
