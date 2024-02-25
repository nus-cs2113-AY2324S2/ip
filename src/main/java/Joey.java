import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Joey {
    private static final String DASHED_LINE = "____________________________________________________________";

    private static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File("./data/tasks.txt");

        if (!file.exists()) return tasks;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Task task = parseTask(line); // Use parseTask here
                    tasks.add(task);
                } catch (IllegalArgumentException e) {
                    System.out.println("Error parsing task: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load tasks: " + e.getMessage());
        }
        return tasks;
    }

    private static void saveTasksToFile(ArrayList<Task> tasks) throws IOException {
        File file = new File("./data/tasks.txt");
        file.getParentFile().mkdirs();
        try(PrintWriter writer = new PrintWriter(file)){
            for(Task task : tasks){
                if (task != null) {
                    writer.println(task.toSaveFormat());
                }
            }
        }
    }

    private static Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        switch (parts[0]) {
            case "T":
                return new Todo(description, isDone);
            case "D":
                if (parts.length < 4) throw new IllegalArgumentException("Invalid deadline format.");
                String by = parts[3].trim();
                return new Deadline(description, by, isDone);
            case "E":
                if (parts.length < 5) throw new IllegalArgumentException("Invalid event format.");
                String from = parts[3].trim();
                String to = parts[4].trim();
                return new Event(description, from, to, isDone);
            default:
                throw new IllegalArgumentException("Unknown task type: " + parts[0]);
        }
    }


    public static void main(String[] args) {
        ArrayList<Task> tasks = loadTasksFromFile();
        System.out.println("Hello! I'm Joey");
        System.out.println("What can I do for you?");
        System.out.println(DASHED_LINE);

        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                String userCommand = scanner.nextLine();
                System.out.println(DASHED_LINE);

                if (userCommand.equalsIgnoreCase("bye")) {
                    saveTasksToFile(tasks);
                    System.out.println(" bye bye, take care:)!");
                    System.out.println(DASHED_LINE);
                    break;
                } else if (userCommand.equalsIgnoreCase("list")) {
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + ". " + tasks.get(i).toString());
                    }
                    System.out.println(DASHED_LINE);
                } else if (userCommand.startsWith("todo")) {
                    try {
                        String description = userCommand.substring(5).trim();
                        if (description.isEmpty()) {
                            throw new JoeyException("Hey girlie, the description of the todo can't be empty. Follow this format instead: todo buy eggs");
                        }
                        tasks.add(new Todo(description, false));
                        System.out.println(" Got it. I've added this task:");
                        System.out.println("   " + tasks.get(tasks.size() - 1));
                        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println(DASHED_LINE);
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("OOPS!!! The description of a todo cannot be empty.");
                        System.out.println(DASHED_LINE);
                    } catch (JoeyException e) {
                        System.out.println("OOPS!!! " + e.getMessage());
                        System.out.println(DASHED_LINE);
                    }
                    saveTasksToFile(tasks);
                } else if (userCommand.startsWith("deadline")) {
                    try {
                        String[] parts = userCommand.substring(9).split("/by");
                        if (parts.length != 2) {
                            throw new JoeyException("Hey girlie, please provide both description and deadline for the deadline task. Follow this format instead: deadline return book /by Sunday");
                        }

                        String description = parts[0].trim();
                        String by = parts[1].trim();

                        if (description.isEmpty() || by.isEmpty()) {
                            throw new JoeyException("Hey girlie, the description and deadline for the deadline task can't be empty. Follow this format instead: deadline return book /by Sunday");
                        }

                        tasks.add(new Deadline(description, by, false));
                        System.out.println(" Got it. I've added this task:");
                        System.out.println("   " + tasks.get(tasks.size() - 1));
                        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println(DASHED_LINE);
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("OOPS!!! Hey girlie, please provide both description and deadline for the deadline task. Follow this format instead: deadline return book /by Sunday");
                        System.out.println(DASHED_LINE);
                    } catch (JoeyException e) {
                        System.out.println("OOPS!!! " + e.getMessage());
                        System.out.println(DASHED_LINE);
                    }
                    saveTasksToFile(tasks);
                } else if (userCommand.startsWith("event")) {
                    try {
                        String[] parts = userCommand.split("/from");
                        if (parts.length != 2) {
                            throw new JoeyException("Hey girlie, please provide both description, start time, and end time for the event task. Follow this format instead: event project meeting /from Mon 2pm /to 4pm");
                        }

                        String description = parts[0].substring(6).trim();
                        String[] timingParts = parts[1].split("/to");
                        if (timingParts.length != 2) {
                            throw new JoeyException("Hey girlie, please provide both description, start time, and end time for the event task. Follow this format instead: event project meeting /from Mon 2pm /to 4pm");
                        }

                        String from = timingParts[0].trim();
                        String to = timingParts[1].trim();

                        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                            throw new JoeyException("Hey girlie, please provide both description, start time, and end time for the event task. Follow this format instead: event project meeting /from Mon 2pm /to 4pm");
                        }

                        tasks.add(new Event(description, from, to, false));
                        System.out.println(" Got it. I've added this task:");
                        System.out.println("   " + tasks.get(tasks.size() - 1));
                        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println(DASHED_LINE);
                    } catch (JoeyException e) {
                        System.out.println("OOPS!!! " + e.getMessage());
                        System.out.println(DASHED_LINE);
                    }
                    saveTasksToFile(tasks);
                } else if (userCommand.startsWith("mark")) {
                    int taskIndex = Integer.parseInt(userCommand.split(" ")[1]) - 1;
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        Task task = tasks.get(taskIndex);
                        task.markDone();
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println("   [" + (task.isDone() ? "X" : " ") + "] " + task.getDescription());
                        System.out.println(DASHED_LINE);
                    } else {
                        System.out.println(" Task not found. Please enter a valid task number OR enter 'list' to view your current list:)");
                        System.out.println(DASHED_LINE);
                    }
                    saveTasksToFile(tasks);
                } else if (userCommand.startsWith("unmark")) {
                    int taskIndex = Integer.parseInt(userCommand.split(" ")[1]) - 1;
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        Task task = tasks.get(taskIndex);
                        task.markNotDone();
                        System.out.println(" okay, I have marked this task as not done yet:");
                        System.out.println("   [" + (task.isDone() ? "X" : " ") + "] " + task.getDescription());
                        System.out.println(DASHED_LINE);
                    } else {
                        System.out.println(" Task not found. Please enter a valid task number OR enter 'list' to view your current list:)");
                        System.out.println(DASHED_LINE);
                    }
                    saveTasksToFile(tasks);
                } else if (userCommand.startsWith("delete")) {
                    try {
                        int taskIndex = Integer.parseInt(userCommand.substring(7).trim()) - 1;

                        if (taskIndex >= 0 && taskIndex < tasks.size()) {
                            Task removedTask = tasks.remove(taskIndex);

                            System.out.println(" Noted. I've removed this task:");
                            System.out.println("   " + removedTask);
                            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                            System.out.println(DASHED_LINE);
                        } else {
                            System.out.println(" Task not found. Please enter a valid task number OR enter 'list' to view your current list:)");
                            System.out.println(DASHED_LINE);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(" Please enter a valid task number to delete.");
                        System.out.println(DASHED_LINE);
                    }
                    saveTasksToFile(tasks);
                }
                else {
                    System.out.println("Invalid input. Please try again.");
                    System.out.println(DASHED_LINE);
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}