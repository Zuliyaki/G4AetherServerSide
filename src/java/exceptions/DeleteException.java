/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Usuario
 */
public class DeleteException extends Exception {

    /**
     * Creates a new instance of <code>DeleteException</code> without detail
     * message.
     */
    public DeleteException() {
    }

    /**
     * Constructs an instance of <code>DeleteException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public DeleteException(String msg) {
        super(msg);
    }
}
