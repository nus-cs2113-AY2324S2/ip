package junbot.common;
import junbot.parser.Parser;
import junbot.tasks.Deadline;
import junbot.tasks.Event;
import junbot.tasks.Todo;
import junbot.error.InvalidInputException;
import junbot.tasks.Task;
import junbot.ui.Ui;
import java.time.LocalDate;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;
    protected Ui ui;
    protected Parser parser;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        ui = new Ui();
        parser = new Parser();
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
        ui = new Ui();
        parser = new Parser();
    }

    public ArrayList<Task> getTasksList() {
        return this.tasks;
    }

    public int getSize() {
        return getTasksList().size();
    }

    /**
     * Adds a deadline task to the task list. This method parses the full user input and obtains the name,
     * and end date of the given task, and returns the newly added deadline task
     *
     * @param command The full deadline command inputted by user
     * @return The deadline task added to the list.
     * @throws InvalidInputException If the input is invalid.
     */
    public Task addDeadline(String command) throws InvalidInputException {
        String description;
        description = command.replace("deadline", "").trim();

        if (!description.contains("/by")) {
            throw new InvalidInputException("Include a /by for deadline");
        }

        String[] details = new String[2];
        details = description.split("/by", 2);

        String deadlineDescription = details[0].trim();
        String deadlineDateTimeString = details[1].trim();

        LocalDate deadlineDate = parser.parseDate(deadlineDateTimeString);

        Task userTask = new Deadline(deadlineDescription, deadlineDate);
        tasks.add(userTask);
        return userTask;
    }

    /**
     * Adds an event task to the task list. This method parses the full user input and obtains the name,
     * start date and end date of the given task, and returns the newly added event task
     *
     * @param command The full event command inputted by user
     * @return The event task added to the list.
     * @throws InvalidInputException If the input does not include a /from and /to
     */
    public Task addEvent(String command) throws InvalidInputException {
        String description;
        description = command.replace("event", "").trim();
        if (!description.contains("/from") || !description.contains("/to")) {
            throw new InvalidInputException("Include a /from and /to for event");
        }

        String[] details = new String[3];
        details = description.split("/from|/to", 3);

        String eventDescription = details[0].trim();
        String eventStartDateString = details[1].trim();
        String eventEndDateString = details[2].trim();

        LocalDate startDate = parser.parseDate(eventStartDateString);
        LocalDate endDate = parser.parseDate(eventEndDateString);

        Task userTask = new Event(eventDescription, startDate, endDate);
        tasks.add(userTask);
        return userTask;
    }

    /**
     * Adds a todo task to the task list. This method parses the entire full user input and obtains the name
     * of the todo task, and returns the newly added todo task
     *
     * @param description The full todo command inputted by user
     * @return The todo task added to the list.
     * @throws InvalidInputException If the input is does not have format todo <task>.
     */
    public Task addTodo(String command) throws InvalidInputException {
        String description;
        description = command.replace("todo", "").trim();
        if(description.isEmpty()){
            throw new InvalidInputException("Please state a task : todo <task>");
        }

        Task userTask = new Todo(description);
        tasks.add(userTask);
        return userTask;
    }

    /**
     * Marks a task as done in the task list.
     *
     * @param listPosition The position of the task in the list.
     * @return The task marked as done.
     */
    public Task markTaskInList(int listPosition) {
        Task task = this.getTasksList().get(listPosition);
        task.markTask();
        return task;
    }

    /**
     * Unmarks a previously marked task in the task list.
     *
     * @param listPosition The position of the task in the list.
     * @return The task unmarked.
     */
    public Task unmarkTaskInList(int listPosition) {
        Task task = this.getTasksList().get(listPosition);
        task.unmarkTask();
        return task;
    }

    /**
     * Finds a task in the task list containing the inputted keyword, returns an ArrayList of all found tasks
     *
     * @param keyword the word to find in task list
     * @return The ArrayList containing all found tasks
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            String taskDescription = tasks.get(i).getDescription();

            if (taskDescription.contains(keyword)){
                foundTasks.add(tasks.get(i));
            }
        }
        return foundTasks;
    }

    /**
     * Prints all matching tasks which contains the inputted keyword. Method first uses findTasks() function to create
     * a ArrayList containing all found tasks, before printing all elements of obtained ArrayList
     *
     * @param keyword the word to find in task list
     */
    public void printMatchingTasks(String keyword) {
        int taskNumber = 1;
        ArrayList<Task> foundTasks = findTasks(keyword);

        System.out.println("Here are the matching tasks in your list: ");
        ui.printDivider();
        for (Task foundTask : foundTasks) {
            System.out.print(taskNumber + ". ");
            System.out.println(foundTask);
            taskNumber += 1;
        }
        ui.printDivider();
    }
}
