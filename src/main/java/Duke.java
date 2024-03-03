import java.io.FileNotFoundException;

public class Duke {
    public Duke() {
    }

    public static void main(String[] args) {
        try {
            CacheManager.bootFromCache();
        } catch (FileNotFoundException e1) {
            CacheManager.spawnCacheFile();
        }
        Formatter.printWelcomeMsg();
        while (CommandExecutor.isRunning) {
            try {
                CommandExecutor.beginListening();
                CommandExecutor.processInput();
                CommandExecutor.executeCommand();
            } catch (ProcessInputException e) {
                Formatter.printErrorExecutionFail();
            }
        }
    }
}
