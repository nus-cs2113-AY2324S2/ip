import java.util.Scanner;
public class Main {
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printGreetings() {
        printLine();
        System.out.println("Hello! I'm Doraemon!");
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void printMessage(Todo task, int taskNum) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.formatTask());
        System.out.println("Now you have " + taskNum + " tasks in the list.");
        printLine();
    }

    public static void printMarked(Todo task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.formatTask());
    }


    public static void main(String[] args) {
        //initializing the arrays to 100 first
        Todo[] list = new Todo[100];
        int taskNum = 0;
        int pos;
        String description;
        String date;
        String start;
        String end;
        Scanner in = new Scanner(System.in);
        printGreetings();
        String temp = in.nextLine();
        while (!temp.equals("bye")) {
            //query
            if (temp.equals("list")) {
                for (int i = 0; i < taskNum; i++) {
                    System.out.println(i+1 + "." + list[i].formatTask());
                }
                printLine();
            }
            //todo type
            else if (temp.startsWith("todo")) {
                temp = temp.substring(5); //trim "todo"
                list[taskNum]= new Todo(temp);
                printMessage(list[taskNum], taskNum + 1);
                taskNum++;
            }
            //deadline type
            else if (temp.startsWith("deadline")) {
                pos = temp.indexOf("/");
                description = temp.substring(8, pos);
                date = temp.substring(pos + 3);
                list[taskNum]= new Deadline(description, date);
                printMessage(list[taskNum], taskNum + 1);
                taskNum++;
            }
            //event type
            else if (temp.startsWith("event")) {
                pos = temp.indexOf("/");
                description = temp.substring(5, pos);
                temp = temp.substring(pos + 5);
                pos = temp.indexOf("/");
                start = temp.substring(0, pos);
                end = temp.substring(pos + 3);
                list[taskNum]= new Event(description, start, end);
                printMessage(list[taskNum], taskNum + 1);
                taskNum++;
            } else if (temp.startsWith("mark")) {
                temp = temp.substring(5); //trim "mark"
                int indexToMark = Integer.parseInt(temp) - 1;
                list[indexToMark].markStatus();
                printMarked(list[indexToMark]);
                printLine();
            }
            //invalid input
            else {
                System.out.println("What can I do for you?");
                printLine();
            }
            temp = in.nextLine();
        }
        System.out.println("Bye. Have a great day!");
        printLine();
    }
}
