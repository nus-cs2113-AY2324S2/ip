import ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Noob {
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.printWelcomeMessage();
        ui.startConversation();
        ui.printGoodbyeMessage();
    }
}
