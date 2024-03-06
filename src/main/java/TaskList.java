import java.util.ArrayList;

public class TaskList {
    static final ArrayList<Task> tasks = Storage.load();

    public static void deleteTask(int indexToDelete) {
        tasks.remove(indexToDelete);
        Storage.save(tasks);
    }

    public static void addTask(Task task) {
        tasks.add(task);
        Storage.save(tasks);
    }

    public static int getSize() {
        return tasks.size();
    }

    public static void printList() {
        int count = 0;
        for(Task t:tasks) {
            if(t == null) {
                return;
            }
            count++;
            System.out.print(count + ".");
            System.out.println(t);
        }
    }

    public static Task getTask(int index) {
        return tasks.get(index);
    }

    public static void searchTasks(String stringToFind) {
        Ui ui = new Ui();
        ui.printFormat();
        System.out.println("Here are the matching tasks in your list: ");
        int count = 1;
        for(Task t:tasks) {
            if(t == null) {
                return;
            }
            if(t.toString().contains(stringToFind)) {
                System.out.print(count + ". ");
                System.out.println(t);
                count++;
            }
        }
        ui.printFormat();
    }

    public static boolean isWithinBounds(int currentIndex, int index) {
        Ui ui = new Ui();
        if(index < currentIndex && currentIndex >= 0) {
            return true;
        }
        ui.printFormat();
        if(index == 0) {
            System.out.println("Hey, the index is out of bounds!");
        }
        if(currentIndex == 0) {
            System.out.println("Hey, you have no tasks added yet!");
        } else {
            System.out.println("Hey, you don't have that many tasks!");
        }
        ui.printFormat();
        return false;
    }

}
