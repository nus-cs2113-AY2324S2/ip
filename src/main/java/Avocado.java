//import src.main.java.DukeException;

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
            try {
                String line;
                Scanner in = new Scanner(System.in);
                line = in.nextLine();
                if (line.equals("bye")) {
                    return;
                }
                String[] array;
                array = line.split(" ");
                if (array[0].startsWith("mark")) {
                    commandMark(array, list);
                } else if (array[0].startsWith("unmark")) {
                    commandUnmark(array, list);
                } else if (array[0].startsWith("todo")) {
                    try {
                        commandTodo(list, n, line);
                        i++;
                        n++;
                    } catch (DukeException e) {
                        System.out.println("please enter description");
                    }
                } else if (array[0].startsWith("deadline")) {
                    commandLine(list, n, line);
                    i++;
                    n++;
                } else if (array[0].startsWith("event")) {
                    commandEvent(list, n, line);
                    i++;
                    n++;
                } else if (line.equals("list")) {
                    commandList(list, n);
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("unknown command");
            }
        }
    }



    private static void commandMark(String[] array, Task[] list) {
        list[Integer.parseInt(array[1]) - 1].setDone();
        System.out.print(list[Integer.parseInt(array[1]) - 1].taskDescription());
    }

    private static void commandUnmark(String[] array, Task[] list) {
        list[Integer.parseInt(array[1]) - 1].setNotDone();
        System.out.print(list[Integer.parseInt(array[1]) - 1].taskDescription());
    }

    private static void commandTodo(Task[] list, int i, String line)
            throws DukeException {
        try {
            ToDo newtodo = new ToDo(line.substring(5));
            list[i] = newtodo;
            System.out.println(list[i].taskDescription());
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("please enter description");
        }
    }


    private static void commandLine(Task[] list, int i, String line) {
        Line newline = new Line(line.substring(9, line.indexOf("/")), line.substring(line.indexOf("/") + 1));
        list[i] = newline;
        System.out.println(newline);
    }

    private static void commandEvent(Task[] list, int i, String line) {
        String lengthy = line.substring(0, line.indexOf("/") + 1);
        int lengths = lengthy.length(); /////System.out.println(lengths);
        Event newevent = new Event(line.substring(6, line.indexOf("/")),
                line.substring(line.indexOf("/") + 5, line.indexOf("/",
                        lengths + 1)), line.substring(line.indexOf("/", lengths) + 4));
        list[i] = newevent;
        System.out.println(newevent);
    }

    private static void commandList(Task[] list, int n) {
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + "." + list[i].taskDescription());
        }
    }
}