import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;

public class Duke {
    static boolean ifExit = false; //exits program if true
    static ArrayList<Task> tasks = new ArrayList<>(); //List of tasks

    public static void echoTask() {
        System.out.println("--------------------------------------");
        System.out.println("Got it! I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1));
        System.out.println("--------------------------------------");
    }

    public static void printBye() {
        System.out.println("--------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("--------------------------------------");
        ifExit = true;
    }

    public static void printList() throws IllegalShapeException {
        {
            if (tasks.isEmpty()) {
                throw new IllegalShapeException(); //Throws exception for empty list
            }
            System.out.println("--------------------------------------");
            System.out.println("Here are the tasks in your lists:");
            for (Task item : tasks) {
                System.out.print((tasks.indexOf(item) + 1) + "."); //+ 1 as ArrayList index starts from 0
                System.out.println(item);
            }
            System.out.println("--------------------------------------");
        }
    }

    public static void introStart() {
        String logo
                = " _______    ___           _____   _____ ___    ___\n"
                + "|   ____\\___\\  \\___    __/   __| /   __|\\  \\__/  /\n"
                + " \\   \\   \\___  ____\\__|__    ___|    ___|\\_   __/\n"
                + "  \\   \\     |  | /  _  \\|   |    |  |     /  / \n"
                + " __\\   \\    |  ||    __/|   |    |  |  __/  / \n"
                + "/_______|   |__| \\_____||___|    |__| |____/ \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("--------------------------------------");
        System.out.println("Hello! I'm Steffy");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------------------");
    }

    public static void markTask(String line) throws IllegalShapeException {
        if (Integer.parseInt(line.substring(5)) >= tasks.size() ) {
            throw new IllegalShapeException(); //Throws exception for marking out of bounds
        }
        tasks.get(Integer.parseInt(line.substring(5)) - 1).markAsDone();
        System.out.println("--------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println
                (tasks.get(Integer.parseInt(line.substring(5)) - 1).getStatusIcon() + " "
                        + tasks.get(Integer.parseInt(line.substring(5)) - 1).description);
        System.out.println("--------------------------------------");
    }

    public static void unmarkTask(String line) throws IllegalShapeException {
        if (Integer.parseInt(line.substring(7)) > tasks.size()) {
            throw new IllegalShapeException(); //Throws exception for unmarking out of bounds
        }
        tasks.get(Integer.parseInt(line.substring(7)) - 1).unmarkDone();
        System.out.println("--------------------------------------");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println
                (tasks.get(Integer.parseInt(line.substring(7)) - 1).getStatusIcon() + " "
                        + tasks.get(Integer.parseInt(line.substring(7)) - 1).description);
        System.out.println("--------------------------------------");
    }

    public static void deleteTask(int i) {
        System.out.println("--------------------------------------");
        System.out.println("Noted, I have removed this task:");
        System.out.println(tasks.get(i));
        tasks.remove(tasks.get(i));
        System.out.println("Now you have " + tasks.size() + " tasks left");
        System.out.println("--------------------------------------");
    }

    private static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter("dukeLog.txt", true);
        fw.write(textToAdd);
        fw.close();
    }

    public static void performAction(String line) throws IllegalShapeException{
        int eventDividerPositionTo = line.indexOf("/to");
        int eventDividerPositionFrom = line.indexOf("/from");
        int deadlineDividerPositionBy = line.indexOf("/by");

        if(line.isEmpty()) {
            throw new IllegalShapeException(); //Throws error for empty inputs
        }

        switch(line.split(" ")[0].toLowerCase()) {
        case "bye": //Exits program with farewell dialogue
            printBye();
            break;
        case "list": //Shows entire list of tasks
            try {
                printList();
            } catch (IllegalShapeException e) {
                System.out.println("--------------------------------------");
                System.out.println("Your list is empty!");
                System.out.println("--------------------------------------");
            }
            break;
        case "unmark": //unmark a task
            try {
                unmarkTask(line);
                try {
                    writeToFile(line + System.lineSeparator());
                } catch (IOException e){
                    System.out.println("Something went wrong: " + e.getMessage());
                }

            } catch (IllegalShapeException e) {
                System.out.println("--------------------------------------");
                System.out.println("There is no such Task! :( ");
                System.out.println("--------------------------------------");
            }
            break;
        case "mark": //marks a task as done
            try {
                markTask(line);
                try {
                    writeToFile(line + System.lineSeparator());
                } catch (IOException e){
                    System.out.println("Something went wrong: " + e.getMessage());
                }

            } catch (IllegalShapeException e) {
                System.out.println("--------------------------------------");
                System.out.println("There is no such Task! :( ");
                System.out.println("--------------------------------------");
            }
            break;
        case "todo": //add a new task
            tasks.add(new Todo(line));
            echoTask();
            try {
                writeToFile(line + System.lineSeparator());
            } catch (IOException e){
                System.out.println("Something went wrong: " + e.getMessage());
            }

            break;
        case "event": //add a new event task
            tasks.add(new Event(line.substring(0, eventDividerPositionFrom).trim(), line.substring(eventDividerPositionFrom + 5,eventDividerPositionTo).trim(), line.substring(eventDividerPositionTo + 3).trim()));
            echoTask();
            try {
                writeToFile(line + System.lineSeparator());
            } catch (IOException e){
                System.out.println("Something went wrong: " + e.getMessage());
            }

            break;
        case "deadline": //add a new deadline task
            tasks.add(new Deadline(line.substring(0, deadlineDividerPositionBy).trim(), line.substring(deadlineDividerPositionBy + 3).trim()));
            echoTask();
            try {
                writeToFile(line + System.lineSeparator());
            } catch (IOException e){
                System.out.println("Something went wrong: " + e.getMessage());
            }

            break;
        case "delete":
            deleteTask(Integer.parseInt(line.substring(7)) - 1);
            try {
                writeToFile(line + System.lineSeparator());
            } catch (IOException e){
                System.out.println("Something went wrong: " + e.getMessage());
            }
            break;
        default:
            System.out.println("--------------------------------------");
            System.out.println("Sorry! I don't know what that means!");
            System.out.println("--------------------------------------");
        }

    }

    public static void main(String[] args) {
        introStart(); //Prints starting screen

        while (!ifExit) {
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            try {
                performAction(line); //Executes an action based on first word of command in String line
            } catch (IllegalShapeException e) {
                System.out.println("--------------------------------------");
                System.out.println("Your input is empty!");
                System.out.println("--------------------------------------");
            }
        }
    }
}
