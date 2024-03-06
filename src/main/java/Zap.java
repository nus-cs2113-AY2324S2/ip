import java.util.List;

public class Zap {
    private static List<Task> tasks;
    private final Ui ui;
    private final Storage storage;
    private final Parser parser;

    public Zap(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        tasks = storage.load(); // Load tasks from storage
        this.parser = new Parser(this); // Assuming Parser constructor takes a Zap instance
    }

    public void run() {
        ui.printInstructions();
        parser.parseCommands(); // Delegate command parsing to Parser
        storage.save(tasks); // Save tasks to storage after processing commands
        ui.printFarewell();
    }

    static void addTodoTask(String userCommand) {

        String taskDescription = userCommand.substring(4).trim();
        if (taskDescription.isEmpty()) {
            System.out.println("Woi. You think I robot then can waste my time. Gimme description >:(");
        } else {
            tasks.add(new TodoTask(taskDescription));

            System.out.println("____________________________________________________________");
            System.out.println(" Got it. Avril added this task:");
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
        String deadline = deadlineParts[1].trim();

        tasks.add(new DeadlineTask(description, deadline));
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. Avril added this task:");
        System.out.println("   [D][ ] " + description + " (By: " + deadline + ")");
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

        String startTime = timeParts[0].trim();
        String endTime = timeParts[1].trim();
        tasks.add(new EventTask(description, startTime, endTime));

        System.out.println("____________________________________________________________");
        System.out.println(" Got it. Avril added this task:");
        System.out.println("   [E][ ] " + description + " (From: " + startTime + " To: " + endTime + ")");
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

    private static boolean isValidTaskIndex(int taskIndex) {
        return taskIndex >= 0 && taskIndex < tasks.size();
    }

    public static void main(String[] args) {
        new Zap("tasks.txt").run();
    }
}