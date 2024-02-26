/**
 * The Sebastian class contains the main method to run the Sebastian program.
 * It displays a welcome message and initializes the user interface.
 */
public class Sebastian {
    /**
     * The main method to run the Sebastian program.
     * It displays a welcome message and initializes the user interface.
     *
     * @param args The command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        String logo = " ____       _               _   _\n" +
                "/ ___|  ___| |__   __ _ ___| |_(_) __ _ _ __\n" +
                "\\___ \\ / _ \\ '_ \\ / _` / __| __| |/ _` | '_ \\\n" +
                " ___) |  __/ |_) | (_| \\__ \\ |_| | (_| | | | |\n" +
                "|____/ \\___|_.__/ \\__,_|___/\\__|_|\\__,_|_| |_|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Sebastian, your humble butler\n" +
                "What can I do for you?");
        Ui myUserInterface = new Ui();
    }

}
