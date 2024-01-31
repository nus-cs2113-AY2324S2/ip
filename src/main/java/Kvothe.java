import java.util.Scanner;

public class Kvothe {

    private static String[] textStorage = new String[100];
    private static int textStorageIndex = 0;

    public static void main(String[] args) {
        String greeting = "Hello! I'm Kvothe.\n"
                + "\t\tWhat can I do for you?\n";

        String bye = "Bye. Hope to see you again soon!";

        echo(greeting);

        interact();

        echo(bye);
    }


    private static void echo(String line, boolean isTop, boolean isBottom) {
        if (isTop) {
            System.out.println("\t\t____________________________________________________________");
        }

        System.out.println("\t\t" + line);

        if (isBottom) {
            System.out.println("\t\t____________________________________________________________");
        }

    }

    private static void echo(String line) {
        System.out.println("\t\t____________________________________________________________");
        System.out.println("\t\t" + line);
        System.out.println("\t\t____________________________________________________________");

    }

    private static void add(String line) {
        textStorage[textStorageIndex] = line;
        textStorageIndex++;
    }

    private static void list() {
        for (int i = 0; i < textStorageIndex; i++) {
            echo(i + 1 + ". " + textStorage[i], i == 0, i == textStorageIndex - 1);
        }
    }

    public static void interact() {
        String stopWord = "bye";
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while (!line.equals(stopWord)) {
            switch (line) {
                case "list":
                    list();
                    break;
                default:
                    add(line);
                    echo("added: " + line);
                    break;
            }
            line = in.nextLine();
        }

    }


}