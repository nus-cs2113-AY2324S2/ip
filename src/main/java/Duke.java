/**
 * Represents a task management program that allows users to manage their tasks.
 *
 * <p>Users can add different types of tasks including todos, deadlines, and events.
 * They can also mark tasks as done, delete tasks, and search for tasks by keyword.
 *
 * @param FILE_PATH the file path where tasks are stored
 * @param ui the user interface for displaying messages
 * @param tasks the list of tasks currently managed by the program
 *
 * @throws DukeException if there is an error in processing user commands
 * @throws IOException if there is an error in loading or saving tasks from/to file
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String FILE_PATH = "./data/duke.txt";
    private static final Ui ui = new Ui();
    private static final List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        // Load tasks from file
        try {
            loadTasksFromFile(FILE_PATH);
        } catch (IOException e) {
            ui.showError("Error loading tasks from file: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        ui.showWelcomeMessage();

        while (true) {
            String input = scanner.nextLine();
            ui.showLine();

            try {
                String command = parseCommand(input);
                switch (command) {
                    case "bye":
                        ui.showGoodbyeMessage();
                        saveTasksToFile(FILE_PATH);
                        scanner.close();
                        return;
                    case "list":
                        showTasks(tasks);
                        break;
                    case "delete":
                        String[] deleteDetails = parseDetails(input);
                        int deleteIndex = Integer.parseInt(deleteDetails[1]) - 1;
                        if (deleteIndex < 0 || deleteIndex >= tasks.size()) {
                            throw new DukeException("Invalid task number.");
                        }
                        Task removedTask = tasks.remove(deleteIndex);
                        ui.showTaskRemovedMessage(removedTask, tasks.size());
                        break;
                    case "todo":
                        String[] todoDetails = parseDetails(input);
                        if (todoDetails.length < 2 || todoDetails[1].isEmpty()) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                        tasks.add(new Todo(todoDetails[1]));
                        ui.showTaskAddedMessage(tasks.get(tasks.size() - 1), tasks.size());
                        break;
                    case "deadline":
                        String[] deadlineDetails = parseDeadlineDetails(input);
                        if (deadlineDetails.length < 2 || deadlineDetails[1].isEmpty()) {
                            throw new DukeException("The description and date of a deadline cannot be empty.");
                        }
                        tasks.add(new Deadline(deadlineDetails[0], deadlineDetails[1]));
                        ui.showTaskAddedMessage(tasks.get(tasks.size() - 1), tasks.size());
                        break;
                    case "event":
                        String[] eventDetails = parseEventDetails(input);
                        if (eventDetails.length < 3 || eventDetails[1].isEmpty() || eventDetails[2].isEmpty()) {
                            throw new DukeException("The description, start, and end of an event cannot be empty.");
                        }
                        tasks.add(new Event(eventDetails[0], eventDetails[1], eventDetails[2]));
                        ui.showTaskAddedMessage(tasks.get(tasks.size() - 1), tasks.size());
                        break;
                    case "mark":
                        String[] markDetails = parseDetails(input);
                        int markIndex = Integer.parseInt(markDetails[1]) - 1;
                        if (markIndex < 0 || markIndex >= tasks.size()) {
                            throw new DukeException("Invalid task number.");
                        }
                        tasks.get(markIndex).markAsDone();
                        ui.showTaskMarkedDoneMessage(tasks.get(markIndex));
                        break;
                    case "unmark":
                        String[] unmarkDetails = parseDetails(input);
                        int unmarkIndex = Integer.parseInt(unmarkDetails[1]) - 1;
                        if (unmarkIndex < 0 || unmarkIndex >= tasks.size()) {
                            throw new DukeException("Invalid task number.");
                        }
                        tasks.get(unmarkIndex).markAsUndone();
                        ui.showTaskMarkedUndoneMessage(tasks.get(unmarkIndex));
                        break;
                    case "find":
                        String keyword = input.substring(5).trim();
                        List<Task> matchingTasks = findTasksByKeyword(keyword);
                        ui.showMatchingTasks(matchingTasks);
                        break;
                    default:
                        throw new DukeException("Invalid command");
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (NumberFormatException e) {
                ui.showError("Invalid task number. Please enter a valid number.");
            } catch (IOException e) {
                ui.showError("Error saving tasks to file: " + e.getMessage());
            }
        }
    }

    private static void loadTasksFromFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                switch (type) {
                    case "T":
                        tasks.add(new Todo(description, isDone));
                        break;
                    case "D":
                        String by = parts[3];
                        tasks.add(new Deadline(description, by, isDone));
                        break;
                    case "E":
                        String from = parts[3];
                        String to = parts[4];
                        tasks.add(new Event(description, from, to, isDone));
                        break;
                    default:
                        break;
                }
            }
            scanner.close();
        }
    }

    private static void saveTasksToFile(String filePath) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs(); // Create parent directories if they don't exist
        FileWriter writer = new FileWriter(file);
        for (Task task : tasks) {
            writer.write(task.toFileString() + "\n");
        }
        writer.close();
    }

    private static String parseCommand(String input) {
        return input.split(" ")[0];
    }

    private static String[] parseDetails(String input) {
        return input.split(" ", 2);
    }

    private static String[] parseDeadlineDetails(String input) throws DukeException {
        String[] parts = input.split(" /by ", 2);
        if (parts.length != 2 || parts[1].isEmpty()) {
            throw new DukeException("Invalid deadline command format. Usage: deadline [description] /by [date]");
        }
        return parts;
    }

    private static String[] parseEventDetails(String input) throws DukeException {
        String[] parts = input.split(" /from ", 2);
        if (parts.length != 2 || parts[1].indexOf(" /to ") == -1) {
            throw new DukeException("Invalid event command format. Usage: event [description] /from [start] /to [end]");
        }
        String from = parts[1].split(" /to ", 2)[0];
        String to = parts[1].split(" /to ", 2)[1];
        return new String[]{parts[0], from, to};
    }

    private static List<Task> findTasksByKeyword(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    private static void showTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks added yet.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
        ui.showLine();
    }
}
