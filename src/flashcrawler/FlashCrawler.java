/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flashcrawler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Carlos
 */
public class FlashCrawler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scn = new Scanner(new File("input.txt"));
        ArrayList<String> ins = new ArrayList();
        while (scn.hasNextLine()) {
            String input = scn.nextLine();
            ins.add(input);
        }

        String URL;
        PrintWriter writer = null;
        writer = new PrintWriter(new FileOutputStream(new File("error-log.txt"), true));

        String File;
        for (String name : ins) {
            File offlinePath = new File("/games/" + name + ".swf");
            String onlinePath = "http://wsh.gamib.com/x/" + name + "/" + name + ".swf";
            System.out.println("Downloading " + onlinePath + " into " + offlinePath);
            URL url = null;
            try {
                System.out.println("...");
                url = new URL(onlinePath);
            } catch (MalformedURLException ex) {
                System.out.println("Failed to create url object");
                writer.println("Error when creating url: " + onlinePath + "\tname");
            }
            try {
                System.out.println("...");
                FileUtils.copyURLToFile(url, offlinePath);
                System.out.println("Success.");
            } catch (IOException ex) {
                System.out.println("Error when downloading game: " + offlinePath);
                writer.println("Error when downloading game: " + offlinePath);
            }
            
        }
        writer.close();
        System.out.println("Process complete!");
    }
}
