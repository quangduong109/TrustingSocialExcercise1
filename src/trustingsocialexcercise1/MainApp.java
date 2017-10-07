/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trustingsocialexcercise1;

import trustingsocialexcercise1.sort.ExternalSort;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author quangdv3
 */
public class MainApp {
    private static final String defaultFileNameInput = "input/input10.csv";
    private static final String defaultFileNameInputSorted = "input/input10_sorted.csv";
    private static final String defaultFileNameOutput = "output/output.csv";
    private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * @param args the command line
     *             arguments
     */
    //default data has been standardized
    public static void main(String[] args) {
        Writer writer = null;
        Scanner scanner = null;
        String fileNameInput = null;
        String fileNameOutput = null;
        String fileNameInputSorted = null;

        for (int param = 0; param < args.length; ++param) {
            if (fileNameInput == null) {
                fileNameInput = args[param];
                fileNameInputSorted = fileNameInput + ".sorted";
            } else if (fileNameOutput == null) {
                fileNameOutput = args[param];
            } else {
                System.out.println("Cannot parse: " + args[param]);
                showUsage();

            }
        }
        if(fileNameInput == null){
            fileNameInput = defaultFileNameInput;
            fileNameInputSorted = defaultFileNameInputSorted;
            fileNameOutput = defaultFileNameOutput;
            showUsage();
        }

        try {
            ExternalSort.sortFileInput(fileNameInput, fileNameInputSorted);

            scanner = new Scanner(new File(fileNameInputSorted), "UTF-8");
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileNameOutput), "utf-8"));
            writer.write("PHONE_NUMBER,REAL_ACTIVATION_DATE \n");
            String lineNext = null;
            while (scanner.hasNextLine()) {
                String line;
                if (lineNext == null) {
                    line = scanner.nextLine().trim();
                } else {
                    line = lineNext;
                    lineNext = null;
                }
                String[] splitLine = line.split(",");
                if (splitLine.length == 2) {
                    writer.write(splitLine[0] + "," + splitLine[1] + "\n");
                    continue;
                }
                Date activeTime = format.parse(splitLine[1]);
                Date deactiveTime = format.parse(splitLine[2]);
                while (scanner.hasNextLine()) {
                    lineNext = scanner.nextLine().trim();
                    if (lineNext != null) {
                        String[] splitNextLine = lineNext.split(",");
                        if(!splitLine[0].equals(splitNextLine[0])){
                            writer.write(splitLine[0] + "," + format.format(activeTime) + "\n");
                            break;
                        } else{
                            if(splitNextLine.length == 2){
                                break;
                            }
                            try {
                                Date activeTimeNextLine = format.parse(splitNextLine[1]);
                                if (deactiveTime.getTime() < activeTimeNextLine.getTime()) {
                                    activeTime = activeTimeNextLine;
                                    deactiveTime = format.parse(splitNextLine[2]);
                                } else if(deactiveTime.getTime() == activeTimeNextLine.getTime()) {
                                    deactiveTime = format.parse(splitNextLine[2]);
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                scanner.close();
            } catch (Exception ex) {
            }
            try {
                writer.close();
            } catch (Exception ex) {
            }
        }


    }

    private static void showUsage() {
        System.out.println("Run command: java trustingsocialexcercise1.MainApp inputFilePath outputFilePath");
    }
}
