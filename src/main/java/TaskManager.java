public class TaskManager {
    public static final int MAX_ENTRIES = 100;
    int numberOfItems = 0;
    Task[] itemList = new Task[MAX_ENTRIES];

    public void handleCommand(String command) {
        if (command.equals("list")) {
            printList();
        } else if (command.startsWith("mark") || command.startsWith(("unmark"))) {
            handleMarkAsDone(command);
        } else if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")){
            printOutput(command);
        } else {
            printErrorMessage();
        }
    }

    private void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < numberOfItems; i++) {
            System.out.println(i + 1 + "." + itemList[i].toString());
        }
    }

    public void handleMarkAsDone(String command) {
        String[] stringArray = command.split(" ");
        int index = Integer.parseInt(stringArray[1]) - 1;
        if (command.startsWith("mark")) {
            itemList[index].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
        } else if (command.startsWith("unmark")) {
            itemList[index].markAsNotDone();
            System.out.println("OK, I've marked this task as not yet done:");
        }
        System.out.println(itemList[index].toString());
    }
    
    public void handleAddTask(String command) throws InvalidInputException, NoInputException{
        String[] stringArray = command.split(" ");
        if (stringArray.length == 1) {
            throw new NoInputException();
        }

        if (command.startsWith("todo")){
            int toDoStartIndex = 4;
            String toDoString = command.substring(toDoStartIndex);
            Todo toDo = new Todo(toDoString);
            itemList[numberOfItems] = toDo;
        }
        else if (command.startsWith("deadline")){
            if (!command.contains("/by")) {
                throw new InvalidInputException();
            }
            int deadlineIndex = command.indexOf("/by");
            String description = command.substring(8, deadlineIndex);
            String by = command.substring(deadlineIndex + 4);
            Deadline deadline = new Deadline(description, by);
            itemList[numberOfItems] = deadline;
        }
        else if (command.startsWith("event")){
            if (!command.contains("/from") || !command.contains("/to")) {
                throw new InvalidInputException();
            }
            int startIndex = command.indexOf("/from");
            int endIndex = command.indexOf("/to");
            String description = command.substring(5, startIndex);
            String start = command.substring(startIndex + 6, endIndex);
            String end = command.substring(endIndex + 4);
            Event event = new Event(description, start, end);
            itemList[numberOfItems] = event;
        }
    }

    private void printOutput(String command) {
        String[] stringArray = command.split(" ");
        try {
            handleAddTask(command);
            System.out.println("Got it. I've added this task:");
            System.out.println(itemList[numberOfItems].toString());
            numberOfItems += 1;
            System.out.println("Now you have " + numberOfItems + " tasks in the list.");
        } catch (NoInputException e) {
            System.out.println("OOPS!!! The description of a " + stringArray[0] + " cannot be empty.");
        } catch (InvalidInputException e) {
            if (stringArray[0].equals("deadline")) {
                System.out.println("OOPS!!! " + stringArray[0] + " must contains /by");
            } else if (stringArray[0].equals("event")) {
                System.out.println("OOPS!!! " + stringArray[0] + " must contains /from and /to");
            }
        }
    }

    private void printErrorMessage() {
        System.out.println(" OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
