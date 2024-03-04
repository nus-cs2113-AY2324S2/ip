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
            array = line.split(" ");
            if (array[0].startsWith("mark")) {
                commandMark(array, list);
            } else if (array[0].startsWith("unmark")) {
                commandUnmark(array, list);
            } else if (array[0].startsWith("todo")) {
                commandTodo(list, i, n, line);
            } else if (array[0].startsWith("deadline")) {
                commandLine(list, i, n, line);
            } else if (array[0].startsWith("event")) {
                commandEvent(list, i, n, line);
            } else if (line.equals("list")) {
                commandList(list, n);
            } else {
                list[i] = new Task(line);
                System.out.println("added: " + line);
                i++;
                n++;
            }
        }
    }

    private static void commandMark(String[] array, Task[] list) {
        list[Integer.parseInt(array[1]) - 1].setDone();
        System.out.print(list[Integer.parseInt(array[1]) - 1].toString());
    }

    private static void commandUnmark(String[] array, Task[] list) {
        list[Integer.parseInt(array[1]) - 1].setNotDone();
        System.out.print(list[Integer.parseInt(array[1]) - 1].toString());
    }

    private static void commandTodo(Task[] list, int i, int n, String line) {
        ToDo newtodo = new ToDo(line.substring(5));
        list[i] = newtodo;
        n++;
        System.out.println(list[i].toString());
        i++;
    }

    private static void commandLine(Task[] list, int i, int n, String line) {
        Line newline = new Line(line.substring(9, line.indexOf("/")), line.substring(line.indexOf("/") + 1));
        list[i] = newline;
        n++;
        System.out.println(newline);
        i++;
    }

    private static void commandEvent(Task[] list, int i, int n, String line) {
        String lengthy = line.substring(0, line.indexOf("/") + 1);
        int lengths = lengthy.length(); /////System.out.println(lengths);
        Event newevent = new Event(line.substring(6, line.indexOf("/")),
                line.substring(line.indexOf("/") + 5, line.indexOf("/",
                        lengths + 1)), line.substring(line.indexOf("/", lengths) + 4));
        list[i] = newevent;
        n++;
        System.out.println(newevent);
        i++;
    }

    private static void commandList(Task[] list, int n) {
        for (int l = 0; l < n; l++) {
            System.out.println((l + 1) + "." + list.getDescription());
        }
    }
}