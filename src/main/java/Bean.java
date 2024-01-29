import java.util.Scanner;
import java.util.Arrays;

public class Bean {
    public static void printList(Task[] list){
        int numItems = 1;

        for(Task item : list){
            System.out.print(numItems + ": ");
            item.printTask();
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

        Task[] list = new Task[100];
        int numItems = 0;
        while(!line.equals("bye")) {
            if(line.equals("list")){
                printList(Arrays.copyOf(list, numItems));
            }
            else {
                System.out.println("    added: " + line);
                System.out.println(separator);
                list[numItems] = new Task(line);
                numItems += 1;
            }
            line = in.nextLine();
        }

        System.out.println("Bean will take a nap now. Bye!");
    }
}
