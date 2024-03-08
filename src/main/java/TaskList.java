import java.util.ArrayList;

public class TaskList {
    private static final String LINE = "____________________________________________________________";
    private static ArrayList<Task> allTasks = new ArrayList<>();
    private static int numberOfTasks = 0;

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    public static ArrayList<Task> getAllTasks() {
        return allTasks;
    }

    public static void addTask(String userInput, boolean isLoad) {

        Task newTask = Parser.parseTask(userInput);

        if (newTask == null) {
            return;
        }

        allTasks.add(newTask);
        numberOfTasks += 1;

        // prints message if task is not being generated from load file
        if (!isLoad) {
            System.out.println("The following task has been added: ");
            System.out.println(newTask + " as task " + numberOfTasks);
        }
    }

    public enum taskStatus {
        MARK, UNMARK, DELETE
    }

    public static void operateTask(String userInput, taskStatus status) {
        int taskDisplayNumber = Integer.parseInt(userInput.replaceAll("\\D", "").trim());
        int taskNumber = taskDisplayNumber - 1;
        try {
            Task task = allTasks.get(taskNumber);
            if (status == taskStatus.MARK) {
                task.markAsDone();
                System.out.println(LINE);
                System.out.println("The following task was marked");
                System.out.println(task);
                System.out.println(LINE);
            } else if (status == taskStatus.UNMARK) {
                task.markAsUndone();
                System.out.println(LINE);
                System.out.println("The following task was unmarked");
                System.out.println(task);
                System.out.println(LINE);
            } else if (status == taskStatus.DELETE) {
                allTasks.remove(taskNumber);
                numberOfTasks -= 1;

                System.out.println(LINE);
                System.out.println("The following task was deleted");
                System.out.println(task.toString());
                System.out.println(LINE);
            } else {
                System.out.println("I'm lost");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(LINE);
            System.out.println("Either the task number you selected doesn't exist");
            System.out.println("Try a different number or type list to check");
            System.out.println(LINE);
        }
    }

    public static ArrayList<Task> findTasks(String userInput) {
        ArrayList<Task> tasksWithKeyword = new ArrayList<>();
        String keyword = userInput.substring("find".length()).trim();

        for (int index = 0; index < numberOfTasks; index += 1) {
            Task task = allTasks.get(index);
            String taskDescription = task.getDescription();

            if (taskDescription.contains(keyword)) {
                tasksWithKeyword.add(task);
            }
        }
        return tasksWithKeyword;
    }
}