package utilities.commands;

import main.Aragorn;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;
import ui.Constants;
import utilities.parser.InputParser;

public class AddTask {

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
