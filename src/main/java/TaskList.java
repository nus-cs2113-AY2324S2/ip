import java.util.ArrayList;
import java.util.NoSuchElementException;

public class TaskList {
    private static final String LINE = "____________________________________________________________";
    private static Ui ui = new Ui();
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void remove(int index) {
        tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void addNewTask(String command) {
        String[] commandParts = command.split(" ");
        String tasking = commandParts[0];

        switch (tasking) {
            case "todo":
                try {
                    tasks.add(new Todo(command));
                } catch (IllegalArgumentException e) {
                    ui.println(LINE + "\nOOPS!!! The description of a todo cannot be empty.\n" + LINE);
                    return;
                }
                break;
            case "deadline":
                try {
                    tasks.add(new Deadline(command));
                } catch (IllegalArgumentException e) {
                    ui.println(LINE + "\nOOPS!!! The description of a deadline cannot be empty.\n" + LINE);
                    return;
                } catch (NoSuchElementException e) {
                    ui.println(LINE + "\nOOPS!!! Try this format: deadline <task> /by <time>.\n" + LINE);
                    return;
                }
                break;
            case "event":
                try {
                    tasks.add(new Event(command));
                } catch (IllegalArgumentException e) {
                    ui.println(LINE + "\nOOPS!!! The description of a event cannot be empty.\n" + LINE);
                    return;
                } catch (NoSuchElementException e) {
                    ui.println(LINE + "\nOOPS!!! Try this format: event <task> /from <start_time> /to <end_time>.\n" + LINE);
                    return;
                }
                break;
            default:
                ui.println(LINE + "\nOOPS!!! I'm sorry, but I don't know what that means :-(\n" + LINE);
                return;
        }
        tasks.get(tasks.size() - 1).printTask(tasks.size());
    }

    public void deleteTask(String command) {
        int indexToDelete = Integer.parseInt(command.substring(command.indexOf(' ') + 1).trim()) - 1;
        if (indexToDelete >= 0 && indexToDelete < tasks.size()) {
            ui.println(LINE + "\nNoted. I've removed this task:");
            ui.println("   "+ tasks.get(indexToDelete).toString());
            tasks.remove(indexToDelete);
            ui.println("Now you have " + tasks.size() + " tasks in the list.\n" + LINE);
        } else {
            ui.println(LINE + "\nInvalid task number.\n" + LINE);
        }
    }
    public void findTasks(String command) {
        String keyword = command.substring(5).trim().toLowerCase();

        if (keyword.trim().equalsIgnoreCase("find")) {
            throw new IllegalArgumentException();
        }
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            ui.println("No matching tasks found for keyword: " + keyword + "\n" + LINE);
        } else {
            ui.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.println((i + 1) + ". " + matchingTasks.get(i).toString());
            }
            ui.println(LINE);
        }
    }
}