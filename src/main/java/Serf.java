import java.util.Scanner;
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
        System.out.println(GREETINGMESSAGE);
        Scanner requestedMessage = new Scanner(System.in);
        String receivedMessage = requestedMessage.nextLine();

        while(!receivedMessage.equals("bye")) {
            if (receivedMessage.equals("list") && taskCounter > 0) { // list is not empty
                System.out.println(VERTICALLINES);
                System.out.println(FIVEWHITESPACES + "My lord, here are the tasks as recorded in thy list");
                for (int i = 0; i < taskCounter; i += 1) {
                    System.out.println(FIVEWHITESPACES
                            + (i + 1)
                            + "."
                            + "["
                            + taskList[i].getStatusIcon()
                            + "] "
                            + taskList[i].description
                            + " ");
                }
                System.out.println(VERTICALLINES);
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
                System.out.println(FIVEWHITESPACES + "Acknowledged sire, I've marked this task as complete");
                System.out.println(FIVEWHITESPACES
                        + "  "
                        + "["
                        + taskList[Integer.parseInt(number) - 1].getStatusIcon() + "] "
                        + taskList[Integer.parseInt(number) - 1].getDescription());
                System.out.println(VERTICALLINES);

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
                System.out.println(FIVEWHITESPACES + "Acknowledged sire, I've marked this task as incomplete");
                System.out.println(FIVEWHITESPACES
                        + "  "
                        + "["
                        + taskList[Integer.parseInt(number) - 1].getStatusIcon() + "] "
                        + taskList[Integer.parseInt(number) - 1].getDescription());
                System.out.println(VERTICALLINES);
            } else {  // add new task to taskList
                taskList[taskCounter] = new Task(receivedMessage);
                taskCounter += 1;
                System.out.println(VERTICALLINES);
                System.out.println(FIVEWHITESPACES + "added: " + receivedMessage);
                System.out.println(VERTICALLINES);
            }
            receivedMessage = requestedMessage.nextLine();
        }
        System.out.println(FAREWELLMESSAGE);
    }
}