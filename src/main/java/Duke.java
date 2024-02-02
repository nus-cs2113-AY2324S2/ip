import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke  {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__\n" +
                "    },_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Greet greet = new Greet();
        greet.sayHello();

        List<Task> taskList = new ArrayList<>();

        String line;
        Scanner in = new Scanner(System.in);
        while(true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            }
            if(line.equals("list")) {
                greet.printList(taskList);
                continue;
            }
            if(line.split(" ")[0].equals("mark")) {
                int indexToPrint = Integer.parseInt(line.split(" ")[1])-1;
                greet.markTaskAsDone(taskList, indexToPrint);
                continue;
            }
            if(line.split(" ")[0].equals("unmark")) {
                int indexToPrint = Integer.parseInt(line.split(" ")[1])-1;
                greet.markTaskAsUndone(taskList, indexToPrint);
                continue;
            }
            Task task = new Task(line);
            taskList.add(task);

            greet.printHyphen();
            System.out.println();
            System.out.println("added: " + line);
            greet.printHyphen();
            System.out.println();
        }
        greet.sayBye();

    }
}