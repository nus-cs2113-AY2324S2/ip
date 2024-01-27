import java.util.Scanner;
public class Evelyn {

    public static Task[] tasks;
    public static int indexOfTask = 0;
    public static void markTask(int index, boolean done) {
        if (index >= 0 && index < indexOfTask) {
            if (done) {
                tasks[index].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
            } else {
                tasks[index].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println("  " + tasks[index].getStatusIcon() + " " + tasks[index].description);
            printLine();
        } else {
            System.out.println("Invalid task index. Please try again.");
            printLine();
        }
    }
    public static void printList(Task[] tasks){
        int index = 1;
        for (Task task : tasks){
            if(task == null){
                printLine();
                break;
            }
            else {
                System.out.println(index + ". " + tasks[index - 1].getStatusIcon() + tasks[index - 1].description);
                index++;
            }
        }
    }
    public  static void echo(){
        String line;
        System.out.println("type your command: ");
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        printLine();
        if(line.equals("bye")){
            return;
        }
        else if(line.equals("list")){
            printList(tasks);
            echo();
        }
        else if(line.startsWith("mark")){
            int index = Integer.parseInt(line.substring(5).trim()) - 1;
            markTask(index,true);
            echo();
        }
        else if(line.startsWith("unmark")) {
            int index = Integer.parseInt(line.substring(7).trim()) - 1;
            markTask(index,false);
            echo();

        }
        else {
            tasks[indexOfTask] = new Task(line);
            indexOfTask++;
            System.out.println("added: " + line);
            printLine();
            echo();
        }
    }
    public static void printLine(){
        System.out.print("____________________________________________________________\n");
    }
    public static void greeting(){
        printLine();
        System.out.println("Hello! I'm Evelyn");
        System.out.println("What can I do for you?");
        printLine();
    }
    public static void end(){
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
    public static void main(String[] args) {
        greeting();
        tasks = new Task[100];
        echo();
        end();
    }

}
