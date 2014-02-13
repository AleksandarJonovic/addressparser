/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExceptionPackage;

/**
 *
 * @author Anders
 */
public class InvalidInputException extends Exception {

    String cause;
    /**
     * Creates a new instance of <code>InvalidInputException</code> without
     * detail message.
     */
    public InvalidInputException()
    {
        cause = "Cause not specified";
    }

    public String getCauseString()
    {
        return cause;
    }
    
    public String getMessageString()
    {
        return "Invalid address input";
    }
    
    /**
     * Constructs an instance of <code>InvalidInputException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidInputException(String msg)
    {
        super(msg);
        cause = msg;
    }
}
