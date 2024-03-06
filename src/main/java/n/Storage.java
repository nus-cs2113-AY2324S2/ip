package n;

import n.exceptions.EmptyTaskDescriptionException;
import n.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

import static n.TaskList.taskList;

public class Storage {
    public static final String FILEPATH = "./n.txt";
    public static void loadTaskList() throws FileNotFoundException, EmptyTaskDescriptionException {
        if (Files.exists(Path.of("./n.txt"), LinkOption.NOFOLLOW_LINKS)) {
            File initialTaskList = new File("./n.txt");
            Scanner s = new Scanner(initialTaskList);
            while (s.hasNextLine()){
                String taskDescription = s.nextLine();
                if (taskDescription.equals("Task List:") || taskDescription.startsWith("Number of Tasks")) {
                    continue;
                }
                Type taskType = Parser.getTaskType(taskDescription);
                String task = Parser.textToTaskDescription(taskDescription, taskType);
                switch (taskType) {
                    case ToDo:
                        taskList.add(new ToDo(task, taskList.size()));
                        break;
                    case Event:
                        taskList.add(new Event(task, taskList.size()));
                        break;
                    case Deadline:
                        taskList.add(new Deadline(task, taskList.size()));
                        break;
                }
                //mark task if task is completed
                if (taskDescription.charAt(8) == 'X') {
                    taskList.get(taskList.size() - 1).setDone(true);
                }
            }
            s.close();
        }
    }
    public static void saveTaskList() {
        Path filePath = Path.of(FILEPATH);
        try {
            Files.deleteIfExists(filePath);
            Files.createFile(filePath);
            Files.write(filePath, ("Task List:\n").getBytes(), StandardOpenOption.APPEND);
            for (Task task : taskList) {
                // Write each task into the text file
                Files.write(filePath, (task.toString()+"\n").getBytes(), StandardOpenOption.APPEND);
            }
            Files.write(filePath, ("Number of Tasks: " +taskList.size()).getBytes(), StandardOpenOption.APPEND);
            Ui.printTaskListSavedMessage(filePath);
        } catch (IOException e) {
            Ui.printMessage(Ui.SAVE_AS_FILE_ERROR);
        }
    }
}
