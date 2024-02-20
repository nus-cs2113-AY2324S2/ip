package duke;

/** Universal print functions used by most Classes */
public class print {
    /**
     * Prints line of 30 underscores.
     */
    public static void printLine(){
        int lineUnderscore = 30;
        for(int i = 0; i < lineUnderscore; i++){
            System.out.print("_");
        }
        System.out.println();
    }

    /**
     * Prints line, followed by message, followed by line.
     *
     * @param message Message to be printed.
     */
    public static void printMessage(String message){
        printLine();
        System.out.println(message);
        printLine();
    }
}
