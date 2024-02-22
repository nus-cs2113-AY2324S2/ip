import java.io.IOException;

public class Jarvas {
    public static void main(String[] args) {
        CommandHandling.initialiseBot();
        try {
            CommandHandling.processInput();
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        } catch (CustomException e) {
            Reply.printException(e);
        } finally {
            CommandHandling.terminateBot();
        }
    }
}
