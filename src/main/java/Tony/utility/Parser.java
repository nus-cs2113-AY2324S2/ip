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
    public Parser(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Command parse(String line) throws IOException {
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
    public void checkArrayLength(String[] taskArrayString) throws TonyException {
        if (taskArrayString.length != 2) {
            throw new TonyException();
        }
    }

    public void checkNumberWithinRange(int num) throws TonyException {
        if (num > tasks.size()) {
            throw new TonyException();
        }
    }

    public void checkFirstCommandWord() {
        System.out.println("Please begin input with the following words: "
                + System.lineSeparator()
                + "'list / todo / deadline / event / delete / mark' ");
    }


}
