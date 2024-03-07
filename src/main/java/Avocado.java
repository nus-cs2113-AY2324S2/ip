import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Avocado {
    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        System.out.println("Hello from avocado");
        System.out.println("How can I help you ?");
        System.out.println("See you!");
        int i = 0;
        int n = 0;
        newFile(list);

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
                    saveTasks(list);
                } else if (array[0].startsWith("unmark")) {
                    commandUnmark(array, list);
                    saveTasks(list);
                } else if (array[0].startsWith("todo")) {
                    try {
                        commandTodo(list, n, line);
                        saveTasks(list);
                        i++;
                        n++;
                    } catch (DukeException e) {
                        System.out.println("please enter description");
                    }
                } else if (array[0].startsWith("deadline")) {
                    commandLine(list, n, line);
                    saveTasks(list);
                    i++;
                    n++;
                } else if (array[0].startsWith("event")) {
                    commandEvent(list, n, line);
                    saveTasks(list);
                    i++;
                    n++;
                } else if (line.equals("list")) {
                    commandList(list, n);
                } else if (array[0].startsWith("delete")) {
                    commandDelete(array, list);
                    saveTasks(list);
                    i--;
                    n--;
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("unknown command");
            }
        }
    }

    private static void commandMark(String[] array, ArrayList<Task> list) {
        list.get(Integer.parseInt(array[1]) - 1).setDone();
        System.out.print(list.get(Integer.parseInt(array[1]) - 1).taskDescription());
    }

    private static void commandUnmark(String[] array, ArrayList<Task> list) {
        list.get(Integer.parseInt(array[1]) - 1).setNotDone();
        System.out.print(list.get(Integer.parseInt(array[1]) - 1).taskDescription());
    }

    private static void commandTodo(ArrayList<Task> list, int i, String line)
            throws DukeException {
        try {
            ToDo newtodo = new ToDo(line.substring(5));
            list.add(newtodo);
            System.out.println(newtodo);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("please enter description");
        }
    }

    private static void commandLine(ArrayList<Task> list, int i, String line) {
        Line newline = new Line(line.substring(9, line.indexOf("/")), line.substring(line.indexOf("/") + 1));
        list.add(newline);
        System.out.println(newline);
    }

    private static void commandEvent(ArrayList<Task> list, int i, String line) {
        String lengthy = line.substring(0, line.indexOf("/") + 1);
        int lengths = lengthy.length();
        Event newevent = new Event(line.substring(6, line.indexOf("/")),
                line.substring(line.indexOf("/") + 5, line.indexOf("/",
                        lengths + 1)), line.substring(line.indexOf("/", lengths) + 4));
        list.add(newevent);
        System.out.println(newevent);
    }

    private static void commandList(ArrayList<Task> list, int n) {
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + "." + list.get(i).taskDescription());
        }
    }

    private static void commandDelete(String[] array, ArrayList<Task> list) {
        int commandIndex = Integer.parseInt(array[1]) - 1;
        System.out.println("Noted. I've removed this task: ");
        System.out.println(" " + list.get(commandIndex).taskDescription());
        list.remove(commandIndex);
    }

    private static void newFile(ArrayList<Task> tasks) {
        File f = new File("./data/avocado.txt");
        if (!f.exists()) {
            return;
        }
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                Task task = null;
                String line = s.nextLine();
                String[] p = line.split(" \\| ");
                boolean isDone = p[1].trim().equals("1");
                String description = p[2].trim();
                switch (p[0].trim()) {
                    case "T":
                        task = new ToDo(description);
                        break;
                    case "D":
                        task = new Line(description, p[3].trim());
                        break;
                    case "E":
                        task = new Event(description, p[3].trim(), p[4].trim());
                        break;
                }
                if (isDone) {
                    task.setDone();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("file error");
        }
    }

    public static void saveTasks(ArrayList<Task> tasks) {
        File f = new File("./data/avocado.txt");
        if (!f.exists()) {
            f.getParentFile().mkdirs();
        }
        try {
            PrintWriter fw = new PrintWriter(f);
            for (Task task : tasks) {
                fw.println(format(task));
            }
            fw.close();
        } catch (FileNotFoundException e) {
            System.out.println("saving error");
        }
    }

    private static String format(Task task) {
        if (task instanceof ToDo) {
            return "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
        } else if (task instanceof Line) {
            return "D | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() +
                    " | " + ((Line) task).getBy();
        } else if (task instanceof Event) {
            return "E | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() +
                    " | " + ((Event) task).getFrom() + " | " + ((Event) task).getTo();
        } else {
            throw new IllegalArgumentException("");
        }
    }
}
