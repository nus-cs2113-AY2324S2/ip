import java.util.Scanner;
public class Helpy {
    public static void greetUser() {
        String horizontalLine = "______________________\n";
        String name = "░▒█░▒█░▒█▀▀▀░▒█░░░░▒█▀▀█░▒█░░▒█\n" +
                "░▒█▀▀█░▒█▀▀▀░▒█░░░░▒█▄▄█░▒▀▄▄▄▀\n" +
                "░▒█░▒█░▒█▄▄▄░▒█▄▄█░▒█░░░░░░▒█░░\n";
        System.out.println(horizontalLine
                + "Greetings, I am\n" + name);
        System.out.println("How can I help you?\n" + horizontalLine);
    }
    public static void echoCommand(String command) {
        String horizontalLine = "______________________\n";
        System.out.print(horizontalLine);
        System.out.println(command);
        System.out.println(horizontalLine);
    }
    public static void goodbyeUser() {
        String horizontalLine = "______________________\n";
        System.out.print(horizontalLine);
        System.out.println("Goodbye, see you next time!");
        System.out.println(horizontalLine);
    }
    public static void main(String[] args) {
        greetUser();

        Scanner in = new Scanner(System.in);
        String command = "";
        while (!command.equals("bye")) {
            command = in.nextLine();
            if (command.equals("bye")) {
                goodbyeUser();
            } else {
                echoCommand(command);
            }
        }
    }
}
