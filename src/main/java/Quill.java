import java.util.Scanner;
public class Quill {
    public static void main(String[] args) {
        String horizontalLine = "\n____________________________________________________________\n";
        String name = "Quill";
        String line;
        String[] texts = new String[100];
        int totalTexts = 0;
        Scanner in = new Scanner(System.in);

        System.out.println(horizontalLine + "Hello! I'm " + name + ".\nWhat can i do for you?" + horizontalLine);

        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                System.out.println((horizontalLine));
                for (int i = 0; i < totalTexts; i++) {
                    System.out.println(i + 1 + ". " + texts[i]);
                }
                System.out.println(horizontalLine);
                line = in.nextLine();
            } else {
                System.out.println(horizontalLine + "Added: " + line + horizontalLine);
                texts[totalTexts] = line;
                totalTexts++;
                line = in.nextLine();
            }
        }
        System.out.println(horizontalLine + "Bye! Hope to see you again soon!" + horizontalLine);
    }
}
