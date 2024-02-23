import java.util.ArrayList;
import java.util.Scanner;

public class Baymax {
    public static void main(String[] args) {
        String name = "Baymax";
        System.out.println("Hello! I'm " + name + ", your personal task manager." + System.lineSeparator()
                + "What can I do for you today?" + System.lineSeparator() + System.lineSeparator());

        ArrayList<Task> taskArrayList = new ArrayList<>();
        int num = 0;
        String text;

        while(true) {

            try {

                Scanner in = new Scanner(System.in);
                text = in.nextLine();
                // "bye" -- terminate
                if (text.equalsIgnoreCase("bye")) {
                    break;
                }

                // "mark" -- mark task
                else if (text.startsWith("mark")) {
                    int position = Integer.parseInt(text.substring("mark".length() + 1)) - 1;
                    taskArrayList.get(position).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[X] " + taskArrayList.get(position).description);
                }

                // "unmark" -- unmark task
                else if (text.startsWith("unmark")) {
                    int position = Integer.parseInt(text.substring("unmark".length() + 1)) - 1;
                    taskArrayList.get(position).isDone = false;
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[ ] " + taskArrayList.get(position).description);
                }

                // "tasks" -- displays tasks
                else if (text.equalsIgnoreCase("list")) {
                    for (int i = 0; i < 100; i++) {

                        if (taskArrayList.get(i) == null) {
                            break;
                        }
                        System.out.println(i + 1 + ". " + "[" + taskArrayList.get(i).type + "] "
                                + "[" + taskArrayList.get(i).getStatusIcon() + "] " + taskArrayList.get(i).description);
                    }
                }

                // ADD TASK
                else if (text.startsWith(("todo")) || text.startsWith("deadline") || text.startsWith("event")) {
                    Task.addTask(text, taskArrayList, num);
                    num++;
                }

                // DELETE TASK
                else if (text.startsWith("delete")) {
                    int deleteIndex = Integer.parseInt(text.substring("delete".length() + 1)) - 1;


                }

                // "{other words}" -- REJECT
                else {
                    System.out.println("OH NOOO! I don't know what that means.");
                }
            } catch (InvalidTodoSyntaxException e) {
                InvalidTodoSyntaxException.handleInvalidTodoSyntaxException();
            }
        }

        System.out.println("I hope you are satisfied with my service! Goodbye.");
    }

}
