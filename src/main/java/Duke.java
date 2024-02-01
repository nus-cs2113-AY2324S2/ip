import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printWelcomeMessage();

        while (true){
            String userInput = scanner.nextLine();
            printLineSeparator();

            if (userInput.equals("bye")){
                printGoodbyeMessage();
                break;
            }

            echoUserInput(userInput);
        }

        scanner.close();
    }
    private static void printWelcomeMessage(){
        printLineSeparator();
        System.out.println("Hello! I'm Byte, your friendly chat assistant!");
        System.out.println("What can I do for you?");
        printLineSeparator();
    }
    private static void printGoodbyeMessage(){
        System.out.println("Bye. Hope to see you again soon!");
        printLineSeparator();
    }
    private static void echoUserInput(String userInput){
        System.out.println(userInput);
        printLineSeparator();
    }
    private static void printLineSeparator(){
        System.out.println("____________________________________________________________");
    }
}


