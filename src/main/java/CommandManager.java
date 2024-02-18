public class CommandManager {
    public static int listCounter;
    public static final int LIST_SIZE = 100;
    public static Task[] taskList; //public static?

    public CommandManager() {
        taskList = new Task[LIST_SIZE];
        listCounter = 0;
    }

    public static String getCommand(String input) throws EmptyTaskException {
        if (input.equals("bye") || input.equals("list")) {
            return input;
        }

        int spaceIndex = input.indexOf(" ");
        if (spaceIndex == -1) {
            throw new EmptyTaskException();
        }
        return input.substring(0, spaceIndex);
    }
    public static boolean isInputBye(String input) {
        String inputCommand = input;
        try {
            inputCommand = getCommand(input);
            String inputTask;
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
                inputTask = input.substring(5);
                int listIndex = Integer.parseInt(inputTask) - 1;
                taskList[listIndex].markDone();
                drawLine();
                break;
            case "unmark":
                inputTask = input.substring(7);
                listIndex = Integer.parseInt(inputTask) - 1;
                taskList[listIndex].markUndone();
                drawLine();
                break;
            case "todo":
                inputTask = input.substring(5);
                taskList[listCounter] = new Todo(inputTask, listCounter);
                taskList[listCounter].printRespond();
                drawLine();
                listCounter++;
                break;
            case "deadline":
                inputTask = input.substring(9);
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
                inputTask = input.substring(6);
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
                System.out.println("  !! this isn't a command i recognise...\n  sorry, pls try again");
                drawLine();
                break;
            }
        } catch (EmptyTaskException e) {
            System.out.println("  !! hmmm, no task was specified.");
            drawLine();
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