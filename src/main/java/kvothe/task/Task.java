package kvothe.task;
import kvothe.exception.WrongArgumentsException;

import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a task; something to be done.
 */
public class Task {

    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    //TODO: keep track of the received args
    /**
     * Parses the line and returns the arguments to create a new task.
     * @param line the line to parse
     * @param validArgs expected arguments for the task.
     * @return the values for the arguments.
     * @throws WrongArgumentsException if the arguments in the input are invalid
     */
    public static String[] parseLine(String line, String[] validArgs) throws WrongArgumentsException {

        String args[] = line.split(" ");
        String newArgs[] = new String[validArgs.length + 1];
        String aux = "";

        int i;
        int indexOfNewArgs = 0;
        int indexOfValidArgs = 0;
        int lenOfArgGiven = 0;

        for (i = 1; i < args.length; i++) {
            String word = args[i];
            if (word.equals(validArgs[Math.min(indexOfValidArgs, validArgs.length - 1)])) {
                if (lenOfArgGiven == 0) {
                    String name = indexOfValidArgs > 0 ? validArgs[indexOfValidArgs - 1] : "description";
                    throw new WrongArgumentsException("No value for " + name);
                }

                newArgs[indexOfNewArgs++] = aux.trim();
                aux = "";
                indexOfValidArgs++;
                lenOfArgGiven = 0;
            } else if (word.startsWith("/")) {
                //We are forcing to receive the arguments in one order.
                //TODO: consider to allow the user to input the arguments in any order
                throw new WrongArgumentsException("Invalid argument " + word +
                        ". Expected " + validArgs[indexOfValidArgs]);
            } else {
                aux += word + " ";
                lenOfArgGiven++;
            }
        }

        newArgs[indexOfNewArgs] = aux.trim();

        if (lenOfArgGiven == 0) {
            String name = indexOfValidArgs > 0 ? validArgs[indexOfValidArgs - 1] : "description";
            throw new WrongArgumentsException("No value for " + name);
        }

        if (indexOfValidArgs != validArgs.length) {
            String message = "Missing arguments. Expected ";
            for (String arg : validArgs) {
                message += arg + " ";
            }
            throw new WrongArgumentsException(message);
        }

        return newArgs;
    }

    /**
     * Parses the line and returns the arguments to create a new task of type TODO.
     * @param line the line to parse
     * @return the values for the arguments.
     * @throws WrongArgumentsException if the arguments in the input are invalid
     */
    public static String[] parseLine(String line) throws WrongArgumentsException {

        String args[] = line.split(" ");
        String newArgs[] = new String[1];
        String aux = "";

        for (int i = 1; i < args.length; i++) {
            String word = args[i];
            if (word.startsWith("/")) {
                throw new WrongArgumentsException("kvothe.task.Todo does not accept arguments.");
            } else {
                aux += word + " ";
            }
        }

        if (aux.isEmpty()) {
            throw new WrongArgumentsException("No value for description." );
        }

        newArgs[0] = aux.trim();

        return newArgs;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean isDone){
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); //return tick or X symbols
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String toFileString() {
        String status = this.isDone ? "1" : "0";
        return  "|" + status + "|" + this.description + "|";
    }

    /**
     * Writes a task to a file.
     * @param tasks the tasks to write to the file
     * @param filename the name of the file where to write the tasks
     */
    public static void dumpToFile(ArrayList<Task> tasks, String filename){

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false));

            for (Task task : tasks) {
                writer.write(task.toFileString());
                // [Type][Done] descr (arg1: value1, arg2: value2 ...)
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    /**
     * Reads tasks from a file and returns an array of tasks.
     * @param filename the name of the file where to read the tasks
     * @return the tasks read from the file
     */
    public static ArrayList<Task> loadFromFile(String filename){
        ArrayList<Task> tasks = new ArrayList<Task>();

        try {
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                char type = line.charAt(0);
                boolean isDone = line.charAt(2) == '1';
                String args[] = line.split("\\|");
                switch (type) {
                case 'T':
                    tasks.add(new Todo(args[2]));
                    break;
                case 'D':
                    tasks.add(new Deadline(args[2], args[3]));
                    break;
                case 'E':
                    tasks.add(new Event(args[2], args[3], args[4]));
                    break;
                default:
                    System.err.println("Invalid task type: " + type);
                    break;
                }

                tasks.get(tasks.size() - 1).setIsDone(isDone);

            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading from the file: " + e.getMessage());
        }

        return tasks;
    }

}



