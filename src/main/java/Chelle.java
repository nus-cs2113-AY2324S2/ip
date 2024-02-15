import java.util.Scanner;

public class Chelle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println("Hello! I'm Chelle.\nI like to talkity talkity talk!");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();
            String userCommand = userInput.split(" ")[0];

            switch (userCommand) {
            case "bye":
                System.out.println("Chelle: Bye! Hope to see you again soon!");
                scanner.close();
                return;

            case "list":
                System.out.println("Chelle: ");
                displayTasks(tasks, taskCount);
                break;

            case "mark":
                try {
                    markTask(userInput, tasks, taskCount);
                } catch (InvalidCommandFormatException e){
                    System.out.println("Chelle: Invalid format. Use 'mark ___'.");
                }
                break;

            case "unmark":
                try {
                    unmarkTask(userInput, tasks, taskCount);
                } catch (InvalidCommandFormatException e){
                    System.out.println("Chelle: Invalid format. Use 'unmark ___'.");
                }
                break;

            case "todo":
                try {
                    taskCount = createTask(userInput, tasks, taskCount, TaskType.TODO);
                } catch (InvalidCommandFormatException e){
                    System.out.println("Chelle: Invalid format. Use 'todo ___'.");
                }
                break;

            case "deadline":
                try {
                    taskCount = createTask(userInput, tasks, taskCount, TaskType.DEADLINE);
                } catch (InvalidCommandFormatException e){
                    System.out.println("Chelle: Invalid format. Use 'deadline ___ /by ___'.");
                }
                break;

            case "event":
                try {
                    taskCount = createTask(userInput, tasks, taskCount, TaskType.EVENT);
                } catch (InvalidCommandFormatException e){
                    System.out.println("Chelle: Invalid format. Use 'event ___ /from ___ /to ___'.");
                }
                break;
            default:
                System.out.println("Chelle: Invalid command. Please start your command with one of the following commands: \n" +
                        "'list', 'mark', 'unmark', 'todo', 'deadline' or 'event'.");
                break;
            }
        }
    }

    private static void displayTasks(Task[] tasks, int count) {
        if (count == 0) {
            System.out.println("No tasks to display.");
        } else {
            for (int i = 0; i < count; i++) {
                System.out.println((i + 1) + ". " + tasks[i].toString());
            }
        }
    }

    private static int createTask(String userInput, Task[] tasks, int taskCount, TaskType taskType) throws InvalidCommandFormatException{
        switch (taskType) {
        case TODO:
            if (userInput.length() < 6) {
                throw new InvalidCommandFormatException();
            } else {
                tasks[taskCount] = new ToDo(userInput.substring(5));
            }
            break;
        case DEADLINE:
            if (userInput.length() < 10 || !userInput.contains("/by")) {
                throw new InvalidCommandFormatException();
            } else {
                tasks[taskCount] = new Deadline(userInput.substring(9));
            }
            break;
        case EVENT:
            if (userInput.length() < 7 || !(userInput.indexOf("/from") < userInput.indexOf("/to"))) {
                throw new InvalidCommandFormatException();
            } else {
                tasks[taskCount] = new Event(userInput.substring(6));
            }
            break;
        default:
            System.out.println("Chelle: Error");
            return taskCount;
        }

        taskCount++;
        System.out.println("Chelle: Got it. I've added this task:\n        " +
                tasks[taskCount - 1].toString() +
                "\n        Now you have " + taskCount + " tasks in the list.");
        return taskCount;
    }

    private static void markTask(String userInput, Task[] tasks, int taskCount) throws InvalidCommandFormatException{
        if (userInput.length() < 6) {
            throw new InvalidCommandFormatException();
        } else {
            userInput = userInput.substring(5);
        }

        try {
            int taskIndex = Integer.parseInt(userInput) - 1;
            if (taskIndex >= 0 && taskIndex < taskCount) {
                tasks[taskIndex].markDone();
                System.out.println("Chelle: Nice! I've marked this task as done:\n        " +
                        tasks[taskIndex].toString());
            } else {
                System.out.println("Chelle: Invalid task index.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Chelle: Invalid task index.");
        }
    }

    private static void unmarkTask(String userInput, Task[] tasks, int taskCount) throws InvalidCommandFormatException{
        if (userInput.length() < 8) {
            throw new InvalidCommandFormatException();
        } else {
            userInput = userInput.substring(7);
        }

        try {
            int taskIndex = Integer.parseInt(userInput) - 1;
            if (taskIndex >= 0 && taskIndex < taskCount) {
                tasks[taskIndex].markUndone();
                System.out.println("Chelle: OK, I've marked this task as not done yet:\n        " +
                        tasks[taskIndex].toString());
            } else {
                System.out.println("Chelle: Invalid task index.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Chelle: Invalid task index.");
        }
    }
}

