package geepee.system;

public abstract class SystemMessage {
    
    /**
     * Returns a string representing a horizontal line.
     */
    public static String getHorizontalLine() {
        return "    ________________________________________________";
    }

    /**
     * Prints welcome message.
     */
    public static void printWelcomeMessage() {
        System.out.println(getHorizontalLine());
        System.out.println("    Hello! I'm GeePee, your friendly chatbot assistant!");
        System.out.println("    What can I do for you?");
        System.out.println(getHorizontalLine());
    }

    /**
     * Prints exit message.
     */
    public static void printExitMessage() {
        System.out.println(getHorizontalLine());
        System.out.println("    Bye! Hope to see you again soon!");
        System.out.println(getHorizontalLine());
    }

    /**
     * Prints error message for FileNotFound exceptions.
     */
    public static void printFileNotFoundMessage() {
        System.out.println(getHorizontalLine());
        System.out.println("    No file found! The initialised list will be empty.");
    }

    /**
     * Prints error message for IO exceptions.
     */
    public static void printIOExceptionMessage() {
        System.out.println(getHorizontalLine());
        System.out.println("    Unable to overwrite file!");
        System.out.println(getHorizontalLine());
    }
}
