package MassimoBoi;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    TaskList(List<Task> taskList){
        this.taskList = taskList;
    }

    TaskList(){
        this.taskList = new ArrayList<>();
    }

    public Task get(int index){
        return taskList.get(index);
    }

    public int size(){
        return taskList.size();
    }
    public void addTask(Task task){
        taskList.add(task);
        printNewTaskMessage(task);
    }
    public void deleteTask(int task){
        printDeleteTaskMessage(task);
        taskList.remove(task);
    }

    public void printTask(Task task) {
        System.out.printf("""
                %s%s %s
                """, task.taskType(), task.getStatus(), task.getDescription());
    }
    public void printNewTaskMessage(Task newTask){
        System.out.println("Got it! Ya boi has added: ");
        printTask(newTask);
        System.out.printf("You now have %d %s in the list\n", taskList.size(), taskList.size() == 1 ? "task" : "tasks");
    }

    public void printDeleteTaskMessage(int taskIndex) {
        System.out.println("Got it! I have deleted: ");
        printTask(taskList.get(taskIndex));
        System.out.printf("You now have %d %s in the list\n", taskList.size()-1, taskList.size() == 1 ? "task" : "tasks");
    }

    public void printList() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.print(i + 1 + ". ");
            printTask(taskList.get(i));
        }
    }
    public void findAndPrint(String query){
        int index = 1;
        System.out.println("Here's what I found:");
        for(int i = 0; i < taskList.size(); i++){
            if(taskList.get(i).getDescription().contains(query)){
                System.out.print(index + ". ");
                printTask(taskList.get(i));
                index++;
            }
        }
    }
}
