import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("I'm Ekud! What can I do for you?");

        String userInput;
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;
        userInput = in.nextLine();

        while(!userInput.equals("bye")){
            String[] words = userInput.split(" ");
            if(userInput.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < taskCount; i++){
                    System.out.println((i + 1) + "." + tasks[i]);
                }
            }
            else if(words[0].equals("mark")){
                int taskIndex = Integer.parseInt(words[1]) - 1;
                tasks[taskIndex].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[taskIndex]);
            }
            else if(words[0].equals("unmark")){
                int taskIndex = Integer.parseInt(words[1]) - 1;
                tasks[taskIndex].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks[taskIndex]);
            }
            else{
                tasks[taskCount] = new Task(userInput);
                System.out.println("added: " + tasks[taskCount]);
                taskCount++;
            }
            userInput = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon.");
    }
}
