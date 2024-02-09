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

    public static final String greetingsMessage = horizontalLine + lineSeparator
            + "    Hello! I'm" + logo + lineSeparator
            + "    What can I do for you?" + lineSeparator + horizontalLine;

    public static final String goodbyeMessage = horizontalLine + lineSeparator
            + "    Bye. Hope to see you again soon!"
            + lineSeparator + horizontalLine;

    public static boolean readUserInput(String userInput, TaskList taskList) {
        if (userInput.equals("bye")) {
            System.out.println(taskList.goodbyeMessage);
            return true;

        } else if (userInput.equals("list")) {
            taskList.printTasksList();

        } else if (userInput.startsWith("mark ")) {
            taskList.markTask(userInput);

        } else if (userInput.startsWith("unmark ")) {
            taskList.unMarkTask(userInput);

        } else if (userInput.startsWith("todo ")) {
            taskList.addToDo(userInput);

        } else if (userInput.startsWith("deadline ")) {
            taskList.addDeadline(userInput);

        } else if (userInput.startsWith("event ")) {
            taskList.addEvent(userInput);

        } else if (userInput.startsWith("add ")) {
            taskList.addNewTask(userInput);

        } else {
            System.out.println("Enter something to add to your task list");

        }
        return false;
    }

    public void printTasksList() {
        System.out.println(horizontalLine);

        if (taskCount == 0) {
            System.out.println("     List is empty, please enter some text to add to list.");

        } else {
            System.out.println("     Here are the tasks in your list:");

            for (int i = 0; i < taskCount; i++) {
                String taskFormatter = String.format("     %d. %s",
                        (i + 1), taskList[i]);

                System.out.println(taskFormatter);
            }
        }
        System.out.println(horizontalLine);
    }

    public void markTask(String userInput) {
        try {
            int taskListIndex = Integer.parseInt(
                    userInput.substring(5))
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
            int taskListIndex = (Integer.parseInt(
                    userInput.substring(7))
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
                + (taskCount + 1) + " tasks in the list" + lineSeparator
                + horizontalLine);

        taskCount++;
    }

    public void addDeadline(String description) {
        String deadlineDescription = description.substring(9);

        String[] descriptionArguments = deadlineDescription.split("/by");

        String properDescription= descriptionArguments[0].trim();
        String byDate = descriptionArguments[1].trim();

        String formattedDescription = String.format("%s (by: %s)",
                properDescription, byDate);

        taskList[taskCount] = new Deadline(formattedDescription, byDate);

        System.out.println(horizontalLine + lineSeparator
                + "     Got it. I've added this task: " + lineSeparator
                + "       " + taskList[taskCount] + lineSeparator + "     Now you have "
                + (taskCount + 1) + " tasks in the list" + lineSeparator
                + horizontalLine);

        taskCount++;
    }

    public void addEvent(String description) {
        String deadlineDescription = description.substring(6);

        String[] descriptionArguments = deadlineDescription.split("/from");
        String[] fromAndToArguments = descriptionArguments[1].split("/to");

        String properDescription= descriptionArguments[0];
        String from = fromAndToArguments[0].trim();
        String to = fromAndToArguments[1].trim();

        String formattedDescription = String.format("%s(from: %s to: %s)",
                properDescription, from, to);

        taskList[taskCount] = new Event(formattedDescription, from, to);

        System.out.println(horizontalLine + lineSeparator
                + "     Got it. I've added this task: " + lineSeparator
                + "       " + taskList[taskCount] + lineSeparator + "     Now you have "
                + (taskCount + 1) + " tasks in the list" + lineSeparator
                + horizontalLine);

        taskCount++;
    }
}
