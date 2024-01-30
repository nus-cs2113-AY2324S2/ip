import java.util.Scanner;
public class JigaChat {
    public static void readCommand() {
        String command;
        Scanner in = new Scanner(System.in);
        command =in.nextLine();
        if (command.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
        else {
            System.out.println(command);
            readCommand();
        }

    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm JigaChat");
        System.out.println("What can I do for you?");
        readCommand();
        }
    }
