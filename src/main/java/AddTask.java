import java.util.Arrays;
import java.util.Scanner;

public class AddTask {
    public static void checkDescription(String[] userInputWords) throws
            DukeExceptions.NoDescriptionException {
        if (userInputWords.length == 1) {
            DukeExceptions.throwNoDescriptionException();
        }
    }

    public static Task addSpecialTask(String description, int listIndex, Task[] tasks) {
        String[] userInputWords = description.split(" ");
        try {
            checkDescription(userInputWords);
        } catch(DukeExceptions.NoDescriptionException e) {
            String taskType = userInputWords[0];
            PrintText.printWithLinebreak(taskType + " must have a description.");
            return new Task("Not special task");
        }
        char type = Character.toUpperCase(userInputWords[0].charAt(0));
        Task specialTask = new Task(description, type);
        tasks[listIndex] = specialTask;
        return specialTask;
    }

    public static void taskListManager(Task[] tasks) {
        int index = 0;
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
                PrintTask.list(Arrays.copyOf(tasks, index));
                PrintText.print(PrintText.LINEBREAK + "\n");
                break;
            case "mark":
            case "unmark":
                MarkTask.mark(userInputWords, Arrays.copyOf(tasks, index));
                break;
            case "todo":
            case "deadline":
            case "event":
                Task specialTask = addSpecialTask(text, index, tasks);
                if (specialTask.type != ' ') {
                    tasks[index] = specialTask;
                    index++;
                    PrintTask.specialTask(specialTask, index);
                }
                break;
            default:
                Task task = new Task(text);
                tasks[index] = task;
                index++;
                PrintTask.normalTask(task, index);
            }
            PrintTask.printToFile(Arrays.copyOf(tasks, index));
        }
    }
}

