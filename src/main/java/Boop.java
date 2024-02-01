import java.util.Scanner;

public class Boop {
    public static String[] lst = new String[100];
    public static int count = 0;

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
                break;
            } else if (curCommand.equals("list")){
                printLst();
            } else {
                lst[count] = curCommand;
                count += 1;
                System.out.println("added: " + curCommand);

            }
            System.out.println("____________________________________________________________");
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
        //System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printLst() {
        for(int i = 0; i < count; i+= 1) {
            System.out.println("" + (i+1) + ". " + lst[i]);
        }

    }
}
