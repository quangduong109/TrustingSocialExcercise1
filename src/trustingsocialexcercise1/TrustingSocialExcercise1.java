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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author quangdv3
 */
public class TrustingSocialExcercise1 {
    private static final String fileNameInput = "input/input50M.csv";
    private static final String fileNameOutput = "output/output50M.csv";
    private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * @param args the command line
     *             arguments
     */
    //Yeu cau data truyen vao phai chuan, neu ko chuan thi co doan code check data :D
    public static void main(String[] args) {

        Map<String, TimeItem> mapPhoneWithTime = new HashMap();

        Writer writer = null;
        Scanner sc = null;
        try {
            sc = new Scanner(new File(fileNameInput), "UTF-8");
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileNameOutput), "utf-8"));
            writer.write("PHONE_NUMBER,REAL_ACTIVATION_DATE \n");
            sc.nextLine();
            int index = 0;
            while (sc.hasNextLine()) {
                index++;
                if(index%10000==0){
                    System.out.println(index);
                }
                String line = sc.nextLine();
                String[] split = line.split(",");
                if (split.length == 2) {
                    writer.write(split[0] + "," + split[1] + "\n");
                    TimeItem timeItem = new TimeItem();
                    timeItem.writeToFile = true;
                    mapPhoneWithTime.put(split[0], timeItem);
                } else if (split.length == 3) {
                    TimeItem timeItem = mapPhoneWithTime.get(split[0]);
                    if (timeItem != null) {
                        if (!timeItem.writeToFile) {
                            try {
                                Date activeTime = format.parse(split[1]);
                                if (activeTime.getTime() > timeItem.activeTime.getTime()) {
                                    timeItem = new TimeItem();
                                    timeItem.writeToFile = false;
                                    timeItem.activeTime = activeTime;
                                    timeItem.deactiveTime = format.parse(split[2]);
                                    mapPhoneWithTime.put(split[0], timeItem);
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }

                    } else {
                        try {
                            timeItem = new TimeItem();
                            timeItem.writeToFile = false;
                            timeItem.activeTime = format.parse(split[1]);
                            timeItem.deactiveTime = format.parse(split[2]);
                            mapPhoneWithTime.put(split[0], timeItem);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            Set<Map.Entry<String, TimeItem>> entries = mapPhoneWithTime.entrySet();
            for (Map.Entry entry : entries) {
                TimeItem value = (TimeItem) entry.getValue();
                if (!value.writeToFile) {
                    writer.write(entry.getKey() + "," + format.format(value.activeTime) + "\n");
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
