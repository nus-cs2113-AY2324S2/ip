import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static void inputChecker() {
        Scanner in = new Scanner(System.in); //declared a scanner object
        String line; //declared a string object to take in user input

        String[] list; //declare a list with string data type
        list = new String[100]; //create a list of 100 elements
        int counter = 0;
        int index = 1;

//start of user input
        while (true) {
            line = in.nextLine(); //takes in user input

            //when user exits
            if (line.equals("bye")) {
                System.out.println("Bye human. Come back soon !");
                break;
            }

            //if user wants to display a list
            else if (line.equals("list")) {
                for (String s : list) {
                    if (s == null){
                        break;
                    }
                    System.out.println(index + ". " + s);
                    index += 1;
                }
                System.out.println("____________________________________________________________");
                index = 1;
                continue;
            }

            //user adds in elements
            System.out.println("added: " + line);
            System.out.println("____________________________________________________________");
            list[counter] = line;
            counter += 1;
        }
    }

    public static void main(String[] args) {

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        inputChecker();
    }
}


