import java.util.Scanner;

public class Duke {
    final static String appName = "mimichat";

    public static void startupSequence(){
        System.out.println("-------------------------------------------");
        System.out.println("Hello! I'm " + appName);
        System.out.println("What can I do for you?");
        System.out.println("-------------------------------------------");
    }

    public static void customPrint(String input){
        System.out.println("-------------------------------------------");
        System.out.println(input);
        System.out.println("-------------------------------------------");

    }
    public static void main(String[] args) {

        // Initial welcome message
        startupSequence();

        while(true){
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("bye")){
                customPrint("Bye. Hope to see you again soon!");
                break;
            }
            customPrint(input);
        }

    }
}
