import java.util.Scanner;
public class Yuki {

    public static void printLine() {
        System.out.println("---------------------------------------------");
    }
    public static void main(String[] args) {
        printLine();
        System.out.println("I am Yuki, your personal chat bot and your evil twin.");
        System.out.println("Time to get grinding.");
        printLine();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        Task[] tasks = new Task[100];
        int count = 0;

        while (!line.equals("bye")) {
            printLine();
            if (line.equals("list")) {
                System.out.println("Wake up your idea and do these tasks:");
                for (int i = 0; i < count; i++) {
                    System.out.println(Integer.toString(i + 1)
                            + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
                }
            } else if (line.split(" ")[0].equals("mark")){
                int index = Integer.parseInt(line.split(" ")[1]) - 1;
                tasks[index].markAsDone();
            } else if (line.split(" ")[0].equals("unmark")) {
                int index = Integer.parseInt(line.split(" ")[1]) - 1;
                tasks[index].markAsUndone();
            } else {
                Task t = new Task(line);
                tasks[count] = t;
                System.out.println("new task for you: " + t.description);
                count++;
            }
            printLine();
            line = in.nextLine();
        }

        printLine();
        System.out.println("Breaks are only for the weak.");
        printLine();

    }
}
