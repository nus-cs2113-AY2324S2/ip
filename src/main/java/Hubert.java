import java.util.Scanner;

public class Hubert {

    private static int markIndex(String line, int num) {
        String markIndexChar = line.substring(num, line.length());
        return Integer.parseInt(markIndexChar) - 1;
    }

    private static Task addTask(String line) {
        Task task;
        int n = line.length();
        if (line.startsWith("todo")) {
            task = new Todo(line.substring(5, n));
        } else if (line.startsWith("deadline")) {
            String description = line.substring(9, n);
            String[] deadline = description.split(" /by ");
            task = new Deadline(deadline[0], deadline[1]);
        } else if (line.startsWith("event")) {
            String description = line.substring(6, n);
            String[] start = description.split(" /from ");
            String[] end = start[1].split(" /to ");
            task = new Event(start[0], end[0], end[1]);
        } else {
            task = new Task(line);
        }
        return task;
    }
    public static void main(String[] args) {
        //greetings
        String horizontal = "____________________________________________________________";
        String botName = "Hubert";
        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you?");
        System.out.println(horizontal);

        //echo
        //add task
        Scanner in = new Scanner(System.in);
        String line;
        line = in.nextLine();

        //store in array
        Task[] tasks = new Task[100];
        int indexTask = 0;
        int markIndexInt;

        //check for exit word
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < indexTask; i++) {
                    //when printing toString() method used automatically
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else if (line.startsWith("mark")) {
                markIndexInt = markIndex(line, 5);
                tasks[markIndexInt].isDone = true;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[markIndexInt]);
            } else if (line.startsWith("unmark")) {
                markIndexInt = markIndex(line, 7);
                tasks[markIndexInt].isDone = false;
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks[markIndexInt]);
            } else {
                tasks[indexTask] = addTask(line);
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[indexTask]);
                System.out.println("Now you have " + (indexTask + 1) + " tasks in the list.");
                indexTask++;
            }
            System.out.println(horizontal);
            line = in.nextLine();
        }

        //exit
        if (line.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(horizontal);
        }
    }
}