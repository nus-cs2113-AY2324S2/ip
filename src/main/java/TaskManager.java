public class TaskManager {
    // Constants
    public static final String LINE = "____________________________________________________________";
    public static int insertIndex = 0;

    public static void handleUserTasks(String userInput, Task[] listTasks)  {
        try {
            insertUserTasks(userInput, listTasks);
            insertIndex ++;
        } catch (InvalidKeywordException e) {
            printInvalidKeywordMessage();
        } catch (ArrayIndexOutOfBoundsException e) {
            printListIsFullMessage();
        }
    }

    public static void insertUserTasks (String userInput, Task[] listTasks) throws InvalidKeywordException {
        if (userInput.startsWith("todo")) {
            handleToDoTasks(userInput, listTasks, insertIndex);
        } else if (userInput.startsWith("deadline")) {
            handleDeadlineTasks(userInput, listTasks, insertIndex);
        } else if (userInput.startsWith("event")) {
            handleEventTasks(userInput, listTasks, insertIndex);
        } else {
            throw new InvalidKeywordException();
        }
    }

    public static void handleEventTasks(String userInput, Task[] listTasks, int insertIndex) {
        if (!userInput.contains("/from") || !userInput.contains("/to")) {
            System.out.println("Invalid command.");
            System.out.println(LINE);
        }
        else {
            String removeEventString = userInput.replaceFirst("event", "").trim();
            String[] firstPartition = removeEventString.split("/from");
            String eventItem = firstPartition[0];
            String fromAndToString = firstPartition[1];
            String[] secondPartition = fromAndToString.split("/to");
            String eventFrom = secondPartition[0];
            String eventTo = secondPartition[1];

            listTasks[insertIndex] = new Event (eventItem, eventFrom, eventTo);
            System.out.println(LINE);
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + listTasks[insertIndex]);
            insertIndex += 1;
            System.out.println("Now you have " + insertIndex + " tasks in the list.");
            System.out.println(LINE);
        }
    }

    public static void handleDeadlineTasks(String userInput, Task[] listTasks, int insertIndex) {
        if (!userInput.contains("/by")) {
            System.out.println("Invalid command.");
            System.out.println(LINE);
        }
        else {
            String removeDeadlineString = userInput.replaceFirst("deadline", "").trim();
            String[] parts = removeDeadlineString.split("/by");
            String deadlineItem = parts[0];
            String dueBy = parts[1];
            listTasks[insertIndex] = new Deadline(deadlineItem, dueBy);
            System.out.println(LINE);
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + listTasks[insertIndex]);
            insertIndex += 1;
            System.out.println("Now you have " + insertIndex + " tasks in the list.");
            System.out.println(LINE);
        }
    }

    public static void handleToDoTasks(String userInput, Task[] listTasks, int insertIndex) {
        String toDoItem = userInput.substring(4).trim();
        listTasks[insertIndex] = new ToDo(toDoItem);
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + listTasks[insertIndex]);
        insertIndex += 1;
        System.out.println("Now you have " + insertIndex + " tasks in the list.");
        System.out.println(LINE);
    }

    public static void handleTasksMarkings(String userInput, Task[] listTasks) {
        if (userInput.startsWith("mark ")) {
            int indexToMark = Integer.parseInt(userInput.substring(5).trim());
            if (indexToMark >= 1 && indexToMark <= listTasks.length && listTasks[indexToMark - 1] != null) {
                listTasks[indexToMark - 1].markAsDone();
                System.out.println(LINE);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(listTasks[indexToMark - 1].getStatusIcon()
                        + " " + listTasks[indexToMark - 1].getDescription());
                System.out.println(LINE);
            } else {
                System.out.println(LINE);
                System.out.println("Invalid task index. Please try again.");
                System.out.println(LINE);
            }
        }
        else {
            int indexToUnmark = Integer.parseInt(userInput.substring(7).trim());
            if (indexToUnmark >= 1 && indexToUnmark <= listTasks.length && listTasks[indexToUnmark - 1] != null) {
                listTasks[indexToUnmark - 1].unmarkAsDone();
                System.out.println(LINE);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(listTasks[indexToUnmark - 1].getStatusIcon()
                        + " " + listTasks[indexToUnmark - 1].getDescription());
                System.out.println(LINE);
            } else {
                System.out.println(LINE);
                System.out.println("Invalid task index. Please try again.");
                System.out.println(LINE);
            }
        }
    }

    public static void printList(Task[] listTasks) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < listTasks.length; i++) {
            if (listTasks[i] != null) {
                System.out.println((i + 1) + ". " + listTasks[i].toString());
            }
        }
        System.out.println(LINE);
    }

    private static void printListIsFullMessage() {
        System.out.println(LINE);
        System.out.println("List is full. Cannot add more items.");
        System.out.println(LINE);
    }

    private static void printInvalidKeywordMessage() {
        System.out.println(LINE);
        System.out.println("Invalid keyword, the available keywords are:"
                + "\n(todo), (deadline), (event)"
                + "\nPlease type again.");
        System.out.println(LINE);
    }
}
