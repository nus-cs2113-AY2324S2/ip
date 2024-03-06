package bobby;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Bobby {
    public static final String FILE_PATH = "data/tasks.txt";

    public static String obtainTodoDescription(String input) throws BobbyException {
        if (input.length() < 5 || input.substring(5).trim().isEmpty()) {
            throw new BobbyException();
        }
        return input.substring(5);
    }

    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage(FILE_PATH);
        boolean isExit = false;
        ArrayList<Task> tasks = new ArrayList<>(); // Using ArrayList instead of array
        Scanner in = new Scanner(System.in);
        ui.showWelcomeMessage();
        storage.createFile();
        try {
           storage.loadFile(tasks);
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
        }

        while (!isExit) {
            String input = in.nextLine();
            String command;
            String description;
            String by;
            String from;
            if (input.indexOf(' ') > 0) {
                command = input.substring(0, input.indexOf(' '));
            } else {
                command = input;
            }
            int entry;
            switch (command) {
            case "bye":
                ui.showByeMessage();
                isExit = true;
                break;
            case "mark":
                entry = Integer.parseInt(input.substring(5));
                if (entry > 0 && entry <= tasks.size()) {
                    tasks.get(entry - 1).setDone(true);
                    ui.showMarkMessage();
                    System.out.println(entry + "." + tasks.get(entry - 1));
                    try {
                        storage.writeToFile(tasks);
                    } catch (IOException e) {
                        ui.showSavingError();
                    }
                }
                break;
            case "unmark":
                entry = Integer.parseInt(input.substring(7));
                if (entry > 0 && entry <= tasks.size()) {
                    tasks.get(entry - 1).setDone(false);
                    ui.showUnmarkMessage();
                    System.out.println(entry + "." + tasks.get(entry - 1));
                    try {
                        storage.writeToFile(tasks);
                    } catch (IOException e) {
                        ui.showSavingError();
                    }
                }
                break;
            case "list":
                ui.showList(tasks);
                break;
            case "todo":
                try {
                    description = obtainTodoDescription(input);
                } catch (BobbyException e) {
                    ui.showInvalidTodoMessage();
                    break;
                }
                tasks.add(new Todo(description, false));
                ui.showValidTodoMessage(tasks);
                try {
                    storage.writeToFile(tasks);
                } catch (IOException e) {
                    ui.showSavingError();
                }
                break;
            case "deadline":
                try {
                    description = input.substring(9, input.indexOf("/by") - 1);
                    by = input.substring(input.indexOf("/by") + 4);
                } catch (StringIndexOutOfBoundsException e) {
                    ui.showInvalidDeadlineMessage();
                    break;
                }
                tasks.add(new Deadline(description, false, by));
                ui.showValidDeadlineMessage(tasks);
                try {
                    storage.writeToFile(tasks);
                } catch (IOException e) {
                    ui.showSavingError();
                }
                break;
            case "event":
                try {
                    description = input.substring(6, input.indexOf("/from") - 1);
                    by = input.substring(input.indexOf("/to") + 4);
                    from = input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1);
                } catch (StringIndexOutOfBoundsException e) {
                    ui.showInvalidEventMessage();
                    break;
                }
                tasks.add(new Event(description, false, by, from));
                ui.showValidEventMessage(tasks);
                try {
                    storage.writeToFile(tasks);
                } catch (IOException e) {
                   ui.showSavingError();
                }
                break;
            case "delete":
                entry = Integer.parseInt(input.substring(7));
                if (entry > 0 && entry <= tasks.size()) {
                    ui.showDeleteMessage(tasks, entry);
                    try {
                        storage.writeToFile(tasks);
                    } catch (IOException e) {
                        ui.showSavingError();
                    }
                }
                break;
            default:
                ui.showInvalidCommandError();
                break;
            }
        }
    }
}
