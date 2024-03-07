package Yoj;

import Yoj.exception.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import static Yoj.List.*;

public class Yoj {
    public static void printLine() {
        System.out.println("________________________________________");
    }
    public static void printShortLine() {
        System.out.println("_____________");
    }
    public static void printHello() {
        String logo =
                "__   __   ___    _____ \n"
                        + "\\ \\ / /  / _ \\  | ___ |\n"
                        + " \\ Y /  | | | |     | | \n"
                        + "  \\ /   | | | |     | | \n"
                        + "  | |   | |_| |  ___| | \n"
                        + "  |_|    \\___/  |____/          \n";
        printLine();
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm YOJ");
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void main(String[] args) throws YojException, InvalidCommandException, FileNotFoundException {
        printHello();
        // get user input
        Scanner in = new Scanner(System.in);
        tasks = YojFile.loadTasks();
        String userInput;
        do {
            userInput = in.nextLine();
            try {
                manageUserInput(userInput);
            } catch (YojException e){
                System.out.println(e.getMessage());
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (!userInput.equals("bye"));
        in.close();

    }
}
