package ip.main;

import java.util.Scanner;

public class Ui {
    private Scanner in = new Scanner(System.in);

    private final static String logo =
            "  ____   _   _      __     ______    _       _  _____\n" +
            " / ___| | | | |    /  \\    |  _  \\  | |     | ||  ___|\n" +
            "| |     | |_| |   / /\\ \\   | |_| /  | |     | || |___\n" +
            "| |     |  _  |  / /__\\ \\  |  __ \\  | |     | ||  ___|\n" +
            "| |___  | | | | / ______ \\ | |  \\ \\ | |____ | || |___\n" +
            " \\____| |_| |_|/_/      \\_\\|_|   \\_\\|______||_||_____|\n";

    public void introduce() {
        printWithoutLeadingSpace("Hello! I'm Charlie!\n" + logo);
        printWithoutLeadingSpace("What can I do for you?");
    }

    public void printWithoutLeadingSpace(String s) {
        System.out.println(s);
    }

    public void print(String s) {
        System.out.println("     " + s);
    }

    public String getInput() {
        return in.nextLine().trim();
    }
}
