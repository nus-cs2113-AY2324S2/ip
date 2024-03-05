package kurobot;

import kurobot.exceptions.InvalidDescriptionException;
import kurobot.exceptions.InvalidTimeException;

/**
 * Parser the user input to get data such as
 * task name, deadline and task number.
 */
public class Parser {

    private String input;
    private String taskName;
    private String deadline;
    private String start;
    private String end;
    private int index;

    public Parser(String input) {
        this.input = input.strip();
    }

    /**
     * Extract the command type from the given input, such as "todo" or "mark".
     *
     * @return Command type.
     */
    public String parserCommand(){
        String[] words = input.split(" ",2);
        String command = words[0];
        return command;
    }

    /**
     * Extract the name of the task from input and set the task name.
     *
     * @throws InvalidDescriptionException If nothing was given after command keyword.
     */
    public void parserToDo() throws InvalidDescriptionException {
        String[] words = input.split(" ",2);
        if (words.length < 2){
            throw new InvalidDescriptionException();
        }
        taskName = words[1];
    }

    /**
     * Extract the name of the task from input and set the task name.
     * Extract the deadline of the task and set the deadline.
     *
     * @throws InvalidDescriptionException If nothing was after command keyword.
     * @throws InvalidTimeException If nothing was after "/by" or no "/by".
     */
    public void parserDeadline() throws InvalidDescriptionException, InvalidTimeException{
        //check if description was given
        String[] words = input.split(" ",2);
        if (words.length < 2){
            throw new InvalidDescriptionException();
        }

        String description = words[1];

        //check if due date was given
        String[] phrases = description.split("/by", 2);
        if (phrases.length < 2){
            throw new InvalidTimeException();
        }
        taskName = phrases[0];
        deadline = phrases[1].strip();
    }

    /**
     * Extract the name of the task from input and set the task name.
     * Extract the start time and end time of the task and store it.
     *
     * @throws InvalidDescriptionException If nothing was after command keyword.
     * @throws InvalidTimeException If nothing was after "/from" or "/to", or those don't exist.
     */
    public void parserEvent() throws InvalidDescriptionException, InvalidTimeException {
        //check if description was given
        String[] words = input.split(" ",2);
        if (words.length < 2){
            throw new InvalidDescriptionException();
        }
        String description = words[1];

        //check if duration was given
        String[] phrases = description.split("/from",2);
        if (phrases.length < 2){
            throw new InvalidTimeException();
        }
        taskName = phrases[0];

        //check if both "from" and "to" was given
        String[] period = phrases[1].split("/to",2);
        if(period.length < 2){
            throw new InvalidTimeException();
        }
        start = period[0].strip();
        end = period[1].strip();
    }

    /**
     * Extract the index of the task that should be operated.
     * Use when command keyword is mark, unmark or delete.
     *
     * @throws InvalidDescriptionException If nothing was after command keyword.
     */
    public void parserTaskIndex() throws InvalidDescriptionException {
        //check if task number was given
        String[] words = input.split(" ");
        if (words.length != 2){
            throw new InvalidDescriptionException();
        }

        //get task number
        String taskIndex = words[1];
        index = Integer.parseInt(taskIndex);
    }

    public int getIndex() {
        return index;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }
}
