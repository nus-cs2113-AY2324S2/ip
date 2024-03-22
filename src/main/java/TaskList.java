import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * Represents a list of tasks in the Dul application. Provides operations for managing tasks.
 */
public class TaskList {

    private List<Task> tasks;
    /**
     * Retrieves the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }


    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }

    public void addTodoTask(String taskType, Ui ui) {
        tasks.add(new TodoTask(taskType));
        ui.showMessage("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1).toString() + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    public void addDeadlineTask(String taskType, Ui ui) {
        String[] parts = taskType.split(" /by ", 2);
        if (parts.length != 2) {
            ui.showError("Invalid deadline format. Please use: deadline [description] /by [date/time]");
            return;
        }
        String description = parts[0];
        LocalDateTime by;
        try {
            // Try parsing with "d/M/yyyy HHmm" format
            by = LocalDateTime.parse(parts[1], DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            try {
                // If parsing fails, try parsing with "yyyy-MM-dd HHmm" format
                by = LocalDateTime.parse(parts[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            } catch (DateTimeParseException ex) {
                ui.showError("Invalid date/time format. Please use: dd/MM/yyyy HHmm or yyyy-MM-dd HHmm");
                return;
            }
        }
        tasks.add(new DeadlineTask(description, by));
        ui.showMessage("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1).toString() + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    public void addEventTask(String taskType, Ui ui) {
        String[] parts = taskType.split(" /from ", 2);
        if (parts.length != 2) {
            ui.showError("Invalid event format. Please use: event [description] /from [start time] /to [end time]");
            return;
        }
        String[] eventParts = parts[1].split(" /to ", 2);
        if (eventParts.length != 2) {
            ui.showError("Invalid event format. Please use: event [description] /from [start time] /to [end time]");
            return;
        }
        String description = parts[0];
        LocalDateTime from;
        LocalDateTime to;
        try {
            // Try parsing with "d/M/yyyy HHmm" format
            from = LocalDateTime.parse(eventParts[0], DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            to = LocalDateTime.parse(eventParts[1], DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            try {
                // If parsing fails, try parsing with "yyyy-MM-dd HHmm" format
                from = LocalDateTime.parse(eventParts[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                to = LocalDateTime.parse(eventParts[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            } catch (DateTimeParseException ex) {
                ui.showError("Invalid date/time format. Please use: dd/MM/yyyy HHmm or yyyy-MM-dd HHmm");
                return;
            }
        }
        tasks.add(new EventTask(description, from, to));
        ui.showMessage("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1).toString() + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    public void markTaskDone(int index, Ui ui) {
        if (index < 0 || index >= tasks.size()) {
            ui.showError("Invalid task index.");
            return;
        }
        tasks.get(index).markDone();
        ui.showMessage("Nice! I've marked this task as done:\n  " + tasks.get(index).toString());
    }

    public void markTaskNotDone(int index, Ui ui) {
        if (index < 0 || index >= tasks.size()) {
            ui.showError("Invalid task index.");
            return;
        }
        tasks.get(index).markNotDone();
        ui.showMessage("OK, I've marked this task as not done yet:\n  " + tasks.get(index).toString());
    }

    public void deleteTask(int index, Ui ui) {
        if (index < 0 || index >= tasks.size()) {
            ui.showError("Invalid task index.");
            return;
        }
        Task removedTask = tasks.remove(index);
        ui.showMessage("Noted. I've removed this task:\n  " + removedTask.toString() + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    public void findTaskByKeyword(String keyword, Ui ui) {
        List<Task> matchingTasks = tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());
        if (matchingTasks.isEmpty()) {
            ui.showMessage("No matching tasks found.");
        } else {
            ui.showMessage("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.showMessage((i + 1) + "." + matchingTasks.get(i).toString());
            }
        }
    }
}

