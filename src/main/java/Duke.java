import java.util.Scanner;

public class Duke {
//    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//    }
    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n" +
                "Hello! I'm Duck\n" +
                "What can I do for you?");

        String userInput;
        do {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            System.out.println(userInput);

        } while (!userInput.equals("bye"));

        System.out.println("____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }
}
