import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AddTask {
    public static void checkDescription(String[] userInputWords) throws
            DukeExceptions.NoDescriptionException {
        if (userInputWords.length == 1) {
            DukeExceptions.throwNoDescriptionException();
        }
    }

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

    public static void taskListManager(ArrayList<Task> tasks) {
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
                }
                break;
            case "delete":
                DeleteTask.delete(userInputWords, tasks);
                index--;
                break;
            default:
                Task task = new Task(text);
                tasks.add(task);
                index++;
                PrintTask.normalTask(task, index);
            }
            PrintTask.printToFile(tasks);
        }
    }
}

