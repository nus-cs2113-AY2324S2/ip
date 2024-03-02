package FileManagerPackage;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;
import UserInputs.Parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class handles file input/output operations for managing tasks.
 */
public class Storage {

    protected static final int DATE_LENGTH = 5;
    private static final int TIME_LENGTH = 4;
    /**
     * Reads the data from the file and populates the task list.
     *
     * @param s       The Scanner object for reading from the file.
     * @param list    The list to store tasks.
     */
    public static void readFile(Scanner s, ArrayList<Task> list) {
        int currentIteration = 0;
        while (s.hasNext()) {
            String[] currentLine = s.nextLine().split("\\s*\\|\\s*");

            switch (currentLine[0]) {
                case "D":
                    String inputDateString = currentLine[3].strip();
                    LocalDate date = Parser.processDate(inputDateString.substring(0, inputDateString.length() - DATE_LENGTH));
                    LocalTime time = Parser.processTime(inputDateString.substring(inputDateString.length() - TIME_LENGTH));
                    list.add(new Deadline(currentLine[2], date, time));
                    break;
                case "T":
                    list.add(new Todo(currentLine[2]));
                    break;
                case "E":
                    String[] duration = currentLine[3].split("\\s*\\ - \\s*");

                    LocalDate dateFrom = Parser.processDate(duration[0].strip().substring(0, duration[0].length()- DATE_LENGTH));
                    LocalTime timeFrom = Parser.processTime(duration[0].strip().substring(duration[0].length()- TIME_LENGTH));
                    LocalDate dateTo = Parser.processDate(duration[1].strip().substring(0, duration[1].length()- DATE_LENGTH));
                    LocalTime timeTo = Parser.processTime(duration[1].strip().substring(duration[0].length()- TIME_LENGTH));
                    list.add(new Event(currentLine[2],dateFrom, timeFrom, dateTo, timeTo));
                    break;
            }
            if (currentLine[1].equals("1")) {
                list.get(currentIteration).setDone(true);
            }
            currentIteration++;
        }
    }

    /**
     * Checks if the directory and file exist; creates them if not.
     *
     * @param f     The file object to check/create.
     * @param filePath The path of the file.
     */
    private static void checkIfDirExist(File f, String filePath) {
        if (!f.exists()) {
            File file = new File(filePath);
            File parentDir = file.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            try {
                FileWriter fileWriter = new FileWriter(filePath);
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("     An error occurred while creating file: " + e.getMessage());
            }
        }
    }

    /**
     * Retrieves the file object representing the task data file.
     *
     * @return The File object representing the task data file.
     */
    public static File getFile() {
        String filePath = Paths.get(System.getProperty("user.dir"), "data", "ThawBot.txt").toString();
        File f = new File(filePath);
        checkIfDirExist(f, filePath);
        return f;
    }

    /**
     * Saves the task list data to the file.
     *
     * @param list    The list of task to be saved
     */
    public static void saveData(ArrayList<Task> list) {
        try {
            final String filePath = "data/ThawBot.txt";
            FileWriter fw = new FileWriter(filePath);
            String textToAdd = "";
            for (int i = 0; i < list.size(); i++) {
                textToAdd += list.get(i).printFileFormat() + System.lineSeparator();
            }
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            System.out.println("     An error occurred while reading file: " + e.getMessage());
        }
    }
}
