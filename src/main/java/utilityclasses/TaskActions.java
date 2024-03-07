package utilityclasses;
import drosstasks.Task;
import myexceptions.InvalidTodoException;
import drosstasks.DrossList;
import utilityclasses.Ui;
import utilityclasses.FileIO;
import java.util.ArrayList;

import java.util.ArrayList;

public class TaskActions {

    private static Ui ui;

    //Method to list all tasks
    public static void listAllTasks(DrossList list) {
        Ui.printLine();
        list.printAllTasks();
        Ui.printLine();
    }

    //Method to find a task based on its name
    public static void handleSearchForTask(String name, DrossList list){
        ArrayList<Task> matches = new ArrayList<>();
        ui = new Ui();
        for (int i = 0; i < list.getSize(); i++) {
            Task currentTask = list.getTask(i);
            if (currentTask.getDescription().contains(name)) {
                matches.add(currentTask);
            }
        }
        if (matches.isEmpty()){
            ui.printEmptySearchResult();
        } else {
            ui.printSearchResults(matches);
        }
    }


    //Method to toggle tasks as marked or unmarked
    public static void toggleMark(String instruction, DrossList list) {
        ui = new Ui();
        String[] tokens = instruction.split(" ");
        try {
            int index = Integer.parseInt(tokens[1]);
            if (tokens[0].equals("mark")) {
                list.markDoneByIndex(index);
                FileIO.saveTasksToFile(list);
            } else {
                list.markUndoneByIndex(index);
                FileIO.saveTasksToFile(list);
            }
            listAllTasks(list);
        } catch (ArrayIndexOutOfBoundsException e){
            ui.printLine();
            System.out.println("Yeah sure go ahead and mark that invisible task sir!");
            ui.printLine();
        } catch (IndexOutOfBoundsException e){
            ui.printLine();
            System.out.println("Your mother task");
            ui.printLine();
        }

    }

    //Method to handle task deletion
    public static void handleDeleteTask(int index, DrossList list){
        list.deleteTask(index);
    }

    //Method to handle task creation and parse input to appropriately construct the correct object
    public static void handleTaskCreation(String line, DrossList list) {
        if (line.startsWith("todo")) {
            try {
                list.addTask(line.substring("todo".length()).trim());
                FileIO.saveTasksToFile(list);
            } catch (InvalidTodoException e){
                System.out.println("You want to do nothing? Be my guest... Type it this way todo [task] if you are kind enough to stop wasting my time");
            }
        } else if (line.startsWith("deadline")) {
            String[] parts = line.substring("deadline".length()).trim().split(" /by ", 2);
            try {
                list.addTask(parts[0], parts[1]);
                FileIO.saveTasksToFile(list);
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Go ahead, live life with no deadlines. Procrastinate forever. deadline /by [time] is what you need to type, genius.");
            }
        } else if (line.startsWith("event")) {
            String[] parts = line.substring("event".length()).trim().split(" /from ", 2);
            try {
                String[] timeParts = parts[1].split(" /to ", 2);
                list.addTask(parts[0], timeParts[0], timeParts[1]);
                FileIO.saveTasksToFile(list);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("An event without a name, a start or end... What do you think you are, a philosopher?? event [name] /from [time1] /to [time2] is the way for goodness sakes...");
            }
        }
        list.printLastTask();
        Ui.printLine();
    }
}
