import java.util.Scanner;

public class Baymax {
    public static void main(String[] args) {
        String name = "Baymax";
        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?\n\n");

        while(true) {
            String text;
            Scanner in = new Scanner(System.in);
            text = in.nextLine();

            if(text.equalsIgnoreCase("bye")) {
                break;
            }

            System.out.println(text);
        }

        System.out.println("Bye. Hope to see  you again soon!");
    }

}
