import doraemonexceptions.EmptyListException;
import doraemonexceptions.InValidCommandException;
import doraemonexceptions.IsEmptyException;
import todo.Todo;
import ui.Ui;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Main {
    public static final String PATHNAME = "src/data/prevData";

    private static void addTodo(ArrayList<Todo> list, String task, int taskNum) {
        list.add(taskNum, new Todo(task));
    }
    private static void addDeadline(ArrayList<Todo> list, String task, int taskNum) {
            int pos = task.indexOf("/");
            String description = task.substring(0, pos);
            String date = task.substring(pos + 3);
            list.add(taskNum, new Deadline(description, date));
    }

    private static void addEvent(ArrayList<Todo> list, String task, int taskNum) {
        int pos = task.indexOf("/");
        String description = task.substring(0, pos);
        task = task.substring(pos + 5);
        pos = task.indexOf("/");
        String start = task.substring(0, pos);
        String end = task.substring(pos + 3);
        list.add(taskNum, new Event(description, start, end)) ;
    }

    public static int loadData(ArrayList<Todo> list, Ui ui) throws IOException {
        File storedFile = new File(PATHNAME);
        int taskNum = 0;
        if (!storedFile.exists()) {
            new File(storedFile.getParent()).mkdirs();
            System.out.println("No previous data was found, a new file is created.");
            ui.printLine();
        } else {
            Scanner dataInput = new Scanner(storedFile);
            while (dataInput.hasNext()) {
                String currTask = dataInput.nextLine();
                String type = currTask.substring(1,2);
                currTask = currTask.substring(6); //trim off [T][ ]
                switch (type) {
                case "T":
                    addTodo(list, currTask, taskNum);
                    break;
                case "D":
                    addDeadline(list, currTask, taskNum);
                    break;
                case "E":
                    addEvent(list, currTask, taskNum);
                    break;
                default:
                    //if it is none of the valid types, do not add task
                    taskNum--;
                }
                taskNum++;
            }
        }
        return taskNum;
    }

    private static void writeTask(Todo task) throws IOException {
        FileWriter file = new FileWriter(PATHNAME, true);
        file.write(task.getWriteFormat() + System.lineSeparator());
        file.close();
    }

    public static void main(String[] args) {
        Ui ui = new Ui();
        ArrayList<Todo> list = new ArrayList<>();
        int taskNum = 0;
        Scanner in = new Scanner(System.in);
        ui.printGreetings();
        try {
            taskNum = loadData(list, ui);
        } catch (IOException e) {
            System.out.println("Something went wrong, please try again!");
            ui.printLine();
        }
        String temp = in.nextLine();
        while (!temp.equals("bye")) {
            try {
                if (temp.equals("list")) {
                    if (taskNum == 0) {
                        throw new EmptyListException();
                    }
                    for (int i = 0; i < taskNum; i++) {
                        System.out.println(i + 1 + "." + list.get(i).formatTask());
                    }
                    ui.printLine();
                } else if (temp.startsWith("todo")) {
                    temp = temp.substring(4); //trim off command
                    addTodo(list, temp, taskNum);
                    ui.printMessage(list.get(taskNum), taskNum + 1);
                    writeTask(list.get(taskNum));
                    taskNum++;
                } else if (temp.startsWith("deadline")) {
                    temp = temp.substring(8); //trim off command
                    addDeadline(list, temp, taskNum);
                    ui.printMessage(list.get(taskNum), taskNum + 1);
                    writeTask(list.get(taskNum));
                    taskNum++;
                } else if (temp.startsWith("event")) {
                    temp = temp.substring(5); //trim off command
                    addEvent(list, temp, taskNum);
                    ui.printMessage(list.get(taskNum), taskNum + 1);
                    writeTask(list.get(taskNum));
                    taskNum++;
                } else if (temp.startsWith("mark")) {
                    temp = temp.substring(5); //trim "mark"
                    int indexToMark = Integer.parseInt(temp) - 1;
                    list.get(indexToMark).markStatus();
                    ui.printMarked(list.get(indexToMark));
                    ui.printLine();
                } else if (temp.startsWith("delete")){
                    temp = temp.substring(7); //trim delete
                    int indexToDel = Integer.parseInt(temp) - 1;
                    taskNum--;
                    ui.printDelMessage(list.get(indexToDel), taskNum);
                    list.remove(indexToDel);
                } else if (temp.isEmpty()) {
                    throw new IsEmptyException();
                } else {
                    throw new InValidCommandException();
                }
                temp = in.nextLine();
            } catch (IsEmptyException e) {
                System.out.println("Please type something! Try: todo/deadline/event to add a record!");
                ui.printLine();
                temp = in.nextLine();
            } catch (InValidCommandException e) {
                System.out.println("Invalid Command! Try: todo/deadline/event to add a record!");
                ui.printLine();
                temp = in.nextLine();
            } catch (EmptyListException e) {
                System.out.println("The list is empty! Please add something!");
                ui.printLine();
                temp = in.nextLine();
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        ui.printExit();
    }
}
