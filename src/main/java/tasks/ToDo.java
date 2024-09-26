package tasks;

import exceptions.DuckInvalidToDoDescriptionException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

/**
 * Represents a Todo task which only contains description
 * subclass of Task
 */
public class ToDo extends Task{

    private static final String LINE_SEPARATOR = "____________________________________________________________";
    private static final String ADDED_MESSAGE = "Got it. I've added this task: \n";
    public ToDo(String description) {
        super(description);
    }

    /**
     * Overrides toStrong such that it prints out Todo in formatted style
     * @return
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Add ToDo it arraylist tasks and updates number of tasks
     * @param tasks arraylist of tasks
     * @param userInput unformatted user input
     * @param index number of tasks in arraylist
     * @return updated number of tasks
     */
    public static int addToDo(ArrayList<Task> tasks, String userInput, int index) {
        try {
            String split = userInput.substring(5);
            if (split.trim().isEmpty()) {
                throw new DuckInvalidToDoDescriptionException();
            }
            ToDo newToDo = new ToDo(split);
            tasks.add(newToDo);
            appendToDoDuckDataFile(newToDo);
            System.out.println(LINE_SEPARATOR);
            System.out.println(ADDED_MESSAGE + tasks.get(index));
            index++;
            System.out.println("Now you have " + index + " tasks in the list.");
            System.out.println(LINE_SEPARATOR);
        } catch (IndexOutOfBoundsException e){
            System.out.println("Too many items! Max 100 items");
        } catch (DuckInvalidToDoDescriptionException e) {
            System.out.println("Invalid ToDo input. Please type in format: todo [string]");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return index;
    }

    /**
     * Add todo into duck.txt file in format
     * @return line to append into data file
     */
    public static String appendToDoDuckDataFile(ToDo toDo) throws IOException {
        return "T | " + toDo.getDescription() + "\n";
    }
}
