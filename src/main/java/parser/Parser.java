package parser;
import doraemonexceptions.EmptyListException;
import doraemonexceptions.InValidCommandException;
import doraemonexceptions.IsEmptyException;
import tasklist.TaskList;
import tasklist.todo.Todo;
import ui.Ui;
import storage.Storage;
import java.lang.String;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static tasklist.TaskList.*;

public class Parser {
    protected ArrayList<Todo> list;
    protected int taskNum;
    protected Scanner in;
    public Parser(ArrayList<Todo> list, int taskNum) {
        this.list = list;
        this.taskNum = taskNum;
        in = new Scanner(System.in);

    }


    public void runParser(String pathName) throws IOException {
        Storage storage = new Storage(pathName);
        Ui ui = new Ui();
        String temp = in.nextLine();
        while (!temp.equals("bye"))
        {
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
                    storage.writeTask(list.get(taskNum));
                    taskNum++;
                } else if (temp.startsWith("deadline")) {
                    temp = temp.substring(8); //trim off command
                    addDeadline(list, temp, taskNum);
                    ui.printMessage(list.get(taskNum), taskNum + 1);
                    storage.writeTask(list.get(taskNum));
                    taskNum++;
                } else if (temp.startsWith("event")) {
                    temp = temp.substring(5); //trim off command
                    addEvent(list, temp, taskNum);
                    ui.printMessage(list.get(taskNum), taskNum + 1);
                    storage.writeTask(list.get(taskNum));
                    taskNum++;
                } else if (temp.startsWith("mark")) {
                    temp = temp.substring(5); //trim "mark"
                    int indexToMark = Integer.parseInt(temp) - 1;
                    list.get(indexToMark).markStatus();
                    ui.printMarked(list.get(indexToMark));
                    ui.printLine();
                } else if (temp.startsWith("delete")) {
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
                ui.showIsEmptyException();
                temp = in.nextLine();
            } catch (InValidCommandException e) {
                ui.showInValidCommandException();
                temp = in.nextLine();
            } catch (EmptyListException e) {
                ui.showEmptyListException();
                temp = in.nextLine();
            } catch (IOException e) {
                ui.showIOException();
            }
        }
    }
}
