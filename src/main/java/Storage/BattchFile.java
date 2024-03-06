package Storage;

import TaskList.Task;
import Ui.PrintText;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents an add command where a new task is added to the existing list of task.
 * String <code>FILENAME</code> represents the designated relative file path for the file.
 * File <code>battchData</code> represents the File object created to be updated.
 */
public class BattchFile {
    private static final String FILENAME = "./battchData.txt";
    private static File battchData;

    /**
     * Returns lateral location of the specified position.
     * If the position is unset, NaN is returned.
     *
     * @param filePath File path where the file is located.
     * @param textToAdd The line of text to write to the file.
     * @param ifAppend Indicate if append the text at the end of the file (true)
     *                 or overwrite the file (false).
     * @throws IOException If file is not found at the indicated file path.
     */
    private static void writeToFile(String filePath, String textToAdd, boolean ifAppend) throws IOException {
        FileWriter writer = new FileWriter(filePath, ifAppend);
        writer.write(textToAdd);
        writer.close();
    }
    public static void updateFile(String inputText, boolean ifAppend) {
        try {
            writeToFile(FILENAME, inputText, ifAppend);
        } catch (IOException e) {
            PrintText.printWithLinebreak("IOExceptions occurred");
        }
    }

    /**
     * Retrieve the index of the last line in a file at file path FILENAME.
     * Returns 0 if the file is not found.
     */
    public static int latestIndex() {
        try {
            File fileToLook = new File(FILENAME);
            Scanner s = new Scanner(fileToLook);
            String lastLine = "";
            int index = 0;
            while (s.hasNext()) {
                lastLine = s.nextLine();
                index += 1;
            }
            return index;
        } catch (Exception e) {
            PrintText.printWithLinebreak("return index 0");
            return 0;
        }
    }

    /**
     * Returns the private File battchData.
     */
    public static File getFileData() {
        return battchData;
    }

    /**
     * Read lines from the file and identify tasks written inside.
     * Add the identified tasks into a list of existing tasks.
     *
     * @param fileToRead The file to read from.
     * @param existTasks Arraylist storing existing tasks.
     */
    public static void readFromFile(File fileToRead, ArrayList<Task> existTasks) {
        try {
            Scanner scanner = new Scanner(fileToRead);
            while (scanner.hasNext()) {
                String lineSkipped = scanner.nextLine();
                String typeAsString = lineSkipped.substring(2, 5);
                String isMarkedAsString = lineSkipped.substring(5, 8);
                char taskType;
                boolean isDone;
                String description = lineSkipped.substring(9);
                switch (typeAsString) {
                case "[E]":
                    taskType = 'E';
                    break;
                case "[D]":
                    taskType = 'D';
                    break;
                case "[T]":
                    taskType = 'T';
                    break;
                default:
                    taskType = ' ';
                    break;
                }
                isDone = isMarkedAsString.equals("[X]");
            existTasks.add(new Task(description, taskType, isDone));
            }
        } catch (FileNotFoundException e) {
            PrintText.printWithLinebreak("File does not exist.");
        }
    }

    public static void main(String[] args) {
        battchData = new File(FILENAME);
        try {
            writeToFile(battchData.getPath(), "", true);
        } catch (IOException e) {
            PrintText.printWithLinebreak("File does not exist.");
        }
    }
}
