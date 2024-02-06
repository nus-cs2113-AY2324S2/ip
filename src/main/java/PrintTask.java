public class PrintTask {
    public static void list(Task[] tasks) {
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

    public static void specialTask(Task newTask, int taskNum) {
        String intro = "Got it. I've added this task: \n";
        char type = newTask.getTypeIcon();
        String taskDisplayed;
        String[] words = newTask.description.split(" ");
        switch(words[0]) {
        case "todo":
            taskDisplayed = newTask.description.replace("todo ", "");
            break;
        case "deadline":
            taskDisplayed = newTask.description.replace("deadline ", "");
            taskDisplayed = taskDisplayed.replaceFirst("/", "(");
            taskDisplayed = taskDisplayed.replaceFirst("by ", "by: ") + ")";
            break;
        case "event":
            taskDisplayed = newTask.description.replace("event ", "");
            taskDisplayed = taskDisplayed.replaceFirst("/", "(");
            taskDisplayed = taskDisplayed.replaceFirst("/", "");
            taskDisplayed = taskDisplayed.replaceFirst("from ", "from: ");
            taskDisplayed = taskDisplayed.replaceFirst("to ", "to: ") + ")";
            break;
        default:
            taskDisplayed = newTask.description;
        }
        String typeMark = "[" + type + "]";
        String statusMark = "[" + newTask.getStatusIcon() + "] ";
        String taskCount = String.format("Now you have %d tasks in the list.", taskNum);
        String output = intro +typeMark + statusMark + taskDisplayed + "\n" + taskCount;
        PrintText.printWithHorizon(output);
        newTask.setDescription(taskDisplayed);
    }

    public static void normalTask(Task newTask, int taskNum) {
        String taskCount = String.format("Now you have %d tasks in the list.", taskNum);
        PrintText.printWithHorizon("added: " + newTask.description + "\n" + taskCount);
    }
}
