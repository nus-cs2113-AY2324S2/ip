import java.util.Scanner;

public class Vibes {
    private static final String CHATBOT_NAME = "Vibes";
    private static final String DASHED_LINE = "\t---------------------------------------------------------------------------------------";

    public static String extractCommand(String userInput){
        if (userInput.contentEquals("list")){
            return "list";
        } else if (userInput.contentEquals("bye")){
            return "bye";
        } else {
            return userInput.substring(0,userInput.indexOf(" "));
        }
    }

    public static void main(String[] args) {
        String userInput;
        boolean isExit = true;
        Scanner in = new Scanner(System.in);
        List taskList = new List();

        System.out.println(DASHED_LINE);
        System.out.println("\t Hello! I'm " + CHATBOT_NAME + "\n\t What can I do for you?");
        System.out.println(DASHED_LINE);

        while(isExit){
            int taskNumber;
            userInput = in.nextLine().trim();
            String commandToExecute = extractCommand(userInput.toLowerCase());

            System.out.println(DASHED_LINE);
            switch (commandToExecute){
            case "bye":
                System.out.println("\t Bye. Hope to see you again soon!");
                isExit = false;
                break;
            case "list":
                taskList.listTasks();
                break;
            case "mark":
                taskNumber = Integer.parseInt(userInput.substring(5)) - 1;
                taskList.setAsDone(taskNumber);
                break;
            case "unmark":
                taskNumber = Integer.parseInt(userInput.substring(7)) - 1;
                taskList.setAsNotDone(taskNumber);
                break;
            default:
                taskList.addTask(userInput);
                break;
            }
            System.out.println(DASHED_LINE);
        }
    }
}