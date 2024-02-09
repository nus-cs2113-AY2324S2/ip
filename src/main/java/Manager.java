import java.util.Scanner;

public class Manager {
    private static int taskIndex = 0;
    private static final Task[] taskList = new Task[100];

    public static void acceptInput () {
        Scanner input = new Scanner(System.in);
        String inputString = " ";
        Commands action;
        boolean flag = true;

        do {
            inputString = input.nextLine();
            action = classifyCommand(inputString);

            int spaceIndex = inputString.indexOf(" ");
            int slashIndex = inputString.indexOf("/");

            if (slashIndex == -1) {
                slashIndex = inputString.length();
            }

            switch (action) {
            case TODO:
            case DEADLINE:
            case EVENT:
                addTasks(action,inputString.substring(spaceIndex,slashIndex),inputString,slashIndex);
                continue;
            case BYE:
                sayBye();
                flag = false;
                break;
            case LIST:
                displayList(taskList, taskIndex);
                continue;
            case MARK:
                taskList[Integer.parseInt(inputString.substring(5)) - 1].mark();
                continue;
            case UNMARK:
                taskList[Integer.parseInt(inputString.substring(7)) - 1].unmark();
                continue;
            case INVALID:
                System.out.println("Your command was invalid.");
            }

        } while (flag);

    }
    private static void addTasks(Commands taskType, String taskName, String inputString, int slashIndex) {
        String line = "-----------------------------------\n";
        String indent = "   ";
        switch (taskType) {
        case TODO:
            ToDo newToDo = new ToDo(taskIndex, false, taskName);
            taskList[taskIndex] = newToDo;
            taskIndex++;
            break;
        case DEADLINE:
            String deadline = inputString.substring(slashIndex+3);
            Deadline newDeadline = new Deadline(taskIndex,false,taskName,deadline);
            taskList[taskIndex] = newDeadline;
            taskIndex++;
            break;
        case EVENT:
            String startAndEnd = inputString.substring(slashIndex+5);
            String startTime = startAndEnd.substring(0,startAndEnd.indexOf("/"));
            String endTime = startAndEnd.substring(startAndEnd.indexOf("/")+3);
            Event newEvent = new Event(taskIndex,false,taskName,startTime,endTime);
            taskList[taskIndex] = newEvent;
            taskIndex++;
            break;
        }


        System.out.print(line);
        System.out.print(indent);
        System.out.println("OK, I've added: " + taskName);
        if (taskList[taskIndex-1].getType().equals("D")) {
            System.out.println(indent + taskList[taskIndex-1].displayCurrentTask() + " (by: " + taskList[taskIndex-1].getEndTime() +")");

        }else if (taskList[taskIndex-1].getType().equals("E")){
            System.out.println(indent + taskList[taskIndex-1].displayCurrentTask() + " (from: " + taskList[taskIndex-1].getStartTime()
                    + " to: " + taskList[taskIndex-1].getEndTime() + ")");

        }else {
            System.out.println(indent + taskList[taskIndex-1].displayCurrentTask());
        }

        System.out.println(indent + "Now, you have " + taskIndex+ " tasks in your list.");
        System.out.print(line);
    }
    private static Commands classifyCommand(String inputString) {

        Commands command;
        switch (inputString) {
        // Cases include farewell and list commands
        case "bye":
        case "Bye":
        case "BYE":
            command = Commands.BYE;
            return command;
        case "list":
        case "List":
        case "LIST":
            command = Commands.LIST;
            return command;
        }
        switch (inputString.substring(0, inputString.indexOf(" "))) {
        case "todo":
            command = Commands.TODO;
            return command;
        case "deadline":
            command = Commands.DEADLINE;
            return command;
        case "event":
            command = Commands.EVENT;
            return command;
        // Cases for marking tasks
        case "mark":
            command = Commands.MARK;
            return command;
        case "unmark":
            command = Commands.UNMARK;
            return command;
        }
       return Commands.INVALID;

    }

    // Method for displaying list
    private static void displayList(Task[] taskList, int taskIndex) {
        String line = "-----------------------------------\n";
        String indent = "   ";
        for (int i = 0; i < taskIndex; i++) {
            System.out.print(indent);
            if (taskList[i].getType().equals("D")) {
                System.out.println(taskList[i].getOrder()+1 + ". [D] " + taskList[i].doneCheckbox + " "
                        + taskList[i].getTaskName() + " (by: " + taskList[i].getEndTime() +")");
            }else if (taskList[i].getType().equals("E")){
                System.out.println(taskList[i].getOrder()+1 + ". [E] " + taskList[i].doneCheckbox + " "
                        + taskList[i].getTaskName() + " (from: " + taskList[i].getStartTime()
                        + " to: " + taskList[i].getEndTime() + ")");
            }else {
                System.out.println(taskList[i].getOrder()+1 + ". [T]" + taskList[i].doneCheckbox + " "
                        + taskList[i].getTaskName());
            }
        }
        System.out.print(line);
    }


    // Method for farewell message
    private static void sayBye() {
        String line = "-----------------------------------\n";
        String farewell = "Farewell. Hope to see you again soon!";
        System.out.print(line);
        System.out.println(farewell);
        System.out.print(line);
    }

    // Method for echo, not used after Level-1
    private static void echo() {
        String line = "-----------------------------------\n";
        String indent = "   ";
        String echoLine;
        Scanner input = new Scanner(System.in);
        echoLine = input.nextLine();
        while (true) {
            if(echoLine.equals("bye") || echoLine.equals("Bye") || echoLine.equals("BYE")) {
                break;
            }
            System.out.print(line);
            System.out.print(indent);
            System.out.println(echoLine);
            System.out.print(line);
            echoLine = input.nextLine();
        }

    }

}
