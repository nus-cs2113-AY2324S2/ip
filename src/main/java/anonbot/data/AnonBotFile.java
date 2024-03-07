package anonbot.data;

import java.io.File;

import anonbot.exception.InitialisationException;

/**
 * A static class containing default configuration for reading and writing persistent data.
 */
public class AnonBotFile {
    private static final String DEFAULT_DIRECTORY = "data";
    private static final String DEFAULT_FILE_NAME = "data/tasklist.txt";

    protected static String getDefaultFileName() {
        return DEFAULT_FILE_NAME;
    }

    protected static String getDefaultDirectory() {
        return DEFAULT_DIRECTORY;
    }

    /**
     * Initialises the default directory to hold the save file if the directory doesn't already exist.
     *
     * @throws InitialisationException When any exception relating to directory creation occurs.
     */
    public static void initialiseDefaultDirectory() throws InitialisationException {
        try {
            File f = new File(getDefaultDirectory());
            f.mkdirs();
        } catch (NullPointerException e) {
            throw new InitialisationException("[Critical] Pathname Argument is null");
        } catch (SecurityException e) {
            throw new InitialisationException("[Critical] Filesystem Security Manager Permission error.");
        } catch (Exception e) {
            // This function is critical to ensure data can be written and saved
            // Hence any potential uncaught exceptions must be highlighted in one way or another.
            e.printStackTrace();
            throw new InitialisationException("[Critical] Unknown exception caught during initialisation.");
        }
    }
}
