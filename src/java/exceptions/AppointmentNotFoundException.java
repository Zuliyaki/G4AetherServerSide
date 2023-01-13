/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Janam
 */
public class AppointmentNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>AppointmentNotFoundException</code> without
     * detail message.
     */
    public AppointmentNotFoundException() {
    }

    /**
     * Constructs an instance of <code>AppointmentNotFoundException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public AppointmentNotFoundException(String msg) {
        super(msg);
    }
}
