public class CommandManager {
    public static int listCounter;
    public static final int LIST_SIZE = 100;
    public static Task[] taskList; //public static?

    public CommandManager() {
        taskList = new Task[LIST_SIZE];
        listCounter = 0;
    }
    public static String identifyCommand(String input) {
        String inputCommand = input.trim().toLowerCase();
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
        return inputCommand;
    }

    public static boolean verifyInput(String input) {
        String inputCommand = identifyCommand(input);
        String inputTask = input;
        if (inputCommand.startsWith("done")) {
//            inputCommand = "done";
            inputTask = input.substring(5);
        } else if (inputCommand.startsWith("unmark")) {
//            inputCommand = "unmark";
            inputTask = input.substring(7);
        } else if (inputCommand.startsWith("todo")) {
//            inputCommand = "todo";
            inputTask = input.substring(5);
        } else if (inputCommand.startsWith("deadline")) {
//            inputCommand = "deadline";
            inputTask = input.substring(9);
        } else if (inputCommand.startsWith("event")) {
//            inputCommand = "event";
            inputTask = input.substring(6);
        }

        switch (inputCommand) {
        case "bye":
            return true;
        case "list":
            System.out.println("tasks to be done:");
            if (listCounter == 0) {
                System.out.println("  >> nothing so far :)");
                drawLine();
                break;
            }
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
//            System.out.println("added: " + input);
//            drawLine();
//
//            taskList[listCounter] = new Task(input, listCounter);
//            listCounter++;
            System.out.println("  this isn't a command i recognise...\n  sorry, pls try again");
            drawLine();
            break;
        }
        return false;
    }

    //TODO: repeated method from main
    public static void drawLine() {
        int LINE_LENGTH = 60;
        for (int i = 0; i < LINE_LENGTH; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }
}