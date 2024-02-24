package PrintMessages;

import Exceptions.ThawException;
import Tasks.Task;

import java.util.ArrayList;

public class UI {
    public UI() {
    }

    public void printsGreeting() {
        String greetingMessage = "Hello! I'm ThawBot!\nWhat can I do for you?\n";
        System.out.println(greetingMessage);
    }

    public void printGoodByeMessage() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(goodbyeMessage);
    }

    public void printList(ArrayList<Task> task) {
        for (int i = 0; i < task.size(); i ++) {
            System.out.println((i + 1) + ". " + task.get(i).getStatusIcon());
        }
    }

    public void printAcknowledgementMessage(ArrayList<Task> task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.get(task.size() - 1).getStatusIcon());
        System.out.print("Now you have " + task.size() + " task in the list.");
    }

    public void handleError(ThawException e) {
        if (e.getMessage().startsWith("Empty command")) {
            System.out.println("OOPS!!! The description of a " + e.getMessage().substring(14) +" cannot be empty.");
        } else if (e.getMessage().equals("Invalid command")) {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (e.getMessage().equals("Invalid date time format")) {
            System.out.println("OOOOOPSZZZZ! I'm sorry, there may be an error with the date format in your input. \n" +
                    "Please ensure that the date and time format is in dd-MM-yy format + space + HHmm");
        }
    }
}
