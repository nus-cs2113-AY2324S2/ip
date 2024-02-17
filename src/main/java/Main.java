import bossman.BossMan;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            BossMan chatBot = new BossMan();
            chatBot.greetUser();
            chatBot.startChat();
            chatBot.endChat();
        } catch (IOException e) {
            System.out.println("Something went wrong with your text file storage, try again");
        }
    }
}
