import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Brad";
        System.out.println("Hello I am " + name + ".\n");
        System.out.println("How can I help you today?\n");
        Scanner userInput = new Scanner(System.in);
        while (true)
        {
            String x = userInput.nextLine();
            boolean exit = x.equals("bye");
            if (exit)
            {
                break;
            }
            else
            {
                echoPrint(x);
            }
        }
        System.out.println("Bye. Hope to see you again!");

    }
    private static void echoPrint(String message)
    {
        String separator = "____________________________________________________________";
        System.out.println(separator);
        System.out.println(message);
        System.out.println(separator + ".\n");
    }
}