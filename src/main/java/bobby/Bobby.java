package bobby;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Bobby {
    public static final String FILE_PATH = "data/tasks.txt";

    public static String obtainTodoDescription(String input) throws BobbyException {
        if (input.length() < 5 || input.substring(5).trim().isEmpty()) {
            throw new BobbyException();
        }
        return input.substring(5);
    }

    public static void writeToFile(Task[] tasks, int counter) throws IOException {
        FileWriter overwrite = new FileWriter(FILE_PATH);
        FileWriter append = new FileWriter(FILE_PATH, true);
        if (counter > 0) {
            overwrite.write(taskToFileFormat(tasks[0]));
            for (int i = 1; i < counter; i += 1) {
                append.write(taskToFileFormat(tasks[i]) + "\n");
            }
        }
        overwrite.close();
        append.close();
    }

    public static String taskToFileFormat(Task task) {
        if (task instanceof Todo) {
            return "T | " + (task.isDone ? "1" : "0") + " | " + task.getDescription() + "\n";
        } else if (task instanceof Deadline) {
            return "D | " + (task.isDone ? "1" : "0") + " | " + task.getDescription() + " | " + task.getBy() + "\n";
        } else if (task instanceof Event) {
            return "E | " + (task.isDone ? "1" : "0") + " | " + task.getDescription() + " | " + task.getFrom() + " | "
                    + task.getBy() + "\n";
        }
        return "";
    }

    private static void printFileContents() throws FileNotFoundException {
        File f = new File(FILE_PATH); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        System.out.println("in print file");
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static void parseTask(String line, Task[]tasks, int index) {
        String[] parts = line.split("\\|");
        String label = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();
        switch (label) {
        case "T":
            tasks[index] = new Todo(description);
            tasks[index].setDone(isDone);
            break;
        case "D":
            String by = parts[3].trim();
            tasks[index] = new Deadline(description, by);
            tasks[index].setDone(isDone);
            break;
        case "E":
            String from = parts[3].trim();
            String to = parts[4].trim();
            tasks[index] = new Event(description, from, to);
            tasks[index].setDone(isDone);
            break;
        default:
        }
    }


    public static int loadFile(int counter, Task[] tasks) throws FileNotFoundException {
        File f = new File(FILE_PATH);
        Scanner scanner = new Scanner(f);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            parseTask(line, tasks, counter);
            counter += 1;
        }
        return counter;
    }

    public static void main(String[] args) {
        int counter = 0;
        boolean isExit = false;
        Task[] tasks = new Task[100];
        Scanner in = new Scanner(System.in);
        System.out.println("Hello I'm Bobby\n" + "What can I do for you?");
        File f = new File(FILE_PATH);
        try {
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("Something went wrong :(");
        }
        try {
            counter = loadFile(counter, tasks);
        } catch (FileNotFoundException e) {
            System.out.println("Error loading saved tasks, file not found");
        }

        while (!isExit) {
            String input = in.nextLine();
            String command;
            String description;
            String by;
            String from;
            if (input.indexOf(' ') > 0) {
                command = input.substring(0, input.indexOf(' '));
            } else {
                command = input;
            }
            int entry;
            switch (command) {
            case "bye":
                System.out.println("See you again soon!");
                isExit = true;
                break;
            case "mark":
                entry = Integer.parseInt(input.substring(5));
                if (entry > 0 && entry <= counter) {
                    tasks[entry - 1].setDone(true);
                    System.out.println("Marked as done");
                    System.out.println(entry + "." + tasks[entry - 1]);
                    try {
                        writeToFile(tasks,counter);
                    } catch (IOException e) {
                        System.out.println("Error saving changes.");
                    }
                }
                break;
            case "unmark":
                entry = Integer.parseInt(input.substring(7));
                if (entry > 0 && entry <= counter) {
                    tasks[entry - 1].setDone(false);
                    System.out.println("Unmarked");
                    System.out.println(entry + "." + tasks[entry - 1]);
                    try {
                        writeToFile(tasks,counter);
                    } catch (IOException e) {
                        System.out.println("Error saving changes.");
                    }
                }
                break;
            case "list":
                for (int i = 0; i < counter; i += 1) {
                    System.out.println((i + 1) + "." + tasks[i]);
                }
                break;
            case "todo":
                try {
                    description = obtainTodoDescription(input);
                } catch (BobbyException e) {
                    System.out.println("Please enter a valid task.");
                    break;
                }
                tasks[counter] = new Todo(description);
                System.out.println("Okay, added:\n" + tasks[counter]);
                counter += 1;
                System.out.println("Now you have " + counter + " task(s) in the list.");
                try {
                    writeToFile(tasks,counter);
                } catch (IOException e) {
                    System.out.println("Error saving changes.");
                }
                break;
            case "deadline":
                if (!input.contains("/by")) {
                    System.out.println("Please enter a valid deadline.");
                    break;
                }
                try {
                    description = input.substring(9, input.indexOf("/by") - 1);
                    by = input.substring(input.indexOf("/by") + 4);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Please enter a valid deadline task in this format:");
                    System.out.println("deadline Return Book /by Sunday");
                    break;
                }
                tasks[counter] = new Deadline(description, by);
                System.out.println("Okay, added:\n" + tasks[counter]);
                counter += 1;
                System.out.println("Now you have " + counter + " task(s) in the list.");
                try {
                    writeToFile(tasks,counter);
                } catch (IOException e) {
                    System.out.println("Error saving changes.");
                }
                break;
            case "event":
                if (!input.contains("/to") || !input.contains("/from")) {
                    System.out.println("Please enter a valid start and end date.");
                    break;
                }
                try {
                    description = input.substring(6, input.indexOf("/from") - 1);
                    by = input.substring(input.indexOf("/to") + 4);
                    from = input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Please enter a valid event in this format:");
                    System.out.println("event Project Meeting /from Mon 2pm /to 4pm");
                    break;
                }
                tasks[counter] = new Event(description, by, from);
                System.out.println("Okay, added:\n" + tasks[counter]);
                counter += 1;
                System.out.println("Now you have " + counter + " task(s) in the list.");
                try {
                    writeToFile(tasks,counter);
                } catch (IOException e) {
                    System.out.println("Error saving changes.");
                }
                break;
            default:
                System.out.println("Sorry, I didn't quite understand that.\nPlease enter a valid command.");
                break;
            }
        }
    }
}