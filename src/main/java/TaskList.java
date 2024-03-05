import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public static void addTask(String input) throws PhoebeException {
        input = input.trim();
        Task newTask = null;
        if (input.toLowerCase().startsWith("todo")) {
            String description = input.substring(4).trim();
            if (description.isEmpty()) {
                Ui.printTodoNotSpecified();
                return;
            }
            newTask = new ToDo(description);
        } else if (input.toLowerCase().startsWith("deadline")) {
            String[] parts = input.substring(8).split("/by", 2);
            if (parts.length < 2 || parts[0].trim().isEmpty()) {
                Ui.printDeadlineNotSpecified();
                return;
            }
            newTask = new Deadline(parts[0].trim(), parts[1].trim());
        } else if (input.toLowerCase().startsWith("event")) {
            String[] parts = input.substring(5).split("/from", 2);
            if (parts.length < 2 || parts[0].trim().isEmpty()) {
                Ui.printEventNotSpecified();
                return;
            }
            String[] timeParts = parts[1].trim().split("/to", 2);
            if (timeParts.length < 2) {
                Ui.printEventUnclear();
                return;
            }
            newTask = new Event(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim());
        } else {
            Ui.printUserIsStupid();
            return;
        }

        tasks.add(newTask);
        Ui.printTaskAdded(newTask, tasks.size());
    }

    public static void deleteTask(String input) throws PhoebeException {
        try {
            int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                Ui.printDeleteTaskNotFound();
                return;
            }
            Task removedTask = tasks.remove(taskIndex);
            Ui.printTaskDeleted(removedTask, tasks.size());
        } catch (NumberFormatException e) {
            Ui.printDeleteTaskError();
        }
    }

    public static List<Task> displayTasks() {
        if (tasks.isEmpty()) {
            Ui.printDisplayEmptyTasks();
        } else {
            Ui.printDisplayTasks();
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
        return null;
    }

    public static void markTask(String input) throws PhoebeException {
        try {
            int taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                Ui.printMarkUnmarkMissingTask();
                return;
            }
            tasks.get(taskIndex).markAsDone();
            Ui.printMarkDoneTask(tasks.get(taskIndex));
        } catch (NumberFormatException e) {
            Ui.printMarkUnmarkMissingTask();
        }
    }

    public static void unmarkTask(String input) throws PhoebeException {
        try {
            int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                Ui.printMarkUnmarkMissingTask();
                return;
            }
            tasks.get(taskIndex).markAsUndone();
            Ui.printMarkUndoneTask(tasks.get(taskIndex));
        } catch (NumberFormatException e) {
            Ui.printMarkUnmarkMissingTask();
        }
    }

private static boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }
}
