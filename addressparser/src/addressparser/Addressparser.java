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
        
        String[] test = ps.parseThis("mariavej 23b 2300 maria er awesome by");
        
        System.out.println("Printing the results 1");
        for(int i = 0; i < test.length ; i++){
            System.out.println(test[i]);
        }
    }
}
