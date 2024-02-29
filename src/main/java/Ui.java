import java.util.Scanner;
public class Ui {
    private static final String LINE = "____________________________________________________________";

    private Scanner in = new Scanner(System.in);

    private final static String logo =
                    "░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓███████▓▒░▒▓████████▓▒░ \n" +
                    "░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░     \n" +
                    "░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░     \n" +
                    "░▒▓███████▓▒░░▒▓████████▓▒░▒▓███████▓▒░  ░▒▓█▓▒░     \n" +
                    "░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░     \n" +
                    "░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░     \n" +
                    "░▒▓███████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░  ";

    public void greetUser() {
        System.out.println(logo + "\nHello! I'm Bartholomew, but you can call me Bart for short :)");
        System.out.println("What can I do for you?\nType 'help' for a list of available commands!\n" + LINE);
    }

    public void println(String s) {
        System.out.println(s);
    }
    public String getInput() {
        return in.nextLine().trim();
    }
}
