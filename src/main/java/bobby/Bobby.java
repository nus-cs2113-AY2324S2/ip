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

    public static void writeToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task task : tasks) {
            fw.write(taskToFileFormat(task));
        }
        fw.close();
    }

    public static String taskToFileFormat(Task task) {
        if (task instanceof Todo) {
            return "T | " + (task.isDone ? "1" : "0") + " | " + task.getDescription() + "\n";
        } else if (task instanceof Deadline) {
            return "D | " + (task.isDone ? "1" : "0") + " | " + task.getDescription() + " | " + task.getBy() + "\n";
        } else if (task instanceof Event) {
            return "E | " + (task.isDone ? "1" : "0") + " | " + task.getDescription() + " | " + task.getFrom() + " | "
                    + task.getBy() + "\n";
        }
        return "";
    }

    public static void parseTask(String line, ArrayList<Task> tasks) {
        String[] parts = line.split("\\|");
        String label = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();
        switch (label) {
        case "T":
            tasks.add(new Todo(description,isDone));
            break;
        case "D":
            String by = parts[3].trim();
            tasks.add(new Deadline(description, isDone, by));
            break;
        case "E":
            String from = parts[3].trim();
            String to = parts[4].trim();
            tasks.add(new Event(description, isDone, from, to));
            break;
        default:
        }
    }


    public static void loadFile(ArrayList<Task> tasks) throws FileNotFoundException {
        File f = new File(FILE_PATH);
        Scanner scanner = new Scanner(f);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            parseTask(line, tasks);
        }
    }

    public static void main(String[] args) {
        Ui ui = new Ui();
        boolean isExit = false;
        ArrayList<Task> tasks = new ArrayList<>(); // Using ArrayList instead of array
        Scanner in = new Scanner(System.in);
        ui.showWelcomeMessage();
        File f = new File(FILE_PATH);
        try {
            f.createNewFile();
        } catch (IOException e) {
            ui.showTextFileError();
        }
        try {
           loadFile(tasks);
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
                        writeToFile(tasks);
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
                        writeToFile(tasks);
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
                    writeToFile(tasks);
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
                    writeToFile(tasks);
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
                    writeToFile(tasks);
                } catch (IOException e) {
                   ui.showSavingError();
                }
                break;
            case "delete":
                entry = Integer.parseInt(input.substring(7));
                if (entry > 0 && entry <= tasks.size()) {
                    ui.showDeleteMessage(tasks, entry);
                    try {
                        writeToFile(tasks);
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
