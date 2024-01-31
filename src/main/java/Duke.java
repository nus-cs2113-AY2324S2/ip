public class Duke {
    public static void main(String[] args) {
        String logo =
                " ___     _   ___   ___   __\n"
              + "|   \\   | | |   | |   | |  |\n"
              + "| |\\ \\  | | | | | | | | |  |__\n"
              + "| | \\ \\_| | | | | | | | | ___ |\n"
              + "|_|  \\____| |___| |___| |_____|\n"  ;
        System.out.println("Hello from\n" + logo);
        Conversation c = new Conversation();
        c.startConversation();
    }
}
