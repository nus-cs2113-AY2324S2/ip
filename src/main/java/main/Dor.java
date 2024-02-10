package main;

import logic.UserInterface;

public class Dor {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Dor");
        System.out.println("What can I do for you?");
        startListening();
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void startListening() {
        UserInterface UI = new UserInterface();
        UI.processInput();
    }
}
