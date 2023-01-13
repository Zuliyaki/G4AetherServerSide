/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author unaiz
 */
public class DiagnosisNotFoundException extends Exception {
       /**
     * Creates a new instance of <code>DailyNoteNotFoundException</code> without
     * detail message.
     */
    public DiagnosisNotFoundException() {
    }

    /**
     * Constructs an instance of <code>DailyNoteNotFoundException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public DiagnosisNotFoundException(String msg) {
        super(msg);
    }
}

