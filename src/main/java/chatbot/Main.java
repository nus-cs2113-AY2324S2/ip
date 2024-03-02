package chatbot;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Chatbot horizon = new Chatbot();
        horizon.initiate();
        horizon.run();
        try {
            horizon.exit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
