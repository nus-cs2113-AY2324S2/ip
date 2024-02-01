import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printsGreeting();
        mimicMessage();

    }
    private static void printsGreeting() {
        String greetingMessage = "Hello! I'm ThawBot!\nWhat can I do for you?\n";
        System.out.println(greetingMessage);
    }

    private static void printGoodByeMessage() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(goodbyeMessage);
    }

    private static void mimicMessage(){
        Task[] list = new Task[100];
        int currentIteration = 0;
        Scanner input = new Scanner(System.in);
        boolean canExit = false;
        while (!canExit) {
            String usersInput = input.nextLine();
            if (usersInput.equals("bye")) {
                canExit = true;
                printGoodByeMessage();
            }
            else if (usersInput.equals("list")) {
                for (int i = 0; i < currentIteration; i ++) {
                    System.out.println((i + 1) + ". " + list[i].getStatusIcon() + " " + list[i].description);
                }
            }
            else if (usersInput.startsWith("mark")) {
                System.out.println("Nice! I've marked this task as done:");
                int taskIndex = Integer.parseInt(usersInput.substring(5)) - 1;
                list[taskIndex].isDone = true;
                System.out.println(list[taskIndex].getStatusIcon() + " " + list[taskIndex].description);
            }
            else if (usersInput.startsWith("unmark")) {
                System.out.println("OK, I've marked this task as not done yet:");
                int taskIndex = Integer.parseInt(usersInput.substring(7)) - 1;
                list[taskIndex].isDone = false;
                System.out.println(list[taskIndex].getStatusIcon() + " " + list[taskIndex].description);
            }
            else {
                System.out.println("added: " + usersInput);
                list[currentIteration] = new Task(usersInput);
                currentIteration++;
            }

        }
    }
}
