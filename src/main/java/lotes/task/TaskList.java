package lotes.task;
import java.util.ArrayList;
import lotes.ui.UserInterface;

public class TaskList {
    public static ArrayList<Task> taskList = new ArrayList<>();

    public TaskList() {
        ArrayList<Task> taskList = new ArrayList<>();
    }

    // Prints out the list of tasks to the user.

//    public static void printTasksList() {
//        System.out.println(UserInterface.line);
//
//        if (taskList.isEmpty()) {
//            System.out.println(UserInterface.indent + " List is empty,"
//                    + " please enter some text to add to list.");
//
//        } else {
//            System.out.println(UserInterface.indent +" Here are the tasks in your list:");
//            int taskListIndex = 0;
//            for (Task task : taskList) {
//                taskListIndex++;
//                String taskFormatter = String.format("%s %d. %s",
//                        UserInterface.indent, taskListIndex, task);
//
//                System.out.println(taskFormatter);
//            }
//        }
//        System.out.println(UserInterface.line);
//    }

    // Mark the task number in the task list.

//    public static void markTask(String userInput) {
//        String inputString = userInput.substring(5);
//        int taskListIndex = (Integer.parseInt(inputString) - 1);
//
//        taskList.get(taskListIndex).setDone(true);
//
//        System.out.println(UserInterface.line + UserInterface.separator
//                + UserInterface.indent +" Nice! I've marked this task as done:"
//                + UserInterface.separator + UserInterface.indent + " " + taskList.get(taskListIndex)
//                + UserInterface.separator + UserInterface.line);
//    }

    //  Unmark the task number in the task list.

//    public static void unMarkTask(String userInput) {
//        String inputString = userInput.substring(7);
//        int taskListIndex = (Integer.parseInt(inputString) - 1);
//
//        taskList.get(taskListIndex).setDone(false);
//
//        System.out.println(UserInterface.line + UserInterface.separator
//                + UserInterface.indent + " OK, I've marked this task as not done yet:"
//                + UserInterface.separator + UserInterface.indent + " " + taskList.get(taskListIndex)
//                + UserInterface.separator + UserInterface.line);
//    }

    // Adds a new To Do task to the task list,
    // tasks without any date/time attached to it.

//    public static void addToDo(String description) {
//        String toDoDescription = description.substring(5);
//
//        Task newTask = new ToDo(toDoDescription);
//        taskList.add(newTask);
//
//        String formattedString = String.format("%s%s Got it. I've added this task: %s" +
//                        "       %s%s     Now you have %d tasks in the list%s"
//                , UserInterface.line + UserInterface.separator, UserInterface.indent, UserInterface.separator
//                , newTask, UserInterface.separator, taskList.size(), UserInterface.separator + UserInterface.line);
//
//        System.out.println(formattedString);
//    }

    // Adds a new Deadline task to the task list,
    // tasks that need to be done before a specific date/time (/by).

//    public static void addDeadline(String description) {
//        String deadlineDescription = description.substring(9);
//
//        String[] descriptionArguments = deadlineDescription.split("/by");
//
//        String properDescription= descriptionArguments[0].trim();
//        String byDate = descriptionArguments[1].trim();
//
//        String formattedDescription = String.format("%s (by: %s)",
//                properDescription, byDate);
//
//        Task newTask = new Deadline(formattedDescription, byDate);
//        taskList.add(newTask);
//
//        String formattedString = String.format("%s%s Got it. I've added this task: %s" +
//                        "       %s%s     Now you have %d tasks in the list%s"
//                , UserInterface.line + UserInterface.separator, UserInterface.indent, UserInterface.separator
//                , newTask, UserInterface.separator, taskList.size(), UserInterface.separator + UserInterface.line);
//
//        System.out.println(formattedString);
//    }

    // Adds a new Event task to the task list,
    // tasks that start at a specific date/time (from),
    // and ends at a specific date/time (/to).

//    public static void addEvent(String description) {
//        String deadlineDescription = description.substring(6);
//
//        String[] descriptionArguments = deadlineDescription.split("/from");
//        String[] fromAndToArguments = descriptionArguments[1].split("/to");
//
//        String properDescription= descriptionArguments[0];
//        String from = fromAndToArguments[0].trim();
//        String to = fromAndToArguments[1].trim();
//
//        String formattedDescription = String.format("%s(from: %s to: %s)",
//                properDescription, from, to);
//
//        Task newTask = new Event(formattedDescription, from, to);
//        taskList.add(newTask);
//
//        String formattedString = String.format("%s%s Got it. I've added this task: %s" +
//                        "       %s%s     Now you have %d tasks in the list%s"
//                , UserInterface.line + UserInterface.separator, UserInterface.indent, UserInterface.separator
//                , newTask, UserInterface.separator, taskList.size(), UserInterface.separator + UserInterface.line);
//
//        System.out.println(formattedString);
//    }

    // Adds a new task to the task list.

//    public static void addNewTask(String description) {
//        Task newTask = new Task(description);
//        taskList.add(newTask);
//
//        String formattedString = String.format("%s%s%s added: %s%s%s",
//                UserInterface.line, UserInterface.separator, UserInterface.indent
//                , description, UserInterface.separator, UserInterface.line);
//
//        System.out.println(formattedString);
//    }

//    public static void deleteTask(String userInput) {
//        String inputString = userInput.substring(7);
//        int taskListIndex = (Integer.parseInt(inputString) - 1);
//
//        System.out.println(UserInterface.line + UserInterface.separator
//                + UserInterface.indent +" Noted. I've removed this task:"
//                + UserInterface.separator + UserInterface.indent + " " + taskList.get(taskListIndex)
//                + UserInterface.separator + UserInterface.line);
//
//        taskList.remove(taskListIndex);
//    }


//    public void findTask(String userInput) {
//    }
}