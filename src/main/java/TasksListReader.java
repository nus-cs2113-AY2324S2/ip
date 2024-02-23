import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TasksListReader implements FileInterface {
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
