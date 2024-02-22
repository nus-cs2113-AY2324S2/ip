import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
public class OGF {

    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final String STORAGE_PATH_NAME = "data/tasklist.txt";



    private static boolean isRunning = true;
    private static void printBreakLine(){
        System.out.println(("____________________________________________________________"));
    }

    private static void printTaskAdded(Task task){
        System.out.println("Alright, adding this task to the list: ");
        System.out.println(task);
        System.out.printf("You have %d tasks in the list.%n", tasks.size());
        printBreakLine();
    }

    private static void printAllTasks(){
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i + 1 + ". ");
            System.out.println(tasks.get(i));
        }
        printBreakLine();
    }
    private static boolean handleInput (String input) throws OGFException{
        switch (input.split(" ")[0]) {
            case ("bye"): // exits program
                System.out.println("Bye bye now!");
                return (false);
            case ("list"):// lists all the tasks
                System.out.println("Here are your tasks for today: ");
                printAllTasks();
                break;
            case ("delete"):
                if (!input.contains(" ")){
                    throw new OGFException("did not indicate task to delete");
                }
                int taskToDelete = Integer.parseInt(input.split(" ")[1]) - 1;
                System.out.println("Deleting task: " + tasks.get(taskToDelete));
                tasks.remove(taskToDelete);
                System.out.println("Here are your tasks now: ");
                printAllTasks();
                break;
            case ("mark"):
                //Fallthrough
            case ("unmark"):
                if (!input.contains(" ")){
                    throw new OGFException("Looks like you didn't indicate task to mark/unmark. \nFormat: mark/unmark [number] e.g. mark 1 to mark the first task.");
                }
                int taskNo = Integer.parseInt(input.split(" ")[1]) - 1;
                if (input.split(" ")[0].equals("mark")) {
                    System.out.println("Good Job! I'm setting this task as done: ");
                    tasks.get(taskNo).setDone(true);
                } else {
                    System.out.println("Oop! I'm setting this task as undone: ");
                    tasks.get(taskNo).setDone(false);
                }
                System.out.println(tasks.get(taskNo));
                printBreakLine();
                overwriteFileWithList();
                break;
            case ("todo"):
                if (!input.contains(" ") || input.indexOf(" ") == input.length()-1){
                    throw new OGFException("Did not write anything after \"todo\", dont waste my time and yours pls");
                }
                String newTodoDesc = input.substring(input.indexOf(" ")+1);

                tasks.add( new Todo(newTodoDesc));
                printTaskAdded(tasks.get(tasks.size()-1));
                writeToFile(tasks.get(tasks.size()-1).toSerial() + System.lineSeparator(), true);
                overwriteFileWithList();
                break;
            case ("deadline"):
                if (!input.contains(" ")){
                    throw new OGFException("Did not write anything after \"deadline\", dont waste my time and yours pls");
                }
                if (!input.contains(" /by ")){
                    throw new OGFException("Did not enter deadline, use /by, followed by your deadline to add a deadline to your deadline");
                }
                String newDeadlineDesc = input.substring(input.indexOf(" ")+1, input.indexOf(" /by"));
                String newDeadlineTime = input.substring(input.indexOf("/by") + 4);
                tasks.add(new Deadline(newDeadlineDesc, newDeadlineTime));
                printTaskAdded(tasks.get(tasks.size()-1));
                writeToFile(tasks.get(tasks.size()-1).toSerial() + System.lineSeparator(), true);
                break;
            case ("event"):
                if (!input.contains(" ")){
                    throw new OGFException("Did not write anything after \"event\", dont waste my time and yours pls");
                }
                if (!input.contains(" /from ") || !input.contains(" /to ")){
                    throw new OGFException("Did not enter event start and end, use /from and /to to enter start and end time respectively");
                }
                String newEventDesc = input.substring(input.indexOf(" ")+1, input.indexOf(" /from"));
                String newEventStart = input.substring(input.indexOf("/from") + 6, input.indexOf(" /to"));
                String newEventEnd = input.substring(input.indexOf("/to") + 4);
                tasks.add(new Event(newEventDesc, newEventStart, newEventEnd));
                printTaskAdded(tasks.get(tasks.size()-1));
                writeToFile(tasks.get(tasks.size()-1).toSerial() + System.lineSeparator(), true);
                break;

            default:
                throw new OGFException("unrecognised input");

        }
        return true;
    }

    private static Task parseStoredTask(String data) throws OGFException{
        String[] params = data.split(",");
        switch (params[0]){
            case ("todo"):
                return new Todo(params[1], Boolean.parseBoolean(params[2]));
            case ("deadline"):
                return new Deadline(params[1], params[3], Boolean.parseBoolean(params[2]));
            case ("event"):
                return new Event(params[1], params[3], params[4], Boolean.parseBoolean(params[2]));
            default:
                throw(new OGFException(data));
        }
    }
    private static void writeToFile(String textToAdd, boolean willAppend) {
        try {
            FileWriter fw = new FileWriter(STORAGE_PATH_NAME, willAppend);
            fw.write(textToAdd);
            fw.close();
        }
        catch(IOException e){
            System.out.println("Write file error: " + e);
        }
    }

    private static void overwriteFileWithList(){
        String listSerial = "";
            for (int i = 0; i < tasks.size();i++){
                listSerial = listSerial + tasks.get(i).toSerial() + System.lineSeparator();
            }
            writeToFile(listSerial, false);

    }
    public static void main(String[] args) {
try {
    File storedList = new File(STORAGE_PATH_NAME);
    if (!storedList.exists()) {
        storedList.getParentFile().mkdirs();
        storedList.createNewFile();
    }
    Scanner storageScanner = new Scanner(storedList);

        while (storageScanner.hasNext()) {

            tasks.add(parseStoredTask(storageScanner.nextLine()));
        }
    }
    catch(OGFException | ArrayIndexOutOfBoundsException e){
            System.out.println("File corruption error: " + e);
    }
    catch(IOException e){
    System.out.println("Couldn't load file: " + e);
    }


        String logo = "\n" +
                " _____  _    _                   _____ ______ \n" +
                "|  _  || |  | |                 |  __ \\|  ___|\n" +
                "| | | || |_ | |__    ___  _ __  | |  \\/| |_   \n" +
                "| | | || __|| '_ \\  / _ \\| '__| | | __ |  _|  \n" +
                "\\ \\_/ /| |_ | | | ||  __/| |    | |_\\ \\| |    \n" +
                " \\___/  \\__||_| |_| \\___||_|     \\____/\\_|    \n" +
                "                                              \n" +
                "                                              \n";
        System.out.println("Welcome! I'm your \n" + logo + "Nice to meet you!");
        System.out.println("What can I do for you?");

        Scanner inputScanner = new Scanner(System.in);
        while (isRunning) {
            String input = inputScanner.nextLine();
            try {
                isRunning = handleInput(input);
            }
            catch (OGFException error){
                System.out.println(error.getMessage());
                printBreakLine();
            }
            catch (NumberFormatException error){
                System.out.println("Looks like the input you entered was not an integer: " + input);
                printBreakLine();
            }
            catch (IndexOutOfBoundsException error){
                System.out.println("Whoops, looks like you tried to access an index not in the list! :)");
                printBreakLine();
            }
            }
        }

    }

