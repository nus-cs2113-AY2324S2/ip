import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * TaskList controls list
 */

public class TaskList {
    private ArrayList<Task> list;
    private Storage storage;
    private Parser parser;

    /**
     * TaskList takes in the list of items and stores them in storage as well as reads command
     * @param list item
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
        this.storage = new Storage();
        this.parser = new Parser();
        this.storage.newFile(list);
        list.clear();
        processUserInput();
    }

    /**
     * processUserInput to take in commadn
     */

    public void processUserInput() {

            int i = 0;
            int n = 0;
            Scanner in = new Scanner(System.in);
            while (true) {
                try {
                    String line;
                    line = in.nextLine();
                    if (line.equals("bye")) {
                        break;
                    }

                    String[] array = parser.getArray(line);
                    if (array[0].startsWith("mark")) {
                        commandMark(array, list);
                        storage.saveTasks(list);
                    } else if (array[0].startsWith("unmark")) {
                        commandUnmark(array, list);
                        storage.saveTasks(list);
                    } else if (array[0].startsWith("todo")) {
                        try {
                            commandTodo(list, n, line);
                            storage.saveTasks(list);
                            i++;
                            n++;
                        } catch (DukeException e) {
                            System.out.println("please enter description");
                        }
                    } else if (array[0].startsWith("deadline")) {
                        commandLine(list, n, line);
                        storage.saveTasks(list);
                        i++;
                        n++;
                    } else if (array[0].startsWith("event")) {
                        commandEvent(list, n, line);
                        storage.saveTasks(list);
                        i++;
                        n++;
                    } else if (line.equals("list")) {
                        commandList(list, n);
                    } else if (array[0].startsWith("delete")) {
                        commandDelete(array, list);
                        storage.saveTasks(list);
                        i--;
                        n--;
                    } else if (array[0].startsWith("find")) {
                        commandFind(array, list);
                    }
                        else {
                        throw new IllegalArgumentException();
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("unknown command");
                }
            }
            in.close();
        }

    /**
     * as X
     * @param array the command split by space
     * @param list list of items
     */
    private void commandMark(String[] array, ArrayList<Task> list) {
        list.get(Integer.parseInt(array[1]) - 1).setDone();
        System.out.print(list.get(Integer.parseInt(array[1]) - 1).taskDescription());
    }

    /**
     * undo x
     * @param array the command split by spacce
     * @param list list
     */

    private void commandUnmark(String[] array, ArrayList<Task> list) {
        list.get(Integer.parseInt(array[1]) - 1).setNotDone();
        System.out.print(list.get(Integer.parseInt(array[1]) - 1).taskDescription());
    }

    /**
     * commandTodo an item as todo
     * @param list list of item
     * @param i where the item is in the array
     * @param line command
     * @throws DukeException when todo description not provoded
     */

    private void commandTodo(ArrayList<Task> list, int i, String line) throws DukeException {
        try {
            ToDo newtodo = new ToDo(line.substring(5));
            list.add(newtodo);
            System.out.println(newtodo);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("please enter description");
        }
    }

    /**
     * Line due
     * @param list lsit of items
     * @param i where the item is
     * @param line command
     */
    private void commandLine(ArrayList<Task> list, int i, String line) {
        Line newline = new Line(line.substring(9, line.indexOf("/")), line.substring(line.indexOf("/") + 1));
        list.add(newline);
        System.out.println(newline);
    }

    /**
     * Event an event with a period
     * @param list list of items
     * @param i where the item is
     * @param line command
     */

    private void commandEvent(ArrayList<Task> list, int i, String line) {
        String lengthy = line.substring(0, line.indexOf("/") + 1);
        int lengths = lengthy.length();
        Event newevent = new Event(line.substring(6, line.indexOf("/")),
                line.substring(line.indexOf("/") + 5, line.indexOf("/",
                        lengths + 1)), line.substring(line.indexOf("/", lengths) + 4));
        list.add(newevent);
        System.out.println(newevent);
    }

    /**
     * list all items including their status in the list
     * @param list list
     * @param n numberof item
     */

    private void commandList(ArrayList<Task> list, int n) {
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + "." + list.get(i).taskDescription());
        }
    }

    /**
     * delete an item
     * @param array command split
     * @param list command
     */

    private void commandDelete(String[] array, ArrayList<Task> list) {
        int commandIndex = Integer.parseInt(array[1]) - 1;
        System.out.println("Noted. I've removed this task: ");
        System.out.println(" " + list.get(commandIndex).taskDescription());
        list.remove(commandIndex);
    }

    /**
     * finds all items with the corresponding description
     * @param array commands
     * @param list cpmmand
     */
    private void commandFind(String [] array,  ArrayList<Task> list) {
        String find = array[1];
        int number = 0;
        ArrayList<Task> taskReturn = new ArrayList<Task>();
        for (Task task : list) {
            if (task.taskDescription().contains(find)) {
                taskReturn.add(task);
                number ++;
            }
        }
        for (int i = 0; i < number; i ++) {
            System.out.println((i + 1) + "." + taskReturn.get(i).taskDescription());
        }

    }
}
