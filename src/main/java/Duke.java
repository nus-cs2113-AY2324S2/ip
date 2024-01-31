import java.util.Scanner;
import java.util.Arrays;

public class Duke  {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__\n" +
                "    },_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Greet greet = new Greet();
        greet.sayHello();

        String[] list = new String[100];

        String line;
        Scanner in = new Scanner(System.in);
        int listIndex = 0;
        while(true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            }
            if(line.equals("list")) {
                greet.printList(list, listIndex);
                continue;
            }
            greet.printHyphen();
            System.out.println();
            System.out.println("added: " + line);
            greet.printHyphen();
            System.out.println();
            list[listIndex] = line;
            listIndex++;
        }
        greet.sayBye();

    }
}