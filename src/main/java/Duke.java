import java.util.Scanner;

public class Duke {
    public Duke() {
    }

    public static void main(String[] args) {
        String botName = "Hirofumi";
        System.out.println("************************************************************");
        System.out.println(" Hello! I'm " + botName);
        System.out.println(" What can I do for you?");
        System.out.println("************************************************************");

        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        boolean isRunning = true;

        int MAX_NUMBERED_LIST_LENGTH = 100;
        Task[] numberedList = new Task[MAX_NUMBERED_LIST_LENGTH];
        int listCount = 0;

        while (isRunning) {
            if (userInput.equals("bye")) {
                System.out.println("************************************************************");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("************************************************************");
                isRunning = false;
            } else if (userInput.equals("list") && listCount == 0) {
                System.out.println("List is empty.");
            } else if (userInput.equals("list") && listCount > 0) {
                for (int i = 0; i < listCount; i++) {
                    System.out.print((i + 1) + ".");
//                    System.out.printf("[%s][%s] ", numberedList[i].getType(), numberedList[i].getStatusIcon());
                    System.out.println(numberedList[i]);
                }
            } else if (userInput.startsWith("mark")) {
                String secondArgument = userInput.substring(userInput.indexOf(' ') + 1);
                int userSelectedIndex = Integer.parseInt(secondArgument);
                numberedList[userSelectedIndex - 1].markAsDone();

                System.out.println("Nice! I've marked this task as done:");
                System.out.print((userSelectedIndex) + ".");
                System.out.printf("[%s] ", numberedList[userSelectedIndex - 1].getStatusIcon());
                System.out.println(numberedList[userSelectedIndex - 1].description);
            } else if (userInput.startsWith("unmark")) {
                String secondArgument = userInput.substring(userInput.indexOf(' ') + 1);
                int userSelectedIndex = Integer.parseInt(secondArgument);
                numberedList[userSelectedIndex - 1].markAsNotDone();

                System.out.println("OK, I've marked this task as not done yet:");
                System.out.print((userSelectedIndex) + ".");
                System.out.printf("[%s] ", numberedList[userSelectedIndex - 1].getStatusIcon());
                System.out.println(numberedList[userSelectedIndex - 1].description);
            } else if (userInput.startsWith("todo")) {
                String taskDescription = userInput.substring(userInput.indexOf(' ') + 1);

                Todo newTodo = new Todo(taskDescription);
                numberedList[listCount++] = newTodo;
                System.out.println("added: " + newTodo);
            } else if (userInput.startsWith("deadline")) {
                String taskDescription = userInput.substring(userInput.indexOf(' ') + 1, userInput.indexOf('/') - 1);
                String deadline = userInput.substring(userInput.indexOf('/') + 1);

                Deadline newDeadline = new Deadline(taskDescription, deadline);
                numberedList[listCount++] = newDeadline;
                System.out.println("added: " + newDeadline);
            } else if (userInput.startsWith("event")) {
                String taskDescription = userInput.substring(userInput.indexOf(' ') + 1, userInput.indexOf('/') - 1);
                String timePeriod = userInput.substring(userInput.indexOf('/') + 1);
                String startTime = timePeriod.substring(0, timePeriod.indexOf('/') - 1);
                String endTime = timePeriod.substring(timePeriod.indexOf('/') + 1);

                Event newEvent = new Event(taskDescription, startTime, endTime);
                numberedList[listCount++] = newEvent;
                System.out.println("added: " + newEvent);
            } else {
                Task newTask = new Task(userInput);
                numberedList[listCount++] = newTask;
                System.out.println("added: " + userInput);
            }
            userInput = in.nextLine();
        }
    }
}
