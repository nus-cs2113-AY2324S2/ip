import java.util.Scanner;
public class JingHao {
    public static void main(String[] args) {
        String divider = "____________________________________________________________";
        String name = "JingHao";
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println(divider);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(divider);

        boolean loop = true;
        while (loop) {
            // Reads user input
            line = in.nextLine();
            System.out.println(divider);

            // Check if the user wants to exit
            if (!line.equalsIgnoreCase("bye")) {
                System.out.println(line);
                System.out.println(divider);
            }
            else{
                loop = false;
            }


        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(divider);
    }
}
