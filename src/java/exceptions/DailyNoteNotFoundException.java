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
public class DailyNoteNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>DailyNoteNotFoundException</code> without
     * detail message.
     */
    public DailyNoteNotFoundException() {
    }

    /**
     * Constructs an instance of <code>DailyNoteNotFoundException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public DailyNoteNotFoundException(String msg) {
        super(msg);
    }
}
