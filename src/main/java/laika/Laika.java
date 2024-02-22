package laika;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Laika {

    public static void modifyTask(Task[] taskList, String line){
        String[] words = line.split(" ");
        int taskNumber = Integer.parseInt(words[1]);
        if (words[0].equals("mark")) {
            taskList[taskNumber - 1].markAsDone();
            System.out.println("Laika: Good job! Task has been marked as done.");
        } else {
            taskList[taskNumber - 1].markAsUndone();
            System.out.println("Laika: Alright, task has been marked as undone.");
        }
        System.out.println(taskList[taskNumber-1]);
    }

    public static void addTask(Task[] taskList, String line, int count) throws LaikaException {
        if(line.startsWith("todo")){
            taskList[count] = new Todo(line.replaceFirst("todo ",""));
        }
        else if (line.startsWith("deadline")) {
            String[] words = line.split("/");
            if (words.length != 2){
                throw new LaikaException();
            }
            taskList[count] = new Deadline(words[0].replaceFirst("deadline ",""),
                                           words[1].replaceFirst("by ",""));
        }
        else if (line.startsWith("event")) {
            String[] words = line.split("/");
            if (words.length != 3){
                throw new LaikaException();
            }
            taskList[count] = new Event(words[0].replaceFirst("event ",""),
                                        words[1].replaceFirst("from ",""),
                                        words[2].replaceFirst("to ",""));
        } else {
            throw new LaikaException();
        }
    }

    public static void displayTasks(Task[] taskList,int count){
        System.out.println("Task List:");
        for (int i = 0; i<count;i++){
            System.out.println((i + 1) + ") " + taskList[i]);
        }
    }


    public static void save(String[] lines,int count) throws IOException{
        FileWriter myWriter = new FileWriter("laika.txt");
        for (int i = 0; i<count;i++){
            myWriter.write(lines[i] + System.lineSeparator());

        }
        myWriter.close();

    }

    public static void main(String[] args) throws IOException {
        String line;
        Scanner in = new Scanner(System.in);
        Task[] taskList = new Task[100];
        String[] lines = new String[100];
        File f = new File("laika.txt");
        int count = 0;
        boolean isConvoOngoing = true;

        if (f.createNewFile()){
            System.out.println("Laika: Save file created!");
        }

        try {
            Scanner reader = new Scanner(f);
            while (reader.hasNext()){
                String command = reader.nextLine();
                addTask(taskList,command,count);
                lines[count] = command;
                count++;
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Laika: Error while reading save data.");
        } catch (LaikaException e) {
            throw new RuntimeException(e);
        }


        String logo = " ^..^      /\n"
                + " /_/\\_____/\n"
                + "    /\\   /\\\n"
                + "   /  \\ /  \\\n";
        System.out.println("Laika: Hi! My name is Laika!\n\n" + logo + "Laika: How can I help you?");

        while(isConvoOngoing){
            line = in.nextLine();
            if (line.startsWith("mark") || line.startsWith("unmark")) {
                try {
                    modifyTask(taskList, line);
                }
                catch (NullPointerException e) {
                    System.out.println("Laika: You dont have so many tasks!");
                }
                continue;
            }

            switch(line){
                case "list":
                    displayTasks(taskList,count);
                    break;
                case "bye":
                    isConvoOngoing = false;
                    break;
                default:

                    try{
                        addTask(taskList, line, count);
                    }
                    catch(LaikaException e) {
                        System.out.println("Laika: Hmmmm, I dont understand you?");
                        break;
                    }
                    lines[count] = line;

                    System.out.println("Laika: Gotcha! I've added the task for you\n  "
                            + taskList[count] + System.lineSeparator()
                            + "You have " + (count + 1) + " tasks in your list. :P");
                    count++;



            }
            try{
                save(lines,count);
            }
            catch (IOException e){
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Laika: Bye! Have a nice day!");
    }
}
