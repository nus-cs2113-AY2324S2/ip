package soot.manager;

import soot.Soot;

public class GreetingManager {
    public static void greetUser() {
        System.out.println("Hello! I'm Soot, your personal chatbot companion :)");
        System.out.println("What can I help you with?");
        Soot.drawLine();
    }
    public static void greetGoodbye() {
        System.out.println("Bye! Till the next time we meet...");
        Soot.drawLine();
    }
}
