import java.util.Scanner;
public class Quill {
    public static void main(String[] args) {
        String horizontalLine = "\n____________________________________________________________\n";
        String name = "Quill";
        String line;
        Scanner in = new Scanner(System.in);
        System.out.println(horizontalLine + "Hello! I'm " + name + ".\nWhat can i do for you?" + horizontalLine);

        line = in.nextLine();
        while (!line.equals("bye")) {
            System.out.println(horizontalLine + line + horizontalLine);
            line = in.nextLine();
        }
        System.out.println(horizontalLine + "Bye! Hope to see you again soon!" + horizontalLine);
    }
}
