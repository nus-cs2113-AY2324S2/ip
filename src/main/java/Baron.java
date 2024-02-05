import java.util.Scanner;

public class Baron {
    public static void displayCommandsList(Task[] commandsList) {
        int listNumber = 1;
        System.out.println("\tHere are the tasks in your list:");
        for (Task command: commandsList) {
            if (command == null) {
                break;
            }
            System.out.println("\t" + listNumber + "." + command.getTaskDetails());
            listNumber += 1;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Task[] commandsList = new Task[100];
        int numberOfTasks = 0;
        System.out.println("Hello! I'm Baron");
        System.out.println("What can I do for you?\n");
        while (true) {
            Scanner userInput = new Scanner(System.in);
            String input = userInput.nextLine();
            input = input.toLowerCase();
            String[] inputArray = input.split(" ", 2);
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (input.equals("list")) {
                displayCommandsList(commandsList);
                continue;
            }
            else if (input.startsWith("todo")) {
                commandsList[numberOfTasks] = new ToDo(inputArray[1]);
                numberOfTasks += 1;
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t\t" + commandsList[numberOfTasks - 1].getTaskDetails());
                System.out.println("\tNow you have " + numberOfTasks + (numberOfTasks == 1 ? " task" :" tasks") +" in the list.\n");
                continue;
            }
            else if (input.startsWith("deadline")) {
                int dividerPosition = inputArray[1].indexOf('/');
                String description = inputArray[1].substring(0, dividerPosition);
                String deadline = inputArray[1].substring(dividerPosition + 4);
                commandsList[numberOfTasks] = new Deadline(description, deadline);
                numberOfTasks += 1;
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t\t" + commandsList[numberOfTasks - 1].getTaskDetails());
                System.out.println("\tNow you have " + numberOfTasks + (numberOfTasks == 1 ? " task" :" tasks") +" in the list.\n");
                continue;
            }
            else if (input.startsWith("event")) {
                int firstDividerPosition = inputArray[1].indexOf('/');
                int secondDividerPosition = inputArray[1].indexOf("to");
                String description = inputArray[1].substring(0, firstDividerPosition);
                String eventStart = inputArray[1].substring(firstDividerPosition + 6, secondDividerPosition - 1);
                String eventEnd = inputArray[1].substring(secondDividerPosition + 3);
                commandsList[numberOfTasks] = new Event(description, eventStart, eventEnd);
                numberOfTasks += 1;
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t\t" + commandsList[numberOfTasks - 1].getTaskDetails());
                System.out.println("\tNow you have " + numberOfTasks + (numberOfTasks == 1 ? " task" :" tasks") +" in the list.\n");
                continue;
            }
            else if (input.startsWith("mark")) {
                String taskNumber = inputArray[1];
                int commandIndex = Integer.parseInt(taskNumber);
                commandsList[commandIndex - 1].markAsDone(numberOfTasks);
                continue;
            }
            else if (input.startsWith("unmark")) {
                String taskNumber = inputArray[1];
                int commandIndex = Integer.parseInt(taskNumber);
                commandsList[commandIndex - 1].unmarkAsDone(numberOfTasks);
                continue;
            }
            else {
                System.out.println("Sorry but that is not a valid command. Please enter a valid command");
            }
        }
    }
}