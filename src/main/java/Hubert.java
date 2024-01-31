import java.util.Scanner;

public class Hubert {
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
        String exitWord = "bye";
        String displayList = "list";
        Scanner in = new Scanner(System.in);
        String line;
        line = in.nextLine();

        //store in array
        String[] tasks = new String[100];
        int indexTask = 0;

        //check for exit word
        while (!line.equalsIgnoreCase(exitWord)) {
            //print list if called
            if (line.equalsIgnoreCase(displayList)) {
                for (int i = 0; i < indexTask; i++) {
                    System.out.println((i+1) + ". " + tasks[i]);
                }
            } else {
                System.out.println("added: " + line);
                tasks[indexTask] = line;
                indexTask++;
            }
            System.out.println(horizontal);
            line = in.nextLine();
        }

        //exit
        if (line.equalsIgnoreCase(exitWord)) {
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(horizontal);
        }
    }
}