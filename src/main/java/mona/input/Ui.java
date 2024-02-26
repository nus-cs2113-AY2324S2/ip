package mona.input;

import java.util.Scanner;

public class Ui {
    protected Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public String getUserInput() {
        return in.nextLine();
    }
}
