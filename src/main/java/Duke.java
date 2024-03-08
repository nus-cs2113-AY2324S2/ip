import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Duke {

    static ArrayList<Task> tasks = new ArrayList<>(); //List of tasks
    static boolean ifNewWrite = false;
    private static UI ui;

    /**
     * Prints out latest task that has been added to list as an echo
     */
    public static void echoTask() {
        System.out.println("--------------------------------------");
        System.out.println("Got it! I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1));
        System.out.println("--------------------------------------");
    }

    /**
     * Prints out entire list of tasks that has been added when "list" command is entered into CLI
     *
     * @throws IllegalShapeException if tasks is empty
     */
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

    /**
     * Prints list of tasks that have descriptions containing the searched keyword
     *
     * @param line string to find tasks that contains this string
     * @throws IllegalShapeException when task list is empty
     */
    public static void printFindList(String line) throws IllegalShapeException {
        {
            int counter = 1;
            if (tasks.isEmpty()) {
                throw new IllegalShapeException(); //Throws exception for empty list
            }
            System.out.println("--------------------------------------");
            System.out.println("Here are the matching tasks in your lists:");
            for (Task item : tasks) {
                if (item.description.contains(line)) {
                    System.out.print(counter + ".");
                    System.out.println(item);
                    counter ++;
                }
            }
            System.out.println("--------------------------------------");
        }
    }

    /**
     * Sets status of the task to true. Prints dialogue of task being marked. Shows that task is completed.
     *
     * @param line string that the user has inputted
     * @throws IllegalShapeException when user tries to mark a task index that does not exist or is out of bounds.
     */
    public static void markTask(String line) throws IllegalShapeException {
        if (Integer.parseInt(line.substring(5)) > tasks.size() ) {
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

    /**
     * Set status of the task to false. Prints dialogue of task being unmarked. Shows that task is not completed yet.
     *
     * @param line string that the user has inputted
     * @throws IllegalShapeException when user tries to unmark a task index that does not exist or is out of bounds
     */
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

    /**
     * Deletes the task with the index of int i from tasks list.
     * Prints out the number of tasks remaining
     *
     * @param i the task index of the task to be deleted
     */
    public static void deleteTask(int i) {
        System.out.println("--------------------------------------");
        System.out.println("Noted, I have removed this task:");
        System.out.println(tasks.get(i));
        tasks.remove(tasks.get(i));
        System.out.println("Now you have " + tasks.size() + " tasks left");
        System.out.println("--------------------------------------");
    }

    /**
     * Writes the command user has given to the CLI into a txt file
     * Does not include commands such as "bye", "list" and "find"
     *
     * @param textToAdd string that user has inputted
     * @throws IOException when error occurred during writing
     */
    private static void writeToFile(String textToAdd) throws IOException {
        if (ifNewWrite) {
            FileWriter fw = new FileWriter("dukeLog.txt", true);
            fw.write(textToAdd);
            fw.close();
        }
    }

    /**
     * Performs an action based on what the String line inputted by the user is.
     * Will add task, delete task, print list, find tasks, mark tasks, unmark tasks, end the program or input invalid command
     *
     * @param line String line that the user has inputted
     * @throws IllegalShapeException when line is empty and no action can be taken
     */
    public static void performAction(String line) throws IllegalShapeException{
        int eventDividerPositionTo = line.indexOf("/to");
        int eventDividerPositionFrom = line.indexOf("/from");
        int deadlineDividerPositionBy = line.indexOf("/by");

        if(line.isEmpty()) {
            throw new IllegalShapeException(); //Throws error for empty inputs
        }

        switch(line.split(" ")[0].toLowerCase()) {
        case "bye": //Exits program with farewell dialogue
            ui.printBye();
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
        case "find":
            printFindList(line.substring(4));
            break;
        default:
            System.out.println("--------------------------------------");
            System.out.println("Sorry! I don't know what that means!");
            System.out.println("--------------------------------------");
        }

    }

    /**
     * Inputs the commands saved in the txt file so to resume from previous tasks list
     *
     * @throws FileNotFoundException when there is no file found
     */
    public static void inputFileContents() throws FileNotFoundException {
        File f = new File("dukeLog.txt");
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            try {
                performAction(s.nextLine()); //Executes an action based on first word of command in String line
            } catch (IllegalShapeException e) {
                System.out.println("--------------------------------------");
                System.out.println("Your input is empty!");
                System.out.println("--------------------------------------");
            }
        }
        ifNewWrite = true;
    }

    public static void main(String[] args) {
        ui = new UI();
        ui.logo();
        ui.greeting();

        try {
            inputFileContents();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        while (!ui.ifExit) {
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
