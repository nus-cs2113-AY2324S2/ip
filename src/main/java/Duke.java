import java.util.Scanner;

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


        String line;
        Scanner in = new Scanner(System.in);
        while(true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            }
            greet.printHyphen();
            System.out.println();
            System.out.println(line);
            greet.printHyphen();
            System.out.println();
        }
        greet.sayBye();

    }
}

