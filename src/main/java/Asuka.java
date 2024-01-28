import java.util.Scanner;

public class Asuka {
    public static void main(String[] args) {

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Asuka\nWhat can I do for you?");
        System.out.println("____________________________________________________________");
        Scanner myObj = new Scanner(System.in);
        String command = myObj.nextLine();
        while (!command.equals("bye")) {
            System.out.println("____________________________________________________________");
            System.out.println(command);
            System.out.println("____________________________________________________________");
            command = myObj.nextLine();
        };
        myObj.close();
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
