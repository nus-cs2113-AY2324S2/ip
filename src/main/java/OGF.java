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


        String[] list = new String[100];
        int numItems = 0;
        Scanner in = new Scanner(System.in);

        while (true) {
            String input = in.nextLine();
            switch (input.split(" ")[0]) {
                case ("bye"):
                    System.out.println("Bye bye now!");
                    return;
                case ("list"):
                    System.out.println("Here are your tasks for today: ");
                    for (int i = 0; i < numItems; i++){
                        System.out.println(i+1 + ". " + list[i]);
                    }
                    break;
                default:
                    list[numItems] = input;
                    numItems++;
                    System.out.println("Added: " + input);
                    System.out.println(("____________________________________________________________"));
                    break;
            }
        }

    }
}
