import java.util.Scanner;
import java.util.WeakHashMap;

public class Casper {
    private static final String SEPARATOR = "    _____________________________________________";
    private static final Task[] taskList = new Task[100];
    private static int noOfTasks = 0;
    private static void wrapEchoMessage(String message){
        System.out.println(SEPARATOR);
        System.out.println("     "+message);
        System.out.println(SEPARATOR);
    }

    private static void echoGreetings(){
        String logo = "       ___ __ _ ___ _ __   ___ _ __\n"
                + "      / __/ _` / __| '_ \\ / _ \\ '__|\n"
                + "     | (_| (_| \\__ \\ |_) |  __/ |   \n"
                + "      \\___\\__,_|___/ .__/ \\___|_|   \n"
                + "                   | |              \n"
                + "                   |_|               ";
        System.out.println("    Starting\n" + logo);
        wrapEchoMessage("Boo! I'm Casper!\n     What can I do for you?");
    }

    private static void handleQueries(){
        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            String userInput = inputScanner.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            if (userInput.equals("list")) {
                echoTaskList();
            } else if (userInput.startsWith("mark")) {
                int targetTaskNumber = validateMarkInput(userInput);
                if (targetTaskNumber != -1) {
                    taskList[targetTaskNumber-1].markTask();
                }
            } else if (userInput.startsWith("unmark")) {
                int targetTaskNumber = validateMarkInput(userInput);
                if (targetTaskNumber != -1) {
                    taskList[targetTaskNumber-1].unMarkTask();
                }
            } else if (userInput.startsWith("event")) {
                Task newTask = getEvent(userInput);
                taskList[noOfTasks] = newTask;
                noOfTasks++;
                wrapEchoMessage("Got it. I've added this task: \n       "
                        +newTask+"\n     Now you have "+noOfTasks+" tasks in the list");
            } else if (userInput.startsWith("deadline")) {
                Task newTask = getDeadline(userInput);
                taskList[noOfTasks] = newTask;
                noOfTasks++;
                wrapEchoMessage("Got it. I've added this task: \n       "
                        +newTask+"\n     Now you have "+noOfTasks+" tasks in the list");
            } else if (userInput.startsWith("todo")) {
                Task newTask = getTodo(userInput);
                taskList[noOfTasks] = newTask;
                noOfTasks++;
                wrapEchoMessage("Got it. I've added this task: \n       "
                        +newTask+"\n     Now you have "+noOfTasks+" tasks in the list");
            } else {
                wrapEchoMessage("Pardon? I didn't get your message.");
            }
        }
    }

    private static Task getEvent(String userInput) {
        int fromIndex = userInput.indexOf("/from") + "/from".length();
        int toIndex = userInput.indexOf("/to") + "/to".length();
        int descIndex = userInput.indexOf("event")+"event".length();

        String eventDesc= userInput.substring(descIndex, userInput.indexOf("/from")).trim();
        String from = userInput.substring(fromIndex, userInput.indexOf("/to")).trim();
        String to = userInput.substring(toIndex).trim();
        return new Event(eventDesc, from, to);
    }

    private static Task getDeadline(String userInput) throws StringIndexOutOfBoundsException {
        int byIndex = userInput.indexOf("/by") + "/by".length();
        int descIndex = userInput.indexOf("deadline")+"deadline".length();

        if (byIndex < descIndex) {
            throw new StringIndexOutOfBoundsException();
        }

        String deadlineDesc = userInput.substring(descIndex, userInput.indexOf("/by")).trim();
        String by = userInput.substring(byIndex).trim();
        return new Deadline(deadlineDesc, by);
    }

    private static Task getTodo(String userInput) {
        int descIndex = userInput.indexOf("todo")+"todo".length();
        String todoDesc = userInput.substring(descIndex).trim();
        return new Todo(todoDesc);
    }

    private static void echoTaskList() {
        System.out.println(SEPARATOR);
        if (noOfTasks==0) {
            System.out.println("     You have no tasks this time around.");
        } else {
            System.out.println("     Here are the tasks in your list:");
            for (int i=1; i<=noOfTasks; i++) {
                System.out.println("     "+i+". "+taskList[i-1]);
            }
        }
        System.out.println(SEPARATOR);
    }

    private static boolean checkNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static int validateMarkInput(String userInput){
        String[] userInputSplit = userInput.split(" ");
        if (userInputSplit.length==2 && checkNumeric(userInputSplit[1])) {
            int targetTaskNumber = Integer.parseInt(userInputSplit[1]);
            if (targetTaskNumber<=noOfTasks&&targetTaskNumber>0) {
                return targetTaskNumber;
            } else {
                wrapEchoMessage("That task does not exist!");
                return -1;
            }
        } else {
            wrapEchoMessage("Invalid input!");
            return -1;
        }
    }

    public static void main(String[] args) {
        echoGreetings();
        handleQueries();
        wrapEchoMessage("Alright, see you around!");
    }

}
