import java.util.Scanner;
public class Katleen {
    public static final String line = "____________________________________________________________";

    public static void main(String[] args) {
        System.out.println(line);
        System.out.println("Hello! I'm Katleen.");
        System.out.println("What can I do for you?");
        System.out.println(line);

        Task[] tasks = new Task[100];
        int count = 0;
        Scanner in = new Scanner(System.in);
        String text = in.nextLine();
        while (!text.equals("bye")) {
            switch (text) {
            case "list":
                System.out.println(line);
                for (int i = 0; i < count; i++) {
                    System.out.print(i+1);
                    System.out.println(". " + tasks[i].description);
                }
                System.out.println(line);
                break;
            default:
                System.out.println(line);
                System.out.print("added: ");
                System.out.println(text);
                Task task = new Task(text);
                tasks[count] = task;
                count++;
                System.out.println(line);
                break;
            }
            text = in.nextLine();
            if (text.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye, have a nice day!");
                System.out.println(line);
            }
        }

        return;
    }
}
