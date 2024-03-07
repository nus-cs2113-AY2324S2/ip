package com.arriky.app;

import com.arriky.exception.ArrikyRuntimeException;
import com.arriky.exception.ErrorMessage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to save and import operations of task list from/to local file.
 * @author Songyue Wang
 */
public class Storage {
    private static final String TASK_LIST_FILE_PATH = "./data/tasklist.txt";
    private static final String DATA_DIR_PATH = "./data";

    /**
     * Save tasklist entries to a local file.
     * @param data The arraylist containing all formatted string representations of tasks in the tasklist to save.
     * @throws ArrikyRuntimeException If there is error in saving to local file.
     */
    public static void writeLinesToFile(ArrayList<String> data) throws ArrikyRuntimeException {
        try {
            File dataDir = new File(DATA_DIR_PATH);
            if (!dataDir.exists()){
                dataDir.mkdirs();
            }

            FileWriter fw = new FileWriter(TASK_LIST_FILE_PATH);
            for (String line : data) {
                fw.write(line);
                fw.write(System.lineSeparator());
            }

            fw.close();
        } catch (IOException e) {
            throw new ArrikyRuntimeException(ErrorMessage.SAVING_ERROR);
        }

    }

    /**
     * Read the local record file line by line, and store the lines in an arraylist.
     * @return An arraylist containing
     * @throws ArrikyRuntimeException If the local record file cannot be found.
     */
    public static ArrayList<String> loadAllLines() throws ArrikyRuntimeException {
        ArrayList<String> savedData = new ArrayList<String>();

        try {
            Scanner s = new Scanner(new File(TASK_LIST_FILE_PATH));
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
