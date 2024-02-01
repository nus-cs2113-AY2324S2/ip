import java.util.Scanner;

public class Bobble {
    public static void main(String[] args) {
        start();
        Scanner input = new Scanner(System.in);
        String userCommand = input.nextLine();
        while (!userCommand.equals("bye")) {
            echo(userCommand);
            userCommand = input.nextLine();
        }
        goodbye();
    }

    //Greets user
    public static void start(){
        System.out.println("____________________________________________________________\n" +
                "Hello! I'm Bobble.\n" +
                "What can I do for you?");
    }

    //Exits
    public static void goodbye() {
        System.out.println("____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }

    //Echos commands entered by the user
    public static void echo(String message) {
        System.out.println("____________________________________________________________\n" +
                message +
                "\n____________________________________________________________\n");
    }
}
