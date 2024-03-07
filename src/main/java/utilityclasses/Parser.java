package utilityclasses;
import drosstasks.DrossList;
import utilityclasses.TaskActions;
import java.util.Scanner;


public class Parser {
    private static Scanner in = new Scanner(System.in);
    private static TaskActions actions;

    //Method to read in a line of text from standard input
    public static String readLine() {
        return in.nextLine().trim();
    }

    //Method to determine what to do based on the input entered
    public static void readParseExecuteUserInput(DrossList list) {
        actions = new TaskActions();
        String line = readLine();
        while (!line.equals("bye")) {
            if (line.startsWith("list")) {
                actions.listAllTasks(list);
            } else if (line.startsWith("mark") || line.startsWith("unmark")) {
                actions.toggleMark(line, list);
            } else if (line.startsWith("todo") || line.startsWith("deadline") || line.startsWith("event")) {
                actions.handleTaskCreation(line, list);
            }
            else if (line.startsWith("delete")) {
                try {
                    int index = Integer.parseInt(line.split(" ")[1]);
                    actions.handleDeleteTask(index, list);
                    FileIO.saveTasksToFile(list);
                    System.out.println("Task " + index + " successfully deleted boss");
                    Ui.printLine();
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Delete what lah");
                    Ui.printLine();
                } catch (IndexOutOfBoundsException e){
                    System.out.println("Delete simi delete");
                    Ui.printLine();
                }
            }
            else {
                Ui.printLine();
                System.out.println("Please enter a valid command");
                Ui.printLine();
            }
            line = readLine();
        }
    }

}
