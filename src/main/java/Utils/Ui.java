package Utils;

import java.util.Scanner;

public class Ui {

    public static void showWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String name ="Altria";
        String prefix = "\n\t";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello, I'm "+name);
        System.out.println("What can I do for you?");
        return;
    }

    public static String readCommand(){
        Scanner in = new Scanner(System.in);
        String instruction = " ";
        instruction = in.nextLine();
        instruction = instruction.toLowerCase();
        return instruction;
    }

    public static void printLine(){
        System.out.println("\n------------------------------------------------------------------\n");
    }
}
