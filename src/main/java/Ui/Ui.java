package Ui;

public class Ui {
    public void greet(String botName) {
        PrintText.printWithLinebreak("Hello! I'm " + botName + "\n" +
                "What can I do for you?");
    }

    public void bye() {
        PrintText.printWithLinebreak("Bye. Hope to see you again soon!");
    }
}
