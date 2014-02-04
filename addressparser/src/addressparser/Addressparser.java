/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addressparser;

import ExceptionPackage.InvalidInputException;

/**
 *
 * @author Alex
 */
public class Addressparser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
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
     * Splits a string with adresses for each linebreak there is. Returns an
     * array of strings which have the information for each address split in
     * usable information.
     *
     * @param addresses a string of addresses or a single address.
     * @return a string array with addresses.
     */
    public String[] parseAddresses(String addresses)
    {
        Parser parser = new Parser();

        String[] differentAdresses = addresses.split("\n");
        String[] resultSet = new String[differentAdresses.length];

        for (int i = 0; i < differentAdresses.length; i++)      /* 1 */
        {
            resultSet[i] = parseSingleAdress(differentAdresses[i]);
        }
        return resultSet;
    }

    /**
     * Takes a String and then split it into blocks of information about an
     * adress and return a string with those blocks divided by an # char
     *
     * @param address a string which may contain information about an address.
     * @return a string with address information splittet by an # char
     */
    private String parseSingleAdress(String address)
    {
        Parser parser = new Parser();
        String[] addressSplitted;
        try
        {
            addressSplitted = parser.parseThis(address);
        } catch (InvalidInputException ex)
        {
            return ex.getMessageString() + " - cause: " + ex.getCauseString();
        };
        String finalString = "";
        for (int i = 0; i < addressSplitted.length; i++)          /* 2 */

        {
            //System.out.println(addressSplitted[i]);
            if (addressSplitted[i] != null)                       /* 3 */

            {
                if (i == 3)                                       /* 4 */

                {
                    finalString += addressSplitted[i].substring(0, 1);
                } else
                {
                    finalString += addressSplitted[i];
                }

            }
            if (i != addressSplitted.length - 1)                  /* 5 */

            {
                finalString += "#";
            }
        }
        return finalString;
    }

    /**
     * Print out the Strings contained in the addressesInput and
     * addressesOutput, next to eachother so one can examine the results.
     *
     * @param addressesInput the String imput the program has handled
     * @param addressesOutput the string array the program has created.
     */
    private void print(String[] addressesInput, String[] addressesOutput)
    {
        for (int i = 0; i < addressesInput.length; i++)         /* 6 */

        {
            System.out.println("Input:  " + addressesInput[i] + "\nOutput: " + addressesOutput[i]);
            System.out.println("- - - - ");
        }
    }
}
