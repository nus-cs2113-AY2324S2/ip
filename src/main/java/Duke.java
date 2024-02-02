import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("I'm Ekud! What can I do for you?");

        String userInput;
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();

        while(!userInput.equals("bye")){
            System.out.println(userInput);
            userInput = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon.");
    }
}
