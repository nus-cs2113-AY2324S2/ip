import java.util.Scanner;
public class Duke {

    public static void main(String[] args) {
        String name = "Hirofumi";
        System.out.println("************************************************************");
        System.out.println(" Hello! I'm " + name);
        System.out.println(" What can I do for you?");
        System.out.println("************************************************************");
        String userInput = null;
        Scanner in = new Scanner(System.in);
        while (userInput == null || !(userInput.equals("bye"))) {
            userInput = in.nextLine();
            System.out.println(userInput);
        }
        System.out.println("************************************************************");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("************************************************************");
    }
}
