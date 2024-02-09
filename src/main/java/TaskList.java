public class TaskList {
    private static final Task[] taskList = new Task[100];

    private static int taskCount = 0;

    public static final String lineSeparator = System.lineSeparator();

    public static final String horizontalLine = "    _________________________"
            + "___________________________";

    public static final String logo = "  #        ####  ##### ######  ####" + lineSeparator
            + "                #       #    #   #   #      #" + lineSeparator
            + "                #       #    #   #   #####   ####" + lineSeparator
            + "                #       #    #   #   #           #" + lineSeparator
            + "                #######  ####    #   ######  ####";

    public static final String greetings = horizontalLine + lineSeparator
            + "    Hello! I'm" + logo + lineSeparator
            + "    What can I do for you?" + lineSeparator + horizontalLine;

    public void printTasksList() {
        System.out.println(horizontalLine);

        if (taskCount == 0) {
            System.out.println("     List is empty, please enter some text to add to list.");

        } else {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                String taskFormatter = String.format("     %d. %s", (i + 1), taskList[i]);
                System.out.println(taskFormatter);

            }
        }
        System.out.println(horizontalLine);
    }

    public void markTask(String userInput) {
        try {
            int taskListIndex = Integer.parseInt(userInput.substring(5))
                    - 1;

            taskList[taskListIndex].setDone(true);

            System.out.println(horizontalLine + lineSeparator
                    + "     Nice! I've marked this task as done:"
                    + lineSeparator + "     " + taskList[taskListIndex]
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

            taskList[taskListIndex].setDone(false);

            System.out.println(horizontalLine + lineSeparator
                    + "     OK, I've marked this task as not done yet:"
                    + lineSeparator + "     " + taskList[taskListIndex]
                    + lineSeparator + horizontalLine);

        } catch (IndexOutOfBoundsException | NumberFormatException s) {
            System.out.println("     Invalid integer input");
            printTasksList();
        }
    }

    public void addNewTask(String description) {
        taskList[taskCount] = new Task(description);

        System.out.println(horizontalLine + lineSeparator
                + "     added: " + description + lineSeparator
                + horizontalLine);

        taskCount++;
    }

    public void addToDo(String description) {
        String toDoDescription = description.substring(5);
        taskList[taskCount] = new ToDo(toDoDescription);

        System.out.println(horizontalLine + lineSeparator
                + "     Got it. I've added this task: " + lineSeparator
                + "       " + taskList[taskCount] + lineSeparator + "     Now you have "
                + (taskCount + 1) + " tasks in the list" + lineSeparator + horizontalLine);

        taskCount++;
    }

    public void addDeadline(String description) {
        String deadlineDescription = description.substring(9);

        String[] descriptionArguments = deadlineDescription.split("/by");

        String formattedDescription = String.format("%s (by: %s)",
                descriptionArguments[0].trim(), descriptionArguments[1].trim());

        taskList[taskCount] = new Deadline(formattedDescription, descriptionArguments[0]);

        System.out.println(horizontalLine + lineSeparator
                + "     Got it. I've added this task: " + lineSeparator
                + "       " + taskList[taskCount] + lineSeparator + "     Now you have "
                + (taskCount + 1) + " tasks in the list" + lineSeparator + horizontalLine);

        taskCount++;
    }

    public void addEvent(String description) {
        String deadlineDescription = description.substring(6);

        String[] descriptionArguments = deadlineDescription.split("/from");

        String[] fromAndToArguments = descriptionArguments[1].split("/to");

        String formattedDescription = String.format("%s(from: %s to: %s)", descriptionArguments[0],
                fromAndToArguments[0].trim(), fromAndToArguments[1].trim());

        taskList[taskCount] = new Event(formattedDescription,
                descriptionArguments[0].trim(), descriptionArguments[1].trim());

        System.out.println(horizontalLine + lineSeparator
                + "     Got it. I've added this task: " + lineSeparator
                + "       " + taskList[taskCount] + lineSeparator + "     Now you have "
                + (taskCount + 1) + " tasks in the list" + lineSeparator + horizontalLine);

        taskCount++;
    }
}
