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
public class UserException extends Exception {

    /**
     * Creates a new instance of <code>UserException</code> without
     * detail message.
     */
    public UserException() {
    }

    /**
     * Constructs an instance of <code>UserException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UserException(String msg) {
        super(msg);
    }

}
