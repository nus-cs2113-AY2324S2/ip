import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Spock {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Spock");
        Scanner myObj = new Scanner(System.in);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        String input = myObj.nextLine();
        List<String> list = new ArrayList<String>();
        int no_of_items_in_list = 0;

        for(String reply = input; !reply.equals("bye"); reply = myObj.nextLine()) {



            System.out.println("____________________________________________________________");

            if (reply.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(list.get(i));
                }
            }
            else {
                list.add(no_of_items_in_list + ". " + input);
                no_of_items_in_list++;
                System.out.println("added: " + reply);
            }
            System.out.println("____________________________________________________________");
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}







