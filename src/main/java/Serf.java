import java.util.Scanner;
import java.util.Arrays;
public class Serf {
    public static void main(String[] args) {
        String VERTICALLINES = "    ____________________________________________________________";
        String FIVEWHITESPACES = "     ";
        Task[] taskList = new Task[100];
        int taskCounter = 0; // tracks number of tasks in taskList
        String chatBotName = "Serf";
        String GREETINGMESSAGE = VERTICALLINES
                + "\n"
                + FIVEWHITESPACES
                + "Good day my lord! I'm " + chatBotName + "\n"
                + FIVEWHITESPACES
                + "How shall I serve thee?\n"
                + VERTICALLINES;
        String FAREWELLMESSAGE = VERTICALLINES
                + "\n"
                + FIVEWHITESPACES
                + "Farewell, my lord. \n"
                + VERTICALLINES;
        System.out.println(GREETINGMESSAGE + "\n");
        Scanner requestedMessage = new Scanner(System.in);
        String receivedMessage = requestedMessage.nextLine();

        while(!receivedMessage.equals("bye")) {
            if (receivedMessage.equals("list") && taskCounter > 0) { // list is not empty
                System.out.println(VERTICALLINES);
                System.out.println(FIVEWHITESPACES + "My lord, here are the tasks as recorded in thy list:");
                for (int i = 0; i < taskCounter; i += 1) {
                    switch (taskList[i].getTaskStatus()) {
                        case "T":
                            System.out.println(FIVEWHITESPACES + (i + 1) + "." + "["
                                    + taskList[i].getTaskStatus() + "]" + "["
                                    + taskList[i].getStatusIcon() + "] " + taskList[i].description
                                    + " ");
                            break;
                        case "D":
                            System.out.println(FIVEWHITESPACES + (i + 1) + "." + "["
                                    + taskList[i].getTaskStatus() + "]" + "["
                                    + taskList[i].getStatusIcon() + "] " + taskList[i].description
                                    + " (by: " + taskList[i].getEndDate() + ")");
                            break;
                        case "E":
                            System.out.println(FIVEWHITESPACES + (i + 1) + "." + "["
                                    + taskList[i].getTaskStatus() + "]" + "["
                                    + taskList[i].getStatusIcon() + "] " + taskList[i].description
                                    + " (from: " + taskList[i].getStartDate()
                                    + " to: " + taskList[i].getEndDate() +")");
                            break;
                    }
                }
                System.out.println(VERTICALLINES + "\n");
            } else if (receivedMessage.equals("list") && taskCounter == 0) { // list is not empty
                System.out.println(VERTICALLINES);
                System.out.println(FIVEWHITESPACES + "list is empty");
                System.out.println(VERTICALLINES);
                receivedMessage = requestedMessage.nextLine();
                continue;
            } else if (receivedMessage.contains("mark") && !receivedMessage.contains("unmark")) { // user keys in mark
                String number = "";
                for (int j = 0; j < receivedMessage.length(); j += 1) { // reads number from input and store it in String number
                    if (Character.isDigit(receivedMessage.charAt(j))) {
                        number += receivedMessage.charAt(j);
                    }
                }
                if (number.isEmpty()) {
                    System.out.println(FIVEWHITESPACES + "Error");
                    continue;
                } else {
                    taskList[Integer.parseInt(number) - 1].markAsDone();
                }
                System.out.println(VERTICALLINES);
                System.out.println(FIVEWHITESPACES + "Acknowledged sire, I've marked this task as complete:");
                System.out.println(FIVEWHITESPACES
                        + "  " + "[" + taskList[Integer.parseInt(number) - 1].getTaskStatus() + "]"
                        + "[" + taskList[Integer.parseInt(number) - 1].getStatusIcon() + "] "
                        + taskList[Integer.parseInt(number) - 1].getDescription());
                System.out.println(VERTICALLINES + "\n");

            } else if (receivedMessage.contains("unmark")) { // user keys in unmark
                String number = "";
                for (int j = 0; j < receivedMessage.length(); j += 1) { // reads number from input and store it in String number
                    if (Character.isDigit(receivedMessage.charAt(j))) {
                        number += receivedMessage.charAt(j);
                    }
                }
                if (number.isEmpty()) {
                    System.out.println(FIVEWHITESPACES + "Error");
                    continue;
                } else {
                    taskList[Integer.parseInt(number) - 1].markAsUndone();
                }
                System.out.println(VERTICALLINES);
                System.out.println(FIVEWHITESPACES + "Acknowledged sire, I've marked this task as incomplete:");
                System.out.println(FIVEWHITESPACES
                        + "  " + "[" + taskList[Integer.parseInt(number) - 1].getTaskStatus() + "]"
                        + "[" + taskList[Integer.parseInt(number) - 1].getStatusIcon() + "] "
                        + taskList[Integer.parseInt(number) - 1].getDescription());
                System.out.println(VERTICALLINES + "\n");
            } else if (receivedMessage.contains("todo")) {
                String[] splittedMessage = receivedMessage.split("todo ");
                taskList[taskCounter] = new Task(splittedMessage[1]);
                taskList[taskCounter].setTaskStatus("todo");
                taskCounter += 1;
                //taskList[taskCounter - 1].setTaskStatus("todo");
                System.out.println(VERTICALLINES);
                System.out.println(FIVEWHITESPACES + "Got it. I've added this task:");
                System.out.println(FIVEWHITESPACES + "  " + "[" + taskList[taskCounter - 1].getTaskStatus() + "]" +
                        "[" + taskList[taskCounter - 1].getStatusIcon() + "] " + taskList[taskCounter - 1].getDescription());
                System.out.println(FIVEWHITESPACES + "Now you have " + Integer.toString(taskCounter) + " tasks in the list.");
                System.out.println(VERTICALLINES + "\n");

            } else if (receivedMessage.contains("deadline")) {
                String[] splittedMessage = receivedMessage.split("deadline ");
                String[] doubleSplittedMessage = splittedMessage[1].split(" /by ");
                taskList[taskCounter] = new Task(doubleSplittedMessage[0]);
                taskList[taskCounter].setTaskStatus("deadline");
                taskList[taskCounter].setEndDate(doubleSplittedMessage[1]);
                taskCounter += 1;
                System.out.println(VERTICALLINES);
                System.out.println(FIVEWHITESPACES + "Got it. I've added this task:");
                System.out.println(FIVEWHITESPACES + "  " + "[" + taskList[taskCounter - 1].getTaskStatus() + "]" +
                        "[" + taskList[taskCounter - 1].getStatusIcon() + "] " + taskList[taskCounter - 1].getDescription() +
                        " (by: " + taskList[taskCounter - 1].getEndDate() + ")");
                System.out.println(FIVEWHITESPACES + "Now you have " + Integer.toString(taskCounter) + " tasks in the list.");
                System.out.println(VERTICALLINES + "\n");

            } else if (receivedMessage.contains("event")) {
                String[] splittedMessage = receivedMessage.split("event ");
                String[] doubleSplittedMessage = splittedMessage[1].split(" /from ");
                String[] tripleSplittedMessage = doubleSplittedMessage[1].split(" /to ");
                taskList[taskCounter] = new Task(doubleSplittedMessage[0]);
                taskList[taskCounter].setTaskStatus("event");
                taskList[taskCounter].setStartDate(tripleSplittedMessage[0]);
                taskList[taskCounter].setEndDate(tripleSplittedMessage[1]);
                taskCounter += 1;
                System.out.println(VERTICALLINES);
                System.out.println(FIVEWHITESPACES + "Got it. I've added this task:");
                System.out.println(FIVEWHITESPACES + "  " + "[" + taskList[taskCounter - 1].getTaskStatus() + "]" +
                        "[" + taskList[taskCounter - 1].getStatusIcon() + "] " + taskList[taskCounter - 1].getDescription() +
                        " (from: " + taskList[taskCounter - 1].getStartDate() + " to: " + taskList[taskCounter - 1].getEndDate() +
                        ")");
                System.out.println(FIVEWHITESPACES + "Now you have " + Integer.toString(taskCounter) + " tasks in the list.");
                System.out.println(VERTICALLINES + "\n");

            }
            receivedMessage = requestedMessage.nextLine();
        }
        System.out.println(FAREWELLMESSAGE);
    }
}