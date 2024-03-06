package helperFunctions;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() throws InvalidParamsException {
        if (true) {
            this.tasks = new ArrayList<>();
            System.out.println("arraylist is " + tasks.toString());
        } else {
            throw new InvalidParamsException("error in tasklist");
        }
    }

    /**
     * Prints each Task in Task array
     */
    protected String displayList() {
        if (tasks.isEmpty()) {
            return "List is empty!" + System.lineSeparator()
                    + Ui.printCommandsList() + System.lineSeparator();
        }
        int count = 0;
        StringBuilder fullList = new StringBuilder();
        while (count != tasks.size()) {
            fullList.append(displayListItem(count));
            count += 1;
        }
        return fullList.toString();
    }

    /**
     * Returns a task in String format
     *
     * @param index index of specific task
     */
    private String displayListItem(int index) { // change from void to string
        return ((index + 1) + ". [" + tasks.get(index).getType() + "]["
                + tasks.get(index).getStatus() + "] "
                + tasks.get(index).getDescription()
                + System.lineSeparator());
    }

    protected void addEventTask(String[] req, String line, String FILE_PATH, boolean isReadMode) throws InvalidParamsException {
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
        Task newTask = new Event(description, timeRange);
        tasks.add(newTask);
        if (isReadMode) {
            return; // no need execute code below (for writing only)
        }
        Storage.appendToFile(FILE_PATH, displayListItem(tasks.indexOf(newTask)));
        System.out.println("Event added!");
        System.out.println(displayListItem(tasks.size() - 1));
        System.out.println("Congrats, now have " + tasks.size() + " tasks");
    }

    protected void addDeadlineTask(String[] req, String line, String FILE_PATH, boolean isReadMode) throws InvalidParamsException {
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
        Task newTask = new Deadline(description, deadline);
        tasks.add(newTask);

        if (isReadMode) {
            return; // no need execute code below (for writing only)
        }
        Storage.appendToFile(FILE_PATH, displayListItem(tasks.indexOf(newTask)));
        System.out.println("Deadline added!");
        System.out.println(displayListItem(tasks.size() - 1));
        System.out.println("Congrats, now have " + tasks.size() + " tasks");
    }

    protected void addTodoTask(String[] req, String line, String FILE_PATH, boolean isReadMode) throws InvalidParamsException {
        final int startIndexOfDescription = req[0].length() + 1; // req[0].equals(TODO);

        // checks for invalid input
        if (startIndexOfDescription > line.length() - 1) {
            throw new InvalidParamsException("No task description");
        }
        // process input
        String description = line.substring(startIndexOfDescription);
        // add to tasks
        Task newTask = new Todo(description);
        tasks.add(newTask);
        if (isReadMode) {
            // no need execute code below (for writing only)
            return;
        }
        Storage.appendToFile(FILE_PATH, displayListItem(tasks.indexOf(newTask)));

        System.out.println("Todo added!");
        System.out.println(displayListItem(tasks.size() - 1));
        System.out.println("Congrats, now have " + tasks.size() + " tasks");
    }

    protected void deleteOperation(String[] req, String FILE_PATH) throws InvalidParamsException {
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
        System.out.println(displayListItem(taskIndex - 1));
        tasks.remove(taskIndex - 1);
        Storage.writeToFile(FILE_PATH, displayList());
        System.out.println("Congrats, now have " + tasks.size() + " tasks");
    }


    /**
     * Marks Task as done or not done
     *
     * @param req    String[] input from user
     * @param isMark type of operation: mark or unmark
     */
    protected void markOperation( String[] req, boolean isMark, String FILE_PATH, boolean isReadMode) throws InvalidParamsException {
        // check for input validity
        if (req.length < 2) {
            throw new InvalidParamsException("invalid mark/ unmark operation");
        }
        int taskNum;
        try {
            taskNum = Integer.parseInt(req[1]);
            if (tasks.size() < taskNum || taskNum < 1) {
                throw new InvalidParamsException("No such taskNum");
            }
        } catch (NumberFormatException e) {
            throw new InvalidParamsException("Invalid taskNum");
        }

        // process input
        if (isMark) { // (isMark = true) == mark as done
            tasks.get(taskNum - 1).markAsDone();
        } else {
            tasks.get(taskNum - 1).markAsNotDone();
        }
        if (isReadMode) {
            // no need to execute code below (for writing only)
            return;
        }
        // mark tasks
        Storage.writeToFile(FILE_PATH, displayList());
        String mark = isMark ? "marked" : "unmarked";
        System.out.println("Has " + mark + " task" + taskNum + ":");
        System.out.print(displayListItem(taskNum - 1));
    }
}
