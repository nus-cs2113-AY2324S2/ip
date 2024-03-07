package anonbot.data;

import anonbot.exception.InitialisationException;

import java.io.File;

public class AnonBotFile {
    public static final String DEFAULT_DIRECTORY = "data";
    public static final String FILE_NAME = "data/tasklist.txt";

    public static void initialiseDefaultDirectory() throws InitialisationException {
        try {
            File f = new File(DEFAULT_DIRECTORY);
            f.mkdirs();
        } catch (NullPointerException e) {
            throw new InitialisationException("[Critical] Pathname Argument is null");
        } catch (SecurityException e) {
            throw new InitialisationException("[Critical] Filesystem Security Manager Permission error.");
        } catch (Exception e) {
            // This function is critical to ensure data can be written and saved
            // Hence any potential uncaught exception must be highlighted in one way or another.
            e.printStackTrace();
            throw new InitialisationException("[Critical] Unknown exception caught during initialisation.");
        }
    }
}
