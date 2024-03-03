import java.util.ArrayList;
import java.util.stream.Collectors;

public class List {

    public static void addTask(ArrayList<Task> tasks, Task task) {
        tasks.add(task);
    }

    public static int getTotal (ArrayList<Task> tasks) {
        return tasks.size();
    }

    public static void removeTask(ArrayList<Task> tasks, int index) {
        tasks.remove(index);
    }

    public static void handleTasks(ArrayList<Task> tasks) {
        if (List.getTotal(tasks) == Constant.ARRAY_START_INDEX) {
            Reply.printReply(Reply.EMPTY_LIST);
            return;
        }

        int taskIndex = 1;
        Reply.printReply("Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println(taskIndex + ". "  + task.toString());
            taskIndex++;
        }
    }

    public static void searchList(ArrayList<Task> tasks, String userInput) {
        String query = userInput.substring(Constant.FIND_OFFSET).trim();
        if (query.isEmpty()) {
            throw new CustomException(Reply.UNSPECIFIED_PARAMETER);
        }

        ArrayList<Task> filteredList = filteredByString(tasks, query);
        if (filteredList.isEmpty()) {
            Reply.printReply(Reply.NO_RESULTS);
        } else {
            Reply.printSearch(filteredList);
        }

    }
    public static ArrayList<Task> filteredByString(ArrayList<Task> tasks, String filterString) {
        return (ArrayList<Task>) tasks.stream()
                .filter((t) -> t.getLabel().contains(filterString))
                .collect(Collectors.toList());
    }


}
