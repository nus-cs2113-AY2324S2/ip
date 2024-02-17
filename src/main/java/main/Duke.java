package main;

import tasks.Task;
import tasks.Todo;
import tasks.Deadline;
import tasks.Event;


import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static File f = new File("data.txt");

    public static boolean checkIfMarked(String line) {
        return line.charAt(4) == 'X';
    }
    private static ArrayList<Task> changePresentationFormat(ArrayList<String> listString) throws DukeException {
        ArrayList<Task> listTask = new ArrayList<>();
        String[] splitEntireLine, splitInput;
        String mark, command;
        for (String task: listString) {
            Task t;
            splitEntireLine = task.split(":", 2); // split by whitespaces
            mark = splitEntireLine[0];
            splitInput = splitEntireLine[1].split(" ");
            command = splitInput[0];

            t = switch (command) {
                // User wants a todo task
                case "todo" -> new Todo(splitEntireLine[1], false);

                // User wants a deadline task
                case "deadline" -> new Deadline(splitEntireLine[1], false);

                // User wants an event task
                case "event" -> new Event(splitEntireLine[1], false);
                default -> null;
            };

            if (mark.equals("Marked")) {
                t.isDone = true;
            }
            listTask.add(t);
        }
        return listTask;
    }

    public static ArrayList<String> loadData() throws DukeException {
        ArrayList<String> listString = new ArrayList<>();
        ArrayList<Task> listTask;

        //try to create a file if it does not exist
        try {
            if (!f.exists()) {
                // Create the missing file
                boolean created = f.createNewFile();
                if (created) {
                    System.out.println("File created successfully.");
                } else {
                    System.out.println("Failed to create the file.");
                }
            }

            //take in all the lines in string data type
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                listString.add(s.nextLine());
            }

            return listString;

            //return listTask;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //save the list in our file as string Data type
    public static void saveData(ArrayList<String> listString) {

        try (FileWriter fw = new FileWriter(f)) {
            for (String task : listString) {
                fw.write(task + System.lineSeparator());
            }
            //fw.write(listString.get(-1) + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static boolean removeElement(ArrayList<Task> list, String[] splitLine) {
        try {
            Task t = list.get(Integer.parseInt(splitLine[1]) - 1);
            list.remove(Integer.parseInt(splitLine[1]) - 1);
            System.out.println("____________________________________________________________");
            System.out.println("Noted. I've removed this task:");
            System.out.println(t);
            System.out.println("Now you have " + list.size() + " tasks in the list.");

        } catch(IndexOutOfBoundsException e) {
            System.out.println("Invalid index, please try again!");
            return false;
        }
        return true;
    }

    public enum Commands {
        Todo, Deadline, Event
    }
    public static boolean addTask(ArrayList<Task> list, String line, String[] splitLine, Commands typeOfTask) {
        Task t;
        boolean success = true;

        switch (typeOfTask) {
            case Todo:
                if (checkMinimumArguments(splitLine, 2)) {
                    success = false;
                    break;
                }
                try {
                    t = new Todo(line, true);
                    System.out.println(t);
                    list.add(t);
                } catch (RuntimeException e) {
                    System.out.println("Invalid Syntax, please try again!");
                    success = false;
                }
                break;

            case Deadline:
                if (checkMinimumArguments(splitLine, 4)) {
                    success = false;
                    break;
                }
                try {
                    t = new Deadline(line, true);
                    System.out.println(t);
                    list.add(t);
                } catch (DukeException e) {
                    System.out.println("Invalid Syntax, please try again!");
                    success = false;
                }
                break;

            case Event:
                if (checkMinimumArguments(splitLine, 8)) {
                    success = false;
                    break;
                }
                try {
                    t = new Event(line, true);
                    System.out.println(t);
                    list.add(t);
                } catch (RuntimeException e) {
                    System.out.println("Invalid Syntax, please try again!");
                    success = false;
                }
                break;

        }
        return success;
    }


    // Function to mark or unmark tasks
    public static void userMarkOrUnmark(String command, String line, ArrayList<Task> listTask, ArrayList<String> listString) {

        // User enters Mark, proceed to check if index is valid. If valid, then mark the task number
        int index;
        String originalString, modifiedString;
        Task t;
        if (command.equals("mark")) {
            try {
                index = Integer.parseInt(line.substring(5));
                originalString = listString.get(index - 1);
                modifiedString = originalString.replace("notMarked:", "Marked:");
                listString.set(index - 1, modifiedString);

            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Task number is not a valid number or index out of bounds!");
                return;
            }
            t = listTask.get(index - 1);
            t.isDone =  true;
            System.out.println("Nice! I've marked this task as done:");


        }

        // User enters unmark, proceed to check if index is valid. If valid, then unmark the task number
        else {
            try {
                index = Integer.parseInt(line.substring(7));
                originalString = listString.get(index - 1);
                modifiedString = originalString.replace("Marked:", "notMarked:");
                listString.set(index - 1, modifiedString);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Task number is not a valid number or index out of bounds!");
                return;
            }
            t = listTask.get(index - 1);
            t.isDone =  false;
            System.out.println("OK, I've marked this task as not done yet:");


        }

        saveData(listString);
        System.out.println(t);
        System.out.println("____________________________________________________________");
    }

    // Function to print the list of tasks
    public static void userList(ArrayList<Task> list) {
        for (Task task : list) {
            if (task == null) {
                break;
            }
            System.out.println(list.indexOf(task) + 1 + ". " + task);
        }
        System.out.println("____________________________________________________________");
    }

    // Function to say bye
    public static void userBye() {
        System.out.println("Bye human. Come back soon !");
    }

    // Function to print an unknown command from user
    public static void userWrongCommand() {
        System.out.println("No suitable command found. Please try again!");
    }

    // Function to check the minimum arguments supplied for each task
    public static boolean checkMinimumArguments(String[] splitLine, int number) {
        try {
            if (splitLine.length < number) {
                throw new DukeException("Invalid Syntax! Please try again!");
            }
        } catch (DukeException e) {
            System.out.println("Minimally " + number + " arguments, please try again!");
            return true;
        }
        return false;
    }

    // Start of user input
    public static void userInput() throws DukeException {
        Scanner scanner = new Scanner(System.in);
        String line;

        ArrayList<String> listString = loadData();
        ArrayList<Task> list = changePresentationFormat(listString);

        // Start of user input
        while (true) {
            line = scanner.nextLine().toLowerCase(); // Takes in user input
            String[] splitLine = line.split("\\s+"); // split by whitespaces
            String command = splitLine[0]; //obtain the main command from user, which is the first command
            String savedLine = "";

            switch (command) {
                // User wants to exit
                case "bye":
                    userBye();
                    break;

                // User wants to display the list of tasks
                case "list":
                    if (splitLine.length != 1) {
                        userWrongCommand();
                        break;
                    }
                    userList(list);
                    continue;

                    // User wants to mark or unmark tasks
                case "mark":
                case "unmark":
                    userMarkOrUnmark(splitLine[0], line, list, listString);
                    continue;

                    // User wants a todo task
                case "todo":
                    if (addTask(list, line, splitLine, Commands.Todo)) {
                        System.out.println("Now you have " + list.size() + " tasks in the list.");
                        savedLine = "notMarked:" + line;
                        listString.add(savedLine);
                        saveData(listString);
                    }
                    continue;

                    // User wants a deadline task
                case "deadline":
                    if (addTask(list, line, splitLine, Commands.Deadline)) {
                        System.out.println("Now you have " + list.size() + " tasks in the list.");
                        savedLine = "notMarked:" + line;
                        listString.add(savedLine);
                        saveData(listString);
                    }
                    continue;

                    // User wants an event task
                case "event":
                    if (addTask(list, line, splitLine, Commands.Event)) {
                        System.out.println("Now you have " + list.size() + " tasks in the list.");
                        savedLine = "notMarked:" + line;
                        listString.add(savedLine);
                        saveData(listString);
                    }
                    continue;

                    // User wants to delete task
                case "delete":
                    if (removeElement(list, splitLine)) {
                        listString.remove(savedLine);
                        saveData(listString);
                        continue;
                    }

                default:
                    System.out.println("No suitable command found. Please try again!");
                    continue;
            }
            scanner.close(); // Close scanner after usage
            return;
        }
    }



    public static void main(String[] args) throws DukeException {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        userInput();
    }
}