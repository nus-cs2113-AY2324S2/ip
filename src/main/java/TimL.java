import java.util.Scanner;
import java.lang.String;

public class TimL {
    public static void respondToRandomText(String text){
        System.out.println("    ____________________________________________________________\n" +
                "     " + text + "\n" +
                "    ____________________________________________________________\n");
    }
    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm TimL\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");
        String response;
        Scanner in = new Scanner(System.in);
        response = in.nextLine();
        while (!response.equals("bye")){
            respondToRandomText(response);
            in = new Scanner(System.in);
            response = in.nextLine();
        }
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n");
    }
}
