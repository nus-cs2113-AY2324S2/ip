import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage{

    private File file;
    private Parser parser;
    private static final String FILE_PATH = "./data/chandler.txt";

    // Load the task list from the task file
    public void loadTaskListFromFile(TaskList taskList, String filePath) throws ChandlerException {
        try {
            file = new File(filePath);
            parser = new Parser();
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                parser.parseLineFromFile(line, taskList);
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






