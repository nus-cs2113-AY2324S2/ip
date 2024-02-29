package ip.main;

import ip.task.Todo;
import ip.task.Deadline;
import ip.task.Event;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private static Storage storage;
    private static Ui ui = new Ui();
    private static TaskList tasks = new TaskList();

    public static void main(String[] args) {
        ui.introduce();
        try {
            storage = new Storage("./data/task_list.txt");
        } catch (IOException e) {
            ui.printWithoutLeadingSpace("Unable to create data file!");
            return;
        }

        try {
            storage.readStoredData(tasks);
        } catch (FileNotFoundException e) {
            ui.printWithoutLeadingSpace("File not found!");
            return;
        }

        readInputAndExecute();
    }

    private static void readInputAndExecute() {
        String line;
        while (true) {
            line = ui.getInput();
            if (line.equals("bye")) {
                ui.print("Bye. Hope to see you again soon!");
                break;
            }

            if (line.equals("list")) {
                printTaskList();
                continue;
            }

            try {
                if (line.startsWith("mark")) {
                    markTask(line);
                    storage.updateStoredData(tasks);
                    continue;
                }

                if (line.startsWith("unmark")) {
                    unmarkTask(line);
                    storage.updateStoredData(tasks);
                    continue;
                }

                if (line.startsWith("delete")) {
                    deleteTask(line);
                    storage.updateStoredData(tasks);
                    continue;
                }
            } catch (IndexOutOfBoundsException e) {
                ui.print("Please provide a number from 1 to " + tasks.size());
                continue;
            } catch (NumberFormatException e) {
                ui.print("Please input an integer");
                continue;
            }

            boolean shouldUpdate = addTask(line);
            if (shouldUpdate) {
                storage.updateStoredData(tasks);
            }
        }
    }

    private static boolean addTask(String line) {
        if (line.startsWith("todo")) {
            try {
                tasks.add(new Todo(line));
            } catch (StringIndexOutOfBoundsException e) {
                ui.print("Please input in the form 'todo <description>'");
                return false;
            }
        } else if (line.startsWith("deadline")) {
            try {
                tasks.add(new Deadline(line));
            } catch (StringIndexOutOfBoundsException e) {
                ui.print("Please input in the form 'deadline <description> /by <when>'");
                return false;
            }
        } else if (line.startsWith("event")) {
            try {
                tasks.add(new Event(line));
            } catch (StringIndexOutOfBoundsException e) {
                ui.print("Please input in the form 'event <description> /from <when> /to <when>'");
                return false;
            }
        } else {
            ui.print("Possible commands: bye, list, mark, unmark, todo, deadline, event");
            return false;
        }

        ui.print("New task added: " + tasks.get(tasks.size() - 1).getDetails());
        ui.print("Current number of tasks: " + tasks.size());
        return true;
    }

    private static void deleteTask(String line) {
        int indexDeleted = Integer.parseInt((line.substring(7))) - 1;
        ui.print("Task removed: " + tasks.get(indexDeleted).getDetails());
        tasks.remove(indexDeleted);
        ui.print("Current number of tasks: " + tasks.size());
    }

    private static void printTaskList() {
        ui.print("Here is your list of tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            ui.print((i + 1) + "." + tasks.get(i).getDetails());
        }
    }

    private static void markTask(String line) {
        int indexMarked = Integer.parseInt(line.substring(5)) - 1;
        tasks.get(indexMarked).setDone(true);
        ui.print("Nice! I've marked this task as done:");
        ui.print(tasks.get(indexMarked).getDetails());
    }

    private static void unmarkTask(String line) {
        int indexUnmarked = Integer.parseInt(line.substring(7)) - 1;
        tasks.get(indexUnmarked).setDone(false);
        ui.print("OK, I've marked this task as not done yet:");
        ui.print(tasks.get(indexUnmarked).getDetails());
    }
}
