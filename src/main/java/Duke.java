import java.util.Scanner;
public class Duke {
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    public static void main(String[] args) {
        String[] list = new String[100];
        String[] status = new String[100];
        //initializing the array
        for (int i = 0; i < 100; i++) {
            list[i] = " ";
            status[i] = " ";
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
                int counter = 1; //the numbering in the list
                for (int j = 0; j < index; j++) {
                    System.out.print(counter + ".[" + status[j] + "] ");
                    System.out.println(list[j]);
                    counter++;
                }
                index--;
            } else if (list[index].contains("mark")) {
                list[index] = list[index].substring(5);
                int numForStatus = Integer.parseInt(list[index]) - 1;
                status[numForStatus] = "X";
                System.out.println("Done and marked! Good job!");
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


