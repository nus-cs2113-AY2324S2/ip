package lotes.task;
import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> taskList = new ArrayList<>();

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

        if (taskList.isEmpty()) {
            System.out.println(indent + " List is empty,"
                    + " please enter some text to add to list.");

        } else {
            System.out.println(indent +" Here are the tasks in your list:");
            int taskListIndex = 0;
            for (Task task : taskList) {
                taskListIndex++;
                String taskFormatter = String.format("%s %d. %s",
                        indent, taskListIndex, task);

                System.out.println(taskFormatter);
            }
        }
        System.out.println(line);
    }

    // Mark the task number in the task list.

    public static void markTask(String userInput) {
        String inputString = userInput.substring(5);
        int taskListIndex = (Integer.parseInt(inputString) - 1);

        taskList.get(taskListIndex).setDone(true);

        System.out.println(line + separator
                + indent +" Nice! I've marked this task as done:"
                + separator + indent + " " + taskList.get(taskListIndex)
                + separator + line);
    }

    //  Unmark the task number in the task list.

    public static void unMarkTask(String userInput) {
        String inputString = userInput.substring(7);
        int taskListIndex = (Integer.parseInt(inputString) - 1);

        taskList.get(taskListIndex).setDone(false);

        System.out.println(line + separator
                + indent + " OK, I've marked this task as not done yet:"
                + separator + indent + " " + taskList.get(taskListIndex)
                + separator + line);
    }

    // Adds a new To Do task to the task list,
    // tasks without any date/time attached to it.

    public static void addToDo(String description) {
        String toDoDescription = description.substring(5);

        Task newTask = new ToDo(toDoDescription);
        taskList.add(newTask);

        String formattedString = String.format("%s%s Got it. I've added this task: %s" +
                        "       %s%s     Now you have %d tasks in the list%s"
                , line + separator, indent, separator
                , newTask, separator, taskList.size(), separator + line);

        System.out.println(formattedString);
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

        Task newTask = new Deadline(formattedDescription, byDate);
        taskList.add(newTask);

        String formattedString = String.format("%s%s Got it. I've added this task: %s" +
                        "       %s%s     Now you have %d tasks in the list%s"
                , line + separator, indent, separator
                , newTask, separator, taskList.size(), separator + line);

        System.out.println(formattedString);
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

        Task newTask = new Event(formattedDescription, from, to);
        taskList.add(newTask);

        String formattedString = String.format("%s%s Got it. I've added this task: %s" +
                        "       %s%s     Now you have %d tasks in the list%s"
                , line + separator, indent, separator
                , newTask, separator, taskList.size(), separator + line);

        System.out.println(formattedString);
    }

    // Adds a new task to the task list.

    public static void addNewTask(String description) {
        Task newTask = new Task(description);
        taskList.add(newTask);

        String formattedString = String.format("%s%s%s added: %s%s%s",
                line, separator, indent
                , description, separator, line);

        System.out.println(formattedString);
    }

    public static void deleteTask(String userInput) {
        String inputString = userInput.substring(7);
        int taskListIndex = (Integer.parseInt(inputString) - 1);

        System.out.println(line + separator
                + indent +" Noted. I've removed this task:"
                + separator + indent + " " + taskList.get(taskListIndex)
                + separator + line);

        taskList.remove(taskListIndex);
    }

    public static int getTaskCount(){
        return taskList.size();
    }

    public static void readFromStorage(String description) {
        String taskType = description.substring(1, 2);
        String isMarked = description.substring(4, 5);
        String des = description.substring(7);

        if (taskType.equals("T")) {
//            System.out.println("T");
//            System.out.println(des);
            if (isMarked.equals("X")) {
//                System.out.println(isMarked);
                Task newTask = new ToDo(des, true);
                taskList.add(newTask);

            } else {
                Task newTask = new ToDo(des);
                taskList.add(newTask);

            }

        } else if (taskType.equals("D")) {
//            System.out.println("D");
//            System.out.println(des);
            String[] by = des.split("by: ");
            String byDate = by[1].substring(0, by[1].length() - 1);
//            System.out.println(byDate);
            if (isMarked.equals("X")) {
//                System.out.println(isMarked);

                Task newTask = new Deadline(des, byDate, true);
                taskList.add(newTask);
            } else {
                Task newTask = new Deadline(des, byDate);
                taskList.add(newTask);
            }

        } else if (taskType.equals("E")) {
//            System.out.println("E");
//            System.out.println(des);
            String[] from = des.split("from: ");
            String[] to = from[1].split(" to: ");
            String toTime = to[1].substring(0, to[1].length() - 1);
//            System.out.println(to[0]);
//            System.out.println(toTime);
            if (isMarked.equals("X")) {
//                System.out.println(isMarked);

                Task newTask = new Event(des, to[0], toTime, true);
                taskList.add(newTask);
            } else {
                Task newTask = new Event(des, to[0], toTime);
                taskList.add(newTask);
            }
        }

    }

}