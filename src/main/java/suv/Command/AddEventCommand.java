package suv.Command;

import suv.Helpers.Ui;
import suv.Task.Event;

import static suv.Task.TaskList.tasksList;

/**
 * The AddEventCommand class represents a command to add a new event task.
 * It takes an input string containing the description and time frame details of the event.
 */
public class AddEventCommand {
    final static int EVENT_KEYWORD_END_INDEX = 5;
    final static int TO_KEYWORD_END_INDEX = 2;
    final static int FROM_KEYWORD_END_INDEX = 4;
    final static String LINE = "____________________________________________________________\n";

    /**
     * Constructs a new AddEventCommand object and performs the addition of the event task.
     *
     * @param input The input string containing the description and time frame details of the event.
     * @throws SuvException If there is an error while executing the add event command.
     */
    public AddEventCommand(String input) throws SuvException{
        if(input.trim().length() > 5 && input.contains("/from") &&  input.contains("/to")){
            String from = input.split("/")[1].trim().substring(FROM_KEYWORD_END_INDEX).trim();
            String to = input.split("/")[2].trim().substring(TO_KEYWORD_END_INDEX).trim();
            String description = input.split("/")[0].substring(EVENT_KEYWORD_END_INDEX).trim();

            Event newTask = new Event(description, from, to);
            tasksList.add(newTask);

            Ui.printAddEventMessage(newTask, tasksList.size());
        } else {
            throw new SuvException(LINE + "Oh no! You are missing the suv.Task.Event details\n" + LINE);
        }
    }
}
