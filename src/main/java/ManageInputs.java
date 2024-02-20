import java.util.Scanner;

public class ManageInputs {
    protected static String from;
    protected static String to;
    public String description;

    public static void dealWithEvent(Task[] tasks, int index, String[] inputs, String line) throws UnexpectedCommandException {
        int indexTo = line.indexOf("/to");
        int indexFrom = line.indexOf("/from");

        if ((indexTo == -1) || (indexFrom == -1)) {
            System.out.println("remember to include a description and event timeline!");
            throw new UnexpectedCommandException();
        }
        String from = line.substring(indexFrom + 6, indexTo - 1);
        String to = line.substring(indexTo + 4);
        String description = line.substring(6, indexFrom - 1);

        tasks[index] = new Event(description, from, to);
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks[index]);
    }

    public static void dealWithDeadline(Task[] tasks, int index, String line) throws UnexpectedCommandException {
        int indexBy = line.indexOf("by");
        if (indexBy == -1) {
            System.out.println("remember to include a description and a deadline!");
            throw new UnexpectedCommandException();
        }
        String by = line.substring(indexBy + 3);
        String description = line.substring(0, indexBy - 1);
        tasks[index] = new Deadline(description, by);
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks[index]);
    }

    public static void dealWithTodo(Task[] tasks, int index, String line) throws UnexpectedCommandException {
        int indexSpace = line.indexOf(" ");
        if (indexSpace == -1) {
            System.out.println("todo description cannot be empty");
            throw new UnexpectedCommandException();
        }

        String description = line.substring(indexSpace);

        tasks[index] = new Todo(description);
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks[index]);
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

    public ManageInputs(Task[] tasks, int index, String line){

        while (!line.equals("bye")) {
            Boolean isValidCommand = false;
            Scanner input = new Scanner(System.in);
            line = input.nextLine();

            Task t = new Task(line);
            String[] inputs = line.split(" ");

            if (inputs[0].equals("mark")) {//mark as done
                isValidCommand = true;
                int idx = Integer.parseInt(inputs[1]);
                tasks[idx - 1].markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("[" + tasks[idx - 1].getStatusIcon() + "]" + tasks[idx - 1].description);
            } else if (inputs[0].equals("unmark")) {//unmark done
                isValidCommand = true;
                int idx = Integer.parseInt(inputs[1]);
                tasks[idx - 1].unmarkDone();
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println("[" + tasks[idx - 1].getStatusIcon() + "]" + tasks[idx - 1].description);
            } else if (line.equals("list")) {//lists tasks
                isValidCommand = true;
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < index; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
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
                    System.out.println("Now you have " + index + " tasks in the list.");
                } catch (UnexpectedCommandException e) {

                }
            } else {
                try {
                    handleEmptyInput(line);
                    handleUnexpectedCommand(isValidCommand);
                } catch (UnexpectedCommandException e) {
                    System.out.println("please enter a valid command");
                } catch (EmptyLineException e){
                    System.out.println("enter a task");
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
