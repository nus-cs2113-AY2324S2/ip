/**
 * The TaskManager class manages the list of tasks and handles various commands
 * related to tasks, such as listing, marking, deleting, finding, and adding tasks.
 */

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Handles the "list" command, displaying all tasks in the list.
     */
    void handleListCommand() {
        System.out.println(Constants.LINE_STRING_BOTTOM);
        if (tasks.isEmpty()) {
            System.out.println(Constants.ANSI_RED + "Blimey, your list is empty, innit?");
        } else {
            int index = 1;
            for (Task task : tasks) {
                System.out.println(Constants.ANSI_CYAN + index++ + ". " + task);
            }
        }
        System.out.println(Constants.LINE_STRING_BOTTOM);
    }

    /**
     * Handles the "mark" and "unmark" commands, marking or unmarking a task as done.
     *
     * @param input      The user input string containing the command and task index
     * @param markAsDone A flag indicating whether to mark the task as done (true) or undone (false)
     * @throws IncyException If the task index is invalid or the list is empty
     */
    void handleMarkCommand(String input, boolean markAsDone) throws IncyException {
        if (tasks.isEmpty()) {
            throw new IncyException("Nah, mate, nothin' to tick off, yer list's empty!");
        }

        int index = Integer.parseInt(input.substring(markAsDone ? 5 : 7)) - 1;
        if (!isValidIndex(index)) {
            throw new IncyException("Gimme a legit number, will ya? That one's not on.");
        }

        tasks.get(index).setDone(markAsDone);
        System.out.println(Constants.LINE_STRING_TOP + Constants.ANSI_CYAN + "Banging! This one's sorted!:\n  " + tasks.get(index) + "\n" + Constants.LINE_STRING_BOTTOM);
        saveTasksToFile();
    }

    /**
     * Checks if the given index is a valid index within the tasks list.
     *
     * @param index The index to check
     * @return true if the index is valid, false otherwise
     */
    private boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

    /**
     * Handles the "todo", "deadline", and "event" commands, adding a new task to the list.
     *
     * @param input The user input string containing the command and task details
     * @throws IncyException If the task format is invalid
     */
    void handleAddTask(String input) throws IncyException {
        Task newTask = TaskFactory.createTask(input);
        if (newTask != null) {
            tasks.add(newTask);
            printTaskAddedMessage(newTask);
            saveTasksToFile();
        }
    }

    /**
     * Handles the "delete" command, removing a task from the list.
     *
     * @param input The user input string containing the command and task index
     * @throws IncyException If the task index is invalid or the list is empty
     */
    void handleDeleteCommand(String input) throws IncyException {
        if (tasks.isEmpty()) {
            throw new IncyException("Oi, nothin' to bin 'ere, yer list's bare!");
        }

        int index = Integer.parseInt(input.substring(7)) - 1;
        if (!isValidIndex(index)) {
            throw new IncyException("That ain't a legit number, try again with a proper one, yeah?");
        }

        Task deletedTask = tasks.get(index);
        removeTaskAt(index);
        System.out.println(Constants.LINE_STRING_TOP + Constants.ANSI_CYAN +
                "Safe! I've dashed this task:\n" +
                "  " + deletedTask + "\n" +
                "Now you've got " + tasks.size() + " tasks on your plate.\n" +
                Constants.LINE_STRING_BOTTOM);
        saveTasksToFile();
    }

    /**
     * Handles the "find" command, searching for tasks containing a given keyword.
     *
     * @param input The user input string containing the command and keyword
     * @throws IncyException If the keyword is missing
     */
    void handleFindCommand(String input) throws IncyException {
        if (input.length() <= 5) {
            throw new IncyException("Oi, you forgot to tell me what to find, mate!");
        }
        String keyword = input.substring(5).trim().toLowerCase();
        System.out.println(Constants.LINE_STRING_TOP);
        boolean found = false;
        int index = 1;
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                System.out.println(Constants.ANSI_CYAN + "  " + index++ + ". " + task);
                found = true;
            }
        }
        if (!found) {
            System.out.println(Constants.ANSI_RED + "No tasks found with the keyword '" + keyword + "', bruv.");
        }
        System.out.println(Constants.LINE_STRING_BOTTOM);
    }

    /**
     * Handles the "list by" command, listing tasks for a specific date.
     *
     * @param input The user input string containing the command and date
     * @throws IncyException If the date format is invalid
     */
    void handleListByDateCommand(String input) throws IncyException {
        String dateString = input.substring(9).trim();
        LocalDate date;
        try {
            date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new IncyException("Invalid date format, mate. Use 'yyyy-MM-dd' format.");
        }
        System.out.println(Constants.LINE_STRING_BOTTOM);
        boolean found = false;
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getBy().toLocalDate().equals(date)) {
                    System.out.println(Constants.ANSI_CYAN + "- " + deadline);
                    found = true;
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getStart().toLocalDate().equals(date) || event.getEnd().toLocalDate().equals(date)) {
                    System.out.println(Constants.ANSI_CYAN + "- " + event);
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println(Constants.ANSI_RED + "No tasks found for the specified date, bruv.");
        }
        System.out.println(Constants.LINE_STRING_BOTTOM);
    }

    /**
     * Removes the task at the given index from the tasks list.
     *
     * @param index The index of the task to remove
     */
    private void removeTaskAt(int index) {
        tasks.remove(index);
    }

    /**
     * Prints a message indicating that a task has been added to the list.
     *
     * @param task The task that was added
     */
    private void printTaskAddedMessage(Task task) {
        System.out.println(Constants.LINE_STRING_TOP + Constants.ANSI_CYAN +
                "Sorted! Your task's in the bag, innit mate:\n" +
                "  " + task + "\n" +
                "You're now juggling " + tasks.size() + " tasks on your list, innit.\n" +
                Constants.LINE_STRING_BOTTOM);
    }

    /**
     * Loads tasks from the data file and populates the tasks list.
     */
    void loadTasksFromFile() {
        Path dataFilePath = Paths.get(Constants.DATA_FOLDER, Constants.DATA_FILE);
        if (Files.exists(dataFilePath)) {
            try {
                Scanner scanner = new Scanner(dataFilePath);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Task task = TaskFactory.createTaskFromLine(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
                scanner.close();
            } catch (IOException e) {
                TaskFactory.printFormatError("Failed to load tasks from file: " + e.getMessage());
            }
        }
    }

    /**
     * Saves the tasks list to the data file.
     */
    void saveTasksToFile() {
        Path dataFilePath = Paths.get(Constants.DATA_FOLDER, Constants.DATA_FILE);
        try {
            FileWriter writer = new FileWriter(dataFilePath.toFile());
            for (Task task : tasks) {
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            TaskFactory.printFormatError("Failed to save tasks to file: " + e.getMessage());
        }
    }
}