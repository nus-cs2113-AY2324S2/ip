import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage{

    private File file;
    private Parser parser;
    private static final String FILE_PATH = "./data/chandler.txt";

    /**
     * Load the task list from the file
     *
     * @param taskList The task list
     * @param filePath The file path to the file from which to load the task list
     * @throws ChandlerException If the file is not found or there is an error reading the file
     */
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
            throw new ChandlerException("File not found: " + e.getMessage());
        } catch (IOException e) {
            // Handle IO exceptions
            throw new ChandlerException("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Parses the words in the input line from the file and add a task to the task list
     *
     * @param taskType The type of the task - todo, deadline, or event
     * @param doneStatus The status of the task - done or not done
     * @param description The description of the task
     * @param additionalInfo The additional information of the task such as deadline or event time
     * @param taskList The task list to which the created task will be added.
     * @throws ChandlerException If there is a problem creating the task due to corrupted data or unexpected task type.
     */
    public void createTaskFromParsedLine(String taskType, int doneStatus,
        String description, String additionalInfo, TaskList taskList) throws ChandlerException {
        switch (taskType) {
            case "T": // Todo
                Todo taskTodo = new Todo(description);
                if (doneStatus == 1) {
                    taskTodo.markAsDone();
                }
                taskList.addTask(taskTodo);
                break;
            case "D": // Deadline
                Deadline taskDeadline = new Deadline(description, additionalInfo);
                if (doneStatus == 1) {
                    taskDeadline.markAsDone();
                }
                taskList.addTask(taskDeadline);
                break;
            case "E": // Event
                String[] fromTo = additionalInfo.split(" - ");
                Event taskEvent = new Event(description, fromTo[0], fromTo[1]);
                if (doneStatus == 1) {
                    taskEvent.markAsDone();
                }
                taskList.addTask(taskEvent);
                break;
            default:
                throw new ChandlerException("Corrupted data: Unexpected task type in the file.");
        }
    }

    /**
     * Save the task list to the file
     * Format: Type | Status | Description | Additional Info (optional)
     *
     * @param taskList The task list
     * @throws ChandlerException If there is an error writing to the file
     */
    public void saveTaskListToFile(TaskList taskList) throws ChandlerException {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (int i = 0; i < taskList.getListSize(); i++) {
                Task currentTask = taskList.getTask(i);
                String taskType = currentTask.getTaskType();
                String doneStatus = currentTask.isDone ? "1" : "0";
                String description = currentTask.getDescription();
                String additionalInfo = "";

                if (taskType == null) {
                    throw new ChandlerException(" Corrupted data: Unexpected task type in the file.");
                }
                switch (taskType) {
                    case "T":
                        additionalInfo = "";
                        break;
                    case "D":
                        additionalInfo = " | " + ((Deadline) currentTask).by;
                        break;
                    case "E":
                        additionalInfo = " | " + ((Event) currentTask).from + " - " + ((Event) currentTask).to;
                        break;
                }
                writer.write(taskType + " | " + doneStatus + " | " + description + additionalInfo + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new ChandlerException("Error writing to file: " + e.getMessage());
        }
    }
}






