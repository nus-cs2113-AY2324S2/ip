
/**
 * The Ui class handles user interface interactions by displaying messages to the user.
 * It provides a welcome message upon instantiation and has methods to display instructions and a farewell message.
 */
public class Ui {

    private static final String asciiArt = """
         
         ___     ________   __
        / \\ \\   / /  _ \\ \\ / /
       / _ \\ \\ / /| |_) \\ V /
      / ___ \\ V / |  _ < | |
     /_/   \\_\\_/  |_| \\_\\|_|
    """;

    private static final String welcome = asciiArt +
            """
            
            HEY HEY YOU YOU! I COULD BE YOUR... personal task manager.
            """;

    private static final String instructions =
            """
                    ---------Follow the syntax below:---------------
                    - Todos: todo <taskDescription>
                    - Deadlines: deadline <taskDescription> /by <date> <time>
                    - Events: event <taskDescription> /from <date> /to <date>
                    - Plus point if you can spell my name right.
                    - End the program by saying 'thank you and bye'
                    --------------------------------------------------
            """;
    private static final String farewell = "Byeeeeee! Hope to see you again!";

    Ui() {
        System.out.println(welcome);
    }

    void printInstructions() {
        System.out.println(instructions);
    }

    void printFarewell() {
        System.out.println(farewell);
    }
}
