package com.arriky.app;

import com.arriky.exception.ArrikyRuntimeException;
import com.arriky.exception.ErrorMessage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String taskListFilePath = "./data/tasklist.txt";
    private static final String dataDirPath = "./data";

    public static void writeLinesToFile(ArrayList<String> data) throws ArrikyRuntimeException {
        try {
            File dataDir = new File(dataDirPath);
            if (!dataDir.exists()){
                dataDir.mkdirs();
            }

            FileWriter fw = new FileWriter(taskListFilePath);
            for (String line : data) {
                fw.write(line);
                fw.write(System.lineSeparator());
            }

            fw.close();
        } catch (IOException e) {
            throw new ArrikyRuntimeException(ErrorMessage.SAVING_ERROR);
        }

    }

    public static ArrayList<String> loadAllLines() throws ArrikyRuntimeException {
        ArrayList<String> savedData = new ArrayList<String>();

        try {
            Scanner s = new Scanner(new File(taskListFilePath));
            while (s.hasNext()){
                savedData.add(s.next());
            }
            s.close();
        } catch (FileNotFoundException e) {
            throw new ArrikyRuntimeException(ErrorMessage.LOCAL_RECORD_NOT_EXIST);
        }


        return savedData;
    }
}
