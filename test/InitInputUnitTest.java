
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author quangdv3
 */
public class InitInputUnitTest {
    public static void main(String[] args) {
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("input/input50M.csv"), "utf-8"));
            writer.write("PHONE_NUMBER,ACTIVATION_DATE,DEACTIVATION_DATE \n");
            Random rd = new Random();
            for (int i = 1; i < 50000000; i++) {
                    long phone =  i + rd.nextInt(84000000);
                    if((i) % 100000 == 0 ){
                        System.out.println(i);
                    }
                    writer.write(phone + ",2016-03-01,2016-05-01 \n");
            }

        } catch (IOException ex) {
            // report
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {/*ignore*/
            }
        }
    }
}
