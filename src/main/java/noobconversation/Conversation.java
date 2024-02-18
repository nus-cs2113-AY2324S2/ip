package noobconversation;

import java.util.ArrayList;

import format.Formatting;
import task.Task;

import java.util.Scanner;

public class Conversation {

    protected Formatting format;

    public Conversation() {
        format = new Formatting();
    }

    public void communicate() {
        CommunicateCaseHandle caseHandle = new CommunicateCaseHandle();
        Scanner in = new Scanner(System.in);

        ArrayList<Task> list = new ArrayList<>();
        String line = in.nextLine().trim();

        while (!line.equalsIgnoreCase("bye")) {
            if (line.equalsIgnoreCase("list")) {
                caseHandle.listHandle(list);
            } else if (line.toLowerCase().startsWith("unmark")) {
                caseHandle.totalHandle(line, list, "unmark");
            }else if (line.toLowerCase().startsWith("delete")){
                caseHandle.totalHandle(line, list, "delete");
            }else if (line.toLowerCase().startsWith("mark")) {
                caseHandle.totalHandle(line, list, "mark");
            } else {
                caseHandle.taskHandle(line, list);
            }
            line = in.nextLine().trim();
        }
    }

    public void startConversationInitiateLogo() {

        System.out.println("Hello from\n" + format.logo());
        format.dividingLine();
        System.out.println("\tHi!, I'm 'Noob'");
        System.out.println("\tWhat can I do for you?");
        format.dividingLine();
        format.describeFunctionality();
        format.dividingLine();

        communicate();
        format.dividingLine();
        System.out.println("\tBye. Hope to see you again soon!");
        format.dividingLine();
    }
}
