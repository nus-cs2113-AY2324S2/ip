import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;


public class Floda {
    private static ArrayList<Task> list = new ArrayList<>();
    private final static String NAME = "Floda";
    private static final String FILE_PATH = "./data/tasks.txt";


    public static void main(String[] args) {
        System.out.println("Hello! I'm " + NAME);
        try {
            checkIfFileExists();
            readFromFile();
        } catch (FileNotFoundException | InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("I can keep track of a to-do list for you! Just type what you want to add to the list.");
        while (true) {
            try {
                String line = scanner.nextLine().trim();
                String[] parts = line.split(" ", 2);
                String command = parts[0].trim();
                switch (command) {
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!");
                        scanner.close();
                        saveToFile();
                        return;
                    case "list":
                        if (list.isEmpty()) {
                            System.out.println("Your to-do list is empty.");
                        } else {
                            System.out.println("List so far: ");
                            for (int i = 0; i < list.size(); i++) {
                                System.out.println((i + 1) + "." + list.get(i));
                            }
                        }
                        break;
                    case "mark":
                        handleMarkTask(line);
                        saveToFile();
                        break;
                    case "unmark":
                        handleUnmarkTask(line);
                        saveToFile();
                        break;
                    case "deadline":
                        handleDeadlineTask(line);
                        saveToFile();
                        break;
                    case "todo":
                        handleTodoTask(line);
                        saveToFile();
                        break;
                    case "event":
                        handleEventTask(line);
                        saveToFile();
                        break;
                    case "delete":
                        handleDeleteTask(line);
                        saveToFile();
                        break;
                    default:
                        throw new InvalidInputException("Invalid input!");
                }
            } catch (InvalidInputException | IOException e){
            }
        }
    }

    private static void handleDeleteTask(String line) throws InvalidInputException {
        Scanner taskScanner = new Scanner(line);
        taskScanner.next();
        if (taskScanner.hasNextInt()) {
            int taskNumber = taskScanner.nextInt() - 1;
            if (isValidTaskNumber(taskNumber)) {
                System.out.println("Deleting task: " + list.get(taskNumber));
                list.remove(taskNumber);
                System.out.println("Task deleted successfully!");
            } else {
                throw new InvalidInputException("Invalid task number! Please check with 'list'.");
            }
        }
    }

    private static void handleMarkTask(String line) throws InvalidInputException {
        Scanner taskScanner = new Scanner(line);
        taskScanner.next();
        if (taskScanner.hasNextInt()) {
            int taskNumber = taskScanner.nextInt() - 1;
            if (isValidTaskNumber(taskNumber)) {
                list.get(taskNumber).setDone(true);
                System.out.println("I have marked this task as done:\n" + list.get(taskNumber));
            } else {
                throw new InvalidInputException("Invalid task number! Please check with 'list'.");
            }
        } else {
            throw new InvalidInputException("Invalid input! Please check with 'list'.");
        }
        taskScanner.close();
    }

    private static void handleUnmarkTask(String line) throws InvalidInputException {
        Scanner taskScanner = new Scanner(line);
        taskScanner.next();
        if (taskScanner.hasNextInt()) {
            int taskNumber = taskScanner.nextInt() - 1;
            if (isValidTaskNumber(taskNumber)) {
                list.get(taskNumber).setDone(false);
                System.out.println("I have marked this task as not done:\n" + list.get(taskNumber));
            } else {
                throw new InvalidInputException("Invalid task number! Please check with 'list'.");
            }
        } else {
            throw new InvalidInputException("Invalid input! Please check with 'list'.");
        }
        taskScanner.close();
    }

    private static void handleDeadlineTask(String line) throws InvalidInputException {
        Scanner taskScanner = new Scanner(line);
        taskScanner.next();
        String remaining = taskScanner.nextLine().trim();
        int byIndex = remaining.indexOf("/by");
        if (byIndex == -1 || byIndex == 0) {
            throw new InvalidInputException("Invalid input format! Use: 'deadline <description> /by <time>'");
        }
        String description = remaining.substring(0, byIndex).trim();
        String by = remaining.substring(byIndex + 3).trim();
        list.add(new Deadline(description, by, false));
        System.out.println("Added: " + list.getLast() + "\nNow you have " + list.size() + " items in the list!");
    }

    private static void handleEventTask(String line) throws InvalidInputException {
        Scanner taskScanner = new Scanner(line);
        taskScanner.next();
        String remaining = taskScanner.nextLine().trim();
        int fromIndex = remaining.indexOf("/from");
        if (fromIndex == -1 || fromIndex == 0) {
            throw new InvalidInputException("Invalid input format! Use: 'event <description> /from <start time> /to <end time>'");
        }
        int toIndex = remaining.indexOf("/to");
        if (toIndex == -1 || toIndex <= fromIndex + 5) {
            throw new InvalidInputException("Invalid input format! Use: 'event <description> /from <start time> /to <end time>'");
        }
        String description = remaining.substring(0, fromIndex).trim();
        String from = remaining.substring(fromIndex + 5, toIndex).trim();
        String to = remaining.substring(toIndex + 3).trim();
        list.add(new Events(description, from, to, false));
        System.out.println("Added: " + list.getLast() + "\nNow you have " + list.size() + " items in the list!");
    }

    private static void handleTodoTask(String line) throws InvalidInputException {
        Scanner taskScanner = new Scanner(line);
        taskScanner.next();
        String remaining = taskScanner.nextLine().trim();
        list.add(new ToDo(remaining, false));
        System.out.println("Added: " + list.getLast() + "\nNow you have " + list.size() + " items in the list!");
    }



    private static boolean isValidTaskNumber(int taskNumber) {
        return taskNumber >= 0 && taskNumber < list.size();
    }

    private static void checkIfFileExists() {
        File f = new File(FILE_PATH);
        if (!f.exists()) {
            try {
                f.createNewFile();
                System.out.println("File created: " + f.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
    }

    private static void readFromFile() throws FileNotFoundException, InvalidInputException {
        File file = new File(FILE_PATH);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            parseTask(line);
        }
        scanner.close();
    }

    private static void parseTask(String line) throws InvalidInputException {
        String[] parts = line.split("\\|");
        if (parts.length < 3) {
            throw new InvalidInputException("Invalid input format in file");
        }
        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        switch (type) {
            case "T":
                list.add(new ToDo(description, isDone));
                break;
            case "D":
                if (parts.length < 4) {
                    throw new InvalidInputException("Invalid input format for deadline in file");
                }
                String by = parts[3].trim();
                list.add(new Deadline(description, by, isDone));
                break;
            case "E":
                if (parts.length < 5) {
                    throw new InvalidInputException("Invalid input format for event in file");
                }
                String from = parts[3].trim();
                String to = parts[4].trim();
                list.add(new Events(description, from, to, isDone));
                break;
            default:
                throw new InvalidInputException("Unknown task type in file");
        }
    }

    private static void saveToFile() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task task : list) {
            fw.write(taskToLine(task) + "\n");
        }
        System.out.println("Saved to file");
        fw.close();
    }

    private static String taskToLine(Task task) {
        if (task instanceof ToDo todo) {
            return "T | " + (todo.isDone() ? "1" : "0") + " | " + todo.getDescription();
        } else if (task instanceof Deadline deadline) {
            return "D | " + (deadline.isDone() ? "1" : "0") + " | " + deadline.getDescription() + " | " + deadline.getBy();
        } else if (task instanceof Events event) {
            return "E | " + (event.isDone() ? "1" : "0") + " | " + event.getDescription() + " | " + event.getFrom() + " | " + event.getTo();
        }
        return "";
    }
}
