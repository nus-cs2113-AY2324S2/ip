import java.io.*;
import java.util.Scanner;

public class ChandlerFileManager extends Chandler {

    private File file;
    private final static String STORAGE_FOLDER_PATH = "./data";
    private final static String FILE_PATH = STORAGE_FOLDER_PATH + "/chandler.txt";

    // Load the task list from the task file
    public void loadTaskListFromFile(TaskList taskList) throws ChandlerException {
        try {
            file = new File(FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                parseLineFromFile(line, taskList);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            // Handle file not found
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            // Handle IO exceptions
            System.err.println("Error saving task: " + e.getMessage());
        }
    }

    // Parse the line
    public void parseLineFromFile (String line, TaskList taskList) throws ChandlerException {
        String[] parts = line.split(" \\| "); // Assuming the format is: Type | Status | Description | Additional Info (optional)
        String taskType = parts[0];
        if (parts.length >= 3) {
            Integer doneStatus = Integer.parseInt(parts[1]);
            String description = parts[2];
            String additionalInfo = parts.length > 3 ? parts[3] : "";
            createTaskFromParsedLine(taskType, doneStatus, description, additionalInfo, taskList);
        } else {
            // Handle unexpected format (corrupted data)
            System.err.println("Corrupted data: Unexpected format in the file.");
        }
    }

    // Create the tasks based on the type indicator
    public void createTaskFromParsedLine(String taskType, int doneStatus, String description, String additionalInfo, TaskList taskList) {
        switch (taskType) {
            case "T":
                Todo taskTodo = new Todo(description);
                if (doneStatus == 1) {
                    taskTodo.markAsDone();
                }
                taskList.addTask(taskTodo);
                break;
            case "D":
                Deadline taskDeadline = new Deadline(description, additionalInfo);
                if (doneStatus == 1) {
                    taskDeadline.markAsDone();
                }
                taskList.addTask(taskDeadline);
                break;
            case "E":
                String[] fromTo = additionalInfo.split(" - ");
                Event taskEvent = new Event(description, fromTo[0], fromTo[1]);
                if (doneStatus == 1) {
                    taskEvent.markAsDone();
                }
                taskList.addTask(taskEvent);
                break;
            default:
                System.err.println("Corrupted data: Unexpected task type in the file.");
        }
    }

    // Save the task list to the file (make it in the format of |)
    public void saveTaskListToFile(TaskList taskList) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {

            for (int i = 0; i < taskList.getListSize(); i++) {
                Task currentTask = taskList.getTask(i);
                String taskType = currentTask.getTaskType();
                String doneStatus = currentTask.isDone ? "1" : "0";
                String description = currentTask.getDescription();
                String additionalInfo = "";
                if (taskType.equals("T")) {
                    additionalInfo = "";
                } else if (taskType.equals("D")) {
                    additionalInfo = ((Deadline) currentTask).by;
                } else if (taskType.equals("E")) {
                    additionalInfo = ((Event) currentTask).from + " - " + ((Event) currentTask).to;
                }
                writer.write(taskType + " | " + doneStatus + " | " + description + " | " + additionalInfo + "\n");
            }
            writer.close();
        } catch (IOException e) {
            // Handle IO exceptions
            System.err.println("Error saving task: " + e.getMessage());
        }
    }
}






