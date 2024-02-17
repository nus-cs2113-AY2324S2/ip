import java.util.Scanner;

public class TaskList {
    public static void printList(Task[] taskList, int size) {
        for (int i = 0; i < size; i += 1) {
            System.out.print("      ");
            Task task = taskList[i];
            System.out.print(i + 1 + ".");
            task.print();
        }
    }
    public static void main(String[] args) {
        System.out.println("MOBY: Hello! I'm Moby.");
        System.out.println("MOBY: What can I do for you?");
        String line;
        Scanner in = new Scanner(System.in);
        Task[] list = new Task[100];
        int currSize = 0;
        while (true) {
            System.out.print("YOU: ");
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            }
            if (line.equals("list")) {
                System.out.println("MOBY: Here's the list below");
                if (currSize > 0) {
                    printList(list, currSize);
                } else {
                    System.out.println("MOBY: There's nothing in this list.");
                }
            }
            else if (line.startsWith("mark")){
                System.out.println("MOBY: Nice! I've marked this task as done:");
                System.out.print("      ");
                int index = Integer.parseInt(line.substring(5));
                Task markedTask = list[index - 1];
                markedTask.setMarked(true);
                markedTask.print();
            }
            else if (line.startsWith("unmark")) {
                System.out.println("MOBY: OK, I've marked this task as not done yet:");
                System.out.print("      ");
                int index = Integer.parseInt(line.substring(7));
                Task unmarkedTask = list[index - 1];
                unmarkedTask.setMarked(false);
                unmarkedTask.print();
            } else {
                Task newTask = new Task(line);
                list[currSize++] = newTask;
                System.out.println("MOBY: added: " + line.trim());
            }
        }
        System.out.println("MOBY: Bye. Hope to see you again soon!");
    }
}
