public class TaskManager {
    public static final int MAX_ENTRIES = 100;
    int numberOfItems = 0;
    Task[] itemList = new Task[MAX_ENTRIES];

    public void handleCommand(String command) {
         if (command.equals("list")) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < numberOfItems; i++) {
                System.out.println(i + 1 + "." + itemList[i].toString());
            }
        }
        else if (command.startsWith("mark")) {
            String[] stringArray = command.split(" ");
            int index = Integer.parseInt(stringArray[1]) - 1;
            itemList[index].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(itemList[index].toString());
        }
        else if (command.startsWith("unmark")) {
             String[] stringArray = command.split(" ");
            int index = Integer.parseInt(stringArray[1]) - 1;
            itemList[index].markAsNotDone();
            System.out.println("OK, I've marked this task as not yet done:");
            System.out.println(itemList[index].toString());
        }
        else {
            if (command.startsWith("todo")){
                 String toDoString = command.substring(4);
                 Todo toDo = new Todo(toDoString);
                 itemList[numberOfItems] = toDo;
             }
             else if (command.startsWith("deadline")){
                 int deadlineIndex = command.indexOf("/by");
                 Deadline deadline = new Deadline(command.substring(8, deadlineIndex), command.substring(deadlineIndex + 4));
                 itemList[numberOfItems] = deadline;
             }
             else if (command.startsWith("event")){
                 int startIndex = command.indexOf("/from");
                 int endIndex = command.indexOf("/to");
                 Event event = new Event(command.substring(5, startIndex), command.substring(startIndex + 6, endIndex), command.substring(endIndex + 4));
                 itemList[numberOfItems] = event;
             }
             System.out.println("Got it. I've added this task:");
             System.out.println(itemList[numberOfItems].toString());
             numberOfItems += 1;
             System.out.println("Now you have " + numberOfItems + " tasks in the list.");
         }
    }
}
