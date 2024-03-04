import java.util.ArrayList;
import java.util.Arrays;

public class SearchTasks {
    public static ArrayList<Task> matchedTasks(String toFind, ArrayList<Task> allTasks) {
        ArrayList<Task> outputList = new ArrayList<>();
        ArrayList<String> toFindWords = new ArrayList<>(Arrays.asList(toFind.split(" ")));
        for (Task task : allTasks) {
            ArrayList<String> descriptionWords = new ArrayList<>(Arrays.asList(task.description.split(" ")));
            boolean isOneWordMatched = false;
            for (String word : toFindWords) {
                if (descriptionWords.contains(word)) {
                    isOneWordMatched = true;
                    break;
                }
            }
            if (isOneWordMatched && task.description.contains(toFind)) {
                outputList.add(task);
            }
        }
        return outputList;
    }

    public static void printMatchedTasks(String toFind, ArrayList<Task> allTasks) {
        if (toFind.isEmpty()) {
            PrintText.printWithLinebreak("Please enter a valid item to search.");
            return;
        }
        ArrayList<Task> tasksFound = matchedTasks(toFind, allTasks);
        try {
            Task test = tasksFound.get(0);
        } catch (IndexOutOfBoundsException e) {
            PrintText.printWithLinebreak("There are no matching tasks in your list.");
            return;
        }

        PrintText.print(PrintText.LINEBREAK);
        PrintText.print("Here are the matching tasks in your list: ");
        int index = 1;
        for (Task task : tasksFound) {
            String indexPrinted = index + ".";
            char type = task.getTypeIcon();
            String typeMark = "[" + type + "]";
            String statusMark = "[" + task.getStatusIcon() + "] ";
            PrintText.print(indexPrinted + typeMark + statusMark + task.description);
            index++;
        }
        PrintText.print(PrintText.LINEBREAK + "\n");
    }
}
