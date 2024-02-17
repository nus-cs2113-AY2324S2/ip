package Chat.tasks;

import Chat.exceptions.RepeatMark;
import Chat.exceptions.RepeatUnmark;
import Chat.exceptions.InvalidIndex;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import java.util.ArrayList;
import java.io.File;


public class Evelyn {

    public static ArrayList<Task> tasks;
    public static int indexOfTask = 0;

    public static void markTask(int index, boolean done) throws InvalidIndex, RepeatMark, RepeatUnmark {
        if(index < 0 || index >= indexOfTask){
            throw new InvalidIndex();
        }

        if (done) {
            try {
                tasks.get(index).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
            } catch(RepeatMark e){
                System.out.println("This task is already marked as done.");
            }
        } else {
            try {
                tasks.get(index).markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
            } catch(RepeatUnmark e){
                System.out.println("This task is already unmarked as not done.");
            }
        }
        System.out.println("  " + tasks.get(index).getStatusIcon() + " "
                               + tasks.get(index).description);
        printLine();

    }

    public static void printList(ArrayList<Task> tasks){
        int index = 1;
        for (Task task : tasks) {
            if (task == null) {
                printLine();
                break;
            } else {
                System.out.println(index + ". " + tasks.get(index - 1).toString());
                index++;
            }
        }
    }

    public static void echo() {
        String line;
        System.out.println("type your command: ");
        Scanner in = new Scanner(System.in);
        line = in.nextLine().trim();
        printLine();
        if (line.equals("bye")) {
            return;
        } else if (line.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                printList(tasks);

        } else if (line.startsWith("mark")) {
            mark(line);

        } else if (line.startsWith("unmark")) {
            unmark(line);

        } else if (line.startsWith("todo")) {
            todo(line);
        } else if (line.startsWith("deadline")) {
            deadline(line);

        } else if (line.startsWith("event")) {
            event(line);
        } else if(line.startsWith("delete")){
            delete(line);
        } else {
            System.out.println("Please enter the correct command");
        }
        echo();
    }
    private static void delete(String line){
        try {
            int index = Integer.parseInt(line.substring(7).trim()) - 1;
            try {
                tasks.remove(index);
                int add1toIndex = index + 1;
                System.out.println("Ok! Task " + add1toIndex + " is removed from the list");
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Invalid task index, please try again!");
            }
        } catch (NumberFormatException e){
            System.out.println("Please key in a number after 'mark'");
        }
    }
    private static void event(String line) {
        boolean haveFrom = line.contains("/from");
        boolean haveTo = line.contains("/to");
        if (haveFrom && haveTo) {
            try {
                String[] parts = line.substring(6).split("/from");
                String description = parts[0].trim();
                String[] date = parts[1].trim().split("/to");
                String from = date[0].trim();
                String to = date[1].trim();
                tasks.add(new Events(description, from, to));
                indexOfTask++;
                printAddingWords();
                printLine();
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Please enter a description for the event!");
            }
        } else {
            System.out.println("Please enter a time in the format of '/from (time) /to (time)'");
        }
    }

    private static void deadline(String line) {
        boolean haveBy = line.contains("/by");
        if (haveBy) {
            try {
                String[] parts = line.substring(9).split("/by");
                String description = parts[0].trim();
                String by = parts[1].trim();
                tasks.add(new Deadlines(description, by));
                indexOfTask++;
                printAddingWords();
                printLine();
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Please enter a description for the deadline!");
            }
        } else {
            System.out.println("Please enter a time in the format of 'by/ (time)'");
        }
    }

    private static void todo(String line) {
        try {
            tasks.add(new Todos(line.substring(5).trim()));
            indexOfTask++;
            printAddingWords();
            printLine();
        } catch(StringIndexOutOfBoundsException e){
            System.out.println("Please enter a description for the todo task!");
        }
    }

    private static void unmark(String line) {
        try {
            int index = Integer.parseInt(line.substring(7).trim()) - 1;
            try {
                markTask(index, false);
            }
            catch (RepeatMark | RepeatUnmark e){
                System.out.println("This task is already marked.");
            }
            catch(InvalidIndex e){
                System.out.println("Invalid task index, please try again!");
            }
        } catch(NumberFormatException e){
            System.out.println("Please key in a number after 'unmark'");
        }
    }

    private static void mark(String line) {
        try {
            int index = Integer.parseInt(line.substring(5).trim()) - 1;
            try {
                markTask(index, true);
            } catch (InvalidIndex e){
                System.out.println("Invalid task index, please try again!");
            } catch (RepeatMark | RepeatUnmark e){
                System.out.println("This task is already marked.");
            }
        } catch (NumberFormatException e){
            System.out.println("Please key in a number after 'mark'");
        }
    }

    private static void printAddingWords() {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(indexOfTask - 1));
        System.out.println("Now you have " + indexOfTask + " tasks in the list.");
    }

    public static void printLine() {
        System.out.print("____________________________________________________________\n");
    }

    public static void greeting() {
        printLine();
        System.out.println("Hello! I'm Evelyn");
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void end() {
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void readFile(ArrayList<Task> tasks) throws FileNotFoundException {
        File file = new File("tasks.txt");
        if(file.exists()){
            try{
                Scanner sc = new Scanner(file);
                while(sc.hasNext()){
                    String stringTask = sc.nextLine();
                    Task pastTask = Task.fromString(stringTask);
                    tasks.add(pastTask);
                    indexOfTask++;
                }
            }catch (IOException e){
                System.out.println("error in reading the file");
            } catch (RepeatMark e) {
                System.out.println("This task is already marked.");
            }
        }
        else{
            try{
                System.out.println("creating a new file...\n");
                file.createNewFile();
            } catch(IOException e){
                System.out.println("error in creating new file");
            }
        }
    }
    public static void saveToFile() throws IOException{
        try{
            FileWriter fw = new FileWriter(("tasks.txt"));
            for(int i = 0; i<indexOfTask;i++){
                //fw.write(tasks[i].toString() + "\n");
                fw.write(tasks.get(i).shortType + " | " + tasks.get(i).numisDone() +
                            " | " + tasks.get(i).getDescription() + " | " + tasks.get(i).time + "\n");

            }
            fw.close();
        } catch(IOException e){
            System.out.println("error in saving the file");
        }
    }
    public static void main(String[] args) throws IOException {
        greeting();
        tasks = new ArrayList<>();
        readFile(tasks);
        echo();
        saveToFile();
        System.out.println("all changes saved to file");
        end();
    }

}
