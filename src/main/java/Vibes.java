import java.util.Scanner;

public class Vibes {
    public static void main(String[] args) {
        String name = "Vibes";
        String line = "\t---------------------------------------------------------------------------------------";
        String userInput;
        boolean isExit = true;
        Scanner in = new Scanner(System.in);
        List taskList = new List();

        System.out.println(line);
        System.out.println("\t Hello! I'm " + name + "\n\t What can I do for you?");
        System.out.println(line);

        while(isExit){
            userInput = in.nextLine().trim();
            System.out.println(line);
            switch (userInput.toLowerCase()){
            case "bye":
                System.out.println("\t Bye. Hope to see you again soon!");
                isExit = false;
                break;
            case "list":
                taskList.listTasks();
                break;
            default:
                if (userInput.toLowerCase().startsWith("mark")){
                    int taskNumber = Integer.parseInt(userInput.substring(5)) - 1;
                    taskList.setAsDone(taskNumber);
                } else if (userInput.toLowerCase().startsWith("unmark")) {
                    int taskNumber = Integer.parseInt(userInput.substring(7)) - 1;
                    taskList.setAsNotDone(taskNumber);
                } else {
                    taskList.addTask(userInput);
                }
                break;
            }
            System.out.println(line);
        }
    }
}