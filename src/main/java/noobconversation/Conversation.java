package noobconversation;

import format.Formatting;
import task.Task;

import java.util.Scanner;

public class Conversation {

    protected Formatting f;

    public Conversation(){
        f = new Formatting();
    }

    public void communicate() {
        CommunicateCaseHandle caseHandle = new CommunicateCaseHandle();
        Scanner in = new Scanner(System.in);

        Task[] list = new Task[100];
        int index = 0;
        String line = in.nextLine().trim();

        while (!line.equalsIgnoreCase("bye")) {
            if (line.equalsIgnoreCase("list")) {
                caseHandle.listHandle(index, list);
            } else if (line.toLowerCase().startsWith("unmark")) {
                caseHandle.unmarkHandle(line, in, index, list);
            } else if (line.toLowerCase().startsWith("mark")) {
                caseHandle.markHandle(line, in, index, list);
            } else {
                if(caseHandle.taskHandle(line, in, list, index) == 66319){
                    index++;
                }
            }
            line = in.nextLine().trim();
        }
    }

    public void describeFunctionality() {
        System.out.println("\tI have the following features:");
        System.out.println("\t\t1. Echo and store three types of tasks: 'todo', 'deadline' and 'event'.");
        System.out.println("\t\t2. Type \"todo\" + \"taskContent\" to record a 'todo' task.");
        System.out.println("\t\t3. Type \"deadline\" + \"taskContent\" + \"/by\" + \"dates/times\" to" +
                " record a 'deadline' task.");
        System.out.println("\t\t4. Type \"event\" + \"taskContent\" + \"/from\" + \"dates/times\" + " +
                "\"/to\" + \"dates/times\" to record a 'event' task.");
        System.out.println("\t\t5. Type \"list\" to see what I have recorded.");
        System.out.println("\t\t6. Type \"mark\" + \"number\" to mark tasks as done.");
        System.out.println("\t\t7. Type \"unmark\" + \"number\" to mark tasks as not done.");
        System.out.println("\t\t8. Type \"bye\" to say goodbye to me.");
    }

    public void startConversationInitiateLogo() {
        String logo =
                " ___     _   ___   ___   __\n"
                        + "|   \\   | | |   | |   | |  |\n"
                        + "| |\\ \\  | | | | | | | | |  |__\n"
                        + "| | \\ \\_| | | | | | | | | ___ |\n"
                        + "|_|  \\____| |___| |___| |_____|\n";
        System.out.println("Hello from\n" + logo);
        f.dividingLine();
        System.out.println("\tHi!, I'm 'noobconversation.Noob'");
        System.out.println("\tWhat can I do for you?");
        f.dividingLine();
        describeFunctionality();
        f.dividingLine();

        communicate();
        f.dividingLine();
        System.out.println("\tBye. Hope to see you again soon!");
        f.dividingLine();
    }
}
