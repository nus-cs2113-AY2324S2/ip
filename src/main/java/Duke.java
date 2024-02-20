import java.util.Scanner;

public class Duke {


    public static void main(String[] args) throws UnexpectedCommandException, EmptyLineException {
        Task[] tasks = new Task[100];

        //greeting
        System.out.println("Hello! I'm Apple");
        System.out.println("What can I do for you?");

        int index = 0;//number of items in the list
        String line = " ";

        new ManageInputs(tasks, index, line);
    }
}
