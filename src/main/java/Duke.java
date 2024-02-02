import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        String name = "Brad";
        System.out.println("Hello I am " + name + ".\n");
        System.out.println("How can I help you today?\n");
        Scanner userInput = new Scanner(System.in);
        List inputlist = new List();
        while (true) {
            String x = userInput.nextLine();
            boolean exit = x.equals("bye");
            boolean list = x.equals("list");
            boolean mark = x.startsWith("mark");
            boolean unmark = x.startsWith("unmark");
            if (exit) {
                break;
            } else if (list) {

                printOutput("Here are the tasks in your list:\n" + inputlist.getInputList());
            } else if (mark) {
                int taskNum = Integer.parseInt(x.split(" ")[1]);
                if (taskNum > inputlist.listSize()) {
                    System.out.println("Exceeded existing list size of: " + inputlist.listSize() + ". Please enter a valid number" + "\n");
                } else {
                    inputlist.markAsDone(taskNum, true);
                    String message = "Nice! I've marked this task as done: \n" + inputlist.getItemFromList(taskNum);
                    printOutput(message);
                }
            } else if (unmark) {
                int itemNo = Integer.parseInt(x.split(" ")[1]);
                inputlist.markAsDone(itemNo, false);
                String message = "OK, I've marked this task as not done yet: \n"
                        + inputlist.getItemFromList(itemNo);
                printOutput(message);
            } else {
                inputlist.setInputList(x);
                printOutput("added: " + x);
            }
        }
        printOutput("Bye. Hope to see you again soon!");
    }

    private static void printOutput(String message) {
        String separator = "____________________________________________________________";
        System.out.println(separator);
        System.out.println(message);
        System.out.println(separator);
    }
}