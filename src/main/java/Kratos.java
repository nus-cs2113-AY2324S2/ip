import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Kratos {
    private static Ui ui = new Ui();
    private static Storage storage = new Storage();
    static ArrayList<Task> tasksList = new ArrayList<>();
    private static final String FILE_PATH = "./data/tasks.txt";

    // Method to display marking of tasks
    public static void identifyAndMarkTasks(int taskNumber, String mark) {
        String displayString;
        if (mark.equals("mark")) {
            if (!tasksList.get(taskNumber).isDone) {
                displayString = "Task vanquished. Another notch on the blade of progress.\n" +
                        "What next, mortal?";
                tasksList.get(taskNumber).markTask();
            } else {
                displayString = "Your task bears the mark of completion.\n" +
                        "Attempting to mark it again is futile.";
            }
        } else {
            if (tasksList.get(taskNumber).isDone) {
                displayString = "Task restored from the depths of completion.\n" +
                        "A twist of fate, mortal. What now?\n" +
                        "Reclaim victory or face the abyss once more.";
                tasksList.get(taskNumber).unmarkTask();
            } else {
                displayString = "Foolish mortal.\n" +
                        "Once a task is freed, it shall not be bound again.";
            }
        }
        
        System.out.println(ui.LINE);
        System.out.println(displayString);
        System.out.printf("         %s%n",  tasksList.get(taskNumber).toString());
        System.out.println(ui.LINE);
    }

    // Main method
    public static void main(String[] args) {

        ui.greet();
        tasksList = storage.loadTasks(); // Load tasks from file when the program boots up
        String userInput;
        try {
            while (true) {
                userInput = ui.readCommand();
                switch (userInput) {
                case "bye":
                    ui.end();
                    return;
                case "list":
                    ui.displayTasks(tasksList.size(), tasksList);
                    break;
                default:
                    handleCommand(userInput);
                    break;
                }
                storage.saveTasks(tasksList);
            }
        } finally {
            ui.closeScanner();
        }
    }

    private static void handleCommand(String userInput) {
        try {
            if (userInput.startsWith("mark") || userInput.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]); //Extract task number
                identifyAndMarkTasks(taskNumber - 1, userInput.split(" ")[0]);
            } else if (userInput.startsWith("deadline")) {
                addDeadline(userInput);
            } else if (userInput.startsWith("todo")) {
                addTodo(userInput);
            } else if (userInput.startsWith("event")) {
                addEvent(userInput);
            } else if (userInput.startsWith("delete")) {
                deleteTask(userInput);
            } else {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            KratosException.handleException(e, userInput);
        }
    }

    private static void deleteTask(String userInput) {
        String[] deleteCommand = userInput.split(" ");
        int taskNumber = Integer.parseInt(deleteCommand[1].trim()) - 1;
        if (taskNumber < 0 || taskNumber >= tasksList.size()) {
            throw new IndexOutOfBoundsException();
        }
        ui.taskDeleteMessage(taskNumber,tasksList);
        tasksList.remove(taskNumber);
        ui.taskRemainderDisplay(tasksList);
    }

    private static void addEvent(String userInput) {
        // Split the string by "/by" to separate the deadline and date
        String[] eventParts = userInput.split("/from");

        // Extract the deadline, action, and item
        String eventAndName = eventParts[0].trim();  // "event project meeting"
        String date = eventParts[1].trim();  // "Mon 2pm /to 4pm"

        // Split the event into action and item
        String[] events = eventAndName.split("\\s+", 2);
        String action = events[1]; // project Meeting

        String[] timelineStrings = date.split("/to");

        tasksList.add(new Event(action, timelineStrings[0].trim(), timelineStrings[1].trim()));
        ui.displayMessageSelector(events[0]);
    }

    private static void addTodo(String userInput) {
        // Split line into action and type
        String[] todoParts = userInput.split("\\s+", 2);
        String action = todoParts[1];
        tasksList.add(new Todo(action));
        ui.displayMessageSelector(todoParts[0]);
    }

    private static void addDeadline(String userInput) {
        // Split the string by "/by" to separate the deadline and date
        String[] deadlineParts = userInput.split("/by");

        // Extract the deadline, action, and item
        String deadline = deadlineParts[0].trim();  // "deadline return book"
        String date = deadlineParts[1].trim();  // "Sunday"

        // Split the deadline into action and item
        String[] timelineParts = deadline.split("\\s+", 2);
        String action = timelineParts[0];
        String item = timelineParts[1];
        tasksList.add(new Deadline(item, date));
        ui.displayMessageSelector(timelineParts[0]);
    }


}
