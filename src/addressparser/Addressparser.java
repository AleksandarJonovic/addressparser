/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addressparser;

import ExceptionPackage.InvalidInputException;

/**
 * The address parser sends and receives information about addresses and then
 * sends them to the parser. Also handles the splitting and formating of the
 * output.
 *
 * @author Alex, Anders
 */
public class Addressparser {

    /**
     * This main method is now outdated. The active main method as of 16-12-2014
     * is in AddressMatcher.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Addressparser program = new Addressparser();
        String addressInput = ""
                + "Rued Langgaards Vej 7. 5. sal København S"
                + "\n Rued Langgards vej 7. København 2300 5.sal"
                + "\nRued Langgaards Vej 7 2300 København S"
                + "\nRued Langgaards Vej 7, 5."
                + "\nRued Langgaards Vej 7A København S"
                + "\nRued Langgaards Vej i København";
        program.print(addressInput.split("\n"), program.parseAddresses(addressInput));
    }

    /**
     * Splits a string with addresses for each line-break there is. Returns an
     * array of strings which have the information for each address split in
     * usable information.
     * Is not used anymore as of 16-12-2014 since the user can only enter 1 address.
     *
     * @param addresses a string of addresses or a single address.
     * @return a string array with addresses.
     */
    public String[] parseAddresses(String addresses) {
        Parser parser = new Parser();

        String[] differentAdresses = addresses.split("\n");
        String[] resultSet = new String[differentAdresses.length];

        for (int i = 0; i < differentAdresses.length; i++) /* 1 */ {
            resultSet[i] = parseSingleAdress(differentAdresses[i]);
        }
        return resultSet;
    }

    /**
     * Takes a String and then split it into blocks of information about an
     * address and return a string with those blocks divided by an # char
     *
     * @param address a string which may contain information about an address.
     * @return a string with address information splittet by an # char
     */
    public String parseSingleAdress(String address) {
        Parser parser = new Parser();
        String[] addressSplitted;
        try {
            addressSplitted = parser.parseThis(address);
        } catch (InvalidInputException ex) {
            return ex.getMessageString() + " - cause: " + ex.getCauseString();
        }
        String finalString = "";
        for (int i = 0; i < addressSplitted.length; i++) /* 2 */ {
            // The regex finds the floornumber and the text after ("sal") for example
            // Therefore this is splitted so that only the number is in the output.
            if (addressSplitted[i] != null) /* 3 */ {
                if (i == 3) /* 4 */ {
                    finalString += addressSplitted[i].substring(0, 1);
                } else {
                    finalString += addressSplitted[i];
                }

            }
            if (i != addressSplitted.length - 1) /* 5 */ {
                finalString += "#";
            }
        }
        return finalString;
    }

    /**
     * Print out the Strings contained in the addressesInput and
     * addressesOutput, next to eachother so one can examine the results. Is not
     * used anymore
     *
     * @param addressesInput the String imput the program has handled
     * @param addressesOutput the string array the program has created.
     */
    private void print(String[] addressesInput, String[] addressesOutput) {
        for (int i = 0; i < addressesInput.length; i++) /* 6 */ {
            System.out.println("Input:  " + addressesInput[i] + "\nOutput: " + addressesOutput[i]);
            System.out.println("- - - - ");
        }
    }
}
