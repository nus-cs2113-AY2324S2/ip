import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Chandler {

    public static final String LINE_DIVIDER = "____________________________________________________________";


    public static void main(String[] args) {

        System.out.println(LINE_DIVIDER);
        System.out.println("Hello! I'm Chandler. Your personal sarcastic task manager.");
        Scanner myObj = new Scanner(System.in);
        System.out.println("So, what can you do for me? :)");
        System.out.println(LINE_DIVIDER);

        String input = myObj.nextLine();
        List<Task> list = new ArrayList<>(); // No need new ArrayList<Circle>() because of type inference
        Task reply = new Task(input, false);

        for(int index = 1; !input.equals("bye"); input = myObj.nextLine()) {

            System.out.println(LINE_DIVIDER);
            String[] words = input.split("\\s+"); // Split by whitespace
            String inputCommand = words[0];


            switch (inputCommand) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int k = 0; k < index-1; k++) {
                        System.out.println(k+1 + "." + list.get(k).toString());
                    }
                    break;
                case "mark":
                    int task_number = Integer.parseInt(input.replace("mark ", "")) - 1;
                    System.out.println("Nice! I've marked this task as done:");
                    list.get(task_number).markAsDone();
                    System.out.println(list.get(task_number));
                    break;
                case "unmark":
                    int task_number2 = Integer.parseInt(input.replace("unmark ", "")) - 1;
                    System.out.println("Ok, I've marked this task as not done yet:");
                    list.get(task_number2).markAsUndone();
                    System.out.println(list.get(task_number2));
                    break;
                case "todo":
                    reply = new Task(input.replace("todo ", ""), false);
                    System.out.println("added: " + reply.description);
                    index++;
                    break;
                case "deadline":
                    input = input.replace("deadline ", "");
                    int indexBy = input.indexOf("/by");
                    String description = input.substring(0, indexBy);
                    String by = input.substring(indexBy + 4);
                    reply = new Deadline(description, by);
                    System.out.println(reply);
                    index++;
                    break;
                case "event":
                    input = input.replace("event ", "");
                    int indexFrom = input.indexOf("/from");
                    int indexTo = input.indexOf("/to");
                    String description2 = input.substring(0, indexFrom);
                    String from = input.substring(indexFrom + 6, indexTo);
                    String to = input.substring(indexTo + 4);
                    reply = new Event(description2, from, to);
                    System.out.println("added: " + reply.description);
                    index++;
                    break;
                default:
                    reply = new Task(input, false);
                    list.add(reply);
                    System.out.println("added: " + reply.description);
                    index++;
                    break;
            }
            System.out.println(LINE_DIVIDER);

        }

        System.out.println(LINE_DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_DIVIDER);
    }
}






