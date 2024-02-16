import java.util.Scanner;

public class Duke {
    protected static String from;
    protected static String to;


    public String description;

    public static void dealWithEvent(Task[] list, int index, String[] inputs, String line){
        int indexTo = line.indexOf("/to");
        int indexFrom = line.indexOf("/from");
        String from = line.substring(indexFrom + 6, indexTo - 1);
        String to = line.substring(indexTo + 4);
        String description = line.substring(6, indexFrom - 1);

        list[index] = new Event(description, from, to);
        System.out.println("Got it. I've added this task: ");
        System.out.println(list[index]);
    }
    public static void dealWithDeadline(Task[] list, int index, String line){
        int indexBy = line.indexOf("by");
        String by = line.substring(indexBy + 3);
        String description = line.substring(0, indexBy - 1);
        list[index] = new Deadline(description, by);
        System.out.println("Got it. I've added this task: ");
        System.out.println(list[index]);
    }

    public static void dealWithTodo(Task[] list, int index, String line){
        int indexSpace = line.indexOf(" ");
        String description = line.substring(indexSpace);
        list[index] = new Todo(description);
        System.out.println("Got it. I've added this task: ");
        System.out.println(list[index]);
    }
    public static void main(String[] args) {
        Task[] list = new Task[100];

        //greeting
        System.out.println("Hello! I'm Apple");
        System.out.println("What can I do for you?");

        int index = 0;//number of items in the list
        String line = " ";

        while (!line.equals("bye")) {
            Scanner input = new Scanner(System.in);
            line = input.nextLine();

            Task t = new Task(line);
            String[] inputs = line.split(" ");

            if (inputs[0].equals("mark")) {//mark as done
                int idx = Integer.parseInt(inputs[1]);
                list[idx - 1].markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("[" + list[idx - 1].getStatusIcon() + "]" + list[idx - 1].description);
            } else if (inputs[0].equals("unmark")) {//unmark done
                int idx = Integer.parseInt(inputs[1]);
                list[idx - 1].unmarkDone();
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println("[" + list[idx - 1].getStatusIcon() + "]" + list[idx - 1].description);
            } else if (line.equals("list")) {//lists items
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < index; i++) {
                    //System.out.println((i + 1) + ". [" + list[i].getStatusIcon() + "]" + list[i].description);
                    System.out.println((i + 1) + ". " + list[i]);
                }
            } else if (line.equals("bye")) {//exit chat
                break;
            } else {//add items

                if (inputs[0].equals("event")) {
                    dealWithEvent(list, index, inputs, line);

                } else if (inputs[0].equals("deadline")){
                    dealWithDeadline(list, index, line);
                } else if (inputs[0].equals("todo")){
                    dealWithTodo(list, index, line);
                }
                System.out.println("Now you have " + (index + 1) + " tasks in the list.");
                //System.out.println(from);
                //System.out.println(to);

                index++;
            }



        }
        System.out.println("Bye. Hope to see you again soon!");
    }

}
