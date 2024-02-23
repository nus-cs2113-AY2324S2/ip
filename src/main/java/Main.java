import doraemonexceptions.EmptyListException;
import doraemonexceptions.ExceedListException;
import doraemonexceptions.InValidCommandException;
import doraemonexceptions.IsEmptyException;

import java.util.Scanner;
import java.util.ArrayList;
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

    public static void printDelMessage(Todo task, int taskNum) {
        System.out.println("Got it. I've deleted this task:");
        System.out.println("  " + task.formatTask());
        System.out.println("Now you have " + taskNum + " tasks in the list.");
        printLine();
    }

    public static void printMarked(Todo task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.formatTask());
    }


    public static void main(String[] args) {
        ArrayList<Todo> list = new ArrayList<>();
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
            try {
                //query
                if (temp.equals("list")) {
                    if (taskNum == 0) {
                        throw new EmptyListException();
                    }
                    for (int i = 0; i < taskNum; i++) {
                        System.out.println(i + 1 + "." + list.get(i).formatTask());
                    }
                    printLine();
                } else if (temp.startsWith("todo")) {
                    temp = temp.substring(5); //trim "todo"
                    list.add(taskNum, new Todo(temp));
                    printMessage(list.get(taskNum), taskNum + 1);
                    taskNum++;
                } else if (temp.startsWith("deadline")) {
                    pos = temp.indexOf("/");
                    description = temp.substring(8, pos);
                    date = temp.substring(pos + 3);
                    list.add(taskNum, new Deadline(description, date)) ;
                    printMessage(list.get(taskNum), taskNum + 1);
                    taskNum++;
                } else if (temp.startsWith("event")) {
                    pos = temp.indexOf("/");
                    description = temp.substring(5, pos);
                    temp = temp.substring(pos + 5);
                    pos = temp.indexOf("/");
                    start = temp.substring(0, pos);
                    end = temp.substring(pos + 3);
                    list.add(taskNum, new Event(description, start, end)) ;
                    printMessage(list.get(taskNum), taskNum + 1);
                    taskNum++;
                } else if (temp.startsWith("mark")) {
                    temp = temp.substring(5); //trim "mark"
                    int indexToMark = Integer.parseInt(temp) - 1;
                    list.get(indexToMark).markStatus();
                    printMarked(list.get(indexToMark));
                    printLine();
                } else if (temp.startsWith("delete")){
                    temp = temp.substring(7); //trim delete
                    int indexToDel = Integer.parseInt(temp) - 1;
                    taskNum--;
                    printDelMessage(list.get(indexToDel), taskNum);
                    list.remove(indexToDel);
                } else if (temp.isEmpty()) {
                    throw new IsEmptyException();
                } else {
                    throw new InValidCommandException();
                }
                temp = in.nextLine();
            } catch (IsEmptyException e) {
                System.out.println("Please type something! Try: todo/deadline/event to add a record!");
                printLine();
                temp = in.nextLine();
            } catch (InValidCommandException e) {
                System.out.println("Invalid Command! Try: todo/deadline/event to add a record!");
                printLine();
                temp = in.nextLine();
            } catch (EmptyListException e) {
                System.out.println("The list is empty! Please add something!");
                printLine();
                temp = in.nextLine();
            }
        }
        System.out.println("Bye. Have a great day!");
        printLine();
    }
}
