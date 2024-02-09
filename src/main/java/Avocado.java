
import java.util.Scanner;
public class Avocado {
    public static void main(String[] args) {
        System.out.println("Hello from avocado");
        System.out.println("How can I help you ?");
        System.out.println("See you!");
        Task[] list = new Task[100];
        int i = 0;
        int n = 0;
        while (true) {
            String line;
            Scanner in = new Scanner(System.in);

            line = in.nextLine();
            if (line.equals("bye")) {
                return;
            }
            String[] array;
            Task t = new Task(line);
            array = line.split(" ");
            if (array[0].startsWith("mark")) {
                list[Integer.parseInt(array[1]) - 1].setDone();
                System.out.print("[" + list[Integer.parseInt(array[1]) - 1].getStatusIcon() + "] " + list[Integer.parseInt(array[1]) -1].taskDescription());
            }
            else if (array[0].startsWith("unmark")) {
                list[Integer.parseInt(array[1]) - 1].setNotDone();
                System.out.print("[" + list[Integer.parseInt(array[1]) -1].getStatusIcon() + "]" + list[Integer.parseInt(array[1]) -1 ].taskDescription());
            }
            else if (line.equals("list")) {
                for (i = 0; i < n; i++) {
                    System.out.println((i + 1) + "." + "[" + list[i].getStatusIcon() + "]" + list[i].taskDescription());
                }
            }
            else{
                list[i] = new Task(line);
                System.out.println("added: " + line);
                i++;
                n++;
            }
        }
    }
}