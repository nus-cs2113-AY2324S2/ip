import java.util.Scanner;

public class Gandalf {
    public static final String LINE = "____________________________________________________________";

    public static void makeList() {
        makeListWelcomeMessage();

        Task[] listTasks = new Task[100];
        Scanner in = new Scanner(System.in);
        String text = in.nextLine();

        int insertIndex = 0;

        while (true) {
            if (text.equals("bye")) {
                System.out.println(LINE);
                return;
            } else if (text.equals("list")) {
                System.out.println(LINE);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < listTasks.length; i++) {
                    if (listTasks[i] != null) {
                        System.out.println((i + 1) + ". " + listTasks[i].toString());
                    }
                }
                System.out.println(LINE);
            } else if (text.startsWith("mark ")) {
                int indexToMark = Integer.parseInt(text.substring(5).trim());
                if (indexToMark >= 1 && indexToMark <= listTasks.length && listTasks[indexToMark - 1] != null) {
                    listTasks[indexToMark - 1].markAsDone();
                    System.out.println(LINE);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(listTasks[indexToMark - 1].getStatusIcon() + " " + listTasks[indexToMark - 1].getDescription());
                    System.out.println(LINE);
                } else {
                    System.out.println(LINE);
                    System.out.println("Invalid task index. Please try again.");
                    System.out.println(LINE);
                }
            } else if (text.startsWith("unmark ")) {
                int indexToUnmark = Integer.parseInt(text.substring(7).trim());
                if (indexToUnmark >= 1 && indexToUnmark <= listTasks.length && listTasks[indexToUnmark - 1] != null) {
                    listTasks[indexToUnmark - 1].unmarkAsDone();
                    System.out.println(LINE);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(listTasks[indexToUnmark - 1].getStatusIcon() + " " + listTasks[indexToUnmark - 1].getDescription());
                    System.out.println(LINE);
                } else {
                    System.out.println(LINE);
                    System.out.println("Invalid task index. Please try again.");
                    System.out.println(LINE);
                }
            } else {
                if (insertIndex < listTasks.length) {
                    if (text.startsWith("todo ")) {
                        String toDoItem = text.substring(4).trim();
                        listTasks[insertIndex] = new ToDo(toDoItem);
                        System.out.println(LINE);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + listTasks[insertIndex]);
                        insertIndex += 1;
                        System.out.println("Now you have " + insertIndex + " tasks in the list.");
                        System.out.println(LINE);
                    }
                    else if (text.startsWith("deadline ")) {
                        if (!text.contains("/by")) {
                            System.out.println("Invalid command.");
                            System.out.println(LINE);
                        }
                        else {
                            String removeDeadlineString = text.replaceFirst("deadline", "").trim();
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
                    else if (text.startsWith("event ")) {
                        if (!text.contains("/from") || !text.contains("/to")) {
                            System.out.println("Invalid command.");
                            System.out.println(LINE);
                        }
                        else {
                            String removeEventString = text.replaceFirst("event", "").trim();
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
                    else {
                        System.out.println("Invalid keyword.");
                        System.out.println(LINE);
                    }

                } else {
                    System.out.println(LINE);
                    System.out.println("List is full. Cannot add more items.");
                    System.out.println(LINE);
                }
            }
            text = in.nextLine();
        }
    }

    private static void makeListWelcomeMessage() {
        System.out.println(LINE);
        System.out.println("What would you like to be added to the list?");
        System.out.println(LINE);
    }

    public static void startProgram() {
        Scanner in = new Scanner(System.in);
        String byeStatement = "bye";
        String makeListStatement = "make list";
        String text = in.nextLine();
        boolean hasSaidBye = byeStatement.equals(text);
        boolean isMakeList = text.equals(makeListStatement);

        if (isMakeList) {
            makeList();
            return;
        }
        if (text.equals(byeStatement)) {
            System.out.println(LINE);
            return;
        }

        System.out.println(LINE);
        System.out.println(text);
        System.out.println(LINE);

        while (!hasSaidBye) {
            text = in.nextLine();
            if (text.equals(byeStatement)) {
                hasSaidBye = true;
            } else if (text.equals(makeListStatement)) {
                makeList();
                return;
            } else {
                // Echo the message
                System.out.println(text);
            }
            System.out.println(LINE);
        }
    }

    public static void main(String[] args) {
        startMessage();
        startProgram();
        endMessage();
    }

    private static void endMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    private static void startMessage() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Gandalf");
        System.out.println("What can I do for you? I'll start by repeating your words.");
        System.out.println("Type (make list) to create a To-Do List.");
        System.out.println(LINE);
    }
}