import java.util.ArrayList;
public class TaskList {
    private static Ui ui = new Ui();

    private static ArrayList<Task> tasksList = new ArrayList<>();

    public int size() {
        return tasksList.size();
    }

    public Task get(int index) {
        return tasksList.get(index);
    }

    public TaskList() {
        this.tasksList = new ArrayList<>();
    }

    public void handleTodoCommand(String command) {
        try {
            if (command.length() <= 5) {
                throw new StringIndexOutOfBoundsException();
            }
            ui.showTaskAdded();
            tasksList.add(new Todo(command.substring(5)));
            System.out.println(tasksList.get(tasksList.size() - 1));

            ui.showTaskCountMessage();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! " + e.getMessage());
        }
    }
    public void handleDeadlineCommand(String command) {
        try {
            int dividerPosition = command.indexOf("/by ");
            if (dividerPosition == -1) {
                throw new StringIndexOutOfBoundsException();
            }
            ui.showTaskAdded();
            tasksList.add(new Deadline(command.substring(9, dividerPosition - 1), command.substring(dividerPosition + 4)));
            System.out.println(tasksList.get(tasksList.size() - 1));

            ui.showTaskCountMessage();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! " + e.getMessage());
        }
    }

    public void handleEventCommand(String command) {
        try {
            int from = command.indexOf("/from ");
            int to = command.indexOf("/to ");
            if (from == -1 || to == -1) {
                throw new StringIndexOutOfBoundsException();
            }
            ui.showTaskAdded();
            tasksList.add(new Event(command.substring(6, from - 1), command.substring(from + 6, to - 1), command.substring(to + 4)));
            System.out.println(tasksList.get(tasksList.size() - 1));

            ui.showTaskCountMessage();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! " + e.getMessage());
        }
    }

    public static void printTaskList() {
        ui.showTaskList();

        for (int i = 0; i < tasksList.size(); i++) {
            System.out.println(i + 1 + ". " + tasksList.get(i));
        }
        System.out.println(" ");
    }

    public static int extractTaskIndex(String command) {
        String[] parts = command.split(" ");
        return (parts.length >= 2) ? Integer.parseInt(parts[1]) : -1;
    }

    public void markTaskCompletion(String command, String taskType) {
        int taskIndex = extractTaskIndex(command);
        if (taskIndex > tasksList.size()) {
            ui.showInvalidTaskIndexMessage();
        } else if (taskType.equalsIgnoreCase("mark")) {
            ui.showTaskAsMarked();
            tasksList.get(taskIndex - 1).markAsDone();
        } else {
            ui.showTaskAsUnmarked();
            tasksList.get(taskIndex - 1).unmarkAsDone();
        }
        System.out.println(" ");
    }
    public void markTaskAsDone(String command) {
        int taskIndex = extractTaskIndex(command);
        if (isValidTaskIndex(taskIndex)) {
            tasksList.get(taskIndex - 1).markAsDone();
            ui.showTaskAsMarked();
            System.out.println(tasksList.get(taskIndex - 1));
        } else {
            ui.showInvalidTaskIndexMessage();
        }
        System.out.println(" ");
    }

    public void unmarkTaskAsDone(String command) {
        int taskIndex = extractTaskIndex(command);
        if (isValidTaskIndex(taskIndex)) {
            tasksList.get(taskIndex - 1).unmarkAsDone();
            ui.showTaskAsUnmarked();
            System.out.println(tasksList.get(taskIndex - 1));
        } else {
            ui.showInvalidTaskIndexMessage();
        }
        System.out.println(" ");
    }

    public void addTaskFromSaved(String command, char taskType) {
        try {
            Task task;
            String taskDescription = command.substring(6);
            switch (taskType) {
            case 'T':
                task = addTodoFromSaved(taskDescription);
                break;
            case 'D':
                task = addDeadlineFromSaved(taskDescription);
                break;
            case 'E':
                task = addEventFromSaved(taskDescription);
                break;
            default:
                // Handle unknown task type or return if needed
                System.out.println("Unknown task type: " + taskType);
                return;
            }
            if (task != null) {
                task.markAsDone();
                tasksList.add(task);
            }

        } catch (Exception e) {
            // Handle other general exceptions
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    public Todo addTodoFromSaved(String description) {
        return new Todo(description);
    }

    public static Deadline addDeadlineFromSaved(String description) {
        String by = description.substring(description.indexOf("(by:") + 4);
        return new Deadline(description, by);
    }
    public static Deadline fromFileFormat(String fileLine) {
        String[] parts = fileLine.split(" \\(by: ");

        String description = parts[0].substring(6);
        String by = parts[1];

        Deadline deadlineTask = new Deadline(description, by);

        return deadlineTask;
    }

    public Event addEventFromSaved(String description) {
        String from = description.substring(description.indexOf("(from:") + 6);
        String to = description.substring(description.indexOf("to:") + 3);
        return new Event(description, from, to);
    }

    private static boolean isValidTaskIndex(int taskIndex) {
        return taskIndex > 0 && taskIndex <= tasksList.size();
    }
    public void deleteTask(String command) {
        int taskIndex = extractTaskIndex(command);
        if (isValidTaskIndex(taskIndex)) {
            ui.showTaskDeleted();
            System.out.println(tasksList.get(taskIndex - 1));
            tasksList.remove(taskIndex - 1);
            ui.showTaskCountMessage();
        } else {
            ui.showInvalidTaskIndexMessage();
        }
        System.out.println(" ");
    }
}