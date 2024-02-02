import java.util.Scanner;
public class OGF {
    public static void main(String[] args) {
        String logo = "\n" +
                " _____  _    _                   _____ ______ \n" +
                "|  _  || |  | |                 |  __ \\|  ___|\n" +
                "| | | || |_ | |__    ___  _ __  | |  \\/| |_   \n" +
                "| | | || __|| '_ \\  / _ \\| '__| | | __ |  _|  \n" +
                "\\ \\_/ /| |_ | | | ||  __/| |    | |_\\ \\| |    \n" +
                " \\___/  \\__||_| |_| \\___||_|     \\____/\\_|    \n" +
                "                                              \n" +
                "                                              \n";
        System.out.println("Welcome! I'm your \n" + logo + "Nice to meet you!");
        System.out.println("What can I do for you?");

        String input;
        while (true) {
            Scanner in = new Scanner(System.in);
            input = in.nextLine();
            if (!input.equals("bye")) {
                System.out.println("You said: " + input);
                System.out.println("____________________________________________________________");
                System.out.println("What can I do for you?");
            } else {
                System.out.println("Bye bye now!");
                break;
            }
        }

    }
}
