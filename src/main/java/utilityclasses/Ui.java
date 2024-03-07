package utilityclasses;
public class Ui {

    //Method to print a line of _ characters
    public static void printLine() {
        System.out.println("_".repeat(55));
    }

    //Method to exit Dross bot
    public static void printGoodbyeMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    //Opening message for Dross
    public static void printWelcomeMessage(){
        printLine();
        System.out.println("Hello! I'm Dross");
        System.out.println("What can I do for you?");
        printLine();
    }

}
