import java.util.Scanner;

public class Faiz {
    public static final String HorLine = "\t_________________________________________\n";
    public  static void printHi() {
        System.out.println(HorLine + "\t Hello! I'm Faiz!\n\t What can I do for you?\n" + HorLine);
    }

    public static void printBye() {
        System.out.println(HorLine + "\t Bye. Hope to see you again soon!\n" + HorLine);;
    }

    public static void echo(String line) {
        System.out.println(HorLine + "\t " + line + System.lineSeparator() + HorLine);
    }

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        printHi();
        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            } else {
                echo(line);
            }
        }
        printBye();
    }
}
