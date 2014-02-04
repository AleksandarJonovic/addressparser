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

        Parser ps = new Parser();

        String[] test = ps.parseThis("Rued Langgaards Vej i København S");

        System.out.println("Printing the results");
        String finalString = "";
        for (int i = 0; i < test.length; i++)
        {
            System.out.println(test[i]);
            if (test[i] != null)
            {
                if (i == 3)
                {
                    finalString += test[i].substring(0,1);
                } else
                {
                    finalString += test[i];
                }

            }
            if (i != test.length - 1)
            {
                finalString += "#";
            }
        }
        System.out.println(" - - - Final output - - - ");
        System.out.println(finalString);

    }
}
