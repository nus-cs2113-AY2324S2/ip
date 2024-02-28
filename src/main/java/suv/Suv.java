package suv;

import suv.Command.SuvException;
import suv.Task.FileStorage;
import suv.Helpers.Parser;

import java.util.Scanner;


public class Suv {
    final static String LINE = "____________________________________________________________\n";

    public static void main(String[] args) throws SuvException {
        String name = "suv.Suv";
        Scanner in = new Scanner(System.in);

        System.out.println(LINE +
                " Hello! I'm " + name + "\n" +
                " What can I do for you?\n" +
                LINE
        );

        Parser parser = new Parser();

        String input = in.nextLine();
        FileStorage.fetchData();
        while(!input.equals("bye")) {
            parser.handleInput(input);
            input = in.nextLine();
        }
    }
}