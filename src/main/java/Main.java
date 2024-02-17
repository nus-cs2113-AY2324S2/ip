import bossman.BossMan;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BossMan chatBot = new BossMan();
        chatBot.greetUser();
        chatBot.startChat();
        chatBot.endChat();
    }
}
