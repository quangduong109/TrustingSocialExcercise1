/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trustingsocialexcercise1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author quangdv3
 */
public class TrustingSocialExcercise1 {

    /**
     * @param args the command line
     * arguments
     */
    //Yeu cau data truyen vao phai chuan
    public static void main(String[] args) {
        String fileName = "input/input.csv";
        readInputFile(fileName);
    }

    private static void readInputFile(String fileName) {
        Map<Long, TimeItem> mapPhoneWithTime = new HashMap();
        Map<String, Boolean> mapCheckPhoneExists = new HashMap();

        Writer writer = null;
        Scanner sc = null;
        try {
            sc = new Scanner(new File(fileName), "UTF-8");
            writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("output/output.csv"), "utf-8"));
            writer.write("PHONE_NUMBER,REAL_ACTIVATION_DATE");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] split = line.split(",");
                if (split.length == 2) {
                    writer.write(split[0] + "," + split[1]);
                    mapCheckPhoneExists.put(split[0], Boolean.TRUE);
                } else if (split.length == 3) {

                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(TrustingSocialExcercise1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            // report
        } finally {
            try {
                sc.close();
            } catch (Exception ex) {/*ignore*/
            }
            try {
                writer.close();
            } catch (Exception ex) {/*ignore*/
            }
        }
    }

}
