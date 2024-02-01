import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int itemsCount = 0;
        System.out.println("Hello! My name is Jeff.");
        System.out.println("What can I do for you?");
        boolean isChatting = true;
        while (isChatting){
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();

            switch (line){
            case "list":
                for (int i = 0; i < itemsCount; i += 1){
                    System.out.println((i +1) + ". " + tasks[i].getDescription());
                }
                break;

            case "bye":
                isChatting = false;
                break;


            default:
                tasks[itemsCount] = new Task(line);
                System.out.println("added: " + line);
                itemsCount += 1;

            }

        }

        System.out.println("Bye. Hope to see you again soon!");


    }
}
