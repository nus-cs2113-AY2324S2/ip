import helperFunctions.InvalidParamsException;
import helperFunctions.PrintHelper;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class RecrBad {

    /**
     * Prints each Task in Task array
     *
     * @param tasks Existing Task array
     */
    private static void displayList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()){
            System.out.println("List is empty!" + System.lineSeparator() + PrintHelper.printCommandsList());
            return;
        }
        int count = 1;
        for (Task task : tasks) {
            System.out.println(count + ".[" + task.getType() + "]["
                    + task.getStatus() + "] " + task.getDescription());
            count += 1;
        }
    }

    /**
     * Prints a specific task in Tasks array
     *
     * @param tasks existing Task array
     * @param index index of specific task
     */
    private static void displayListItem(ArrayList<Task> tasks, int index) {
        System.out.println("[" + tasks.get(index).getType() + "]["
                + tasks.get(index).getStatus() + "] "
                + tasks.get(index).getDescription());
    }

    private static void addEventTask(String[] req, String line, ArrayList<Task> tasks) throws InvalidParamsException {
        final int startIndexOfDescription = req[0].length() + 1; // req[0].equals(event)

        int indexTo = line.lastIndexOf('/');
        int indexFrom = line.lastIndexOf('/', indexTo - 1); // counts from back
        // checks for invalid input
        if (indexFrom == -1 || indexTo == -1) {
            throw new InvalidParamsException("No event arguments: Add a '/', then the startTime, followed by a '/' and the endTime");
        }
        if (startIndexOfDescription > indexFrom - 1) {
            throw new InvalidParamsException(("no event description"));
        }
        // process input as Event object
        String description = line.substring(startIndexOfDescription, indexFrom - 1);
        String timeRange = " (from: " + line.substring(indexFrom + 1, indexTo) +
                "to " + line.substring(indexTo + 1) + ")";
        // add to tasks
        tasks.add(new Event(description, timeRange));
        System.out.println("Event added!");
        displayListItem(tasks, tasks.size() - 1);
        System.out.println(System.lineSeparator() + "Congrats, now have " + tasks.size() + " tasks");
    }

    private static void addDeadlineTask(String[] req, String line, ArrayList<Task> tasks) throws InvalidParamsException {
        final int startIndexOfDescription = req[0].length() + 1; // req[0].equals(deadline);

        int indexDeadline = line.lastIndexOf('/');
        // checks for invalid input
        if (indexDeadline == -1) {
            throw new InvalidParamsException("No deadline argument: Add a parameter '/' followed by the deadline");
        }
        if (startIndexOfDescription > indexDeadline - 1) {
            throw new InvalidParamsException("No deadline description");
        }
        // process input as Deadline object
        String deadline = "(by: " + line.substring(indexDeadline + 1) + ")";
        String description = line.substring(startIndexOfDescription, indexDeadline);
        // add to tasks
        tasks.add(new Deadline(description, deadline));
        System.out.println("Deadline added!");
        displayListItem(tasks, tasks.size() - 1);
        System.out.println(System.lineSeparator() + "Congrats, now have " + tasks.size() + " tasks");
    }

    private static void addTodoTask(String[] req, String line, ArrayList<Task> tasks) throws InvalidParamsException {
        final int startIndexOfDescription = req[0].length() + 1; // req[0].equals(TODO);

        // checks for invalid input
        if (startIndexOfDescription > line.length() - 1) {
            throw new InvalidParamsException("No task description");
        }
        // process input
        String description = line.substring(startIndexOfDescription);
        // add to tasks
        tasks.add(new Todo(description));

        System.out.println("Todo added!");
        displayListItem(tasks, tasks.size() - 1);
        System.out.println(System.lineSeparator() + "Congrats, now have " + tasks.size() + " tasks");
    }

    private static void deleteOperation(ArrayList<Task> tasks, String[] req) throws InvalidParamsException {
        // check for input validity
        if (req.length < 2) {
            throw new InvalidParamsException("Invalid delete operation");
        }
        // process input
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(req[1]);
        } catch (NumberFormatException e) {
            throw new InvalidParamsException("Invalid non-integer delete index");
        }
        if (taskIndex > tasks.size() || taskIndex < 1) {
            throw new InvalidParamsException("Out of bounds delete index");
        }
        // delete Task
        System.out.println("Good riddance, task deleted!");
        displayListItem(tasks, taskIndex - 1);
        tasks.remove(taskIndex - 1);
    }

    /**
     * Marks Task as done or not done
     *
     * @param tasks  existing Tasks array
     * @param req    String[] input from user
     * @param isMark type of operation: mark or unmark
     */
    private static void markOperation(ArrayList<Task> tasks, String[] req, boolean isMark) throws InvalidParamsException {
        // check for input validity
        if (req.length < 2) {
            throw new InvalidParamsException("invalid mark/ unmark operation");
        }
        int taskNum = Integer.parseInt(req[1]);
        if (tasks.size() < taskNum || taskNum < 1) {
            throw new InvalidParamsException("No such taskNum");
        }
        // process input
        if (isMark) { // (isMark = true) == mark as done
            tasks.get(taskNum - 1).markAsDone();
            System.out.println("Has marked task" + taskNum + ":");
        } else {
            tasks.get(taskNum - 1).markAsNotDone();
            System.out.println("Has unmarked task" + taskNum + ":");
        }
        displayListItem(tasks, taskNum - 1);
    }
    private static boolean processUserInput(ArrayList<Task> tasks, String line) throws InvalidParamsException {
        String[] req = line.split(" ");

        if (req[0].equalsIgnoreCase("BYE")) {
            PrintHelper.sayBye();
            return false; // EXITS loop
        }
        if (req[0].equalsIgnoreCase("LIST")) {
            displayList(tasks);
        } else if (req[0].toUpperCase().contains("MARK")) { // both unmark & mark contains 'mark'
            boolean isMark = !line.toUpperCase().contains("UNMARK");
            markOperation(tasks, req, isMark);
        } else if (req[0].equalsIgnoreCase("DELETE")) {
            deleteOperation(tasks, req);
        } else if (req[0].equalsIgnoreCase("TODO")) {
            addTodoTask(req, line, tasks); // change tasks to ArrayList<TAsk>, f void now
        } else if (req[0].equalsIgnoreCase("DEADLINE")) {
            addDeadlineTask(req, line, tasks);
        } else if (req[0].equalsIgnoreCase("EVENT")) {
            addEventTask(req, line, tasks);
        } else {
            throw new InvalidParamsException("No such command." + System.lineSeparator() + PrintHelper.printCommandsList());
        }
        return true;
    }

    /**
     * Input
     * [any text]           to addToList,
     * list                 to displayList,
     * mark/unmark [index]  to mark item
     */
    public static void main(String[] args) { // Ctrl B to see def, shift F10 to run, Ctrl Alt L reformat
        Scanner in = new Scanner(System.in);
        PrintHelper.sayHi();
        ArrayList<Task> tasks = new ArrayList<>();
        boolean isTrue = true;

        while (isTrue) {
            PrintHelper.printLine();
            String line = in.nextLine(); // reads input

            try {
                isTrue = processUserInput(tasks, line);
            } catch (InvalidParamsException e) {
                System.out.println(e.getMessage()); // prints out error message
            }
        }
    }
}
