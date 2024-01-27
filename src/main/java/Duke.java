import java.util.Scanner;

public class Duke {
    final static String appName = "mimichat";

    public static void startupSequence() {
        System.out.println("-------------------------------------------");
        System.out.println("Hello! I'm " + appName);
        System.out.println("What can I do for you?");
        System.out.println("-------------------------------------------");
    }

    public static void customPrint(String input) {
        System.out.println("-------------------------------------------");
        System.out.println(input);
        System.out.println("-------------------------------------------");

    }

    public static void listTasks(String[] list, int numberOfTasks){
        System.out.println("-------------------------------------------");
        for(int i = 0; i < numberOfTasks; i++){
            System.out.println(Integer.toString(i+1) + ". " + list[i]);
        }
        System.out.println("-------------------------------------------");


    }

    public static void main(String[] args) {

        // Initial welcome message
        startupSequence();
        String[] taskList = new String[100];
        int numberOfTask = 0;
        boolean isRunning = true;
        while (isRunning) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            switch (input) {
            case "bye":
                customPrint("Bye. Hope to see you again soon!");
                isRunning = false;
                break;
            case "list":
                listTasks(taskList, numberOfTask);
                break;
            default:
                taskList[numberOfTask] = input;
                numberOfTask++;
                customPrint("added: " + input);
                break;
            }
        }

    }
}
