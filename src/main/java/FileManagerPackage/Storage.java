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

public class Storage {
    public static void readFile(Scanner s, ArrayList<Task> list) {
        int currentIteration = 0;
        while (s.hasNext()) {
            String[] currentLine = s.nextLine().split("\\s*\\|\\s*");
            switch (currentLine[0]) {
                case "D":
                    String inputDateString = currentLine[3].strip();
                    LocalDate date = Parser.processDate(inputDateString.substring(0, inputDateString.length() - 5));
                    LocalTime time = Parser.processTime(inputDateString.substring(inputDateString.length() - 4));
                    list.add(new Deadline(currentLine[2], date, time));
                    break;
                case "T":
                    list.add(new Todo(currentLine[2], currentLine[2]));
                    break;
                case "E":
                    String[] duration = currentLine[3].split("\\s*\\ - \\s*");
                    duration[0] = duration[0].strip();
                    duration[1] = duration[1].strip();

                    LocalDate dateFrom = Parser.processDate(duration[0].substring(0, duration[0].length()- 5));
                    LocalTime timeFrom = Parser.processTime(duration[0].substring(duration[0].length()- 4));
                    LocalDate dateTo = Parser.processDate(duration[1].substring(0, duration[1].length()- 5));
                    LocalTime timeTo = Parser.processTime(duration[1].substring(duration[0].length()- 4));
                    list.add(new Event(currentLine[2],dateFrom, timeFrom, dateTo, timeTo));
                    break;
            }

            if (currentLine[1].equals("1")) {
                list.get(currentIteration).setDone(true);
            }
            currentIteration++;
        }
    }

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

    public static File getFile() {
        String filePath = Paths.get(System.getProperty("user.dir"), "data", "ThawBot.txt").toString();
        File f = new File(filePath);
        checkIfDirExist(f, filePath);
        return f;
    }

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
