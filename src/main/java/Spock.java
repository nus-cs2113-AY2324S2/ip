import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class Spock {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Spock");
        Scanner myObj = new Scanner(System.in);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        String input = myObj.nextLine();
        List<Task> list = new ArrayList<>();
        Task reply = new Task(input, false, 1);



        for(int i = 1; !input.equals("bye"); input = myObj.nextLine()) {

            System.out.println("____________________________________________________________");

            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int k = 0; k < i-1; k++) {
                    System.out.println(list.get(k).index + ".[" + list.get(k).getStatusIcon() + "] " + list.get(k).description);
                }
            }
            else if (input.startsWith("mark")) { //TODO: disallow marking a task that is already marked and task_number outside of range
                int task_number = Integer.parseInt(input.replace("mark ", "")) - 1;
                System.out.println("Nice! I've marked this task as done:");
                list.get(task_number).markAsDone();
                System.out.println(".[" + list.get(task_number).getStatusIcon() + "]" + list.get(task_number).description);
            }
            else if (input.startsWith("unmark")) { //TODO: disallow unmarking a task that is already unmarked and task_number outside of range
                int task_number = Integer.parseInt(input.replace("unmark ", "")) - 1;
                System.out.println("Ok, I've marked this task as not done yet:");
                list.get(task_number).markAsUndone();
                System.out.println(".[" + list.get(task_number).getStatusIcon() + "]" + list.get(task_number).description);
            }
            else {
                reply = new Task(input, false, i);
                list.add(reply);
                System.out.println("added: " + reply.description);
                i++;
            }

            System.out.println("____________________________________________________________");

        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}







