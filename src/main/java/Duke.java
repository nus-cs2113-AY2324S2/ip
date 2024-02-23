import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        ArrayList<Task> listOfItems = new ArrayList<>();

        System.out.println(logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Brennan!");
        System.out.println("What can I do for you?\n");

        //String that stores the input entered by the user
        String input;

        //variable stores the number of tasks being added
        int sizeOfAddedItems = 0;

        Scanner in = new Scanner(System.in);

        while (true) {
            input = in.nextLine();

            try {

                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list: ");
                    System.out.println("____________________________________________________________");
                    for (int i = 0; i < sizeOfAddedItems; i++) {

                        System.out.println((i + 1) + ". " + " " + "[" + listOfItems.get(i).typeOfTask + "]" + "[" + listOfItems.get(i).getStatusIcon() + "]" + listOfItems.get(i).description);
                    }
                    System.out.println("____________________________________________________________");
                } else if (Arrays.asList(input.split(" ")).contains("mark")) {
                    String[] splitInput = input.split(" ");

                    if (splitInput.length < 2 || splitInput[1].isEmpty()) {
                        throw new StringIndexOutOfBoundsException();
                    }
                    //Finding the index of the task that the user wants to mark

                    int indexTask = Integer.parseInt(splitInput[1]);

                    listOfItems.get(indexTask - 1).markAsCompleted();

                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println((indexTask) + ". " + "[" + listOfItems.get(indexTask - 1).getStatusIcon() + "]" + listOfItems.get(indexTask - 1).description);
                    System.out.println("____________________________________________________________");
                } else if (Arrays.asList(input.split(" ")).contains("unmark")) {
                    String[] splitInput = input.split(" ");

                    if (splitInput.length < 2 || splitInput[1].isEmpty()) {
                        throw new StringIndexOutOfBoundsException();
                    }
                    //Finding the index of the task that the user wants to mark

                    int indexTask = Integer.parseInt(splitInput[1]);

                    listOfItems.get(indexTask - 1).markAsNotCompleted();

                    System.out.println("____________________________________________________________");
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println((indexTask) + ". " + "[" + listOfItems.get(indexTask - 1).getStatusIcon() + "]" + listOfItems.get(indexTask - 1).description);
                    System.out.println("____________________________________________________________");
                } else if (Arrays.asList(input.split(" ")).contains("todo")) {
                    String[] splitInput = input.split(" ");
                    if (splitInput.length < 2 || splitInput[1].isEmpty()) {
                        throw new StringIndexOutOfBoundsException();
                    } else if (sizeOfAddedItems >= 100) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    listOfItems.add(sizeOfAddedItems, new ToDo(input));
                    sizeOfAddedItems += 1;

                    indicateNewTask(listOfItems.get(sizeOfAddedItems - 1), sizeOfAddedItems);
                } else if (Arrays.asList(input.split(" ")).contains("deadline")) {
                    String[] splitInput = input.split(" ");
                    if (splitInput.length < 2 || splitInput[1].isEmpty()) {
                        throw new StringIndexOutOfBoundsException();
                    } else if (sizeOfAddedItems >= 100) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    listOfItems.add(sizeOfAddedItems, new Deadline(input));
                    sizeOfAddedItems += 1;

                    indicateNewTask(listOfItems.get(sizeOfAddedItems - 1), sizeOfAddedItems);
                } else if (Arrays.asList(input.split(" ")).contains("event")) {
                    String[] splitInput = input.split(" ");
                    if (splitInput.length < 2 || splitInput[1].isEmpty()) {
                        throw new StringIndexOutOfBoundsException();
                    } else if (sizeOfAddedItems >= 100) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    listOfItems.add(sizeOfAddedItems, new Event(input));
                    sizeOfAddedItems += 1;

                    indicateNewTask(listOfItems.get(sizeOfAddedItems - 1), sizeOfAddedItems);
                } else if (Arrays.asList(input.split(" ")).contains("delete")) {
                    String[] splitInput = input.split(" ");
                    deleteTask(listOfItems,splitInput);
                    sizeOfAddedItems--;
                }
                else {
                    throw new IllegalArgumentException();
                }
            } catch (Exception e) {
                DukeException.handleException(e, input);
            }
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }


    public static void indicateNewTask(Task newTask, int currentNumberOfTasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Well done, you've added a new task: ");
        System.out.println("[" + newTask.typeOfTask + "]" + "[" + newTask.getStatusIcon() + "]" + newTask.description);
        System.out.println("Currently you have " + currentNumberOfTasks + " task(s) in your list!");
        System.out.println("____________________________________________________________");
    }

    private static void deleteTask(ArrayList<Task> listOfItems, String[] splitInput) {
        int indexTask = Integer.parseInt(splitInput[1]) ;
        System.out.println("This task has been deleted:");
        System.out.println((indexTask) + ". " + "[" + listOfItems.get(indexTask - 1).getStatusIcon() + "]" + listOfItems.get(indexTask - 1).description);
        System.out.println("Your roster now contains " + (listOfItems.size() - 1) + " endeavors.");

        listOfItems.remove(indexTask-1);
    }
    
}








