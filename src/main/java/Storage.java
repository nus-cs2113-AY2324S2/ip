import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasksFromFile() {
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

    public void saveTasksToFile(ArrayList<Task> tasks) {
        File file = new File("./data/tasks.txt");
        file.getParentFile().mkdirs();
        try(PrintWriter writer = new PrintWriter(file)){
            for(Task task : tasks){
                if (task != null) {
                    writer.println(task.toSaveFormat());
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
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
}
