import java.util.Scanner;

public class Duke {
    public static void printList(String[] list) {
        for (int j = 0; j < list.length; j++) {
            if (list[j] == null) {
                System.out.println();
                return;
            }
            System.out.println(Integer.toString(j+1) + ": " + list[j]);
        }
    }

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        String[] list = new String[100];

        System.out.println("Hello! I'm FredBot.\nWhat can I do for you?\n");

        line = in.nextLine();
        int i = 0;
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                printList(list);
            }
            else {
                list[i] = line;
                i++;
                System.out.println("added: " + line + "\n");
            }
            line = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
