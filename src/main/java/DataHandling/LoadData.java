package DataHandling;

import Exceptions.FileReadException;
import Exceptions.LoadFileException;
import GermaBot.UI;
import Tasks.*;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

/**
 * The LoadData class handles loading tasks from a file into the program.
 */
public class LoadData {
    static ArrayList<Task> toDoList = new ArrayList<>();
    static int counter = 0;

    /**
     * Loads a ToDo task from a string description and adds it to the task list.
     *
     * @param description The description of the ToDo task.
     * @throws LoadFileException If the description is empty.
     */
    public static void loadToDo(String description) throws LoadFileException {
        if (description.isEmpty()) {
            throw new LoadFileException();
        }
        toDoList.add(new ToDo(description));
    }

    /**
     * Loads a Deadline task from a string description and adds it to the task list.
     *
     * @param description The description of the Deadline task, including the deadline date.
     * @throws LoadFileException If the description or deadline date is empty or invalid.
     */
    public static void loadDeadline(String description) throws LoadFileException {
        String[] deadlineDescription = description.split("\\|");
        String task = deadlineDescription[0].trim();
        String by = deadlineDescription[1].trim();
        if (task.isBlank() || by.isBlank()) {
            throw new LoadFileException();
        }
        toDoList.add(new Deadline(task, by));
    }

    /**
     * Loads an Event task from a string description and adds it to the task list.
     *
     * @param description The description of the Event task, including the start and end dates.
     * @throws LoadFileException If the description or dates are empty or invalid.
     */
    public static void loadEvent(String description) throws LoadFileException {
        String[] eventDescription = description.split("\\|");
        String task = eventDescription[0].trim();
        String from = eventDescription[1].trim();
        String by = eventDescription[2].trim();
        if (task.isBlank() || from.isBlank() || by.isBlank()) {
            throw new LoadFileException();
        }
        toDoList.add(new Event(task, from, by));
    }

    /**
     * Loads tasks from a file into the program.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws FileNotFoundException If the file is not found.
     * @throws FileReadException If there is an error reading the file, such as if the file is in an invalid format.
     */
    public static ArrayList<Task> loadFile() throws FileNotFoundException, FileReadException {
        File data = new File("src/data/GermaBotData.txt");
        Scanner fileInput = new Scanner(data);
        do {
            String task = fileInput.nextLine();
            if (task.trim().isEmpty()) {
                throw new FileReadException();
            }
            counter++;
            char type = task.charAt(0);
            String description = task.substring(8);
            boolean isCompleted = task.charAt(4) != '0';
            if (type == 'T') {
                try {
                    loadToDo(description);
                } catch (LoadFileException e) {
                    UI.printLoadFileException();
                }
            } else if (type == 'D') {
                try {
                    loadDeadline(description);
                } catch (LoadFileException e) {
                    UI.printLoadFileException();
                }
            } else if (type == 'E') {
                try {
                    loadEvent(description);
                } catch (LoadFileException e) {
                    UI.printLoadFileException();
                }
            } else {
                break;
            }
            int idxToMark = toDoList.size() - 1;
            toDoList.get(idxToMark).setDone(isCompleted);
        } while (fileInput.hasNext());
        fileInput.close();
        return toDoList;
    }

    /**
     * Returns the number of tasks loaded from the file.
     *
     * @return The number of tasks.
     */
    public static int getCounter() {
        return counter;
    }
}
