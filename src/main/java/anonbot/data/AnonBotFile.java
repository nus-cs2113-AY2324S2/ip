package anonbot.data;

import java.io.File;

public class AnonBotFile {
    public static final String DIRECTORY = "data";
    public static final String FILE_NAME = "data/tasklist3.txt";

    public static void initialiseDefaultDirectory() {
        File f = new File(DIRECTORY);
        f.mkdirs();
    }
}
