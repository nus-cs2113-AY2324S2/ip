import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] line = new String[100];
        Scanner in = new Scanner(System.in);
        System.out.println("Hello! I'm Bobby\n" + "What can I do for you?");

        for (int i = 1; i < 101; i += 1) {
            line[i] = in.nextLine();
            if (!line[i].equals("bye") && !line[i].equals("list")) {
                System.out.println("added: " + line[i]);
            } else {
                if (line[i].equals("list")) {
                    for (int j = 1; j < i; j += 1) {
                        System.out.println(j + ". " + line[j]);
                    }
                    i -= 1;
                } else {
                    if (line[i].equals("bye")) {
                        break;
                    }
                }
            }
        }

        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
