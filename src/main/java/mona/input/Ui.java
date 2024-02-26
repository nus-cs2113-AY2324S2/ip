package mona.input;

import mona.output.ConsolePrint;

import java.util.Scanner;

public class Ui {
    protected Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
        ConsolePrint.greet();
    }

    public String getUserInput() {
        return in.nextLine();
    }
}
