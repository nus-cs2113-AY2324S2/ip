package Parser;

import TaskList.TaskList;
import exceptions.*;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static Storage.UploadData.updateFile;
import static Ui.Ui.*;

public class Parser {
    static TaskList tasks;
    static String filepath;

    public Parser(String filepath, TaskList tasks){
        Parser.filepath = filepath;
        Parser.tasks = tasks;
    }

    private static void echo() {
        printTaskAddedMessage(tasks.get(tasks.size() - 1));
        printRemainingTaskNumber(tasks.size());
    }

    private static void sayBye() {
        try {
            updateFile(filepath,tasks);
        } catch (IOException e) {
            System.out.println("Could not save changes to file.");
        }
        closeApp();
    }

    public static void createTodo(String input) {
        try {
            String todo = input.substring(input.indexOf("todo ") + 5);
            if (todo.isBlank()) {
                throw new EmptyTaskException();
            }
            ToDo newEntry = new ToDo(todo);
            tasks.add(newEntry);
            echo();
        } catch (EmptyTaskException e){
            printError(e.getMessage());
        }

    }


    private static void createDeadline(String input) {
        try {
            String description = input.replaceFirst("deadline ", "");
            int by = description.indexOf("/");
            if (by == -1) {
                throw new MissingDeadlineException();
            }
            String deadline = description.substring(0, by - 1);
            if (deadline.isBlank()) {
                throw new EmptyTaskException();
            }
            String date = description.substring(by + 4);
            Deadline newEntry = new Deadline(deadline, date);
            tasks.add(newEntry);
            echo();
        } catch (EmptyTaskException | MissingDeadlineException e){
            printError(e.getMessage());
        }
    }

    private static void createEvent(String input){
        try {
            String description = input.replaceFirst("event ", "");
            int from = description.indexOf("/from");
            if (from == -1) {
                throw new MissingStartException();
            }
            Event newEntry = getEvent(description, from);
            tasks.add(newEntry);
            echo();
        } catch (MissingStartException | MissingDeadlineException|EmptyTaskException e){
            printError(e.getMessage());
        }
    }

    private static Event getEvent(String description, int from) throws MissingDeadlineException, EmptyTaskException {
        int by = description.indexOf("/to");
        if (by == -1) {
            throw new MissingDeadlineException();
        }
        String startDate = description.substring(from + 6, by - 1);
        String endDate = description.substring(by + 4);
        if (from == 0) {
            throw new EmptyTaskException();
        }
        String event = description.substring(0, from - 1);
        if (event.isBlank()){
            throw new EmptyTaskException();
        }
        return new Event(event,startDate,endDate);
    }

    private static void deleteTask(String input) {
        try {
            int index = Integer.parseInt(input.substring(7)) - 1;
            if (tasks.size() < index) {
                throw new ArrayListOutOfBoundsException();
            }
            printDeleteMessage(tasks.get(index).toString());
            tasks.remove(index);
            printRemainingTaskNumber(tasks.size());
        } catch (ArrayListOutOfBoundsException e){
            printError(e.getMessage());
        }
    }

    private static void handleInput(String input) {
        if (input.contains("todo")) {
                createTodo(input);
        } else if (input.contains("deadline")) {
                createDeadline(input);
        } else if (input.contains("event")) {
                createEvent(input);
        } else if (input.contains("bye")) {
            sayBye();
        } else if (input.startsWith("delete")) {
                deleteTask(input);
        } else {
            printUnknownInputMessage();
        }
    }

    public static int getIndexToMark(String input) {
        int idx = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
        return idx - 1;
    }

    public static void listItems() {
        printTaskList(tasks);
    }

    public static void unmarkItem(String input) {
        int idx = getIndexToMark(input);
        tasks.get(idx).setDone(false);
        printMarkedAsUndoneMessage(tasks.get(idx));
    }

    public static void markItem(String input) {
        int idx = getIndexToMark(input);
        tasks.get(idx).setDone(true);
        printMarkedAsDoneMessage(tasks.get(idx));
    }
    public static void chat() {
        String input;
        do {
            input = new Scanner(System.in).nextLine();
            showLine();
            if (input.equals("list")) {
                listItems();
            } else if (input.contains("unmark")) {
                unmarkItem(input);
            } else if (input.contains("mark")) {
                markItem(input);
            } else {
                handleInput(input);
            }
            showLine();
        } while (!input.equals("bye"));
    }
}
