import java.util.Scanner;

public class Dobby {

    public static void echoCommand() {
        while(true) {
            String command;
            Scanner in = new Scanner(System.in);
            command = in.nextLine();
            if (command.toLowerCase().equals("bye")) {
                System.out.println("~~~~~~~~~~~~~~~~\nDobby say's BYE!");
                break;
            }
            System.out.println("~~~~~~~~~~~~~~~~\n" + command);
        }
    }

    public static void main(String[] args) {
        String logo = " _____        _     _           \n"
                + "|  __ \\      | |   | |\n"
                + "| |  | | ___ | |__ | |__  _   _ \n"
                + "| |  | |/ _ \\| '_ \\| '_ \\| | | |\n"
                + "| |__| | |_| | |_) | |_) | |_| |\n"
                + "|_____/ \\___/|_.__/|_.__/ \\__, |\n"
                + "                           __/ |\n"
                + "                          |___/ \n";
        System.out.println(logo);
        System.out.println("Dobby say's Hello!\nHow can Dobby help?\n");
        echoCommand();
    }



}
