package junbot.common;

import junbot.tasks.Deadline;
import junbot.tasks.Event;
import junbot.tasks.Todo;
import junbot.error.InvalidInputException;
import junbot.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public ArrayList<Task> getTasksList() {
        return this.tasks;
    }

    public int getSize(){
        return getTasksList().size();
    }

    /**
     * Adds a deadline task to the task list. This method parses the full user input and obtains the name,
     * and end date of the given task, and returns the newly added deadline task
     *
     * @param description The full deadline command inputted by user
     * @return The deadline task added to the list.
     * @throws InvalidInputException If the input is invalid.
     */
    public Task addDeadline(String description) throws InvalidInputException {

        description = description.replace("deadline", "").trim();

        if (!description.contains("/by")) {
            throw new InvalidInputException("Include a /by for deadline");
        }

        String[] details = new String[2];
        details = description.split("/by", 2);

        Task userTask = new Deadline(details[0].trim(), details[1].trim());
        tasks.add(userTask);
        return userTask;
    }

    /**
     * Adds an event task to the task list. This method parses the full user input and obtains the name,
     * start date and end date of the given task, and returns the newly added event task
     *
     * @param description The full event command inputted by user
     * @return The event task added to the list.
     * @throws InvalidInputException If the input does not include a /from and /to
     */
    public Task addEvent(String description) throws InvalidInputException {
        description = description.replace("event", "").trim();
        if (!description.contains("/from") || !description.contains("/to")) {
            throw new InvalidInputException("Include a /from and /to for event");
        }

        String[] details = new String[3];
        details = description.split("/from|/to", 3);

        Task userTask = new Event(details[0].trim(), details[1].trim(), details[2].trim());
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
    public Task addTodo(String description) throws InvalidInputException {
        description = description.replace("todo", "").trim();
        if(description.isEmpty()){
            throw new InvalidInputException("Please state a task : todo <task>");
        }

        Task userTask = new Todo(description);
        tasks.add(userTask);
        return userTask;
    }

    /**
     * Checks if a given list position is valid.
     *
     * @param command The input command.
     * @return True if the list position < size of tasks list and > 0, false otherwise.
     */
    public boolean isValidListPosition(String command) {
        if (command == null) {
            return false;
        }
        try {
            int listPosition = Integer.parseInt(command);
            return listPosition <= this.tasks.size() && listPosition > 0;
        } catch (NumberFormatException e) {
            return false;
        }
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

}
