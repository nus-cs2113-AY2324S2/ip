import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TaskList {

    private static ArrayList<Task> tasks;

    public TaskList() {

    }

    public void load() {
        TaskList.tasks = new ArrayList<>();
    }

    static void addTodoTask(String userCommand) {

        String taskDescription = userCommand.substring(4).trim();
        if (taskDescription.isEmpty()) {
            System.out.println("use english pls. type properly.");
        } else {
            tasks.add(new TodoTask(taskDescription));

            System.out.println("____________________________________________________________");
            System.out.println(" Got it. Avril the Bot added this task:");
            System.out.println("   [T][ ]  " + taskDescription);
            if (tasks.size() == 1) {
                System.out.println(" Now you have 1 task in the list.");
            } else {
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            }
            System.out.println("____________________________________________________________");
        }
    }

    static void addDeadline(String userCommand) {
        String[] descParts = userCommand.split("deadline");
        String[] deadlineParts = descParts[1].split("/by", 2);

        if (deadlineParts.length != 2) {
            System.out.println("Invalid deadline format. Please use '/by' to specify day.");
            return;
        }

        String description = deadlineParts[0];
        String deadlineString = deadlineParts[1].trim();

        LocalDateTime deadline;
        try {
            deadline = LocalDateTime.parse(deadlineString, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid deadline date format. Please use the format 'DD-MM-YYYY HHmm'.");
            return;
        }

        tasks.add(new DeadlineTask(description, deadline));
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. Avril the Bot added this task:");
        System.out.println("   [D][ ] " + description + " (By: " + deadline.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")) + ")");

        if (tasks.size() == 1) {
            System.out.println(" Now you have 1 task in the list.");
        } else {
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        }
        System.out.println("____________________________________________________________");
    }


    static void addEvent(String userCommand) {

        String[] descParts = userCommand.split("event");
        String[] eventParts = descParts[1].split("/from", 2);

        if (eventParts.length != 2) {
            System.out.println("Invalid event format. Please use '/from' to specify start and end time.");
            return;
        }

        String description = eventParts[0].trim();
        String[] timeParts = eventParts[1].split("/to", 2);

        if (timeParts.length != 2) {
            System.out.println("Invalid event format. Please use '/to' to specify end time.");
            return;
        }

        String startTimeString = timeParts[0].trim();
        String endTimeString = timeParts[1].trim();

        LocalDateTime startTime, endTime;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
            startTime = LocalDateTime.parse(startTimeString, formatter);
            endTime = LocalDateTime.parse(endTimeString, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date-time format. Please use the format 'DD-MM-YYYY HHmm'.");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedStartTime = startTime.format(formatter);
        String formattedEndTime = endTime.format(formatter);
        tasks.add(new EventTask(description, startTime, endTime));

        System.out.println("____________________________________________________________");
        System.out.println(" Got it. Avril the Bot added this task:");
        System.out.println("   [E][ ] " + description + " (From: " + formattedStartTime + " To: " + formattedEndTime + ")");
        if (tasks.size() == 1) {
            System.out.println(" Now you have 1 task in the list.");
        } else {
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * 'displayTasks' method displays all the lists -- marked and unmarked
     * when the command 'list' is inputted by the user
     */
    static void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("____________________________________________________________");
            System.out.println(" Tasks list is empty.");
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
            System.out.println("____________________________________________________________");
        }
    }

    /**
     * `markTask` method marks a task as done.
     * it extracts the task index, and updates the task's status
     */
    static void markTask(String userCommand) {
        String[] characters = userCommand.split("\\s+");

        if (characters.length != 2 || !characters[1].matches("\\d+")) {
            System.out.println("Invalid command format. Please use 'mark <task index>'.");
            return;
        }

        int taskIndex = Integer.parseInt(characters[1]) - 1;

        if (isValidTaskIndex(taskIndex)) {
            tasks.get(taskIndex).markAsDone();
            System.out.println("____________________________________________________________");
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + tasks.get(taskIndex));
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("Invalid task index. Please enter a valid number.");
        }
    }

    /**
     * `unmarkTask` method un-marks a task that was originally marked as done.
     * it extracts the task index, and updates the task's status, then prints a confirmation message.
     */
    static void unmarkTask(String userCommand) {
        String[] characters = userCommand.split("\\s+");

        if (characters.length == 2 && characters[1].matches("\\d+")) {
            int taskIndex = Integer.parseInt(characters[1]) - 1;

            if (isValidTaskIndex(taskIndex)) {
                tasks.get(taskIndex).markAsNotDone();
                System.out.println("____________________________________________________________");
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   " + tasks.get(taskIndex));
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("Invalid task index.");
            }
        } else {
            System.out.println("Invalid format. Please check your spelling or punctuation.");
        }
    }

    static void deleteTask(String userCommand) {
        String[] characters = userCommand.split("\\s+");

        if (characters.length != 2 || !characters[1].matches("\\d+")) {
            System.out.println("type properly");
            return;
        }

        int taskIndex = Integer.parseInt(characters[1]) - 1;
        if (isValidTaskIndex(taskIndex)) {
            Task deletedTask = tasks.remove(taskIndex);
            System.out.println("____________________________________________________________");
            System.out.println(" orh ok. Make sure hor, I deleted this:");
            System.out.println("   " + deletedTask);
            if (tasks.size() == 1) {
                System.out.println(" Now you have 1 task in the list.");
            } else {
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            }
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("Invalid task index. Please enter a valid number.");
        }
    }

    public static void findTask(String keyword) {
        StringBuilder results = new StringBuilder("Here are the matching tasks in your list:\n");

        boolean found = false;
        for (int i = 0; i < tasks.size(); ++i) {
            Task task = tasks.get(i);
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) { // Case-insensitive search
                results.append(String.format("%d. %s%n", (i + 1), task.toString()));
                found = true;
            }
        }

        if (!found) {
            results.append("No matching tasks found.\n");
        }

        System.out.println(results);
    }

    private static boolean isValidTaskIndex(int taskIndex) {
        return taskIndex >= 0 && taskIndex < tasks.size();
    }

}
