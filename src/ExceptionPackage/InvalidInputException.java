/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExceptionPackage;

import java.util.ArrayList;

/**
 *
 * @author Anders
 */
public class InvalidInputException extends Exception {

    String cause;
    ArrayList<String> possibleStreetNames = new ArrayList<>();
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
    
    public void addPossibleStreetName(String possibleStreetName)
    {
        possibleStreetNames.add(possibleStreetName);
    }
    
    public void printPossibleStreetName()
    {
        if (possibleStreetNames.isEmpty())
        {
            System.out.println("No street matches");
            return;
        }
        System.out.println("No precise street was found, did you mean one of the following?");
        for(String possibleStreetName : possibleStreetNames)
        {
            System.out.println(possibleStreetName);
        }
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
