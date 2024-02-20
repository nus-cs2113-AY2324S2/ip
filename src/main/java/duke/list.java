package duke;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.print.printMessage;
import static duke.command.*;

public class list {
    public static final String chatbotName = "Noriaki";
    public static final String[] validCommands =
            {"list", "mark", "unmark", "todo", "deadline", "event", "bye", "delete"};
    public static List<Task> taskList = new ArrayList<>();

    /**
     * Prints greeting.
     */
    public static void greet(){
        String greetMessage = "Hello! I'm " + chatbotName + "\nWhat can I do for you?";
        String logo =
                " _______               .__        __   .__ \n" +
                        " \\      \\   ___________|__|____  |  | _|__|\n" +
                        " /   |   \\ /  _ \\_  __ \\  \\__  \\ |  |/ /  |\n" +
                        "/    |    (  <_> )  | \\/  |/ __ \\|    <|  |\n" +
                        "\\____|__  /\\____/|__|  |__(____  /__|_ \\__|\n" +
                        "        \\/                     \\/     \\/   \n";

        System.out.println("Hello from\n" + logo);
        printMessage(greetMessage);
    }

    /**
     * Prints goodbye message.
     */
    public static void goodbye(){
        String goodbyeMessage = "Bye! Hope to see you again soon! MEGANE!!";

        printMessage(goodbyeMessage);
    }

    public static void executeCommand(String command, String argument)
            throws MissingParamsException, DukeException.EndListException,
            DukeException.InvalidCommandException, DukeException.InvalidIntegerException,
            DukeException.IntegerOutOfBoundsException {
        if (command.equalsIgnoreCase("bye")) {
            throw new DukeException.EndListException();
        }

        if (Arrays.stream(validCommands).noneMatch(command::equals)){
            throw new DukeException.InvalidCommandException();
        }

        switch (command) {
        case "list":
            printList(taskList);
            break;
        case "mark":
            markTask(taskList, argument, true);
            break;
        case "unmark":
            markTask(taskList, argument, false);
            break;
        case "todo":
            addToDo(taskList, argument);
            break;
        case "deadline":
            addDeadline(taskList, argument);
            break;
        case "event":
            addEvent(taskList, argument);
            break;
        case "delete":
            deleteTask(taskList, argument);
        }
    }

    /**
     * Creates a list that users can add tasks to, read, and mark tasks as done or undone.
     */
    public static void startList(){
        String line;
        Scanner in = new Scanner(System.in);

        while(true){
            line = in.nextLine();

            String[] tokens = line.split(" ", 2);

            String command = tokens[0].toLowerCase().trim();
            String argument = "";

            // If input contains more than one word, assign remaining words to argument
            if (tokens.length > 1) {
                argument = tokens[1].trim();
            }

            try {
                executeCommand(command,argument);
            } catch (DukeException.EndListException e) {
                return;
            } catch (DukeException.InvalidCommandException e) {
                e.printErrorMessage();
            } catch (MissingParamsException e) {
                String errorMessage = "The following parameters are missing:\n"
                        + e + "\n"
                        + "YOU GOTTA SAVE ME AND MY HEART!!";
                printMessage(errorMessage);
            } catch (DukeException.InvalidIntegerException e) {
                e.printErrorMessage();
            } catch (DukeException.IntegerOutOfBoundsException e) {
                e.printErrorMessage();
            }
        }
    }

    public static void main(String[] args){
        greet();
        startList();
        goodbye();
    }
}
