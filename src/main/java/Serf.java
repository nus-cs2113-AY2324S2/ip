import java.util.Scanner;

public class Serf {
    public static void main(String[] args) {
        String VERTICALLINES = "    ____________________________________________________________";
        String[] taskList = new String[100];
        int taskCounter = 0;
        String chatBotName = "Serf";
        String GREETINGMESSAGE = VERTICALLINES
                + "\n"
                + "     Good day my lord! I'm " + chatBotName + "\n"
                + "     How shall I serve thee?\n"
                + VERTICALLINES;
        String FAREWELLMESSAGE = VERTICALLINES
                + "\n"
                + "     Farewell my lord. \n"
                + VERTICALLINES;
        System.out.println(GREETINGMESSAGE);
        Scanner requestedMessage = new Scanner(System.in);
        String receivedMessage = requestedMessage.nextLine();

        while(!receivedMessage.equals("bye")) {
            if (receivedMessage.equals("list") && taskCounter > 0) {
                System.out.println(VERTICALLINES);
                for (int i = 0; i < taskCounter; i += 1) {
                    System.out.println("     " + (i + 1) + ". " + taskList[i] + " ");
                }
                System.out.println(VERTICALLINES);
            }
            else if (receivedMessage.equals("list") && taskCounter == 0){
                System.out.println(VERTICALLINES);
                System.out.println("     " + "list is empty");
                System.out.println(VERTICALLINES);
                receivedMessage = requestedMessage.nextLine();
                continue;
            }
            else {
                taskList[taskCounter] = receivedMessage;
                taskCounter += 1;
                System.out.println(VERTICALLINES);
                System.out.println("     " + "added: " + receivedMessage);
                System.out.println(VERTICALLINES);
            }
            receivedMessage = requestedMessage.nextLine();
        }
        System.out.println(FAREWELLMESSAGE);
    }
}