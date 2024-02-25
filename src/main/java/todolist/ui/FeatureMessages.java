package todolist.ui;

public class FeatureMessages {
    public static void featureIntroMessage() {
        System.out.println("[list] Show your Todolist tasks");
        System.out.println("[mark X] Mark number X task as Done");
        System.out.println("[unmark X] Mark number X task as UnDone");
        System.out.println("[delete X] Delete number X task");
        System.out.println("[todo XXX] Add a todo task");
        System.out.println("[deadline XXX /by XXX] Add a deadline task");
        System.out.println("[event XXX /from XXX /to XXX] Add a Event task");
        System.out.println("[help] Show commands");
        System.out.println("[quit] Return to main menu");
        System.out.println("Where XXX Stands for any string (except space character)");
        messageDivider();
        System.out.println("Please enter a command:");
    }

    public static void messageDivider() {
        System.out.println("------------------------------------------");
    }
    public static void exitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
