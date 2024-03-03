package chris;

import chris.customexceptions.illegalDeadlineInput;
import chris.customexceptions.illegalEventInput;
import chris.customexceptions.illegalTaskNumberInput;
import chris.customexceptions.illegalToDoInput;
import chris.tasktypes.Deadline;
import chris.tasktypes.Event;
import chris.tasktypes.ToDo;
import chris.tasktypes.taskList;
import chris.tasktypes.Task;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Chris {
    protected static taskList tasks = new taskList();
    public static void main(String[] args) {
        printLine();
        System.out.println("Hello, I am Chris");
        try {
            loadTasks("data.txt");
            printList();
        } catch (FileNotFoundException e) {
            System.out.println("You have no current tasks.");
        }
        menu();
        printLine();
    }

    public static void printLine() {
        System.out.println("------------------------------------------------");
    }

    public static void printList() {
        if (tasks.getTaskCount() == 0) {
            System.out.println("Sorry, there are no tasks currently.");
            printLine();
        } else {
            System.out.println("Here are you current tasks:");
            tasks.printTaskList();
        }
    }

    public static void loadTasks(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] taskDescription = s.nextLine().split("\\|");
            switch (taskDescription[0]) {
                case "T":
                    try {
                        tasks.addTask(new ToDo(taskDescription[2], taskDescription[1].equals("X")));
                    } catch (illegalToDoInput e) {
                        System.out.println("File is corrupted");
                    }
                    break;
                case "D":
                    try {
                        tasks.addTask(new Deadline(new String[] {taskDescription[2], taskDescription[3]}, taskDescription[1].equals("X")));
                    } catch (illegalDeadlineInput e) {
                        System.out.println("File is corrupted");
                    }
                    break;
                case "E":
                    try {
                        tasks.addTask(new Event(new String[] {taskDescription[2], taskDescription[3], taskDescription[4]}, taskDescription[1].equals("X")));
                    } catch (illegalEventInput e) {
                        System.out.println("File is corrupted");
                    }
                    break;
            }
        }
        s.close();
    }

    public static void saveTasks(String filePath) throws IOException {
        FileWriter f = new FileWriter(filePath);
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            f.write(tasks.getTask(i).saveString());
            f.write(System.lineSeparator());
        }
        f.close();
    }
    public static void menu() {
        boolean done = false;
        Scanner in = new Scanner(System.in);
        while (!done) {
            System.out.println("What can I do for you?");
            printLine();
            String line = in.nextLine();
            if (line.startsWith("todo")) {
                try {
                    tasks.addTask(new ToDo(line.substring(5).trim(), false));
                    System.out.println("ToDo added!");
                    printLine();
                } catch (illegalToDoInput e) {
                    System.out.println("You've entered an illegal chris.tasktypes.ToDo input, it should be in the form [todo] [description]");
                }
            } else if (line.startsWith("deadline")) {
                try {
                    tasks.addTask(new Deadline(line.substring(9).split("/by"), false));
                    System.out.println("Deadline added!");
                    printLine();
                } catch (illegalDeadlineInput e) {
                    System.out.println("You've entered an illegal chris.tasktypes.Deadline input, it should be in the form [deadline] [description] [/by] [time]");
                }

            } else if (line.startsWith("event")) {
                try {
                    tasks.addTask(new Event(line.substring(6).split("/from|/to"), false));
                    System.out.println("Event added!");
                    printLine();
                } catch (illegalEventInput e) {
                    System.out.println("You've entered an illegal event input, it should be in the form [event] [description] [/from] [time] [/to] [time]");
                }
            } else if (line.startsWith("mark")) {
                String taskNumber = line.substring(5);
                try {
                    tasks.markTask(taskNumber);
                } catch (illegalTaskNumberInput e) {
                    System.out.println("You've entered an illegal task number, it should be a number within the size of the list.");
                }
            } else if (line.startsWith("delete")) {
                String taskNumber = line.substring(7);
                try {
                    Task deletedTask = tasks.deleteTask(taskNumber);
                    System.out.println("The following task has been deleted");
                    System.out.println(deletedTask);
                } catch (illegalTaskNumberInput e) {
                    System.out.println("You've entered an illegal task number, it should be a number within the size of the list.");
                }
            } else if (line.startsWith("list")) {
                printList();
            } else if (line.startsWith("quit")) {
                System.out.println("Goodbye, have a nice day!");
                done = true;
                try {
                    saveTasks("data.txt");
                } catch (IOException e) {
                    System.out.println("Sorry, I was unable to save your tasks!");
                }
            } else {
                System.out.println("Sorry, I don't recognise that command.");
                printLine();
            }
        }
    }
}
