import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a command to add new task based on user input.
 */
public class AddTask {
    /**
     * Check if user input words contains any valid task description.
     *
     * @param userInputWords Words user input as an array.
     * @throws DukeExceptions.NoDescriptionException
     * If user did not put any valid task description.
     */
    public static void checkDescription(String[] userInputWords) throws
            DukeExceptions.NoDescriptionException {
        if (userInputWords.length == 1) {
            DukeExceptions.throwNoDescriptionException();
        }
    }

    /**
     * Returns a new Task that is either of a special type (toDo, event, deadline)
     * or not.
     *
     * @param description Text input by the user.
     * @return A task with a special type or a task with description
     * "Not special task" but no special type.
     */
    public static Task addSpecialTask(String description) {
        String[] userInputWords = description.split(" ");
        try {
            checkDescription(userInputWords);
        } catch(DukeExceptions.NoDescriptionException e) {
            String taskType = userInputWords[0];
            PrintText.printWithLinebreak(taskType + " must have a description.");
            return new Task("Not special task");
        }
        char type = Character.toUpperCase(userInputWords[0].charAt(0));
        return new Task(description, type);
    }

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
                if (specialTask.type != ' ') {
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
                SearchTasks.printMatchedTasks(toFind, tasks);
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

