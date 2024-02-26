package suv;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import static suv.TaskList.tasksList;

public class TaskManager{
    final static int DEADLINE_KEYWORD_END_INDEX = 8;
    final static int TODO_KEYWORD_END_INDEX = 4;
    final static int EVENT_KEYWORD_END_INDEX = 5;
    final static int TO_KEYWORD_END_INDEX = 2;
    final static int FROM_KEYWORD_END_INDEX = 4;
    final static String LINE = "____________________________________________________________\n";

    TaskManager() {
        tasksList.clear();
    }

    public void handleInput(String input){
        try {
            if(input.equals("bye")){
                handleBye();
            } else if (input.equals("list")){
                handleList();
            } else if (input.contains("unmark")){
                handleUnmark(input);
            } else if (input.contains("mark")){
                handleMark(input);
            } else if(input.contains("todo")){
                handleTodo(input);
            }else if(input.contains("deadline")){
                handleDeadline(input);
            } else if(input.contains("event")){
                handleEvent(input);
            } else if(input.contains("delete")){
                handleDelete(input);
            } else {
                throw new SuvException(LINE +"Oh no! I am not sure what you mean.");
            }
            FileStorage.saveTasksToFile("data/data.txt");
        } catch (SuvException e){
            System.out.println(e.warning);
        }
    }

    public void handleTodo(String input) throws SuvException {
        if(input.trim().length() > 4){
            Todo newTask = new Todo(input.substring(TODO_KEYWORD_END_INDEX).trim());

            tasksList.add(newTask);
            Ui.printAddTodoMessage(newTask, tasksList.size());
        } else {
            throw new SuvException(LINE +"Oh no! You are missing the suv.Todo description\n" + LINE);
        }
    }

    public void handleDeadline(String input) throws SuvException{
        if(input.trim().length() > 8 && input.contains("/by")){
            String by = input.split("/by")[1].trim();
            String description = input.split("/by")[0].substring(DEADLINE_KEYWORD_END_INDEX).trim();

            Deadline newTask = new Deadline(description, by);
            tasksList.add(newTask);
            Ui.printAddDeadlineMessage(newTask, tasksList.size());
        } else {
            throw new SuvException(LINE +"Oh no! You are missing the suv.Deadline details\n" + LINE);
        }
    }

    public void handleEvent(String input) throws SuvException{
        if(input.trim().length() > 5 && input.contains("/from") &&  input.contains("/to")){
            String from = input.split("/")[1].trim().substring(FROM_KEYWORD_END_INDEX).trim();
            String to = input.split("/")[2].trim().substring(TO_KEYWORD_END_INDEX).trim();
            String description = input.split("/")[0].substring(EVENT_KEYWORD_END_INDEX).trim();

            Event newTask = new Event(description, from, to);
            tasksList.add(newTask);

            Ui.printAddEventMessage(newTask, tasksList.size());
        } else {
            throw new SuvException(LINE + "Oh no! You are missing the suv.Event details\n" + LINE);
        }
    }

    public void handleMark(String input) throws SuvException{
        int n = Integer.parseInt(input.split(" ")[1]);
        Task currentTask = tasksList.get(n - 1);
        currentTask.mark();
        Ui.printMarkMessage(currentTask.getDescription());
    }

    public void handleUnmark(String input) throws SuvException{
        int n = Integer.parseInt(input.split(" ")[1]);
        tasksList.get(n - 1).unMark();
        Ui.printUnmarkMessage(tasksList.get(n - 1).getDescription());
    }

    public void handleDelete(String input) throws SuvException{
        int n = Integer.parseInt(input.split(" ")[1]);
        Task currentTask = tasksList.get(n - 1);
        tasksList.remove(n - 1);
        Ui.printDeleteTodoMessage(currentTask, tasksList.size());
    }

    public void handleList() throws SuvException {
        Ui.printList();
    }

    public void  handleBye() throws SuvException{
        Ui.printByeMessage();
    }
}
