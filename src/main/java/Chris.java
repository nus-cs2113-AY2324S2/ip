import java.util.Scanner;
public class Chris {
    public static void main(String[] args) {
        String logo = "      ___           ___           ___                       ___     \n" +
                "     /\\  \\         /\\__\\         /\\  \\          ___        /\\  \\    \n" +
                "    /::\\  \\       /:/  /        /::\\  \\        /\\  \\      /::\\  \\   \n" +
                "   /:/\\:\\  \\     /:/__/        /:/\\:\\  \\       \\:\\  \\    /:/\\ \\  \\  \n" +
                "  /:/  \\:\\  \\   /::\\  \\ ___   /::\\~\\:\\  \\      /::\\__\\  _\\:\\~\\ \\  \\ \n" +
                " /:/__/ \\:\\__\\ /:/\\:\\  /\\__\\ /:/\\:\\ \\:\\__\\  __/:/\\/__/ /\\ \\:\\ \\ \\__\\\n" +
                " \\:\\  \\  \\/__/ \\/__\\:\\/:/  / \\/_|::\\/:/  / /\\/:/  /    \\:\\ \\:\\ \\/__/\n" +
                "  \\:\\  \\            \\::/  /     |:|::/  /  \\::/__/      \\:\\ \\:\\__\\  \n" +
                "   \\:\\  \\           /:/  /      |:|\\/__/    \\:\\__\\       \\:\\/:/  /  \n" +
                "    \\:\\__\\         /:/  /       |:|  |       \\/__/        \\::/  /   \n" +
                "     \\/__/         \\/__/         \\|__|                     \\/__/    ";
        System.out.println("Hello from\n" + logo );

        Scanner sc = new Scanner(System.in);
        String command = "";
        String[] listCommands = new String[100];
        int count = 0;
        System.out.println("____________________________________________________________\n" +
                "Hello, I'm Chris\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n");
        while (sc.hasNextLine()) {
            command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("____________________________________________________________\n" +
                        "Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________");
                break;
            } else if (command.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < count; i++) {
                    System.out.println(i + 1 + ". " + listCommands[i]);
                }
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________\n" +
                        "added: " + command + "\n" +
                        "____________________________________________________________");
                listCommands[count] = command;
                count += 1;
            }
        }
        sc.close();

    }
}
