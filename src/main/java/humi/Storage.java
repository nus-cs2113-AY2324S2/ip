package humi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Storage system used to create and save the data text file
 */
public class Storage {
    public String textContent;

    Storage() {
        textContent = "";
    }

    /**
     * Append the contents to a single string that will be saved
     * @param content String to be appended
     */
    public void appendTextContent(String content) {
        textContent += (content + '\n');
    }

    /**
     * Creates new folder and file if both did not exist
     * @param folderPath Path of the folder
     * @param filePath Path of the file
     */
    public void createFile(String folderPath, String filePath) {
        try {
            File dataFolder = new File(folderPath);
            if (!dataFolder.exists()) {
                System.out.println("Folder not found, attempting to create folder.");
                if (dataFolder.mkdirs()) {
                    System.out.println("Data folder has been created successfully.");
                } else {
                    System.err.println("Failed to create data folder.");
                }
            }
            File textFile = new File(filePath);
            if (textFile.createNewFile()) {
                System.out.println("File has been created.");
            }
        } catch (IOException err) {
            System.out.println("Failed to create file.");
        }
    }

    /**
     * Load the saved file and convert it into list of text formatted commands
     * @param filePath Path of the file
     * @return List of text formatted commands
     * @throws FileNotFoundException If the file does not exist
     */
    public ArrayList<String> readFile(String filePath) throws FileNotFoundException {
        ArrayList<String> input = new ArrayList<>();
        File file = new File(filePath);
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            input.add(s.nextLine());
        }
        s.close();
        return input;
    }

    /**
     * Write the given string to the text file
     * @param filePath Path of the text file
     * @param textToAdd String to be written
     * @throws IOException If there is any error encountered
     */
    public void writeFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Change the mark of a particular task in the text file
     * @param isDone Mark of the task
     * @param index Index of the task
     */
    public void markText(boolean isDone, int index) {
        String[] textArray = textContent.split("\n");
        String mark = isDone ? "1" : "0";
        String newString = textArray[index-1].substring(0, 2) + mark + textArray[index-1].substring(3);
        textArray[index-1] = newString;
        textContent = "";
        for (int i = 0; i < textArray.length; i++) {
            appendTextContent(textArray[i]);
        }
    }

    /**
     * Delete a particular task from the text file
     * @param index Index of the task
     */
    public void deleteText(int index) {
        String[] textArray = textContent.split("\n");
        textContent = "";
        for (int i = 0; i < textArray.length; i++) {
            if (i != index - 1) {
                appendTextContent(textArray[i]);
            }
        }
    }
}
