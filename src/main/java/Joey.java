import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.io.IOException;

public class Joey {
    private static final String DASHED_LINE = "____________________________________________________________";
    private static Storage storage;

    private static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File("./data/tasks.txt");

        if (!file.exists()) return tasks;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Task task = parseTask(line);
                    tasks.add(task);
                } catch (JoeyException e) {
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

    private static Task parseTask(String line) throws JoeyException {
        String[] parts = line.split(" \\| ");
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        switch (parts[0]) {
            case "T":
                return new Todo(description, isDone);
            case "D":
                if (parts.length < 4) throw new JoeyException("Invalid deadline format.");
                String by = parts[3].trim();
                return new Deadline(description, by, isDone);
            case "E":
                if (parts.length < 5) throw new JoeyException("Invalid event format.");
                String from = parts[3].trim();
                String to = parts[4].trim();
                return new Event(description, from, to, isDone);
            default:
                throw new JoeyException("Unknown task type: " + parts[0]);
        }
    }


    public static void main(String[] args) {
        //Used Java Collection class.ArrayList<Task>
        ArrayList<Task> tasks = loadTasksFromFile();
        Ui ui = new Ui();
        TaskList taskList = new TaskList(tasks);

        ui.showWelcomeMessage();
        ui.showTaskList(taskList.getTasks());

        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                String userCommand = scanner.nextLine();
                System.out.println(DASHED_LINE);

                if (userCommand.equalsIgnoreCase("bye")) {
                    saveTasksToFile(taskList.getTasks());
                    ui.showByeMessage();
                    break;
                } else {
                    Parser.parseUserCommand(userCommand, taskList, ui);
                }
            }
        } catch (Exception e) {
            ui.showError("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
