import java.util.Scanner;

public class RoleyPoley {
    public static void main(String[] args) {
        String[] list = new String[100];
        greet();
        echo(list);
    }

    public static void createLine() {
        for (int i = 0; i < 20; i++) {
            System.out.print('-');
        }
        System.out.print('\n');
    }

    public static void greet() {
        System.out.println("Hello! I'm RoleyPoley \nWhat can I do for you today?");
        createLine();
    }

    public static void echo(String[] list) {
        String line;
        Scanner in = new Scanner(System.in);
        int counter = 1;
        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                createLine();
                break;
            }
            else if (line.equals("list")) {
                for (int j = 1; j < counter; j++) {
                    System.out.println("\t" + j + ". " + list[j]);
                }
                createLine();
            }
            else {
                System.out.println('\t' + line);
                createLine();
                list[counter] = line;
                counter++;
            }
        }
    }
}
