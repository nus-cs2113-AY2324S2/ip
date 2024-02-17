package duke;

import duke.tasks.Deadlines;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.tasks.Events;
import duke.tasks.ToDos;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class Duke {
    /**
     * Greets the user when the program starts
     */
    public static void printGreeting(){
        // Welcomes user
        printLine();
        System.out.println("    Hello! I'm PoratoBot");
        System.out.println("    How can I assist you mortal?");
        printLine();
    }

    /**
     * Loads the file contents into the task list
     * @param tasklist The task list
     * @throws FileNotFoundException File is not found exception
     * @throws DukeException File is corrupted exception
     */
    public static void loadFile(TaskList tasklist) throws FileNotFoundException, DukeException {
        // Creates a file object
        File dukeFile = new File("src/main/java/duke.txt");
        // Create a Scanner using the File as the source
        Scanner line = new Scanner(dukeFile);
        // Loads the file contents into the task list
        while (line.hasNext()) {
            String[] sentence = line.nextLine().split("/");
            switch (sentence[0]){
            case "T":
                Task todoTask = new ToDos(sentence[2]);
                tasklist.addTask(todoTask);
                if(sentence[1].equals(" 1 ")){
                    todoTask.setTaskStatus(true);
                }
                break;
            case "D":
                Task deadlineTask = new Deadlines(sentence[2], sentence[3]);
                tasklist.addTask(deadlineTask);
                if(sentence[1].equals(" 1 ")){
                    deadlineTask.setTaskStatus(true);
                }
                break;
            case "E":
                Task eventTask = new Events(sentence[2], sentence[3], sentence[4]);
                tasklist.addTask(eventTask);
                if(sentence[1].equals(" 1 ")){
                    eventTask.setTaskStatus(true);
                }
                break;
            default:
                throw new DukeException("File is corrupted or has invalid format");
            }
        }
    }



    /**
     * Prints a line
     */
    public static void printLine(){
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints out the commands for the chatbot
     */
    public static void printCommands(){
        printLine();
        System.out.println("    These are the commands available:");
        System.out.println("    list\n    mark\n    unmark\n    todo\n    deadline\n    event\n    help");
        printLine();
    }

    /**
     * Checks if the string is a number
     * @param str The string that is to be defined as a number or sentence
     * @return true or false
     */
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    public static void main(String[] args) {
        // Creates a class of TaskList
        TaskList taskList = new TaskList(100);
        // Loads Duke.txt file
        try{
            loadFile(taskList);
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        } catch (DukeException e) {
            System.out.println(e);
        }
        String line;
        Scanner in = new Scanner(System.in);
        boolean userSaidBye = false;
        printGreeting();

        while (!userSaidBye) {
            try {
                // Takes in user input
                line = in.nextLine();
                if (line.equals("bye")) {
                    userSaidBye = true;

                } else if (line.equals("list")) {
                    // Prints the all the tasks in the list
                    printLine();
                    System.out.println("     Here are the tasks in your list:");
                    taskList.listTasks();
                    printLine();

                } else if (line.startsWith("mark")) {
                    // Marks the task in the list
                    String[] sentence = line.split(" ");
                    if(sentence.length < 2 || !isNumeric(sentence[1])){
                        throw new DukeException("Please do not give a non numeric or empty description\nMark format: mark 1");
                    }
                    printLine();
                    System.out.println("     Nice! I've marked this task as done:");
                    taskList.checkTask(Integer.parseInt(sentence[1]));
                    System.out.println(" ");
                    printLine();

                } else if (line.startsWith("unmark")) {
                    // Unmarks the task in the list
                    String[] sentence = line.split(" ");
                    if(sentence.length < 2 || !isNumeric(sentence[1])){
                        throw new DukeException("Please do not give a non numeric or empty description\nUnmark format: unmark 1");
                    }
                    System.out.println("     OK, I've marked this task as not done yet:");
                    taskList.uncheckTask(Integer.parseInt(sentence[1]));
                    printLine();

                } else if (line.startsWith("deadline")) {
                    // Adds a deadline task into the list
                    String[] sentence = line.split("deadline|/by");
                    if(sentence.length < 3 || sentence[1].isBlank() || sentence[2].isBlank()){
                        throw new DukeException("ERROR!?!? deadline has an incomplete or empty description mortal!\nDeadline format: deadline help me /by Mon 2pm");
                    }
                    Task newTask = new Deadlines(sentence[1], sentence[2]);
                    taskList.addTask(newTask);
                    printLine();
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + newTask);
                    System.out.println("     Now you have " + taskList.getNoOfTasks() + " tasks in the list.");
                    printLine();

                } else if (line.startsWith("event")) {
                    // Adds an event task into the list
                    String[] sentence = line.split("event|/from|/to");
                    if(sentence.length < 4 || sentence[1].isBlank() || sentence[2].isBlank() || sentence[3].isBlank()){
                        throw new DukeException("ERROR!?!? event has an incomplete or empty description mortal!\nEvent format: event help me /from Mon 2pm /to 3pm");
                    }
                    Task newTask = new Events(sentence[1], sentence[2], sentence[3]);
                    taskList.addTask(newTask);
                    printLine();
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + newTask);
                    System.out.println("     Now you have " + taskList.getNoOfTasks() + " tasks in the list.");
                    printLine();

                } else if (line.startsWith("todo")) {
                    // Adds an todo task into the list
                    String[] sentence = line.split("todo");
                    // Checks if the description of the task is empty
                    if(sentence.length < 2 || sentence[1].isBlank()){
                        throw new DukeException("ERROR!?!? todo has to have a description mortal!\nTodo format: todo help me");
                    }
                    Task newTask = new ToDos(sentence[1]);
                    taskList.addTask(newTask);
                    printLine();
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + newTask);
                    System.out.println("     Now you have " + taskList.getNoOfTasks() + " tasks in the list.");
                    printLine();

                } else if (line.startsWith("help")){
                    printCommands();
                } else {
                    // Throws an invalid command error
                    throw new DukeException("I do not understand this command mortal\nType help for help");
                }
                taskList.saveTaskList();
            } catch (DukeException e){
                System.out.println(e);
            } catch (IOException e) {
                System.out.println("Something went wrong while saving file");
            }


        }
        // Exits program when user says bye
        System.out.println("     Bye. Hope to see you again soon!");
        printLine();
    }
}

