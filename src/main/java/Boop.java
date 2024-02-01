import java.util.Scanner;

public class Boop {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm BOOP");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        Scanner in = new Scanner(System.in);
        String curCommand;
        while(true) {
            curCommand = in.nextLine();
            System.out.println("____________________________________________________________");
            if(curCommand.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else {
                System.out.println(curCommand);
                System.out.println("____________________________________________________________");

            }
        }
        //System.out.println("Bye. Hope to see you again soon!");
    }
}
