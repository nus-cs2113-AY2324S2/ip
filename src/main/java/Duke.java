import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
//hi

public class Duke {
    private static final String FILE_PATH = "./data/duke.txt";
    static ArrayList<Task> tasks = new ArrayList<>();


    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Sura");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        loadTasksFromFile();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                //@@author Nikhil
                String userInput = scanner.nextLine();

                if ("bye".equalsIgnoreCase(userInput)) {
                    break;

                }

                if ("list".equalsIgnoreCase(userInput)) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        System.out.println((i + 1) + "." + task);
                    }
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("mark")) {
                    processTask(userInput, tasks, true);
                } else if (userInput.startsWith("unmark")) {
                    processTask(userInput, tasks, false);
                } else if (userInput.startsWith("todo")) {

                    String description = userInput.substring(4).trim();

                    if (description.isEmpty()) {
                        throw new DukeException("☹ OOPS!!! The task's description cannot be left empty. Add something!");
                    }

                    Todo newTodo = new Todo(description);
                    tasks.add(newTodo);

                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + newTodo);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    //@author Nikhil
                } else if (userInput.startsWith("deadline")) {
                   //@@author Nikhil
                    String content = userInput.substring(8).trim();
                    int index = content.indexOf("/by");

                    if (index == -1) {
                        throw new DukeException("Please use '/by' to specify the deadline time.");
                    } else {
                        String description = content.substring(0, index).trim();
                        String by = content.substring(index + 4).trim();
                        Deadline newDeadline = new Deadline(description, by);
                        tasks.add(newDeadline);

                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + newDeadline);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                        //@@author Nikhil
                    }
                } else if (userInput.startsWith("event")) {
                    //@@author PradeepM

                    String content = userInput.substring(5).trim();

                    String[] parts = content.split("/from | /to ");

                    if (parts.length < 3) {
                        throw new DukeException("Please use the format: event [description] /from [start time] /to [end time]");
                    }

                    String description = parts[0].trim();
                    String from = parts[1].trim();
                    String to = parts[2].trim();

                    Event newEvent = new Event(description, from, to);
                    tasks.add(newEvent);


                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + newEvent);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    //@@author PradeepM
                } else if (userInput.startsWith("delete")) {
                    //@@author PradeepM
                    int taskNumber;
                    try {
                        taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                        if (taskNumber <= 0 || taskNumber > tasks.size()) {
                            throw new DukeException("Invalid task number!");
                        }

                        Task deletedTask = tasks.get(taskNumber - 1);

                        tasks.remove(taskNumber - 1);

                        System.out.println("____________________________________________________________");
                        System.out.println("Noted. I've removed this task: ");
                        System.out.println("  " + deletedTask);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                    } catch (NumberFormatException e) {
                        System.out.println("____________________________________________________________");
                        System.out.println("Please provide a valid task number.");
                        System.out.println("____________________________________________________________");
                    } catch (DukeException de) {
                        System.out.println("____________________________________________________________");
                        System.out.println(de.getMessage());
                        System.out.println("____________________________________________________________");
                    }
                    //@@author PradeepM
                } else {
                    throw new DukeException("☹ OOPS!!! What is that? I don't know what is that? What is the meaning :-(");
                }
            } catch (DukeException de) {
                System.out.println("____________________________________________________________");
                System.out.println(de.getMessage());
                System.out.println("____________________________________________________________");
            }
            saveTasksToFile();
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }


    //@@author Nikhil
    public static void processTask(String userInput, ArrayList<Task> tasks, boolean mark) {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(userInput.split(" ")[1]);
            if (taskNumber <= 0 || taskNumber > tasks.size()) {
                throw new IndexOutOfBoundsException();
            }

            Task task = tasks.get(taskNumber - 1);
            if (mark) {
                task.markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
            } else {
                task.unmarkAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println("  [" + task.getStatusIcon() + "] " + task.description);
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("That task doesn't exist!");
        }
    }
    //@@author Nikhil

    //@@author PradeepM
    public static void saveTasksToFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs(); // Create directories if they don't exist
            }
            file.createNewFile(); // Create the file if it doesn't exist
            try (FileWriter writer = new FileWriter(file)) {
                for (int i = 0; i < tasks.size(); i++) {
                    writer.write(tasks.get(i).toFileString() + "\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Error saving tasks to file: " + e.getMessage());
        }
    }
    //@@author PradeepM

    // Method to load tasks from a file
    // Method to load tasks from a file

    //@@author Nikhil
    public static void loadTasksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromString(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            // Handle the case where the file doesn't exist
            System.err.println("File not found. Creating a new file...");
            File file = new File(FILE_PATH);
            try {
                file.createNewFile(); // Create the file if it doesn't exist
            } catch (IOException ioException) {
                System.err.println("Error creating a new file: " + ioException.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Error loading tasks from file: " + e.getMessage());
        }
    }
    //@@author Nikhil
}

