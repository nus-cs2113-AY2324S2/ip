package sayo;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Sayo {

    private TaskList items;
    private Storage storage;
    private Ui ui;

    public Sayo(String dataFilePath) {
        ui = new Ui();
        storage = new Storage(dataFilePath);
        try {
            items = new TaskList(storage.load());
        } catch (SayoException e) {
            ui.showLoadingError();
            items = new TaskList();
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        ui.showWelcomeMessage();

        String input = " ";

        do {
            try {
                input = scanner.nextLine().trim();
                if (input.equals("list")) {
                    for (int i = 0; i < items.getSize(); i++) {
                        System.out.println((i + 1) + ". " + items.getTask(i));
                    }
                } else if (input.startsWith("mark")) {
                    int index = Parser.getIndexForMark(input);
                    if (index >= 0 && index < items.getSize()) {
                        items.getTask(index).markAsDone();
                        ui.showTaskMarked(items.getTask(index));
                    }
                    storage.save(items.getTasks());
                } else if (input.startsWith("unmark")) {
                    int index = Parser.getIndexForUnmark(input);
                    if (index >= 0 && index < items.getSize()) {
                        items.getTask(index).unmarkAsDone();
                        ui.showTaskUnmarked(items.getTask(index));
                    }
                    storage.save(items.getTasks());
                } else if (input.startsWith("todo")) {
                    if (input.length() <= 5) {
                        throw new SayoException(
                                "Please focus! The description of a todo cannot be empty. Please retry...");
                    } else {
                        String description = Parser.getTodoDescription(input);
                        ToDo newTodo = new ToDo(description);
                        items.addTask(newTodo);
                        ui.showAddedTaskMessage(newTodo, items);
                    }
                    storage.save(items.getTasks());
                } else if (input.startsWith("deadline")) {
                    int byIndex = input.indexOf("/by");
                    if (byIndex == -1 || byIndex + 4 >= input.length()) {
                        ui.requestDeadlineFormat();
                    } else {
                        String description = Parser.getDeadlineDescription(input, byIndex);
                        String by = Parser.getDeadlineBy(input, byIndex);
                        Deadline newDeadline = new Deadline(description, by);
                        items.addTask(newDeadline); 
                        ui.showAddedDeadlineMessage(newDeadline, items);
                    }
                    storage.save(items.getTasks());
                } else if (input.startsWith("event")) {
                    int fromIndex = Parser.getEventFromIndex(input);
                    int toIndex = Parser.getEventToIndex(input);

                    if (fromIndex == -1 || toIndex == -1 || fromIndex + 6 >= input.length() || toIndex + 4 >= input.length()) {
                        ui.requestEventFormat();
                    } else {
                        String description = Parser.getEventDescription(input, fromIndex);
                        String start = Parser.getEventStart(input, fromIndex, toIndex);
                        String end = Parser.getEventEnd(input, toIndex);
                        Event newEvent = new Event(description, start, end);
                        items.addTask(newEvent);
                        ui.showAddedEventMessage(newEvent, items);
                    }
                    storage.save(items.getTasks());
                } else if (input.startsWith("delete")) {
                    try {
                        int index = Parser.getDeleteIndex(input);
                        if (index >= 0 && index < items.getSize()) {
                            Task removedTask = items.removeTask(index); 
                            ui.showDeletedTaskMessage(removedTask, items);
                            storage.save(items.getTasks());
                        } else {
                            ui.requestDeleteFormat();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number after 'delete'.");
                    }
                } else if (input.startsWith("find")) {
                    String keyword = input.substring(5).trim();
                    ArrayList<Task> foundTasks = items.findTasksByKeyword(keyword);
                    ui.showFoundTasks(foundTasks);
                } else if (!input.equals("bye")) {
                    throw new SayoException("Oh no! Apologies, but I don't know what that means :-( Please retry.");
                }
            } catch (SayoException e) {
                System.out.println(e.getMessage());
            }

        } while (!input.equals("bye"));

        ui.showGoodbyeMessage();
        scanner.close();

    }

    public static void main(String[] args) {
        new Sayo("./data/sayo.txt").run();
    }

}