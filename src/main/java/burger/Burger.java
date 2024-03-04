package burger;

import static burger.Parser.Parser.parseUserInput;
import static burger.UI.Utilities.goodbye;
import static burger.UI.Utilities.welcomeMessage;

public class Burger {

    public static final String CHATBOT_NAME = "Burger";

    public static void main(String[] args) {
        welcomeMessage(CHATBOT_NAME);
        parseUserInput();
        goodbye();
    }
}