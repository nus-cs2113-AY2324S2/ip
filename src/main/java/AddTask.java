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

    public static void addTask(Task[] tasks) {
        int index = 0;
        boolean isRunning = true;
        while (isRunning) {
            Scanner userInput = new Scanner(System.in);
            String text = userInput.nextLine();
            String[] words = text.split(" ");
            switch (words[0]) {
            case "bye":
                isRunning = false;
                break;
            case "list":
                PrintText.print(PrintText.HORIZON);
                PrintTask.list(Arrays.copyOf(tasks, index));
                PrintText.print(PrintText.HORIZON + "\n");
                break;
            case "mark":
            case "unmark":
                int taskIndex = hasIndex(words) ?
                        Integer.parseInt(words[1]) - 1 : -1;
                Task.markTask(taskIndex, words[0], Arrays.copyOf(tasks, index));
                break;
            case "todo":
            case "deadline":
            case "event":
                char type = Character.toUpperCase(words[0].charAt(0));
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

