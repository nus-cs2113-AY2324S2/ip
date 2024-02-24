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
            Task t = new Task(line);
            array = line.split(" ");
            if (array[0].startsWith("mark")) {
                list[Integer.parseInt(array[1]) - 1].setDone();
                System.out.print(list[Integer.parseInt(array[1]) -1].taskDescription());
            }
            else if (array[0].startsWith("unmark")) {
                list[Integer.parseInt(array[1]) - 1].setNotDone();
                System.out.print( list[Integer.parseInt(array[1]) -1 ].taskDescription());
            }
            else if (array[0].startsWith("todo")) {
                ToDo newtodo = new ToDo(line.substring(5));
                list[i] = newtodo;
                n++;
                System.out.println(" [T]"  + list[i].taskDescription());
                i++;


            }
            else if (array[0].startsWith("deadline")) {
                Line newline = new Line (line.substring(9, line.indexOf("/")), line.substring(line.indexOf("/") +1));
                list[i] = newline;
                n++;
                System.out.println(newline);
                i++;
            }
            else if (array[0].startsWith("event")) {
                String lengthy= line.substring(0, line.indexOf("/") + 1);
                int lengths = lengthy.length(); /////System.out.println(lengths);
                Event newevent = new Event (line.substring(6, line.indexOf("/")), line.substring(line.indexOf("/") +5
                , line.indexOf("/", lengths +1)), line.substring(line.indexOf("/", lengths) + 4));
                n++;
            System.out.println(newevent);
            i++;
            }
            else if (line.equals("list")) {
                for (i = 0; i < n; i++) {
                    System.out.println((i + 1) + "."  + list[i].taskDescription());
                }
            }
            else{
                list[i] = new Task(line);
                System.out.println("added: " + line);
                i++;
                n++;
            }
        }
    }
}