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
        String[] tasks = new String[100];
        int taskCount = 0;
        userInput = in.nextLine();

        while(!userInput.equals("bye")){
            if(userInput.equals("list")){
                for(int i = 0; i < taskCount; i++){
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            }
            else{
                tasks[taskCount] = userInput;
                System.out.println("added: " + tasks[taskCount]);
                taskCount++;
            }
            userInput = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon.");
    }
}
