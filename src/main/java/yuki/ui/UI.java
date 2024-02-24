package yuki.ui;

import java.io.InputStream;
import java.util.Scanner;

public class UI {
    private final Scanner in;

    public UI() {
        this(System.in);
    }

    public UI (InputStream in) {
        this.in = new Scanner(in);
    }

    public String getUserInput() {
        System.out.println("Enter command: ");
        String fullInputLine = in.nextLine();

        System.out.println("[Command entered: " + fullInputLine + "]");;
        return fullInputLine;
    }
}
