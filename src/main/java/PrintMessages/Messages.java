package PrintMessages;

import Tasks.Task;

import java.util.ArrayList;

public class Messages {
    public static void printsGreeting() {
        String greetingMessage = "Hello! I'm ThawBot!\nWhat can I do for you?\n";
        System.out.println(greetingMessage);
    }

    public static void printGoodByeMessage() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(goodbyeMessage);
    }

    public static void printList(ArrayList<Task> task) {
        for (int i = 0; i < task.size(); i ++) {
            System.out.println((i + 1) + ". " + task.get(i).getStatusIcon());
        }
    }

    public static void printAcknowledgementMessage(ArrayList<Task> task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.get(task.size() - 1).getStatusIcon());
        System.out.print("Now you have " + task.size() + " task in the list.");
    }
}
