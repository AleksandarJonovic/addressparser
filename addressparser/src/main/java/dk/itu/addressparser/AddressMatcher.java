package dk.itu.addressparser;

import dk.itu.ExceptionPackage.InvalidInputException;

import java.io.*;

/**
 * Created by Christian on 12/02/14.
 */
public class AddressMatcher {

  public static String matchStreet(String[] address) {
    String everything = "";
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("road_names.txt"), "LATIN1"));
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        line = line.replaceAll(" ", "");
        if (line.contains(address[0])) {
          sb.append(line);
          sb.append(System.lineSeparator());
        }
        line = br.readLine();
      }
      everything = sb.toString();
      br.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
    return everything;
  }

  public static void main(String[] args) {
    Parser p = new Parser();
    String parseThis = "H C Andersens Boulevard";
    String match;
    try {
      match = AddressMatcher.matchStreet(p.parseThis(parseThis.replaceAll(" ", "")));
//      match = match.replaceAll(" ", "");
      System.out.println(parseThis);
      System.out.println(match);

    } catch (InvalidInputException e) {
      e.printStackTrace();
    }
//    System.out.println(AddressMatcher.matchStreet(new String[]{"H C Andersens Boulevard"}));
//    try {
//      System.out.println(p.parseThis("HC Andersens Boulevard")[0]);
//    } catch (InvalidInputException e) {
//      e.printStackTrace();
//    }
  }
}
