package UI;

public class ExceptionUI {
    public static void printInvalidCommand() {
        System.out.println("Please enter a valid command: todo, deadline or event");
    }

    public static void printInvalidDeleteIndex() {
        System.out.println("The delete command is invalid" + BackboneUI.newLine + BackboneUI.line);
    }

    public static void printInvalidEventMessage() {
        System.out.println("The event command is invalid" + BackboneUI.newLine + BackboneUI.line);
    }

    public static void printInvalidDeadlineMessage() {
        System.out.println("The deadline command is invalid" + BackboneUI.newLine + BackboneUI.line);
    }

    public static void printInvalidTodoMessage() {
        System.out.println("The todo command message is invalid" + BackboneUI.newLine + BackboneUI.line);
    }

    public static void printInvalidTaskIndex() {
        System.out.println("The task you are trying to edit does not exist!" + BackboneUI.newLine + BackboneUI.line);
    }
}
