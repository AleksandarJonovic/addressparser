package addressparser;

import ExceptionPackage.InvalidInputException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Christian on 12/02/14.
 */
public class AddressMatcher {


  
    public AddressMatcher() throws FileNotFoundException {

    }


    /**
     * The active main method of the program. Waits for the user to input some
     * text and prints the address information in the correct format. If
     * something went wrong, the exception information will be printed instead.
     *
     * @param args
     * @throws InvalidInputException
     */
    public static void main(String[] args) throws InvalidInputException {
        Parser p = new Parser();
        p.parseStreetAddress("Brøndby Nord Vej");
    }
}
