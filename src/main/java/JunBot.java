import java.util.Scanner;
public class JunBot {

    public static void commandHandler(){
        String line;
        String divider = "____________________________________________________________\n";
        Scanner userInputScanner = new Scanner(System.in);
        line = userInputScanner.nextLine();

        while (!line.equals("bye")){
            System.out.println(divider + line+ "\n" + divider);
            line = userInputScanner.nextLine();
        }

    }
    public static void main(String[] args) {
        String divider = "____________________________________________________________\n";
        String greeting = "Hello! I'm JunBot\nWhat can I do for you?\n";
        String goodbye = "Bye. Hope to see you again soon!\n";
        String[] userInputsList = new String[100];

        System.out.println(divider + greeting + divider);
        commandHandler();
        System.out.println(divider + goodbye + divider);
    }
}
