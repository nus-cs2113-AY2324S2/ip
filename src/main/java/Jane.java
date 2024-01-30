import java.util.Scanner;
public class Jane {
    public static void main(String[] args) {
        String logo = " _____    _____    ____ _    _____ \n" +
                "|____ |  |     |  |    | |  | ____|\n" +
                "    | |  |  |  |  | |  | |  | |___ \n" +
                " _  | |  |  _  |  | |  | |  |  ___|\n" +
                "| |_| |  | | | |  | |  | |  | |___ \n" +
                "|_____|  |_| |_|  |_| ___|  |_____|\n";
        String horizontalLine = "____________________________________________________________\n";
        String greetMessage = "Hello! I am Jane.\nWhat can I do for you?\n";
        String exitMessage = "Bye. Hope to see you again soon!\n";

        System.out.print(logo + horizontalLine);
        System.out.print(greetMessage + horizontalLine);

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        String[] textList = new String[100];
        int textCount = 0;

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (int i = 1; i <= textCount; i++) {
                    System.out.println(i + ". " + textList[i - 1]);
                }
            }
            else {
                textList[textCount] = input;
                textCount++;
                System.out.println("Jane: " + "added: " + input);
            }
            System.out.print(horizontalLine);
            input = in.nextLine();
        }

        System.out.print(horizontalLine + exitMessage + horizontalLine);

    }
}
