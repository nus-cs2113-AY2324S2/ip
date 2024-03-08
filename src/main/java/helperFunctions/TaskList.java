package helperFunctions;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private final int ZERO_INDEX = 1;

    /**
     * Initializes ArrayList of Task objects
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Prints all task in Tasks
     *
     * @return string of all tasks
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
     * @param index index of specific task (zero-indexed)
     */
    private String displayListItem(int index) {
        return ((index + ZERO_INDEX) + ". [" + tasks.get(index).getType() + "]["
                + tasks.get(index).getStatus() + "] "
                + tasks.get(index).getDescription()
                + System.lineSeparator());
    }

    /**
     * Adds Event task to Tasks
     *
     * @param userInputInParts separates each word in userInput
     * @param userInput is the original CLI user input
     * @param FILE_NAME to store tasks in
     * @param isReadMode specifies if reading from FILE_NAME or writing to it
     * @throws InvalidParamsException if invalid/ missing event arguments
     */
    protected void addEventTask(String[] userInputInParts, String userInput, String FILE_NAME, boolean isReadMode) throws InvalidParamsException {
        final int startIndexOfDescription = userInputInParts[0].length() + ZERO_INDEX; // userInputInParts[0].equals(event)
        final int NOT_FOUND = -1;

        int indexTo = userInput.lastIndexOf('/');
        int indexFrom = userInput.lastIndexOf('/', indexTo - ZERO_INDEX); // counts from back
        // checks for invalid input
        if (indexFrom == NOT_FOUND || indexTo == NOT_FOUND) {
            throw new InvalidParamsException("No event arguments: Add a '/', then the startTime, followed by a '/' and the endTime");
        }
        if (startIndexOfDescription > indexFrom - ZERO_INDEX) {
            throw new InvalidParamsException(("no event description"));
        }
        // process input as Event object
        String description = userInput.substring(startIndexOfDescription, indexFrom - ZERO_INDEX);
        String timeRange = " (from: " + userInput.substring(indexFrom + ZERO_INDEX, indexTo) +
                "to " + userInput.substring(indexTo + ZERO_INDEX) + ")";
        // add to tasks
        Task newTask = new Event(description, timeRange);
        tasks.add(newTask);
        if (isReadMode) {
            return; // no need execute code below (for writing only)
        }
        Storage.appendToFile(FILE_NAME, displayListItem(tasks.indexOf(newTask)));
        System.out.println("Event added!");
        System.out.println(displayListItem(tasks.size() - ZERO_INDEX));
        System.out.println("Congrats, now have " + tasks.size() + " tasks");
    }

    /**
     * Adds Deadline to Tasks. Ideal deadline format is "yyyy/mm/dd", which
     * will be converted to "MMM d yyyy" format
     *
     * @param userInputInParts separates each word in userInput
     * @param userInput is the original CLI user input
     * @param FILE_NAME to store tasks in
     * @param isReadMode specifies if reading from FILE_NAME or writing to it
     * @throws InvalidParamsException if invalid/ missing deadline arguments
     */
    protected void addDeadlineTask(String[] userInputInParts, String userInput, String FILE_NAME, boolean isReadMode) throws InvalidParamsException {
        final int startIndexOfDescription = userInputInParts[0].length() + ZERO_INDEX; // userInputInParts[0].equals(deadline);
        final int NOT_FOUND = -1;
        int indexDeadline = userInput.indexOf('/');
        // checks for invalid input
        if (indexDeadline == NOT_FOUND) {
            throw new InvalidParamsException("No deadline argument: Add a parameter '/' followed by the deadline");
        }
        if (startIndexOfDescription > indexDeadline - ZERO_INDEX) {
            throw new InvalidParamsException("No deadline description");
        }
        // process input as Deadline object
        String deadline = "(by: ";
        if (Parser.isValidDeadline(userInput.substring(indexDeadline + ZERO_INDEX))) {
            deadline += Parser.formatDeadline(userInput.substring(indexDeadline + ZERO_INDEX));
        } else {
            deadline += userInput.substring(indexDeadline + ZERO_INDEX);
        }
        deadline += ")";
        String description = userInput.substring(startIndexOfDescription, indexDeadline);
        // add to tasks
        Task newTask = new Deadline(description, deadline);
        tasks.add(newTask);

        if (isReadMode) {
            return; // no need execute code below (for writing only)
        }
        Storage.appendToFile(FILE_NAME, displayListItem(tasks.indexOf(newTask)));
        System.out.println("Deadline added!");
        System.out.println(displayListItem(tasks.size() - ZERO_INDEX));
        System.out.println("Congrats, now have " + tasks.size() + " tasks");
    }

    /**
     *
     * @param userInputInParts separates each word in userInput
     * @param userInput is the original CLI user input
     * @param FILE_NAME to store tasks in
     * @param isReadMode specifies if reading from FILE_NAME or writing to it
     * @throws InvalidParamsException if invalid/ missing t0do arguments
     */
    protected void addTodoTask(String[] userInputInParts, String userInput, String FILE_NAME, boolean isReadMode) throws InvalidParamsException {
        final int startIndexOfDescription = userInputInParts[0].length() + ZERO_INDEX; // userInputInParts[0].equals(TODO);

        // checks for invalid input
        if (startIndexOfDescription > userInput.length() - ZERO_INDEX) {
            throw new InvalidParamsException("No task description");
        }
        // process input
        String description = userInput.substring(startIndexOfDescription);
        // add to tasks
        Task newTask = new Todo(description);
        tasks.add(newTask);
        if (isReadMode) {
            // no need execute code below (for writing only)
            return;
        }
        Storage.appendToFile(FILE_NAME, displayListItem(tasks.indexOf(newTask)));

        System.out.println("Todo added!");
        System.out.println(displayListItem(tasks.size() - ZERO_INDEX));
        System.out.println("Congrats, now have " + tasks.size() + " tasks");
    }

    /**
     * Deletes a specific task by index
     *
     * @param userInputInParts separates each word in userInput
     * @param FILE_NAME to store tasks in
     * @throws InvalidParamsException if invalid/ missing delete arguments
     */
    protected void deleteOperation(String[] userInputInParts, String FILE_NAME) throws InvalidParamsException {
        final int CORRECT_LENGTH = 2; // arguments: 1 delete command + 1 taskIndex

        // check for input validity
        if (userInputInParts.length < CORRECT_LENGTH) {
            throw new InvalidParamsException("Invalid delete operation");
        }
        // process input
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(userInputInParts[CORRECT_LENGTH - ZERO_INDEX]);
        } catch (NumberFormatException e) {
            throw new InvalidParamsException("Invalid non-integer delete index");
        }
        if (taskIndex > tasks.size() || taskIndex < (CORRECT_LENGTH - ZERO_INDEX)) {
            throw new InvalidParamsException("Out of bounds delete index");
        }
        // delete Task
        System.out.println("Good riddance, task deleted!");
        System.out.println(displayListItem(taskIndex - ZERO_INDEX));
        tasks.remove(taskIndex - ZERO_INDEX);
        Storage.writeToFile(FILE_NAME, displayList());
        System.out.println("Congrats, now have " + tasks.size() + " tasks");
    }


    /**
     * Marks a Task or unmarks a task
     *
     * @param userInputInParts separates each word in userInput
     * @param isMark if true, marks task, else unmarks it
     * @param FILE_NAME to store tasks in
     * @param isReadMode specifies if reading from FILE_NAME or writing to it
     * @throws InvalidParamsException if invalid/ missing mark arguments
     */
    protected void markOperation(String[] userInputInParts, boolean isMark, String FILE_NAME, boolean isReadMode) throws InvalidParamsException {
        final int CORRECT_LENGTH = 2; // arguments: 1 mark command + 1 taskIndex
        // check for input validity
        if (userInputInParts.length < CORRECT_LENGTH) {
            throw new InvalidParamsException("invalid mark/ unmark operation");
        }
        int taskNum;
        try {
            taskNum = Integer.parseInt(userInputInParts[CORRECT_LENGTH - ZERO_INDEX]);
            if (tasks.size() < taskNum || taskNum < (CORRECT_LENGTH - ZERO_INDEX)) {
                throw new InvalidParamsException("No such taskNum");
            }
        } catch (NumberFormatException e) {
            throw new InvalidParamsException("Invalid taskNum");
        }

        // process input
        if (isMark) { // (isMark = true) == mark as done
            tasks.get(taskNum - ZERO_INDEX).markAsDone();
        } else {
            tasks.get(taskNum - ZERO_INDEX).markAsNotDone();
        }
        if (isReadMode) {
            // no need to execute code below (for writing only)
            return;
        }
        // mark tasks
        Storage.writeToFile(FILE_NAME, displayList());
        String mark = isMark ? "marked" : "unmarked";
        System.out.println("Has " + mark + " task" + taskNum + ":");
        System.out.print(displayListItem(taskNum - ZERO_INDEX));
    }

    /**
     * Prints tasks containing a keyword
     *
     * @param userInputInParts separates each word in userInput
     * @throws InvalidParamsException if invalid/ missing find arguments
     */
    public void findOperation(String[] userInputInParts) throws InvalidParamsException {
        final int CORRECT_LENGTH = 2; // arguments: 1 find command + 1 findKeyword

        // check for input validity
        if (userInputInParts.length != CORRECT_LENGTH) {
            throw new InvalidParamsException("invalid find operation");
        }
        String findKeyword = userInputInParts[CORRECT_LENGTH - ZERO_INDEX];
        String tasksToPrint = "";
        int taskIndex = 0;
        for (Task task : tasks) {
            if (task.getDescription().contains(findKeyword)) {
                tasksToPrint += displayListItem(taskIndex);
            }
            taskIndex += 1;
        }
        if (tasksToPrint.isEmpty()) {
            System.out.println("No tasks found");
            return;
        }
        System.out.print(tasksToPrint);
    }
}
