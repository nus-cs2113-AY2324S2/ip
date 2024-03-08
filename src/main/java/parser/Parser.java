package parser;
import doraemonexceptions.EmptyListException;
import doraemonexceptions.InValidCommandException;
import doraemonexceptions.IsEmptyException;
import tasklist.todo.Todo;
import ui.Ui;
import storage.Storage;
import java.lang.String;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with making sense of the user command.
 */
public class Parser {
    protected ArrayList<Todo> list;
    protected int taskNum;
    protected Scanner in;
    public Parser(ArrayList<Todo> list, int taskNum) {
        this.list = list;
        this.taskNum = taskNum;
        in = new Scanner(System.in);

    }

<<<<<<< HEAD
    /**
     * Runs the process of interacting with user. Reads in user input and respond accordingly.
     *
     * @param pathName Path of file where data is saved in.
     * @param tasklist Tasklist initiated in Main, containing task number and array list.
     * @throws IOException If runParser runs into problems.
     */
    public void runParser(String pathName, TaskList tasklist) throws IOException {
=======
    protected boolean hasFoundItem (String temp) {
        for (Todo task : list) {
            if (task.getDescription().contains(temp)) {
                return true;
            }
        }
        return false;
    }


    public void runParser(String pathName) throws IOException {
>>>>>>> master
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
                        ui.printList(list);
                } else if (temp.startsWith("todo")) {
                    temp = temp.substring(4); //trim off command
                    tasklist.addTodo(list, temp, taskNum);
                    ui.printMessage(list.get(taskNum), taskNum + 1);
                    storage.writeTask(list.get(taskNum));
                    taskNum++;
                } else if (temp.startsWith("deadline")) {
                    temp = temp.substring(8); //trim off command
                    tasklist.addDeadline(list, temp, taskNum);
                    ui.printMessage(list.get(taskNum), taskNum + 1);
                    storage.writeTask(list.get(taskNum));
                    taskNum++;
                } else if (temp.startsWith("event")) {
                    temp = temp.substring(5); //trim off command
                    tasklist.addEvent(list, temp, taskNum);
                    ui.printMessage(list.get(taskNum), taskNum + 1);
                    storage.writeTask(list.get(taskNum));
                    taskNum++;
                } else if (temp.startsWith("mark")) {
                    temp = temp.substring(5); //trim "mark"
                    int indexToMark = Integer.parseInt(temp) - 1;
                    list.get(indexToMark).markStatus();
                    ui.printMarked(list.get(indexToMark));
                } else if (temp.startsWith("delete")) {
                    temp = temp.substring(7); //trim delete
                    int indexToDel = Integer.parseInt(temp) - 1;
                    taskNum--;
                    ui.printDelMessage(list.get(indexToDel), taskNum);
                    list.remove(indexToDel);
                } else if (temp.startsWith("find")) {
                    temp = temp.substring(5); //trim find
                    if (hasFoundItem(temp)) {
                        ui.printFoundTaskHeader();
                        for (Todo task : list) {
                            if (task.getDescription().contains(temp)) {
                                ui.printFoundTask(task, list.indexOf(task));
                            }
                        }
                        ui.printLine();
                    } else {
                        ui.printNotFound();
                    }
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
