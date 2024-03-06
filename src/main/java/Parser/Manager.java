package Parser;

import Storage.DukeFile;
import TaskList.DeleteTask;
import TaskList.Task;
import Ui.PrintTask;
import Ui.PrintText;

import java.util.ArrayList;
import java.util.Scanner;

import static TaskList.AddTask.addSpecialTask;

public class Manager {
    /**
     * Monitoring user input, print and update the list of tasks constantly
     * according to the keywords identified.
     * Stop only when user input equals to "bye".
     *
     * @param tasks Arraylist of tasks stored.
     */
    public static void taskListManager(ArrayList<Task> tasks) {
        int index = DukeFile.latestIndex();
        boolean isRunning = true;
        while (isRunning) {
            Scanner userInput = new Scanner(System.in);
            String text = userInput.nextLine();
            String[] userInputWords = text.split(" ");
            switch (userInputWords[0]) {
            case "bye":
                isRunning = false;
                break;
            case "list":
                PrintText.print(PrintText.LINEBREAK);
                PrintTask.list(tasks);
                PrintText.print(PrintText.LINEBREAK + "\n");
                break;
            case "mark":
            case "unmark":
                MarkTask.mark(userInputWords, tasks);
                break;
            case "todo":
            case "deadline":
            case "event":
                Task specialTask = addSpecialTask(text);
                if (specialTask.getType() != ' ') {
                    tasks.add(specialTask);
                    index++;
                    PrintTask.specialTask(specialTask, index);
                    PrintTask.printToFile(specialTask, index, true);
                }
                break;
            case "delete":
                DeleteTask.delete(userInputWords, tasks);
                index--;
                PrintTask.printMultipleToFile(tasks, false);
                break;
            case "find":
                String toFind = text.replace("find ", "");
                PrintTask.printMatchedTasks(toFind, tasks);
                break;
            default:
                Task task = new Task(text);
                tasks.add(task);
                index++;
                PrintTask.normalTask(task, index);
                PrintTask.printToFile(task, index, true);
            }
        }
    }
}
