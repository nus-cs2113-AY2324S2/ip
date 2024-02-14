public class TaskList {
    private static final int MAX_TASKS = 100;
    private static final Task[] taskList = new Task[MAX_TASKS];
    private static int taskCount = 0;

    public static String indent = "    ";
    public static String separator = System.lineSeparator();
    public static String line = indent + "_________________________"
            + "___________________________";

    public static String logo = "  #        ####  ##### ######  ####" + separator
            + "                #       #    #   #   #      #" + separator
            + "                #       #    #   #   #####   ####" + separator
            + "                #       #    #   #   #           #" + separator
            + "                #######  ####    #   ######  ####";

    public static String greetingsMessage = line + separator
            + indent + "Hello! I'm" + logo + separator
            + indent + "What can I do for you?" + separator + line;

    public static String goodbyeMessage = line + separator
            + indent + "Bye. Hope to see you again soon!"
            + separator + line;

    // Prints out the list of tasks to the user.

    public static void printTasksList() {
        System.out.println(line);

        if (taskCount == 0) {
            System.out.println(indent + " List is empty,"
                    + " please enter some text to add to list.");

        } else {
            System.out.println(indent +" Here are the tasks in your list:");

            for (int taskIndex = 0; taskIndex < taskCount; taskIndex++) {
                String taskFormatter = String.format("     %d. %s",
                        (taskIndex + 1), taskList[taskIndex]);

                System.out.println(taskFormatter);
            }
        }
        System.out.println(line);
    }

    // Mark the task number in the task list.

    public static void markTask(String userInput) {
        String inputString = userInput.substring(5);
        int taskListIndex = (Integer.parseInt(inputString) - 1);

        taskList[taskListIndex].setDone(true);

        System.out.println(line + separator
                + indent +" Nice! I've marked this task as done:"
                + separator + indent + " " + taskList[taskListIndex]
                + separator + line);

    }

    //  Unmark the task number in the task list.

    public static void unMarkTask(String userInput) {
        String inputString = userInput.substring(7);
        int taskListIndex = (Integer.parseInt(inputString) - 1);

        taskList[taskListIndex].setDone(false);

        System.out.println(line + separator
                + indent + " OK, I've marked this task as not done yet:"
                + separator + indent + " " + taskList[taskListIndex]
                + separator + line);
    }

    // Adds a new To Do task to the task list,
    // tasks without any date/time attached to it.

    public static void addToDo(String description) {
        String toDoDescription = description.substring(5);

        taskList[taskCount] = new ToDo(toDoDescription);

        String formattedString = String.format("%s%s Got it. I've added this task: %s" +
                        "       %s%s     Now you have %d tasks in the list%s"
                , line + separator, indent, separator
                , taskList[taskCount], separator, (taskCount + 1), separator + line);

        System.out.println(formattedString);

        taskCount++;
    }

    // Adds a new Deadline task to the task list,
    // tasks that need to be done before a specific date/time (/by).

    public static void addDeadline(String description) {
        String deadlineDescription = description.substring(9);

        String[] descriptionArguments = deadlineDescription.split("/by");

        String properDescription= descriptionArguments[0].trim();
        String byDate = descriptionArguments[1].trim();

        String formattedDescription = String.format("%s (by: %s)",
                properDescription, byDate);

        taskList[taskCount] = new Deadline(formattedDescription, byDate);

        String formattedString = String.format("%s%s Got it. I've added this task: %s" +
                        "       %s%s     Now you have %d tasks in the list%s"
                , line + separator, indent, separator
                , taskList[taskCount], separator, (taskCount + 1), separator + line);

        System.out.println(formattedString);

        taskCount++;
    }

    // Adds a new Event task to the task list,
    // tasks that start at a specific date/time (from),
    // and ends at a specific date/time (/to).

    public static void addEvent(String description) {
        String deadlineDescription = description.substring(6);

        String[] descriptionArguments = deadlineDescription.split("/from");
        String[] fromAndToArguments = descriptionArguments[1].split("/to");

        String properDescription= descriptionArguments[0];
        String from = fromAndToArguments[0].trim();
        String to = fromAndToArguments[1].trim();

        String formattedDescription = String.format("%s(from: %s to: %s)",
                properDescription, from, to);

        taskList[taskCount] = new Event(formattedDescription, from, to);

        String formattedString = String.format("%s%s Got it. I've added this task: %s" +
                "       %s%s     Now you have %d tasks in the list%s"
                , line + separator, indent, separator
                , taskList[taskCount], separator, (taskCount + 1), separator + line);

        System.out.println(formattedString);

        taskCount++;
    }

    // Adds a new task to the task list.

    public static void addNewTask(String description) {
        taskList[taskCount] = new Task(description);

        String formattedString = String.format("%s%s%s added: %s%s%s",
                line, separator, indent
                , description, separator, line);

        System.out.println(formattedString);

        taskCount++;
    }
}
