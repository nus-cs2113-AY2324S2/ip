package laika;
import java.util.Scanner;
import java.util.ArrayList;

public class Laika {

    public static void modifyTask(ArrayList<Task> taskList, String[] words){
        int taskNumber = Integer.parseInt(words[1]);
        if (words[0].equals("mark")) {
            taskList.get(taskNumber - 1).markAsDone();
            System.out.println("Laika: Good job! Task has been marked as done.");
        } else {
            taskList.get(taskNumber - 1).markAsUndone();
            System.out.println("Laika: Alright, task has been marked as undone.");
        }
        System.out.println(taskList.get(taskNumber-1));
    }

    public static void deleteTask(ArrayList<Task> taskList, String[] words){
        int taskNumber = Integer.parseInt(words[1]);
        System.out.println("Laika: Gotcha! I've dealt with this task:" + System.lineSeparator()
                            + taskList.get(taskNumber-1));
        taskList.remove(taskNumber-1);
    }

    public static void addTask(ArrayList<Task> taskList, String line) throws LaikaException {
        if(line.startsWith("todo")){
            taskList.add(new Todo(line.replaceFirst("todo ","")));
        }
        else if (line.startsWith("deadline")) {
            String[] words = line.split("/");
            if (words.length != 2){
                throw new LaikaException();
            }
            taskList.add(new Deadline(words[0].replaceFirst("deadline ",""),
                                           words[1].replaceFirst("by ","")));
        }
        else if (line.startsWith("event")) {
            String[] words = line.split("/");
            if (words.length != 3){
                throw new LaikaException();
            }
            taskList.add(new Event(words[0].replaceFirst("event ",""),
                                        words[1].replaceFirst("from ",""),
                                        words[2].replaceFirst("to ","")));
        } else {
            throw new LaikaException();
        }
    }

    public static void displayTasks(ArrayList<Task> taskList){
        System.out.println("Task List:");
        for (int i = 0; i < taskList.size(); i++){
            System.out.println((i + 1) + ") " + taskList.get(i));
        }
    }

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        int count = 0;
        boolean isConvoOngoing = true;
        String logo = " ^..^      /\n"
                + " /_/\\_____/\n"
                + "    /\\   /\\\n"
                + "   /  \\ /  \\\n";
        System.out.println("Laika: Hi! My name is Laika!\n\n" + logo + "Laika: How can I help you?");

        while(isConvoOngoing){
            line = in.nextLine();
            String[] words = line.split(" ", 2);
            switch(words[0]){
                case "mark":
                case "unmark":
                    try {
                        modifyTask(taskList, words);
                    }
                    catch (IndexOutOfBoundsException e) {
                        System.out.println("Laika: You dont have so many tasks!");
                    }
                    break;
                case "list":
                    displayTasks(taskList);
                    break;
                case "bye":
                    isConvoOngoing = false;
                    break;
                case "delete":
                    try {
                        deleteTask(taskList, words);
                    }
                    catch (IndexOutOfBoundsException e) {
                        System.out.println("Laika: You dont have so many tasks!");
                    }
                    count--;
                    System.out.println("Laika: You have " + count + " tasks left!");
                    break;
                default:

                    try{
                        addTask(taskList, line);
                    }
                    catch(LaikaException e) {
                        System.out.println("Laika: Hmmmm, I dont understand you?");
                        break;
                    }

                    System.out.println("Laika: Gotcha! I've added the task for you\n  "
                            + taskList.get(count) + System.lineSeparator()
                            + "You have " + (count + 1) + " tasks in your list. :P");
                    count++;



            }
        }

        System.out.println("Laika: Bye! Have a nice day!");
    }
}
