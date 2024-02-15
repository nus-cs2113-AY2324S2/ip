import java.util.Scanner;

public class Manager {
    private static final String line = "-----------------------------------\n";
    private static final String indent = "   ";
    private static int taskIndex = 0;
    private static final Task[] taskList = new Task[100];

    public static void acceptInput () {
        Scanner input = new Scanner(System.in);
        String inputString;
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
                addTask(action,inputString.substring(spaceIndex,slashIndex),inputString,slashIndex);
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
            case INVALIDTODO:
            case INVALIDDEADLINE:
            case INVALIDEVENT:
            case INVALIDCOMMAND:
            case EMPTYCOMMAND:
                handleCheckedExceptions(action);

            }

        } while (flag);

    }
    private static void addTask(Commands taskType, String taskName, String inputString, int slashIndex) {
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
        switch (inputString) {
        // Cases include farewell and list commands
        case "bye":
        case "Bye":
        case "BYE":
            return Commands.BYE;
        case "list":
        case "List":
        case "LIST":
            return Commands.LIST;
        }

        try {
            String[] commandWords = inputString.split(" ");
            switch (commandWords[0]) {
            case "todo":
                addToDo(commandWords);
                return Commands.TODO;

            case "deadline":
                addDeadline(commandWords);
                return Commands.DEADLINE;
            case "event":
                addEvent(commandWords);
                return Commands.EVENT;
            // Cases for marking tasks
            case "mark":
                return Commands.MARK;

            case "unmark":
                return Commands.UNMARK;

            case "":
                handleEmptyString();

            default:
                handleInvalidCommand();
            }
        } catch (InvalidToDoException e) {
            return Commands.INVALIDTODO;
        } catch (InvalidDeadlineException E) {
            return Commands.INVALIDDEADLINE;
        } catch (InvalidEventException e) {
            return Commands.INVALIDEVENT;
        } catch (EmptyCommandException e) {
            return Commands.EMPTYCOMMAND;
        } catch (InvalidCommandException | ArrayIndexOutOfBoundsException e) {
            return Commands.INVALIDCOMMAND;
        }
        return Commands.INVALIDCOMMAND;

    }

    // Method for displaying list
    private static void displayList(Task[] taskList, int taskIndex) {
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
        String farewell = "Farewell. Hope to see you again soon!";
        System.out.print(line);
        System.out.println(farewell);
        System.out.print(line);
    }

    // Method for echo, not used after Level-1
<<<<<<< HEAD
    private static void echo() {
=======
    /*private static void echo() {
        String line = "-----------------------------------\n";
        String indent = "   ";
>>>>>>> master
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

<<<<<<< HEAD
    }
    public static void addToDo(String[] commandWords) throws InvalidToDoException {
        if (commandWords.length < 2) {
            throw new InvalidToDoException();
        }
    }
=======
    }*/
>>>>>>> master

    public static void addDeadline(String[] commandWords) throws InvalidDeadlineException {
        boolean isValidDeadline = true;
        for (String commandWord : commandWords) {
            if (commandWord.equals("/by")) {
                isValidDeadline = false;
                break;
            }
        }
        if (commandWords.length < 2 || !isValidDeadline) {
            throw new InvalidDeadlineException();
        }
    }

    public static void addEvent(String[] commandWords) throws InvalidEventException {
        boolean isValidEvent = false;
        boolean hasValidStart = true;
        boolean hasValidEnd = true;
        for (String commandWord : commandWords) {
            if (commandWord.equals("/from")) {
                hasValidStart = false;
            }
            if (commandWord.equals("/to")) {
                hasValidEnd = false;
            }
        }
        if (hasValidStart && hasValidEnd) {
            isValidEvent = true;
        }
        if (commandWords.length < 2 || !isValidEvent) {
            throw new InvalidEventException();
        }
    }

    public static void handleEmptyString () throws EmptyCommandException {
        throw new EmptyCommandException();
    }

    public static void handleInvalidCommand () throws InvalidCommandException {
        throw new InvalidCommandException();
    }

    public static void handleCheckedExceptions (Commands action) {
        switch (action) {
        case INVALIDTODO:
            System.out.println("Oh no! You did not enter a ToDo task after the todo command!");
            System.out.println("The correct format should be 'todo (task)'");
            System.out.print(line);
            break;
        case INVALIDDEADLINE:
            System.out.println("Oh no! You did not enter a Deadline task / specify the deadline correctly " +
                    "after the deadline command!");
            System.out.println("The correct format should be 'deadline (task) by/ (deadline)'");
            System.out.print(line);
            break;
        case INVALIDEVENT:
            System.out.println("Oh no! You did not enter a Event task / specify the event correctly " +
                    "after the event command!");
            System.out.println("The correct format should be 'event (task) /from (start time) /to (end time)'");
            System.out.print(line);
            break;
        case EMPTYCOMMAND:
            System.out.println("Oh no! You did not enter anything! What can one do with nothing? I wonder...");
            System.out.println("Valid commands are todo, deadline, event\nThanks!");
            System.out.print(line);
            break;
        case INVALIDCOMMAND:
            System.out.println("Thank you for your input but you did not enter any command! :(");
            System.out.println("Valid commands are todo, deadline, event\nThanks!");
            System.out.print(line);
            break;
        }
    }
}
