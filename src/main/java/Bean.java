import java.util.Scanner;
import java.util.Arrays;

public class Bean {
    public static void printList(String[] list){
        int numItems = 1;

        for(String item : list){
            System.out.println(numItems + ": " + item);
            numItems += 1;
        }
    }

    public static void main(String[] args) {
        String separator = "--------------------------------------";
        System.out.println("Hello! I'm Bean.\nWhat can I do for you?");
        System.out.println(separator);

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        String[] list = new String[100];
        int numItems = 0;
        while(!line.equals("bye")) {
            if(line.equals("list")){
                printList(Arrays.copyOf(list, numItems));
            }
            else {
                System.out.println("    added: " + line);
                System.out.println(separator);
                list[numItems] = line;
                numItems += 1;
            }
            line = in.nextLine();
        }

        System.out.println("Bean will take a nap now. Bye!");
    }
}
