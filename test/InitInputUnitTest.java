
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

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
            writer.write("PHONE_NUMBER,ACTIVATION_DATE,DEACTIVATION_DATE");
            for (int i = 1; i < 50; i++) {
                for (int j = 0; j < 1000000; j++) {
                    long phone = 84987000000l + i;
                    writer.write(phone + ",2016-03-01,2016-05-01");
                }
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
