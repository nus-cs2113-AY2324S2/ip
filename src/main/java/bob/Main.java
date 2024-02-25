package bob;

import java.io.IOException;

public class Main {
    /**
     * Main method. Initializes the Bob Chat-Bot.
     *
     * @param args Arguments from standard input, if any.
     * @throws IOException If there is no read/write access to ./data/state.txt.
     */
    public static void main(String[] args) throws IOException {
        Bob bob = new Bob();
        bob.run();
    }
}
