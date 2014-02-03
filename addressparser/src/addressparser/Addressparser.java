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
    public static void main(String[] args) {
        
        Parser ps = new Parser();
        
        String[] test = ps.parseThis("Brøndby Nord Vej 234b 4.sal 2300 København S");
        
        System.out.println("Printing the results");
        for(int i = 0; i < test.length ; i++){
            System.out.println(test[i]);
        }
        System.out.println("Next test");
        System.out.println("----------------------");
        test = ps.parseThis("Brøndby Nord Vej 235 4.sal 2300 København S");
        
        System.out.println("Printing the results");
        for(int i = 0; i < test.length ; i++){
            System.out.println(test[i]);
        }
    }
}
