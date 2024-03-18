package huan.main;

/**
 * Main Class
 */
public class Huan {
    /**
     * Main function
     * @param args unused
     */
    public static void main(String[] args) {
        UI.displayWelcomeMessage();

        Storage.readFile();

        Parser.parseCommands();
    }

}
