import java.util.Scanner;

public class Soot {
    public static int listCounter = 0;
    public static void main(String[] args) {
        String line;
        String lowerCase;
        Scanner in = new Scanner(System.in);
        Task[] taskList = new Task[100];

        boolean isBye = false;

        drawLine(); //initial greeting
        System.out.println("Hello! I'm Soot, your personal chatbot companion :)");
        System.out.println("What can I help you with?");
        drawLine();

        while (!isBye) {
            line = in.nextLine(); //user input
            drawLine();
            lowerCase = line.toLowerCase();
            isBye = verifyInput(taskList, line);
        }
    }

    public static boolean verifyInput(Task[] taskList, String input) {
        String inputCommand = input.toLowerCase();
        String inputTask = input;
        if (inputCommand.startsWith("done")) {
            inputCommand = "done";
            inputTask = input.substring(5);
        } else if (inputCommand.startsWith("unmark")) {
            inputCommand = "unmark";
            inputTask = input.substring(7);
        } else if (inputCommand.startsWith("todo")) {
            inputCommand = "todo";
            inputTask = input.substring(5);
        } else if (inputCommand.startsWith("deadline")) {
            inputCommand = "deadline";
            inputTask = input.substring(9);
        } else if (inputCommand.startsWith("event")) {
            inputCommand = "event";
            inputTask = input.substring(6);
        }

        switch (inputCommand) {
        case "bye":
            greetGoodbye();
            return true;
        case "list":
            System.out.println("tasks to be done!");
            for (int i = 0; i < listCounter; i++)
                taskList[i].printTask();
            drawLine();
            break;
        case "done":
            int listIndex = Integer.parseInt(inputTask) - 1;
            taskList[listIndex].markDone();
            drawLine();
            break;
        case "unmark":
            listIndex = Integer.parseInt(inputTask) - 1;
            taskList[listIndex].markUndone();
            drawLine();
            break;
        case "todo":
            taskList[listCounter] = new Todo(inputTask, listCounter);
            taskList[listCounter].printRespond();
            drawLine();
            listCounter++;
            break;
        case "deadline":
            //split taskName and dueDate
            int deadlineIndex = inputTask.indexOf('/');
            String taskName = inputTask.substring(0, deadlineIndex - 1);
            String dueDate = inputTask.substring(deadlineIndex + 4);

            taskList[listCounter] = new Deadline(taskName, listCounter, dueDate);
            taskList[listCounter].printRespond();
            drawLine();
            listCounter++;
            break;
        case "event":
            //split taskName and time frames
            int startIndex = inputTask.indexOf('/');
            taskName = inputTask.substring(0, startIndex - 1);

            String timeLine = inputTask.substring(startIndex + 6);
            int endIndex = timeLine.indexOf('/');

            String startDate = timeLine.substring(0, endIndex - 1);
            String endDate = timeLine.substring(endIndex + 4);

            taskList[listCounter] = new Event(taskName, listCounter, startDate, endDate);
            taskList[listCounter].printRespond();
            drawLine();
            listCounter++;
            break;
        default:
            System.out.println("added: " + input);
            drawLine();

            taskList[listCounter] = new Task(input, listCounter);
            listCounter++;
            break;
        }
        return false;
    }
    public static void drawLine() {
        int LINE_LENGTH = 60;
        for (int i = 0; i < LINE_LENGTH; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }

    public static void greetGoodbye() {
        System.out.println("Bye! Till the next time we meet...");
        drawLine();
    }
}
