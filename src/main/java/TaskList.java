import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList = new ArrayList<>();
//    private static Task[] tasks = new Task[100];
//    private static int taskCount = 0;

    public static final String lineSeparator = System.lineSeparator();
    public static final String horizontalLine = "    _________________________"
                                                + "___________________________";
    public static final String logo = "  #        ####  ##### ######  ####" + lineSeparator
                      + "                #       #    #   #   #      #" + lineSeparator
                      + "                #       #    #   #   #####   ####" + lineSeparator
                      + "                #       #    #   #   #           #" + lineSeparator
                      + "                #######  ####    #   ######  ####";

    public void printTasksList() {
        System.out.println(horizontalLine);

        if (taskList.isEmpty()) {
            System.out.println("     List is empty, please enter some text to add to list.");
        } else {
            System.out.println("     Here are the tasks in your list:");

            int taskListArrayIndex = 0;
            for (Task task : taskList) {
                taskListArrayIndex++;
                String taskFormatter = String.format("     %d. %s", taskListArrayIndex, task);
                System.out.println(taskFormatter);
            }
        }
        System.out.println(horizontalLine);
    }

    public void markTask(String userInput) {
        try {
            int taskListIndex = Integer.parseInt(userInput.substring(5))
                    - 1;

            taskList.get(taskListIndex).setDone(true);

            System.out.println(horizontalLine + lineSeparator
                    + "     Nice! I've marked this task as done:"
                    + lineSeparator + "     " + taskList.get(taskListIndex)
                    + lineSeparator + horizontalLine);

        } catch (IndexOutOfBoundsException | NumberFormatException s) {
            System.out.println("     Invalid integer input");
            printTasksList();
        }
    }

    public void unMarkTask(String userInput) {
        try {
            int taskListIndex = (Integer.parseInt(userInput.substring(7))
                    - 1);

            taskList.get(taskListIndex).setDone(false);

            System.out.println(horizontalLine + lineSeparator
                    + "     OK, I've marked this task as not done yet:"
                    + lineSeparator + "     " + taskList.get(taskListIndex)
                    + lineSeparator + horizontalLine);

        } catch (IndexOutOfBoundsException | NumberFormatException s) {
            System.out.println("     Invalid integer input");
            printTasksList();
        }
    }

    public void addTask(String description) {
        Task newTask = new Task(description);
        taskList.add(newTask);

        System.out.println(horizontalLine + lineSeparator
                + "     added: " + description + lineSeparator
                + horizontalLine);
    }

//    public void addNewTask(String description) {
//        tasks[taskCount] = new Task(description);
//        taskCount++;
//
//        System.out.println(horizontalLine + lineSeparator
//                + "     added: " + description + lineSeparator
//                + horizontalLine);
//    }

    public void addToDo(String description) {
        String toDoDescription = description.substring(5);
        Task newTask = new ToDo(toDoDescription);
        taskList.add(newTask);

        System.out.println(horizontalLine + lineSeparator
                + "     Got it. I've added this task: " + lineSeparator
                + "       " + newTask + lineSeparator
                + "     Now you have " + taskList.size() + " tasks in the list" + lineSeparator
                + horizontalLine);
    }

    public void addDeadline(String description) {
        String deadlineDescription = description.substring(9);

        String[] descriptionArguments = deadlineDescription.split("/by");
        String formattedDescription = String.format("%s (by: %s)", descriptionArguments[0].trim(),
                descriptionArguments[1].trim());
        //System.out.println(formattedDescription);
        Task newTask = new Deadline(formattedDescription, descriptionArguments[0]);
        taskList.add(newTask);

        System.out.println(horizontalLine + lineSeparator
                + "     Got it. I've added this task: " + lineSeparator
                + "       " + newTask + lineSeparator
                + "     Now you have " + taskList.size() + " tasks in the list" + lineSeparator
                + horizontalLine);
    }

    public void addEvent(String description) {
        String deadlineDescription = description.substring(6);

        String[] descriptionArguments = deadlineDescription.split("/from");
        String[] fromAndToArguments = descriptionArguments[1].split("/to");
//        System.out.println(fromAndToArguments[0]); //from
//        System.out.println(fromAndToArguments[1]); //to

        String formattedDescription = String.format("%s(from: %s to: %s)", descriptionArguments[0],
                fromAndToArguments[0].trim(), fromAndToArguments[1].trim());
//        System.out.println(formattedDescription);

        Task newTask = new Event(formattedDescription, descriptionArguments[0].trim(),
                descriptionArguments[1].trim());
        taskList.add(newTask);

        System.out.println(horizontalLine + lineSeparator
                + "     Got it. I've added this task: " + lineSeparator
                + "       " + newTask + lineSeparator
                + "     Now you have " + taskList.size() + " tasks in the list" + lineSeparator
                + horizontalLine);
    }
}
