import java.util.List;
public class Greet {

    public void printHyphen() {
        for(int i = 0; i < 50; i++) {
            System.out.print("-");
        }
    }
    public void sayHello() {
        printHyphen();
        System.out.println();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printHyphen();
        System.out.println();
    }

    public void sayBye() {
        printHyphen();
        System.out.println();
        System.out.println("Bye. Hope to see you again soon!");
        printHyphen();
    }

    public void printList(List<Task> taskList) {
        printHyphen();
        System.out.println();
        for(int i = 0; i < taskList.size(); i++) {
            System.out.print(i+1 + ".");
            Task task = taskList.get(i);
            System.out.print("[" + task.getStatusIcon() + "] ");
            System.out.println(task.getDescription());
        }
        printHyphen();
        System.out.println();
    }

    public void markTaskAsDone(List<Task> taskList, int taskNumber) {
        printHyphen();
        System.out.println();
        Task task = taskList.get(taskNumber);
        task.setIsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.print("[" + task.getStatusIcon() + "] ");
        System.out.println(taskList.get(taskNumber).getDescription());
        printHyphen();
        System.out.println();
    }

    public void markTaskAsUndone(List<Task> taskList, int taskNumber) {
        printHyphen();
        System.out.println();
        Task task = taskList.get(taskNumber);
        task.setIsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.print("[" + task.getStatusIcon() + "] ");
        System.out.println(taskList.get(taskNumber).getDescription());
        printHyphen();
        System.out.println();
    }

}

