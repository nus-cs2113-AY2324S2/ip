import bossman.BossMan;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        BossMan chatBot = new BossMan();
        chatBot.greetUser();
        chatBot.startChat();
        chatBot.endChat();
    }
}
