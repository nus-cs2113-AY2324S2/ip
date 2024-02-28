package Gene;

import Gene.ui.Ui;

/**
 * Represents the Gene chatbot application
 * Gene is a chatbot that helps the user with task management.
 */
public class Gene {
    public static void main(String[] args) {
        Ui chatBot = new Ui();
        chatBot.startChat();
        chatBot.endChat();
    }
}