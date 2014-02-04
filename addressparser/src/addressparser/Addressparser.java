/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addressparser;

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
                + "Rued Langgaards Vej"
                + "\nRued Langgaards Vej 7, 5. sal, København S"
                + "\nRued Langgaards Vej 7 2300 København S"
                + "\nRued Langgaards Vej 7, 5."
                + "\nRued Langgaards Vej 7A København S"
                + "\nRued Langgaards Vej i København";
        program.print(addressInput.split("\n"), program.parseAddresses(addressInput));
    }
    
    public String[] parseAddresses(String addresses)
    {
        Parser parser = new Parser();
        
        String[] differentAdresses = addresses.split("\n");
        String[] resultSet = new String[differentAdresses.length];
        
        for (int i = 0 ; i < differentAdresses.length ; i++)
        {
            resultSet[i] = parseSingleAdress(differentAdresses[i]);
        }
        return resultSet;
    }
        
    private String parseSingleAdress(String address)
    {
        Parser parser = new Parser();
        String[] addressSplitted = parser.parseThis(address);
        String finalString = "";
        for (int i = 0; i < addressSplitted.length; i++)
        {
            //System.out.println(addressSplitted[i]);
            if (addressSplitted[i] != null)
            {
                if (i == 3)
                {
                    finalString += addressSplitted[i].substring(0,1);
                } else
                {
                    finalString += addressSplitted[i];
                }

            }
            if (i != addressSplitted.length - 1)
            {
                finalString += "#";
            }
        }
        return finalString;
    }
    
    private void print(String[] addressesInput, String[] addressesOutput)
    {
        for (int i = 0 ; i < addressesInput.length ; i++)
        {
            System.out.println("Input:  " + addressesInput[i] + "\nOutput: " + addressesOutput[i]);
            System.out.println("- - - - ");
        }
    }
}
