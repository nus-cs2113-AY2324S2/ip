package kurobot;

import kurobot.exceptions.InvalidDescriptionException;
import kurobot.exceptions.InvalidTimeException;

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

    public String parserCommand(){
        String[] words = input.split(" ",2);
        String command = words[0];
        return command;
    }

    public void parserToDo() throws InvalidDescriptionException {
        String[] words = input.split(" ",2);
        if (words.length < 2){
            throw new InvalidDescriptionException();
        }
        taskName = words[1];
    }

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
