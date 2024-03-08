package seedu.salmonsan.filing;

import seedu.salmonsan.data.TasksList;
import seedu.salmonsan.data.task.DeadlineTask;
import seedu.salmonsan.data.task.EventTask;
import seedu.salmonsan.data.exception.SalmonMissingArgument;
import seedu.salmonsan.data.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TasksListReader implements FileInterface {
    /**
     * parse the .txt file found into a TasksList object and returns it
     * @param currentFile
     * @return TasksList object containing tasks in .txt file
     * @throws FileNotFoundException
     */
    public static TasksList parse(File currentFile) throws FileNotFoundException {
                TasksList tasksList = new TasksList();
        Scanner scanner = new Scanner(currentFile);
        while(scanner.hasNext()) {
            String line = scanner.nextLine();
            try {
                switch (line.charAt(0)) {
                    case 'T':
                        Task task = new Task();
                        task.parse(line);
                        tasksList.addTask(task);
                        break;
                    case 'D':
                        DeadlineTask deadline = new DeadlineTask();
                        deadline.parse(line);
                        tasksList.addTask(deadline);
                        break;
                    case 'E':
                        EventTask event = new EventTask();
                        event.parse(line);
                        tasksList.addTask(event);
                        break;
                    default:
                        System.out.println("This should not have happened...");
                }
            } catch (SalmonMissingArgument e) {
                throw new RuntimeException(e);
            }
        }
        return tasksList;
    }
}
