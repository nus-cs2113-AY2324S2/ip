package utilities.commands;

import main.Aragorn;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;
import ui.Constants;
import utilities.parser.InputParser;

/**
 * Methods to add todo, deadline, event task to the list.
 */
public class AddTask {

    /**
     * Adds an event task into the task list and prints the number of tasks remaining.
     *
     * @param input Parsed input containing event description, start condition and end condition.
     * @param remainingTasks Number of tasks that remain incomplete.
     */
    protected static void eventCommand(InputParser input, int remainingTasks) {
        try {
            if (input.getSplitInput()[0].isEmpty() || input.getSplitInput()[1].isEmpty() || input.getSplitInput()[2].isEmpty()) {
                return;
            }
            Event newEvent = new Event(input.getSplitInput()[0].trim(), false, input.getSplitInput()[1].trim(), input.getSplitInput()[2].trim());
            Aragorn.getList().add(newEvent);
            Constants.printAddTask(newEvent);
            remainingTasks += 1;
            Constants.printRemainingTasks(remainingTasks, Aragorn.getList().size());
        } catch (NullPointerException e) {
            return;
        }
    }

    /**
     * Adds a deadline task into the task list and prints the number of tasks remaining.
     *
     * @param input Parsed input containing deadline task description and the deadline date.
     * @param remainingTasks Number of tasks that remain incomplete.
     */
    protected static void deadlineCommand(InputParser input, int remainingTasks) {
        try {
            if (input.getSplitInput()[0].isEmpty() || input.getSplitInput()[1].isEmpty()) {
                return;
            }
            Deadline newDeadline = new Deadline(input.getSplitInput()[0].trim(), false, input.getSplitInput()[1].trim());
            Aragorn.getList().add(newDeadline);
            Constants.printAddTask(newDeadline);
            remainingTasks += 1;
            Constants.printRemainingTasks(remainingTasks, Aragorn.getList().size());
        } catch (NullPointerException e) {
            return;
        }
    }

    /**
     * Adds a todo task into the task list and prints the number of tasks remaining.
     *
     * @param input Parsed input containing todo task description.
     * @param remainingTasks Number of tasks that remain incomplete.
     */
    protected static void todoCommand(InputParser input, int remainingTasks) {
        if (input.getSplitInput()[0].trim().isEmpty()) {
            return;
        }
        ToDo newTask = new ToDo(input.getSplitInput()[0].trim(), false);
        Aragorn.getList().add(newTask);
        Constants.printAddTask(newTask);
        remainingTasks += 1;
        Constants.printRemainingTasks(remainingTasks, Aragorn.getList().size());
    }
}
