import java.util.Scanner;
public class Duke {
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    public static void main(String[] args) {
        String[] list = new String[100];
        for (int i = 0; i < 100; i++) {
            list[i] = " ";
        }
        int index = 0;
        Scanner in = new Scanner(System.in);
        printLine();
        System.out.println("Hello! I'm Doraemon!");
        System.out.println("What can I do for you?");
        printLine();
        while (!list[index].equals("bye")) {
            list[index] = in.nextLine();
            if (list[index].equals("bye")) {
                break;
            }
            if (list[index].equals("list")) {
                int counter = 1;
                for (int j = 0; j < index; j++) {
                    System.out.print(counter + ". ");
                    System.out.println(list[j]);
                    counter++;
                }
                index--;
            } else {
                System.out.print("Added: ");
                System.out.println(list[index]);
            }
            printLine();
            index++;
        }
        System.out.println("Bye. Have a great day!");
        printLine();
    }
}

/*
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}

 */


