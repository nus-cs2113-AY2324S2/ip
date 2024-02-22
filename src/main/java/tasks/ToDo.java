package tasks;

import exceptions.DuckInvalidToDoDescriptionException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class ToDo extends Task{

    private static final String LINE_SEPARATOR = "____________________________________________________________";
    private static final String ADDED_MESSAGE = "Got it. I've added this task: \n";
    public ToDo(String description) {
        super(description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public static int addToDo(ArrayList<Task> tasks, String userInput, int index) {
        try {
            String split = userInput.substring(4);
            if (split.trim().isEmpty()) {
                throw new DuckInvalidToDoDescriptionException();
            }
            ToDo newToDo = new ToDo(split);
            tasks.add(newToDo);
            appendToDoDuckDataFile();
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
     */
    public static void appendToDoDuckDataFile() throws IOException {
        String lineToAdd = "T |" + getDescription() + "\n";
        Files.write(FILE_PATH, lineToAdd.getBytes(), StandardOpenOption.APPEND);
    }
}
