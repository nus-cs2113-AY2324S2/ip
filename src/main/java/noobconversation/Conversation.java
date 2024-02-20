package noobconversation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import format.Formatter;
import task.Task;
import memory.FileAccess;
import java.util.Scanner;
import static constant.NormalConstant.CORRECT_TASK_CREATION;


public class Conversation {

    protected Formatter formatter;

    public Conversation() {
        formatter = new Formatter();
    }

    public void communicate() {
        CommunicateCaseHandler caseHandle = new CommunicateCaseHandler();
        Scanner in = new Scanner(System.in);
        FileAccess fileAccess = new FileAccess();

        ArrayList<Task> lists = new ArrayList<>();
        try {
            fileAccess.readFile(lists);
        } catch (FileNotFoundException e) {
            System.out.println("Can not find your file!!!" + e.getMessage());
        }
        String line = in.nextLine().trim();

        while (!line.equalsIgnoreCase("bye")) {
            if (line.equalsIgnoreCase("list")) {
                caseHandle.printList(lists);
            } else if (line.toLowerCase().startsWith("unmark")) {
                caseHandle.handleTotal(line, lists, "unmark");
            }else if (line.toLowerCase().startsWith("delete")){
                 caseHandle.handleTotal(line, lists, "delete");
            }else if (line.toLowerCase().startsWith("mark")) {
                 caseHandle.handleTotal(line, lists, "mark");
            } else {
                if(caseHandle.handleTask(line, lists) == CORRECT_TASK_CREATION) {
                    try {
                        fileAccess.saveTask(lists.get(lists.size() - 1));
                    } catch (IOException e) {
                        System.out.println("Can not save your Task!!!" + e.getMessage());
                    }
                }
            }
            line = in.nextLine().trim();
        }

    }

    public void printWelcomeMessage() {

        System.out.println("Hello from\n" + formatter.generateLogo());
        formatter.printDividingLine();
        System.out.println("\tHi!, I'm 'Noob'");
        System.out.println("\tWhat can I do for you?");
        formatter.printDividingLine();
        formatter.printFunctionality();
        formatter.printDividingLine();
    }

    public void printGoodbyeMessage() {
        formatter.printDividingLine();
        System.out.println("\tBye. Hope to see you again soon!");
        formatter.printDividingLine();
    }

    public void startConversation(){
        communicate();
    }
}
