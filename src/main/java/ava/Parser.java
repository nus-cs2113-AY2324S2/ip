package ava;

public class Parser {
    private boolean isExit;

    public Parser() {
        isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    public void parseCommand(String task, TaskList tasks, Storage storage, Ui ui) {
        if (task.equals("bye")) {
            isExit = true;
        } else if (task.equals("list")) {
            ui.displayTask(tasks);
        } else if (task.contains("mark")) {
            tasks.markTask(task);
        } else if (task.startsWith("todo") || task.startsWith("deadline") || task.startsWith("event")) {
            try {
                tasks.addTask(task, extractTypeForCommand(task));
                ui.printAfterAddingTask(tasks);
            } catch (EmptyTaskNameException e) {
                ui.printEmptyTaskNameExceptionMessage(extractTypeForCommand(task));
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.printDateFormatExceptionMessage();
            }
        } else if (task.startsWith("delete")) {
            tasks.deleteTask(task);
        } else {
            ui.printUnknownCommandExceptionMessage();
        }
        storage.saveTasks(tasks);
    }

    public static String extractTypeForCommand(String task) {
        String[] taskAndDescription = task.split(" ");
        return taskAndDescription[0];
    }

    public static int extractTaskNumber(String type, String command) {
        String taskNumber = command.replace(type, "");
        return Integer.parseInt(taskNumber) - 1;
    }
}
