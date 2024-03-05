package vibes;

import vibes.exception.CommandNotFoundException;
import vibes.exception.InvalidArgumentException;
import vibes.storage.Storage;
import vibes.task.TaskList;

import java.util.Scanner;
import java.io.FileNotFoundException;

public class Vibes {
    private static final String CHATBOT_NAME = "Vibes";
    private static final String DASHED_LINE = "\t---------------------------------------------------------------------------------------";

    public static void main(String[] args) {
        String userInput;
        boolean isExit = false;
        Scanner in = new Scanner(System.in);
        TaskList taskList = new TaskList();
        try {
            Storage.loadTasks(taskList);
        } catch (FileNotFoundException e) {
            System.out.println("File not Found!");
        }

        System.out.println(DASHED_LINE);
        System.out.println("\t Hello! I'm " + CHATBOT_NAME + "\n\t What can I do for you?");
        System.out.println(DASHED_LINE);


        while(!isExit){
            userInput = in.nextLine().trim();
            String commandToExecute = userInput.split(" ")[0].toLowerCase();

            System.out.println(DASHED_LINE);
            if (commandToExecute.equals("bye")) {
                System.out.println("\t Bye. Hope to see you again soon!");
                isExit = true;
            } else {
                try {
                    taskList.executeCommand(commandToExecute, userInput);
                } catch (CommandNotFoundException e) {
                    System.out.println("\t Invalid Command. Please choose between: todo, deadline, event, mark, unmark, " +
                            "and bye");
                } catch (InvalidArgumentException e) {
                    System.out.println("\t Argument not found! The description of a todo cannot be empty.");
                }
            }
            System.out.println(DASHED_LINE);
        }
    }
}