import java.util.Scanner;

public class Dross {

    //Size 100 array tasklist for Dross
    private static List drossTaskList = new List();
    //Method that prints line using "_" char
    public static void printLine() {
        for (int i = 0; i < 55; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }

    //Method that echoes back whatever user typed in provide that it is not "bye"
    public static void echo(){
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while (!line.equals("bye")) {
            printLine();
            System.out.println(line);
            printLine();
            line = in.nextLine();
        }
        goodbye();
    }

    //Method that prints bye message
    public static void goodbye(){
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    //Method that lists out all the tasks
    public static void listAllTasks() {
        printLine();
        drossTaskList.printAllTasks();
        printLine();
    }
    public static void receiveUserInput() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                listAllTasks();
                line = in.nextLine();
            }
            else{
                printLine();
                drossTaskList.addTask(line);
                drossTaskList.printLastTask();
                printLine();
                line = in.nextLine();
            }
        }
        goodbye();
    }
    public static void main(String[] args) {
        printLine();
        System.out.println("Hello! I'm Dross");
        System.out.println("What can I do for you?");
        printLine();
        receiveUserInput();
    }
}
