import java.util.ArrayList;

/**
 * Defines the methods that print text to the user.
 */
public class Ui {
    public Ui() {
    }


    /**
     * Prints default greeting message.
     */
    public void greetingMessage(){
        System.out.println("Hello! My name is Jeff.");
        System.out.println("What can I do for you?");
    }


    /**
     * Prints default goodbye message.
     */
    public void goodbyeMessage(){
        System.out.println("Bye. Hope to see you again soon!");
    }


    /**
     * Prints a message indicating an addition of a task to the list as well as the number of tasks in the list.
     *
     * @param description Task description.
     * @param listSize Number of elements in the list.
     */
    public void listInsertionMessage(String description, int listSize){
        System.out.println("Got it. I've added this task");
        System.out.println(description);
        System.out.println("Now you have " + listSize + " tasks in the list.");
    }

    /**
     * Prints out the entire list of tasks with their respective indexes.
     *
     * @param list List of tasks to be printed.
     */
    public void printTasks(ArrayList<Task> list){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i += 1){
            System.out.println((i +1) + ". "+ list.get(i).getDescription());
        }
    }


    /**
     * Prints default message when a task is marked as completed.
     */
    public void taskMarkedMessage(){
        System.out.println("Nice Ive marked this task as completed ");
    }


    /**
     * Prints default message when a task is unmarked as uncompleted.
     */
    public void taskUnmarkedMessage(){
        System.out.println("OK, I have unchecked this task");
    }


    /**
     * Prints out a message indicating a task has been removed from the list
     *
     * @param taskNumber Index of the task.
     * @param description Description of that task.
     */
    public void taskRemovedMessage(int taskNumber, String description){
        int index = taskNumber - 1;
        System.out.println("Noted. I've removed this task:");
        System.out.println(description);
        System.out.println("Now you have " + (taskNumber - 1) + " tasks in the list.");
    }


    /**
     * Prints default indication that a task has been added to the storage.
     */
    public void successfulUploadMessage(){
        System.out.println("TASK UPLOADED");
    }


    /**
     * Prints default indication that there was no txt file in the Data folder.
     */
    public void showFileNotFoundException(){
        System.out.println("File not found, creating a new one.");
    }

    public void keywordMessage(ArrayList<Task> foundTasks) {
        if (foundTasks.isEmpty()){
            System.out.println("Unfortunately, there were no matches for your search");
        } else{
            System.out.println("I found something!");
            printTasks(foundTasks);
        }
    }
}
