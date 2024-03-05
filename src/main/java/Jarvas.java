import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the Jarvas chatbot.
 */
public class Jarvas {
    /**
     * Executes the Jarvas bot. The full procedure of initialising, processing and terminating is executed.
     *
     * @param args Command-line arguments from the user.
     * @throws CustomException If an error occurs during the execution of the command.
     */
    public static void main(String[] args) {
        ArrayList<Task> tasks = CommandHandling.initialiseBot();
        try {
            CommandHandling.processInput(tasks);
        } catch (IOException e) {
            throw new CustomException(Constant.SAVE_ERROR + e.getMessage());
        } catch (CustomException e) {
            Reply.printException(e);
        } finally {
            CommandHandling.terminateBot(tasks);
        }
    }
}
