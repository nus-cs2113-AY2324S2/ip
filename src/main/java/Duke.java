import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void echo() {
        boolean ifExit = false;
        Scanner in = new Scanner(System.in);
        String[] list = new String[100];
        int printCounter;
        int listIndex = 0;
        while (!ifExit) {
            String line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println("--------------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("--------------------------------------");
                ifExit = true;
            } else if (line.equals("list")) {
                printCounter = 1;
                System.out.println("--------------------------------------");
                for (String item: Arrays.copyOf(list, listIndex)) {
                    System.out.println(printCounter + ". " + item);
                    printCounter ++;
                }
                System.out.println("--------------------------------------");
            } else {
                System.out.println("--------------------------------------");
                System.out.println("added: " + line);
                System.out.println("--------------------------------------");
                list[listIndex] = line;
                listIndex ++;
            }
        }
    }
    public static void main(String[] args) {
        String logo 
                = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("--------------------------------------");
        System.out.println("Hello! I'm Steffy");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------------------");
        echo();
    }
}
