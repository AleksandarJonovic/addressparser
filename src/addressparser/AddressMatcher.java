package addressparser;

import ExceptionPackage.InvalidInputException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Christian on 12/02/14.
 */
public class AddressMatcher {

    protected ArrayList<String> rawAddressList = new ArrayList<>();
    protected ArrayList<String> cleanAddressList = new ArrayList<>();

    /**
     * The constructor of the AddressMatcher. It loads in the road_names.txt
     * file, and creates two arrays, a cleaned one and a raw one. The clean one
     * is used for comparing with user-input the other one is used for output.
     * Here we also sort the two collections.
     *
     * @throws FileNotFoundException throws it if there went something wrong
     * with reading the streetnames file.
     */
    public AddressMatcher() throws FileNotFoundException {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("road_names.txt"), "LATIN1"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            Parser p = new Parser();

            while (line != null) {
                rawAddressList.add(line);
                cleanAddressList.add(p.cleanString(line));
                line = br.readLine();
            }
            br.close();
            Collections.sort(rawAddressList);
            Collections.sort(cleanAddressList);

        } catch (Exception e) {
            e.printStackTrace();
        }
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
        System.out.println(p.parseStreetAddress("Brøndby Nord Vej"));
    }
}
