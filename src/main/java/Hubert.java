import java.util.Scanner;

public class Hubert {

    private static int markIndex(String line, int num) {
        String markIndexChar = line.substring(num, line.length());
        return Integer.parseInt(markIndexChar) - 1;
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

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
                System.out.println("added: " + line);
                tasks[indexTask] = new Task(line);
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