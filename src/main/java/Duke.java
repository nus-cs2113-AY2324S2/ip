import java.sql.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {


    public static void main(String[] args) throws UnexpectedCommandException, EmptyLineException {
        ArrayList<Task> tasks = new ArrayList<Task>();

        //greeting
        System.out.println("Hello! I'm Apple");
        System.out.println("What can I do for you?");

        int index = 0;//number of items in the list
        String line = " ";

        new ManageInputs(tasks, index, line);
    }
}
