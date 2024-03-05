package com.arriky.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileIO {

    private static String taskListFilePath = "./data/tasklist.txt";

    private static String dataDirPath = "./data";

    public static void writeLineToFile(ArrayList<String> data) throws IOException {
        File datDir = new File(dataDirPath);
        if (!datDir.exists()){
            datDir.mkdirs();
        }

        FileWriter fw = new FileWriter(taskListFilePath);
        for (String line : data) {
            fw.write(line);
            fw.write(System.getProperty( "line.separator" ));
        }

        fw.close();
    }

    public static ArrayList<String> readFileByLine() throws FileNotFoundException {
        ArrayList<String> savedData = new ArrayList<String>();

        Scanner s = new Scanner(new File(taskListFilePath));
        while (s.hasNext()){
            savedData.add(s.next());
        }
        s.close();

        return savedData;
    }

}