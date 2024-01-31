import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n" +
                "Hello! I'm Duck\n" +
                "What can I do for you?\n" +
                "  _____  _    _  _____ _  __\n" +
                " |  __ \\| |  | |/ ____| |/ /\n" +
                " | |  | | |  | | |    | ' / \n" +
                " | |  | | |  | | |    |  <  \n" +
                " | |__| | |__| | |____| . \\ \n" +
                " |_____/ \\____/ \\_____|_|\\_\\");

        Task[] tasks = new Task[100]; //stores Tasks in array called tasks
        String userInput;
        int index = 0; //index of where the userInput is stored in texts
        do {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            if (userInput.equals("list")) {
                for (int i = 0; i < index; i++) {
                    String icon = tasks[i].getStatusIcon();
                    System.out.println(i+1 + ". ["  + icon + "] " + tasks[i].getDescription());
                }
                System.out.println("____________________________________________________________\n");
            } else if(userInput.startsWith("mark ")) {
                String[] split = userInput.split(" ");
                int number = Integer.parseInt(split[1]);
                if (number <= index) {
                    tasks[number - 1].setDone(true);
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[X] " + tasks[number-1].getDescription());
                    System.out.println("____________________________________________________________\n");
                } else {
                    System.out.println("Task does not exist yet!");
                }
            } else if (!userInput.equals("bye")) {
                tasks[index] = new Task(userInput);
                index++;
                System.out.println("____________________________________________________________");
                System.out.println("added: " + userInput);
                System.out.println("____________________________________________________________\n");
            }

        } while (!userInput.equals("bye"));

        System.out.println("____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }
}
