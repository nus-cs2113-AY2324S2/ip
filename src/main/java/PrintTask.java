import java.util.ArrayList;

public class PrintTask {
    public static void list(ArrayList<Task> tasks) {
        int index = 1;
        PrintText.print("Here are the tasks in your list:");
        for (Task task : tasks) {
            String indexPrinted = index + ".";
            char type = task.getTypeIcon();
            String typeMark = "[" + type + "]";
            String statusMark = "[" + task.getStatusIcon() + "] ";
            PrintText.print(indexPrinted + typeMark + statusMark + task.description);
            index++;
        }
    }

    public static void printToFile(ArrayList<Task> tasks) {
        String output = "";
        int index = 1;
        for (Task task : tasks) {
            String indexPrinted = index + ".";
            char type = task.getTypeIcon();
            String typeMark = "[" + type + "]";
            String statusMark = "[" + task.getStatusIcon() + "] ";
            output += indexPrinted + typeMark + statusMark + task.description + "\n";
            index++;
        }
        DukeFile.updateFile(output);
    }

    public static void specialTask(Task newTask, int taskNum) {
        String intro = "Got it. I've added this task: \n";
        char type = newTask.getTypeIcon();
        String taskDisplayed;
        String[] descriptionWords = newTask.description.split(" ");
        switch(descriptionWords[0]) {
        case "todo":
            taskDisplayed = newTask.description.replace("todo ", "");
            break;
        case "deadline":
            taskDisplayed = newTask.description.replace("deadline ", "");
            if (taskDisplayed.contains("/by ")) {
                taskDisplayed = taskDisplayed.replaceFirst("/by ", "(by: ");
                taskDisplayed += ")";
            }
            break;
        case "event":
            taskDisplayed = newTask.description.replace("event ", "");
            if (taskDisplayed.contains("/from ") && taskDisplayed.contains("/to ")) {
                taskDisplayed = taskDisplayed.replaceFirst("/from ", "(from: ");
                taskDisplayed = taskDisplayed.replaceFirst("/to ", "to: ");
                taskDisplayed += ")";
            }
            break;
        default:
            taskDisplayed = newTask.description;
        }
        String typeMark = "[" + type + "]";
        String statusMark = "[" + newTask.getStatusIcon() + "] ";
        String taskCount = String.format("Now you have %d tasks in the list.", taskNum);
        String output = intro +typeMark + statusMark + taskDisplayed + "\n" + taskCount;
        PrintText.printWithLinebreak(output);
        newTask.setDescription(taskDisplayed);
    }

    public static void normalTask(Task newTask, int taskNum) {
        String taskCount = String.format("Now you have %d tasks in the list.", taskNum);
        PrintText.printWithLinebreak("added: " + newTask.description + "\n" + taskCount);
    }
}
