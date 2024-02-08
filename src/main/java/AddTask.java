import java.util.Arrays;
import java.util.Scanner;

public class AddTask {
    public static boolean hasIndex(String[] words) {
        try {
            Integer.parseInt(words[1]);
            return true;
        } catch(Exception a) {
            return false;
        }
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
                int taskIndex = hasIndex(userInputWords) ?
                        Integer.parseInt(userInputWords[1]) - 1 : -1;
                Task.markTask(taskIndex, userInputWords[0], Arrays.copyOf(tasks, index));
                break;
            case "todo":
            case "deadline":
            case "event":
                char type = Character.toUpperCase(userInputWords[0].charAt(0));
                Task specialTask = new Task(text, type);
                tasks[index] = specialTask;
                index++;
                PrintTask.specialTask(specialTask, index);
                break;
            default:
                Task task = new Task(text);
                tasks[index] = task;
                index++;
                PrintTask.normalTask(task, index);
            }
        }
    }
}

