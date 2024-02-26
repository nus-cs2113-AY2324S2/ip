package hachi;

import hachi.data.HachiException;
import hachi.data.TaskList;
import hachi.data.task.Deadline;
import hachi.data.task.Event;
import hachi.data.task.Task;
import hachi.data.task.Todo;
import hachi.parser.Parser;
import hachi.storage.Storage;
import hachi.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * This program currently starts the chatbot with a greeting,
 * awaits user input for commands relating to a to-do task manager
 * and ends off the program with a goodbye message.
 *
 * @author clarencepohh
 * @version 06/02/2024
 */

public class Hachi {
    private Ui ui;
    private static TaskList taskList;
    private Storage storage;

    static String filePath = "hachidata/hachidata.txt";
    protected static File folder = new File("hachidata");

    public static void initializeData() throws SecurityException {
        // to move to Storage class
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    
    private static void save(ArrayList<Task> taskArrayList) throws IOException {
        // to move to Storage class
        try {
            FileWriter fw = new FileWriter(filePath, false);
            fw.write("");
            fw.close(); // to clear text file
        } catch (IOException e) {
            System.out.println("\n\tCreating new task list save...");
        }

        FileWriter fw = new FileWriter(filePath);
        for (Task task : taskArrayList) {
            if (task != null) {
                fw.write(task.getSaveFormat() + "\n");
            } else break;
        }
        fw.close();
    }

    private static void loadFile(ArrayList<Task> taskArrayList) throws FileNotFoundException, HachiException {
        // to move to Storage class
        File taskFile = new File(filePath);
        Scanner s = new Scanner(taskFile);

        while (s.hasNext()) {
            String[] lines = s.nextLine().split(" \\| ");
            Task toAdd;

            switch (lines[0]){
            case "T":
                toAdd = new Todo(lines[2]);
                break;

            case "D":
                toAdd = new Deadline(lines[2], lines[3]);
                break;

            case "E":
                toAdd = new Event(lines[2], lines[3], lines[4]);
                break;

            default:
                throw new HachiException(HachiException.CORRUPTED_SAVE_MESSAGE);
            }

            if (lines[1].equals("X")) {
                toAdd.setCompleteness(true);
            }
            taskArrayList.add(toAdd);
        }
    }


    /**
     * The main program that starts the chatbot.
     * Prints to the console for the user to read its messages.
     * Greets the user and awaits user input.
     * <p>
     * Chatbot can:
     * <p>1. retrieve list of tasks with user input "list"
     * <p>2. mark or unmark tasks complete with user input "mark #tasknumber"
     * <p>3. say goodbye to the user with user input "bye" or "goodbye"
     * <p>4. add a to-do to the list of task with "todo <event name>"
     * <p>5. add a deadline to the list of task with "deadline <event name> /by <by date>"
     * <p>6. add an event to the list of task with "event <event name> /from <start date> /to <end date>"
     * <p>7. retrieve a list of chatbot commands with "help"
     *
     * @param args Command line arguments - not used.
     */

    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage();
        taskList = new TaskList();

        Ui.spacerInsert("medium", false);
        Ui.printGreetingMessage();
        Ui.printHelpMessage();
        initializeData();

        // maybe place load file try-catch block in Hachi constructor
        try {
            loadFile(TaskList.getTasksArrayList());
        } catch (FileNotFoundException e) {
            System.out.println("Error finding save file. Creating empty task list.");
        } catch (HachiException e) {
            System.out.println("Save file is corrupted. Creating empty task list.");
        }

        String command = null;

        do {
            try {
                // extract main TRY block as Parser method
                String userInput = Ui.getUserInput();
                String cleanedInput = Ui.cleanUserInput(userInput);
                String firstWord;
                int indexOfSpace = cleanedInput.indexOf(" ");
                firstWord = Parser.getFirstWordOfInput(indexOfSpace, cleanedInput);

                command = Parser.processUserCommand(firstWord, cleanedInput, userInput);
            } catch (HachiException e) {
                System.out.println(e.getMessage());
            }
            
            Ui.spacerInsert("medium", true);
        } while (!Objects.equals(command, "BYE"));

        try {
            save(TaskList.getTasksArrayList());
        } catch (IOException e) {
            System.out.println("There was an error saving tasks.");
        }
    }
}
