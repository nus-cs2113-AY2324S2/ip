package baymax;

import exceptions.InvalidDeadlineSyntaxException;
import exceptions.InvalidEventSyntaxException;
import exceptions.InvalidFindTaskException;
import exceptions.InvalidTodoSyntaxException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ui/User Interface deals with interactions with the user
 */

public class Ui {

    /**
     * Scans user's input and prints replies specific to the input.
     * The tasks argument may be either loaded from existing
     * baymax.txt file or newly created.
     *
     * @param tasks a ArrayList of the Task class
     */
    public static void handleUserInput(ArrayList<Task> tasks) {
        Scanner input = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            String userInput = input.nextLine().trim();

            try {
                if (userInput.equalsIgnoreCase("bye")) {
                    break;
                } else if (userInput.equalsIgnoreCase("list")) {
                    Printer.printTaskList(tasks);
                } else if (userInput.startsWith("mark")) {
                    int index = Parser.obtainIndexToMark(userInput);
                    tasks.get(index).markAsDone();
                    Printer.printMark(tasks.get(index));
                } else if (userInput.startsWith("unmark")) {
                    int index = Parser.obtainIndexToUnmark(userInput);
                    tasks.get(index).unmarkDone();
                    Printer.printUnmark(tasks.get(index));
                } else if (userInput.startsWith("todo")) {
                    TaskList.addTodo(userInput, tasks);
                    Printer.printAddedTask(tasks);
                } else if (userInput.startsWith("deadline")) {
                    TaskList.addDeadline(userInput, tasks);
                    Printer.printAddedTask(tasks);
                } else if (userInput.startsWith("event")) {
                    TaskList.addEvent(userInput, tasks);
                    Printer.printAddedTask(tasks);
                } else if (userInput.startsWith("delete")) {
                    Printer.printDelete(tasks, Parser.obtainDeleteIndex(userInput));
                    TaskList.deleteTask(Parser.obtainDeleteIndex(userInput), tasks);
                } else {
                    Printer.printUnknownInput();
                }
            } catch (InvalidTodoSyntaxException e) {
                Printer.handleInvalidTodoSyntaxException(e);
            } catch (InvalidDeadlineSyntaxException e) {
                Printer.handleInvalidDeadlineSyntaxException(e);
            } catch (InvalidEventSyntaxException e) {
                Printer.handleInvalidEventSyntaxException(e);
            }


        }


    }


}
