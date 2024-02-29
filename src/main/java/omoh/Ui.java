package omoh;

import omoh.customexceptions.EmptyTaskNumberException;
import omoh.customexceptions.EmptyTodoException;
import omoh.tasktypes.Deadline;
import omoh.tasktypes.Event;
import omoh.tasktypes.Task;
import omoh.tasktypes.Todo;

import java.util.Scanner;

public class Ui {
    /**
     * Reads user input from the console and performs corresponding actions based on the input.
     * The method continues reading input until the user enters "bye".
     * Actions performed based on input:
     * - If input is empty, prompts the user to enter a non-empty input.
     * - If input is "list", prints all tasks.
     * - If input starts with "deadline", adds a deadline task.
     * - If input starts with "todo", adds a todo task.
     * - If input starts with "event", adds an event task.
     * - If input starts with "mark", "unmark", or "delete", modifies task status or deletes a task.
     * - Otherwise, adds a general task.
     */
    public static void readUserInput() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equalsIgnoreCase("bye")) {
            if (line.trim().isEmpty()) {
                System.out.println("Please enter a non empty Input!");
            } else if (line.equalsIgnoreCase("list")) {
                Task.printAllTasks();
            } else if (line.startsWith("deadline")) {
                Deadline.addDeadline(line);
            }
            else if (line.startsWith("todo")) {
                try {
                    Todo.addTodo(line);
                } catch (EmptyTodoException e) {
                    e.printStackTrace();
                }
            }
            else if (line.startsWith("event")) {
                Event.addEvent(line);
            }
            else if (line.startsWith("mark") || line.startsWith("unmark") || line.startsWith("delete")) {
                try {
                    int taskNumber = Parser.extractTaskNumber(line);
                    Task.modifyDoneStateOrDelete(taskNumber, line);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter valid number without alphabets after mark or unmark or delete " +
                            "Example: mark 1");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please enter valid number within boundaries of list. " +
                            "Example: if list only has 2 items, dont enter beyond 2");
                } catch (EmptyTaskNumberException e) {
                    e.printStackTrace();
                }
            } else if (line.startsWith("find")) {
                Task.findMatchingTasks(line);
            }
            line = in.nextLine();
        }
    }
}
