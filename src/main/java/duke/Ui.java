package duke;

import static duke.print.printMessage;

/** Universal print functions used by most Classes */
public class Ui {
    public static final String chatbotName = "Noriaki";

    /**
     * Prints greeting.
     */
    public void greet(){
        String greetMessage = "Hello! I'm " + chatbotName + "\nWhat can I do for you?";
        String logo =
                " _______               .__        __   .__ \n" +
                        " \\      \\   ___________|__|____  |  | _|__|\n" +
                        " /   |   \\ /  _ \\_  __ \\  \\__  \\ |  |/ /  |\n" +
                        "/    |    (  <_> )  | \\/  |/ __ \\|    <|  |\n" +
                        "\\____|__  /\\____/|__|  |__(____  /__|_ \\__|\n" +
                        "        \\/                     \\/     \\/   \n";

        System.out.println("Hello from\n" + logo);
        printMessage(greetMessage);
    }

    /**
     * Prints goodbye message.
     */
    public void goodbye(){
        String goodbyeMessage = "Bye! Hope to see you again soon! MEGANE!!";

        printMessage(goodbyeMessage);
    }
}
