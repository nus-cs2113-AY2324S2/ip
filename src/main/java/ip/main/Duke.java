package ip.main;

import ip.task.Todo;
import ip.task.Deadline;
import ip.task.Event;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private static Storage storage;
    private static Ui ui = new Ui();
    private static TaskList tasks = new TaskList(ui);

    public static void main(String[] args) {
        ui.introduce();
        try {
            storage = new Storage("./data/task_list.txt", ui);
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
                tasks.printTaskList();
                continue;
            }

            if (line.startsWith("mark")) {
                boolean shouldUpdate = tasks.markTask(line);
                if (shouldUpdate) {
                    storage.updateStoredData(tasks);
                }
                continue;
            }

            if (line.startsWith("unmark")) {
                boolean shouldUpdate = tasks.unmarkTask(line);
                if (shouldUpdate) {
                    storage.updateStoredData(tasks);
                }
                continue;
            }

            if (line.startsWith("delete")) {
                boolean shouldUpdate = tasks.deleteTask(line);
                if (shouldUpdate) {
                    storage.updateStoredData(tasks);
                }
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
            return tasks.addTodo(line);
        } else if (line.startsWith("deadline")) {
            return tasks.addDeadline(line);
        } else if (line.startsWith("event")) {
            return tasks.addEvent(line);
        } else {
            ui.print("Possible commands: bye, list, mark, unmark, todo, deadline, event");
            return false;
        }
    }
}
