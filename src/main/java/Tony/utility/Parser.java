package Tony.utility;

import Tony.TonyException;
import Tony.command.*;
import Tony.task.Deadline;
import Tony.task.Event;
import Tony.task.Task;
import Tony.task.Todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    protected static final String SEPARATOR = " \\| ";
    private final ArrayList<Task> tasks;

    /**
     * Represents a <code>Parser</code> object that takes in user input
     * and processes the command accordingly.
     * @param tasks the current list of <code>tasks</code> to add each tasks into.
     */
    public Parser(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses user input and process the command based on the first word entered
     * @param line is the String that user entered
     * @return A new <code>Command</code> object created according to user command.
     */

    public Command parse(String line) {
        Parser parser = new Parser(tasks);
        if (line.equals("bye")) {
            return new ByeCommand();
        } else if (line.equals("list")) {
            return new ListCommand();
        } else if (line.contains("mark")) {
            return new MarkCommand(line, parser);
        } else if (line.startsWith("todo")) {
            return new ToDoCommand(line, parser);
        } else if (line.startsWith("deadline")) {
            return new DeadlineCommand(line, parser);
        } else if (line.startsWith("event")) {
            return new EventCommand(line, parser);
        }else if (line.startsWith("delete")) {
            return new DeleteCommand(line, parser);
        } else {
            return new CommandChecker(parser);
        }
    }

    /**
     * Processes each line in the file, split them with the <code>SEPARATOR</code> regex and
     * format them to add them back into a <code>tasks</code> list to be used during program run.
     * @param scanner Scanner object that reads the data from each line in the file.
     * @param lineCount Int counter helps to track index to mark task in the <code>tasks</code>
     * list if task <code>isDone</code>.
     */
    public void extractLineFromFile(Scanner scanner, int lineCount) {
        String lineFromFile = scanner.nextLine();
        String[] taskSplit = lineFromFile.split(SEPARATOR);
        String taskSymbol = taskSplit[0].trim();
        boolean isDone = taskSplit[1].trim().equals("1");
        switch (taskSymbol) {
        case "T":
            tasks.add(new Todo(taskSplit[2].trim()));
            break;
        case "D":
            tasks.add(new Deadline(taskSplit[2].trim(), taskSplit[3].trim()));
            break;
        case "E":
            String[] fromAndTo = taskSplit[3].split("to");
            tasks.add(new Event(taskSplit[2].trim(), fromAndTo[0].trim(), fromAndTo[1].trim()));
            break;
        }
        if (isDone) {
            tasks.get(lineCount).markDone();
        } else {
            tasks.get(lineCount).markNotDone();
        }
    }

    /**
     * Checks if user input meet minimum length of words
     * @param taskArrayString array of user input split by specific command keyword to check its array length
     * @throws TonyException If array length does not meet the minimum requirement.
     */
    public void checkArrayLength(String[] taskArrayString) throws TonyException {
        if (taskArrayString.length != 2) {
            throw new TonyException();
        }
    }

    /**
     * Checks if the number supplied by user to mark/delete is within range of task list.
     * @param num The number supplied by user
     * @throws TonyException If number is not found within the <code>tasks</code> list
     */
    public void checkNumberWithinRange(int num) throws TonyException {
        if (num > tasks.size()) {
            throw new TonyException();
        }
    }

    /**
     * Checks if the command entered by user is Not one of the specified word the program can process.
     */
    public void checkFirstCommandWord() {
        System.out.println("Please begin input with the following words: "
                + System.lineSeparator()
                + "'list / todo / deadline / event / delete / mark' ");
    }


}
