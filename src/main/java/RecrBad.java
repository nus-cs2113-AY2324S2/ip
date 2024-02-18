import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class RecrBad {

    private static Task[] addEventTask(final int startIndexOfDescription, String line, Task[] tasks) throws InvalidParamsException {
        Task[] moreTasks = Arrays.copyOf(tasks, tasks.length + 1);
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
        moreTasks[tasks.length] = new Event(description, timeRange); //append at last elem
        System.out.println("Event added!");
        return moreTasks;
    }

    private static Task[] addDeadlineTask(int startIndexOfDescription, String line, Task[] tasks) throws InvalidParamsException {
        Task[] moreTasks = Arrays.copyOf(tasks, tasks.length + 1);
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
        moreTasks[tasks.length] = new Deadline(description, deadline); //append at last elem
        System.out.println("Deadline added!");
        return moreTasks;
    }

    private static Task[] addTodoTask(final int startIndexOfDescription, String line, Task[] tasks) throws InvalidParamsException {
        // checks for invalid input
        if (startIndexOfDescription > line.length() - 1) {
            throw new InvalidParamsException("No task description");
        }
        Task[] moreTasks = Arrays.copyOf(tasks, tasks.length + 1);
        String description = line.substring(startIndexOfDescription);
        moreTasks[moreTasks.length - 1] = new Todo(description); //append at last elem
        System.out.println("Todo added!");
        return moreTasks;
    }

    /**
     * Adds new Task to Tasks array
     *
     * @param tasks existing Tasks array
     * @param line  string input from user
     * @return moreTasks array with added Task
     */
    private static Task[] addToList(Task[] tasks, String[] req, String line) throws InvalidParamsException {
        // copies and returns longer String[tasks.length+1]
        Task[] moreTasks;
        final int startIndexOfDescription = req[0].length() + 1; //assume got space after TODO/deadline/event

        // process request
        if (req[0].equalsIgnoreCase("TODO")) {
            moreTasks = addTodoTask(startIndexOfDescription, line, tasks);
        } else if (req[0].equalsIgnoreCase("DEADLINE")) {
            moreTasks = addDeadlineTask(startIndexOfDescription, line, tasks);
        } else if (req[0].equalsIgnoreCase("EVENT")) {
            moreTasks = addEventTask(startIndexOfDescription, line, tasks);
        } else {
            // request does not start with todo, deadline, or event
            throw new InvalidParamsException("No such command." + System.lineSeparator() + PrintHelper.printCommandsList());
        }

        displayListItem(moreTasks, tasks.length);
        System.out.println(System.lineSeparator() + "Congrats, now have " + moreTasks.length + " tasks");

        return moreTasks;
    }

    /**
     * Prints each Task in Task array
     *
     * @param tasks Existing Task array
     */
    private static void displayList(Task[] tasks) {
        int count = 1;
        for (Task task : tasks) {
            System.out.println(count + ".[" + task.getType() + "]["
                    + task.getStatus() + "] " + task.description);
            count += 1;
        }
    }

    /**
     * Prints a specific task in Tasks array
     *
     * @param tasks existing Task array
     * @param index index of specific task
     */
    private static void displayListItem(Task[] tasks, int index) {
        System.out.println("[" + tasks[index].getType() + "]["
                + tasks[index].getStatus() + "] "
                + tasks[index].description);
    }

    /**
     * Marks Task as done or not done
     *
     * @param tasks  existing Tasks array
     * @param req    String[] input from user
     * @param isMark type of operation: mark or unmark
     */
    private static void markOperation(Task[] tasks, String[] req, boolean isMark) {
        if (req.length < 1) {
            System.out.println("invalid mark/ unmark operation");
            return;
        }
        int taskNum = Integer.parseInt(req[1]);
        if (tasks.length < taskNum) {
            System.out.println("No such taskNum");
            return;
        }
        if (isMark) { // MARK #taskNum as done
            tasks[taskNum - 1].markAsDone();
            System.out.println("Has marked task" + taskNum + ":");
        } else {
            tasks[taskNum - 1].markAsNotDone();
            System.out.println("Has unmarked task" + taskNum + ":");
        }
        displayListItem(tasks, taskNum - 1);
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
        Task[] tasks = new Task[]{};

        while (true) {
            PrintHelper.printLine();
            String line = in.nextLine(); // reads input
            String[] req = line.split(" ");

            if (req[0].equalsIgnoreCase("BYE")) {
                PrintHelper.sayBye();
                break; // EXITS loop
            }
            if (req[0].equalsIgnoreCase("LIST")) {
                displayList(tasks);
                continue; // GOTO next iteration of loop
            }
            if (req[0].toUpperCase().contains("MARK")) { // both unmark & mark contains 'mark'
                boolean isMark = !line.toUpperCase().contains("UNMARK");
                markOperation(tasks, req, isMark);
                continue; //GOTO next iteration of loop
            }

            try {
                tasks = addToList(tasks, req, line);
            } catch (InvalidParamsException e) {
                System.out.println(e.getMessage()); // prints out error message
            }

        }
    }
}
