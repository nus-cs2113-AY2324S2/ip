import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Persistence {

    private static final String FILE_NAME = "saveFile.txt";

    private static final File SAVE_FILE = new File(FILE_NAME);

    public static void saveTasks(ArrayList<Task> tasks) throws CustomException {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            // Write each task in a specific format
            for (Task task : tasks) {
                writer.write(task.getType() + ":" + task.getLabel() + ":" + task.getRange() + ":" +
                        task.getStatusIcon() + '\n');
            }

            Reply.printReply("Saved tasks as: " + FILE_NAME);
            writer.close();

        } catch (IOException e) {
            throw new CustomException(Reply.SAVE_ERROR);
        }
    }

    public static void verifyIntegrity(File data) throws IOException {
        if (data.createNewFile() && data.canRead()) {
            Reply.printReply("Missing data, creating new data");
        } else {
            Reply.printReply("file found");
        }
    }

    public static int loadTasks(ArrayList<Task> tasks) throws IOException, CustomException {
        int taskCount = 0;

        try (final Scanner scanner = new Scanner(SAVE_FILE)) {

            verifyIntegrity(SAVE_FILE);

            while (scanner.hasNextLine() && !scanner.nextLine().isEmpty()) {
                String[] task = scanner.nextLine().split(":");
                String input = task[0].trim();

                Command command = Command.valueOf(input);

                switch(command){
                case TODO: {
                    spawnToDo(tasks, task, taskCount);
                    break;
                }
                case DEADLINE: {
                    spawnDeadline(tasks, task, taskCount);
                    break;
                }
                case EVENT: {
                    spawnEvent(tasks, task, taskCount);
                    break;
                }
                }
                taskCount++;
            }

            return taskCount;

        } catch (IOException e) {
            if (!new File(FILE_NAME).exists()) {
                System.out.println("Data file not found. Starting with an empty list.");
            } else {
                throw new CustomException(Reply.LOAD_ERROR + taskCount);
            }
        } catch (CustomException e) {
            Reply.printException(e);
        }

        return taskCount;
    }

    // Convert a string back to a Deadline object
    public static void spawnDeadline(ArrayList<Task> tasks,String[] task, int taskCount) throws CustomException {

        String label = task[1].trim();
        String due = task[2].trim();
        boolean isCompleted = !task[3].trim().isEmpty();

        Deadline deadline = new Deadline(label, due);
        tasks.add(deadline);

        updateCompletion(tasks, isCompleted, taskCount);
    }

    // Convert a string back to an Event object
    public static void spawnEvent(ArrayList<Task> tasks,String[] task, int taskCount) throws CustomException {

        String label = task[1].trim();
        String from = task[2].trim();
        String to = task[3].trim();
        boolean isCompleted = !task[4].trim().isEmpty();

        Event event = new Event(label, from, to);
        tasks.add(event);

        updateCompletion(tasks, isCompleted, taskCount);
    }


    // Convert a string back to a ToDo object
    public static void spawnToDo(ArrayList<Task> tasks,String[] task, int taskCount) throws CustomException {
        String label = task[1].trim();
        boolean isCompleted = !task[2].trim().isEmpty();

        ToDo toDo = new ToDo(label);
        tasks.add(toDo);

        updateCompletion(tasks, isCompleted, taskCount);
    }

    public static void updateCompletion(ArrayList<Task> tasks, boolean value, int taskCount) throws CustomException {
        try {
            tasks.get(taskCount).setCompleted(value);
        } catch (CustomException e) {
            Reply.printException(e);
        }
    }

}
