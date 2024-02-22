import java.io.IOException;
import java.util.ArrayList;

public class Jarvas {
    public static void main(String[] args) {
        ArrayList<Task> tasks = CommandHandling.initialiseBot();
        try {
            CommandHandling.processInput(tasks);
        } catch (IOException e) {
            throw new CustomException(Reply.SAVE_ERROR + e.getMessage());
        } catch (CustomException e) {
            Reply.printException(e);
        } finally {
            CommandHandling.terminateBot(tasks);
        }
    }
}
