import java.util.Scanner;

public class Dross {

    //Size 100 array tasklist for Dross
    private static List drossTaskList = new List();
    //Method that prints line using "_" char
    public static void printLine() {
        for (int i = 0; i < 55; i++) {
            System.out.print("_");
        }
        System.out.println();
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

    //Method that receives the user input and toggles the check on the task based on index
    public static void toggleMark(String instruction) {
        String command = instruction.split(" ")[0];
        int index = Integer.parseInt(instruction.split(" ")[1]);
        if (command.equals("mark")){
            drossTaskList.markDoneByIndex(index);
        }
        else{
            drossTaskList.markUndoneByIndex(index);
        }
        listAllTasks();
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
            else if (line.startsWith("mark") || line.startsWith("unmark")) {
                toggleMark(line);
                line = in.nextLine();
            }
            else if (line.startsWith("todo")){
                String withoutCommand = line.substring("todo".length()).trim();
                drossTaskList.addTask(withoutCommand);
                drossTaskList.printLastTask();
                printLine();
                line = in.nextLine();
            }
            else if (line.startsWith("deadline")) {
                String withoutCommand = line.substring("deadline".length()).trim();
                String[] parsedParts = withoutCommand.split(" /by ", 2);

                String taskName = parsedParts[0];
                String taskDeadline = parsedParts[1];

                drossTaskList.addTask(taskName, taskDeadline);
                printLine();
                line = in.nextLine();
            }
            else if (line.startsWith("event")) {
                String withoutCommand = line.substring("event".length()).trim();
                String[] parts = withoutCommand.split(" /from ", 2);
                String taskName = parts[0];
                String[] timeParts = parts[1].split(" /to ", 2);
                drossTaskList.addTask(taskName, timeParts[0], timeParts[1]);
                printLine();
                line = in.nextLine();
            }
            else{
                printLine();
                System.out.println("Please enter a valid command");
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
