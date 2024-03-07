package lotes.ui;

public class UserInterface {

    public static final String indent = "    ";
    public static final String separator = System.lineSeparator();
    public static final String line = indent + "____________________________________________________";

    public static String logo = "  #        ####  ##### ######  ####" + separator
            + "                #       #    #   #   #      #" + separator
            + "                #       #    #   #   #####   ####" + separator
            + "                #       #    #   #   #           #" + separator
            + "                #######  ####    #   ######  ####";

    public static String greetingsMessage = line + separator
            + indent + "Hello! I'm" + logo + separator
            + indent + "What can I do for you?" + separator + line;

    public static String goodbyeMessage = line + separator
            + indent + "Bye. Hope to see you again soon!"
            + separator + line;

    public static String numberFormatException = "Please enter enter a number without other characters.";
    public static String nullPointerException = "Please enter enter a number within the list.";
    public static String indexOutOfBoundsException = "Please enter at least 2 arguments and within bounds.";

    public void showGreetingsMessage() {
        System.out.println(greetingsMessage);
    }

    public void showInitFailedMessage() {
        System.out.println("Initializing failed!" + separator + line);
    }

    public static void showGoodbyeMessage() {
        System.out.println(goodbyeMessage);
    }

    public static void showNumberFormatException() {
        System.out.println(numberFormatException);
    }

    public static void showNullPointerException() {
        System.out.println(nullPointerException);
    }

    public static void showIndexOutOfBoundsException() {
        System.out.println(indexOutOfBoundsException);
    }


}
