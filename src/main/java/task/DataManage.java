package task;

import java.io.*;
import java.util.Scanner;

import exceptions.InputException;
import command.CommandHandler;

public class DataManage {
    private static final String TODO = "T";
    private static final String DEADLINE = "D";
    private static final String EVENT = "E";
    private static final String FOLDER_PATH = "data";
    private static final String TXT_PATH = "data/chris.txt";

    /**
     * Creates a folder to store the data if it does not exist.
     */
    public static void createFolder() {
        File parentDirectory = new File(FOLDER_PATH);
        if (!parentDirectory.exists()) {
            parentDirectory.mkdirs();
        }
    }

    /**
     * Creates a file to store the data if it does not exist.
     */
    public static void createText() {
        File textFile = new File(TXT_PATH);
        try {
            if (!textFile.createNewFile()) {
                System.out.println("already have an existing file locally");
            } else {
                System.out.println("Your journey has started");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves the data to a text file.
     *
     * @param data List of tasks stored inside the ChatBot.
     */
    /*public static void saveText(TaskLists data) {
        try (FileWriter fw = new FileWriter(TXT_PATH, false)) {
            fw.write(data.print());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }*/

    /**
     * Saves the data to the file in a simplified format.
     *
     * @param data List of tasks stored inside the ChatBot.
     */
    public static void saveText(TaskLists data) {
        try (FileWriter fw = new FileWriter(TXT_PATH, false)) {
            fw.write(data.listTasksForSave());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reads the data from a binary file.
     *
     * @return List of tasks stored inside the ChatBot.
     * @throws InputException If the file is not found or there is an I/O error.
     */
    public static TaskLists readSavedData() throws InputException {
        try (Scanner reader = new Scanner(new File(TXT_PATH))) {
            TaskLists listCommands = new TaskLists();
            while (reader.hasNextLine()) {
                addToTaskList(listCommands, reader.nextLine());
            }
            return listCommands;
        } catch (IOException e) {
            throw new InputException("There's no file exists");
        }
    }

    private static void addToTaskList(TaskLists listCommands,
                                      String input) throws InputException {
        int splitLimit = 3;
        String[] taskDetails = input.split(" / ", splitLimit);
        String information = taskDetails[2];
        boolean isDone = taskDetails[1].equals("1");
        switch (taskDetails[0]) {
        case TODO:
            Tasks record = new ToDos(information);
            if (isDone) {
                record.mark();
            }
            listCommands.addTask(record);
            break;
        case DEADLINE:
            String[] deadlines = information.split(" / ", 2);
            if (deadlines.length == 2) {
                Tasks record_1 = new Deadlines(deadlines[0], deadlines[1]);
                if (isDone) {
                    record_1.mark();
                }
                listCommands.addTask(record_1);
            } else {
                throw new InputException("Wrong input syntax, have a time for deadline");
            }
            break;
        case EVENT:
            String[] events = information.split(" / ", 2);
            if (events.length == 2) {
                if ((information.matches("events"))) {
                    throw new InputException("Don't have an empty description for event");
                }
                String[] times = events[1].split(" / ", 2);
                if (times.length == 2) {
                    Tasks record_2 = new Events(events[0], times[0], times[1]);
                    if (isDone) {
                        record_2.mark();
                    }
                    listCommands.addTask(record_2);
                } else {
                    throw new InputException("invalid syntax, have a start and end time");
                }
            } else {
                throw new InputException("wrong input syntax, have a description command");
            }
            break;
        default:
            throw new InputException("Something is wrong with file loading");
        }
    }
}
