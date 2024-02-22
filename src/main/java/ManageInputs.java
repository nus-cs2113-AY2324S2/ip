import java.util.ArrayList;
import java.util.Scanner;


public class ManageInputs {
    protected static String from;
    protected static String to;
    public static String description;
    protected static String by;


    public static void dealWithEvent(ArrayList<Task> tasks, int index, String[] inputs, String line) throws UnexpectedCommandException {
        int indexTo = line.indexOf("/to");
        int indexFrom = line.indexOf("/from");

        if ((indexTo == -1) || (indexFrom == -1)) { //invalid format
            System.out.println("Invalid format! Enter event in the format: event (description) /from (start) /to (end)");
            throw new UnexpectedCommandException();
        }
        try {//timeline not specified/ both not specified
            String from = line.substring(indexFrom + 6, indexTo - 1);
            String to = line.substring(indexTo + 4);
        } catch (IndexOutOfBoundsException e) {
            try {
                String description = line.substring(6, indexFrom - 1);
            } catch (IndexOutOfBoundsException f) {
                System.out.println("event description and timeline not specified");
                throw new UnexpectedCommandException();
            }
            System.out.println("event timeline not specified");
            throw new UnexpectedCommandException();
        }
        try {//description not specified
            String description = line.substring(6, indexFrom - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("event description not specified");
            throw new UnexpectedCommandException();
        }
        String from = line.substring(indexFrom + 6, indexTo - 1);
        String to = line.substring(indexTo + 4);
        String description = line.substring(6, indexFrom - 1);

        tasks.add(index, new Event(description, from, to));
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(index));
    }

    public static void dealWithDeadline(ArrayList<Task> tasks, int index, String line) throws UnexpectedCommandException {
        int indexBy = line.indexOf("by");
        if (indexBy == -1) {//invalid format
            System.out.println("Invalid format! Enter deadline in the format: deadline (description) by (deadline)");
            throw new UnexpectedCommandException();
        }
        try {//deadline / both not specified
            String by = line.substring(indexBy + 3);
        } catch (IndexOutOfBoundsException e) {
            try {
                String description = line.substring(9, indexBy - 1);
            } catch (IndexOutOfBoundsException f) {
                System.out.println("deadline description and deadline not specified");
                throw new UnexpectedCommandException();
            }
            System.out.println("deadline not specified");
            throw new UnexpectedCommandException();
        }
        try {//deadline not specified
            String description = line.substring(9, indexBy - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("deadline description not specified");
            throw new UnexpectedCommandException();
        }
        String description = line.substring(9, indexBy - 1);
        String by = line.substring(indexBy + 3);
        tasks.add(index, new Deadline(description, by));
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(index));
    }

    public static void dealWithTodo( ArrayList<Task> tasks, int index, String line) throws UnexpectedCommandException {
        int indexSpace = line.indexOf(" ");
        if (indexSpace == -1) {
            System.out.println("todo description not specified");
            throw new UnexpectedCommandException();
        }

        String description = line.substring(indexSpace);

        tasks.add(index, new Todo(description));
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(index));
    }

    private void handleUnexpectedCommand(boolean isValidCommand) throws UnexpectedCommandException {
        if (!isValidCommand) {
            throw new UnexpectedCommandException();
        }
    }

    private void handleEmptyInput(String line) throws EmptyLineException {
        if (line.isEmpty()) {
            throw new EmptyLineException();
        }
    }

    public ManageInputs(ArrayList<Task> tasks, int index, String line) {
        while (!line.equals("bye")) {
            Boolean isValidCommand = false;
            Scanner input = new Scanner(System.in);
            line = input.nextLine();

            Task t = new Task(line);
            String[] inputs = line.split(" ");

            if (inputs[0].equals("mark")) {//mark as done
                isValidCommand = true;
                int idx = Integer.parseInt(inputs[1]);
                tasks.get(idx - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(tasks.get(idx-1));
            } else if (inputs[0].equals("unmark")) {//unmark done
                isValidCommand = true;
                int idx = Integer.parseInt(inputs[1]);
                tasks.get(idx - 1).unmarkDone();
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println(tasks.get(idx-1));
            } else if (line.equals("list")) {//lists tasks
                isValidCommand = true;
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < index; i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            } else if (line.equals("bye")) {//exit chat
                isValidCommand = true;
                break;
            } else if (inputs[0].equals("event") || inputs[0].equals("todo") || inputs[0].equals("deadline")) {//add items
                try {
                    if (inputs[0].equals("event")) {
                        isValidCommand = true;
                        dealWithEvent(tasks, index, inputs, line);
                        index++;
                    } else if (inputs[0].equals("deadline")) {
                        isValidCommand = true;
                        dealWithDeadline(tasks, index, line);
                        index++;
                    } else {
                        isValidCommand = true;
                        dealWithTodo(tasks, index, line);
                        index++;
                    }
                } catch (UnexpectedCommandException e) {
                }
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (inputs[0].equals("delete")){
                int idx = Integer.parseInt(inputs[1]);
                System.out.println("Noted. I've removed this task: ");
                System.out.println(tasks.get(idx-1));
                tasks.remove(idx - 1);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                index--;
            } else {
                try {
                    handleEmptyInput(line);
                    handleUnexpectedCommand(isValidCommand);
                } catch (UnexpectedCommandException e) {
                    System.out.println("please enter a valid command");
                } catch (EmptyLineException e) {
                    System.out.println("enter a task");
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
