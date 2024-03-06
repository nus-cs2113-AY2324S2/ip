package huan.main;
public class Huan {

    public static void main(String[] args) {
        String botName = "Huan";
        UI.displayWelcomeMessage();

        Storage.readFile();

        Parser.parseCommands();
    }

}
