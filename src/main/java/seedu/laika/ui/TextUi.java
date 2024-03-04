package seedu.laika.ui;

import java.util.Scanner;

public class TextUi {

    public static final String PREFIX = "Laika: ";

    public static final String LOGO = " ^..^      /\n"
                                    + " /_/\\_____/\n"
                                    + "    /\\   /\\\n"
                                    + "   /  \\ /  \\\n";

    public TextUi(){}

    public void startMessage(){
        System.out.println(PREFIX + "Hi! My name is Laika!\n\n" + LOGO + PREFIX + "How can I help you?");
    }

    public String getUserCommand() {
        System.out.print("Command: ");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }


    public void showError(String message) {
        System.out.println(PREFIX + message);
    }

    public void endMessage() {
        System.out.println(PREFIX + "Bye! Have a nice day!");
    }
}
