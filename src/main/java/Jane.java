import java.io.IOException;

/**
 * Represents the main class for the Jane chatbot.
 * Jane is a task management chatbot that allows users to interact with a list of tasks.
 * This class contains the main method that initializes and runs the Jane chatbot.
 */
public class Jane {
    /** File path that stores the task data. */
    public static final String FILE_PATH = "./data/jane.txt";

    /** Storage system that handles file operations. */
    public static Storage storage;

    /**
     * Initializes and runs the Jane chatbot.
     * It displays a logo, greeting message, and enters a loop to process user input until the "bye" command is entered.
     *
     * @param args Command-line arguments from user.
     * @throws IOException If error occurs during file loading or saving.
     * @throws JaneDataCorruptedException If file data is corruption
     */
    public static void main(String[] args) {
        String Logo = " _____    _____    ____ _    _____ \n"
                + "|____ |  |     |  |    | |  | ____|\n"
                + "    | |  |  |  |  | |  | |  | |___ \n"
                + " _  | |  |  _  |  | |  | |  |  ___|\n"
                + "| |_| |  | | | |  | |  | |  | |___ \n"
                + "|_____|  |_| |_|  |_| ___|  |_____|\n";
        String Separator = "__________________________________________________________________________________________\n";

        System.out.print(Separator + Logo + Separator);
        System.out.print(Message.GREET + Separator);

        try {
            // Initialize storage and load existing files
            storage = new Storage(FILE_PATH);
            storage.ensureDataFileExists();
            TaskList taskList = storage.loadTasksToFile();
            Ui ui = new Ui();

            while (!ui.input.equals("bye")) {
                // Process user input, display output, and save tasks to file
                try {
                    System.out.print(Separator);
                    ui.processInput(ui.input, taskList);
                    System.out.print(Separator);
                    storage.saveTasksToFile(taskList);
                } catch (JaneException e) {
                    System.out.println(e.getMessage());
                    System.out.print(Separator);
                }
                ui.nextInput();
            }

            // Save tasks to file before exiting
            try {
                storage.saveTasksToFile(taskList);
                System.out.print(Separator + Message.EXIT + Separator);
            } catch (IOException e) {
                System.out.println(Message.INEXISTENT_FILE);
            }
        } catch (IOException e) {
            System.out.println(Message.INEXISTENT_FILE);
        } catch (JaneDataCorruptedException e) {
            System.out.println(Message.CORRUPTED_FILE);
        }
    }
}