package ui;

import java.util.Scanner;

public class Ui {
    private static final String LINE_SEP = "____________________________________________________________";

    protected Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void greetUser() {
        System.out.println(LINE_SEP + "\nHello! I'm JingHao" );
        System.out.println("What can I do for you?\n" + LINE_SEP);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void farewell() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_SEP);
    }

    public void promptUser() {
        System.out.print("Input: ");
    }

    public void printMessage(String message) {
        System.out.println(message);
        System.out.println(LINE_SEP);
    }

    public void printDivider() {
        System.out.println(LINE_SEP);
    }

    public void printWithoutDivider(String input) {
        System.out.println(input);
    }

    public void printErrorMessage(String error) {
        System.out.println(error);
        System.out.println(LINE_SEP);
    }

    public void printTotalTask(int total){
        System.out.println("Now you have " + total + " tasks in the list.");
        System.out.println(LINE_SEP);
    }
}

