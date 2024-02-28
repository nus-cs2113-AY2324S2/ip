import java.util.ArrayList;

public class Ui {

    public Ui() {
    }

    public void greetingMessage(){
        System.out.println("Hello! My name is Jeff.");
        System.out.println("What can I do for you?");
    }

    public void goodbyeMessage(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void listInsertionMessage(String description, int listSize){
        System.out.println("Got it. I've added this task");
        System.out.println(description);
        System.out.println("Now you have " + listSize + " tasks in the list.");
    }

    public void printTasks(ArrayList<Task> list){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i += 1){
            System.out.println((i +1) + ". "+ list.get(i).getDescription());
        }
    }


    public void taskMarkedMessage(){
        System.out.println("Nice Ive marked this task as completed ");
    }

    public void taskUnmarkedMessage(){
        System.out.println("OK, I have unchecked this task");
    }

    public void taskRemovedMessage(int taskNumber, String description){
        int index = taskNumber - 1;
        System.out.println("Noted. I've removed this task:");
        System.out.println(description);
        System.out.println("Now you have " + (taskNumber - 1) + " tasks in the list.");
    }

    public void successfulUploadMessage(){
        System.out.println("TASK UPLOADED");
    }


    public void showFileNotFoundException(){
        System.out.println("File not found, creating a new one.");
    }
}
