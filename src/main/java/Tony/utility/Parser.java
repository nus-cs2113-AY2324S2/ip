package Tony.utility;

import Tony.FileManager.FileSaver;
import Tony.TonyException;
import Tony.command.Command;
import Tony.command.ListCommand;
import Tony.command.MarkCommand;
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
    private final FileSaver fileSaver;
    private final Ui ui;
    public Parser(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.ui = new Ui(this.tasks);
        this.fileSaver = new FileSaver(this.tasks);
    }

    public Command parse(String line) throws IOException {
        Parser parser = new Parser(tasks);
        if (line.equals("bye")) {
            ui.printByeMessage();
        } else if (line.equals("list")) {
            return new ListCommand();
        } else if (line.contains("mark")) {
            return new MarkCommand(line, parser);

        } else if (line.startsWith("todo") || line.startsWith("deadline")
                || line.startsWith("event")) {
            checkFirstWordOfTaskCommand(line);
        } else if (line.startsWith("delete")) {
            deleteTaskCommand(line);
        } else {
            checkFirstCommandWord();
        }
        return null;
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

    public void checkNumberWithinRange(int num) throws TonyException {
        if (num > tasks.size()) {
            throw new TonyException();
        }
    }

    public void checkFirstWordOfTaskCommand(String userInput) throws IOException {
        try {
            addATaskCommandParser(userInput);
        } catch (TonyException e) {
            System.out.println("OOPS!! The description of " + userInput
                    + " cannot be empty." + System.lineSeparator());
        }
    }

    public void checkFirstCommandWord() {
        System.out.println("Please begin input with the following words: "
                + System.lineSeparator()
                + "'todo / deadline / event / delete / mark'");
    }

    private void addATaskCommandParser(String userInput) throws TonyException, IOException {
        if (userInput.startsWith("todo")) {
            String[] toDoTask = userInput.split("todo");
            checkArrayLength(toDoTask);
            addTodoCommand(toDoTask);
        } else if (userInput.startsWith("deadline")) {
            String[] deadlineTask = userInput.split("deadline");
            checkArrayLength(deadlineTask);
            addDeadlineCommand(deadlineTask);
        } else if (userInput.startsWith("event")) {
            String[] eventTask = userInput.split("event");
            checkArrayLength(eventTask);
            addEventCommand(eventTask);
        }
    }
    private void checkArrayLength(String[] toDoTask) throws TonyException {
        if (toDoTask.length != 2) {
            throw new TonyException();
        }
    }
    private void addEventCommand(String[] eventTask) throws IOException {
        String[] description = eventTask[1].split("/from | /to");
        Event event = new Event(description[0], description[1], description[2]);
        tasks.add(event);
        ui.printAddOrDeleteTask(description[0], tasks.indexOf(event));
        String eventLine = fileSaver.saveEvent(event);
        fileSaver.saveData(eventLine, true);
    }

    private void addDeadlineCommand(String[] deadlineTask) throws IOException {
        String[] description = deadlineTask[1].split("/by");
        Deadline deadline = new Deadline(description[0], description[1]);
        tasks.add(deadline);
        ui.printAddOrDeleteTask(description[0], tasks.indexOf(deadline));
        String deadlineLine = fileSaver.saveDeadline(deadline);
        fileSaver.saveData(deadlineLine, true);
    }

    private void addTodoCommand(String[] toDoTask) throws IOException {
        Todo todo = new Todo(toDoTask[1]);
        tasks.add(todo);
        ui.printAddOrDeleteTask(toDoTask[0], tasks.indexOf(todo));
        String todoLine = fileSaver.saveTodo(todo);
        fileSaver.saveData(todoLine, true);
    }


    public void deleteTaskCommand(String line) throws IOException {
        try {
            String[] subCommand = line.split(" ");
            int num = Integer.parseInt(subCommand[1]);
            checkNumberWithinRange(num);
            deleteATask(subCommand[0], num);
        } catch (NumberFormatException nfe) {
            System.out.println("Suggest only number after 'delete'!");
        } catch (TonyException e) {
            System.out.println("To delete a task, suggest a number available in the list!");
        }
    }

    public void deleteATask(String subCommand, int num) throws IOException {
        ui.printAddOrDeleteTask(subCommand, num - 1);
        tasks.remove(num - 1);
        fileSaver.updateFile();
    }
}
