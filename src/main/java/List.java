import java.util.ArrayList;

public class List {
    protected ArrayList<Task> tasks;

    public void generateList(){
        tasks = new ArrayList<>();
    }

    public void insertTask(Task task){
        tasks.add(task);
        System.out.println("Got it. I've added this task");
        System.out.println(task.getDescription());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public ArrayList<Task> getTasks(){
        return tasks;
    }

    public void setTasks(ArrayList<Task> aList){
        this.tasks = aList;
    }

    public void listTasks(){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i += 1){
            System.out.println((i +1) + ". "+ tasks.get(i).getDescription());
        }
    }

    public void markIndex(int index){
        System.out.println("Nice Ive marked this task as completed ");
        tasks.get(index - 1).markTask();
    }

    public void unmarkIndex(int index){
        System.out.println("OK, I have unchecked this task");
        tasks.get(index - 1).unmarkTask();
    }

    public void deleteIndex(int index){
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(index - 1).getDescription());
        tasks.remove(index - 1);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}
