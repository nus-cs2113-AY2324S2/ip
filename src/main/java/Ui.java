public class Ui {
    private static final String BOT_NAME = "Kobot";
    private static final String LOGO = "#########################################\n"
            + "##     _   __      _           _       ##\n"
            + "##    | | / /     | |         | |      ##\n"
            + "##    | |/ /  ___ | |__   ___ | |_     ##\n"
            + "##    |    \\ / _ \\| '_ \\ / _ \\| __|    ##\n"
            + "##    | |\\  \\ (_) | |_) | (_) | |_     ##\n"
            + "##    \\_| \\_/\\___/|_.__/ \\___/ \\__|    ##\n"
            + "##                                     ##\n"
            + "#########################################\n";

    public void printLineDivider() {
        System.out.println("-------------------------------------------");
    }
    public void printHelloMessage() {
        System.out.println(LOGO);
        System.out.println("Hello! I'm " + BOT_NAME + ". How may I assist you?");
    }

    public void printGoodbyeMessage() {
        System.out.println("Bye, hope to see you again!");
    }
}
