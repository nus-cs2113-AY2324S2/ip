package duke.ui;

import java.util.Scanner;

public class Ui {
    Scanner sc;
    static final String HORIZONTAL_LINE = "\t_____________________________________________________________________\n";

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    private String messageFormat(String message) {
        return HORIZONTAL_LINE + "\t " + message + System.lineSeparator() + HORIZONTAL_LINE;
    }

    public void printHi() {
        System.out.print(messageFormat("Awakening.... \n\t Hi! I'm Faiz!\n\t What can I do for you?"));
    }

    public void printBye() {
        System.out.print(messageFormat("Deformation.... \n\t Bye! Hope to see you again soon!"));
    }

    public String readCommand() {
        return this.sc.nextLine();
    }

    public void printMessage(String message) {
        System.out.print(messageFormat(message));
    }

    public void printError(String message) {
        System.out.print(messageFormat(message));
    }
}
