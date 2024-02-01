import java.util.Scanner;

public class Serf {
    public static void main(String[] args) {
        String chatBotName = "Serf";
        String greetingMessage = "    ____________________________________________________________\n"
                + "     Good day my lord! I'm " + chatBotName + "\n"
                + "     How shall I serve thee?\n"
                + "    ____________________________________________________________\n";
        String farewellMessage = "    ____________________________________________________________\n"
                + "     Farewell my lord. \n"
                + "    ____________________________________________________________\n";
        System.out.println(greetingMessage);
        Scanner requestedMessage = new Scanner(System.in);
        String receivedMessage = requestedMessage.nextLine();
        //adds vertical lines on top and below word
        String paddedReceivedMessage = "    ____________________________________________________________\n"
                + "    "
                + " "
                + receivedMessage
                + "\n"
                + "    ____________________________________________________________\n";
        while(!receivedMessage.equals("bye")) {
            System.out.println(paddedReceivedMessage);
            receivedMessage = requestedMessage.nextLine();
            paddedReceivedMessage = "    ____________________________________________________________\n"
                    + "    "
                    + " "
                    + receivedMessage
                    + "\n"
                    + "    ____________________________________________________________\n";
        }
        System.out.println(farewellMessage);
    }
}