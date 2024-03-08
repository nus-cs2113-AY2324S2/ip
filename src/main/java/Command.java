public class Command {
    private String fullCommand;
    public Command(String fullCommand)
    {
        this.fullCommand=fullCommand;
    }

    public Command()
    {
        this.fullCommand=null;
    }
    public void setCommand(String fullCommand)
    {
        this.fullCommand=fullCommand;
    }
    public void execute(TaskList tasks,Ui ui,Storage storage)throws BotException
    {
        String[] parts = fullCommand.split(" ", 2);
        String command = parts[0];
        switch (command) {
            case "list":
                printList(tasks, ui, storage);
                break;
            case "mark":
                handleMark(tasks, ui, storage);
                break;
            case "unmark":
                handleUnmark(tasks, ui, storage);
                break;
            case "todo":
                handleTodo(tasks, ui, storage);
                break;
            case "deadline":
                handleDeadline(tasks, ui, storage);
                break;
            case "event":
                handleEvent(tasks, ui, storage);
                break;
            case "delete":
                handleDelete(tasks,ui,storage);
                break;
            case "find":
                handleFind(tasks, ui, storage);
                break;
            case "bye":
                sayBye(tasks, ui, storage);
                break;
            default:
                throw new BotException("I'm sorry, but I don't know what that means :-(");
        }
    }
    private void handleDelete(TaskList tasks,Ui ui,Storage storage) throws  BotException{
    String[] parts = fullCommand.split(" ");
    if (parts.length < 2) {
        throw new BotException("The index of delete cannot be empty.");
    }
    try {
        int index = Integer.parseInt(parts[1]) - 1;
        deleteTask(index,tasks,ui,storage);
    } catch (NumberFormatException e){
        System.out.println("The index is not a number! "); // check the index kind
        ui.printLine();
    }
}
    private void handleUnmark(TaskList tasks,Ui ui,Storage storage) throws  BotException{
        String[] parts = fullCommand.split(" ");
        if (parts.length < 2) {
            throw new BotException("The index of unmark cannot be empty.");
        }
        try{
            int index = Integer.parseInt(parts[1]) - 1;
            if(index+1> tasks.size()) {
                throw new BotException("The index is out of the list size."); //check the index range
            }
            unmarkAsDone(index,tasks, ui, storage);
        } catch (NumberFormatException e){
            System.out.println("The index is not a number! "); // check the index kind
        }
    }
    private void handleMark(TaskList tasks,Ui ui,Storage storage) throws BotException{
        String[] parts = fullCommand.split(" ");
        if (parts.length < 2) {
            throw new BotException("The index of mark cannot be empty.");
        }
        try
        {
            int index = Integer.parseInt(parts[1]) - 1;
            if(index+1> tasks.taskList.size()) {
                throw new BotException("The index is out of the list size."); //check the index range
            }
            markAsDone(index,tasks, ui, storage);
        } catch (NumberFormatException e){
            System.out.println("The index is not a number! "); // check the index kind
        }
    }
    private void handleEvent(TaskList tasks,Ui ui,Storage storage) throws  BotException{
        String[] parts = fullCommand.split(" /from | /to ");
        if (parts.length < 3) {
            throw new BotException("The time (from and to) of an event cannot be empty. (in format: /from xx /to xx)");
        }
        try{
            String taskDescription = parts[0].substring(6);
            String from = parts[1];
            String to = parts[2];
            System.out.println("Got it. I've added this task:");
            Event event = new Event(taskDescription, from, to);
            System.out.println(event);
            addList(event,tasks, ui, storage);
        } catch(IndexOutOfBoundsException e){
            ui.printLine();
            System.out.println("Opps! The event description is missing");
            ui.printLine();
        }
    }

    private void addList(Task task,TaskList tasks,Ui ui,Storage storage)
    {
        tasks.add(task);
        storage.saveTasksToFile(tasks.taskList);
    }

    private void sayBye(TaskList tasks,Ui ui,Storage storage)
    {
        System.out.println("Bye. Hope to see you again soon!");
        ui.printLine();
    }

    private void printList(TaskList tasks,Ui ui,Storage storage)
    {
        System.out.println("Here are the tasks in your list:");
        for(int i=0;i<tasks.size();i++)
        {
            System.out.println((i+1)+". "+tasks.get(i).toString());
        }
        ui.printLine();
    }

    private void markAsDone(int index,TaskList tasks,Ui ui,Storage storage)
    {
        if (index >= 0 && index < tasks.size()) {
            if(tasks.get(index).isComplete()){
                System.out.println("Sorry, this task have been marked as completed. You cannot mark this task again.");
                ui.printLine();
                return;
            }
            System.out.println("Nice! I've marked this task as done:");
            tasks.get(index).markTaskAsDone();
            System.out.println(tasks.taskList.get(index).toString());
            ui.printLine();
        } else {
            System.out.println("Invalid task index.");
            ui.printLine();
        }
        storage.saveTasksToFile(tasks.taskList);
    }
    private void unmarkAsDone(int index,TaskList tasks,Ui ui,Storage storage)
    {
        if (index >= 0 && index <tasks.size()) {
            if(!tasks.get(index).isComplete()) {
                System.out.println("Sorry, this task is not completed yet. You cannot unmark as done.");
                ui.printLine();
                return;
            }
            System.out.println("OK, I've marked this task as not done yet:");
            tasks.get(index).unmarkTaskAsDone();
            System.out.println(tasks.get(index).toString());
            ui.printLine();
        } else {
            System.out.println("Invalid task index.");
            ui.printLine();
        }
        storage.saveTasksToFile(tasks.taskList);
    }


    private void handleTodo(TaskList tasks,Ui ui,Storage storage) throws BotException{
        if (fullCommand.trim().equals("todo")) {
            throw new BotException("The description of a todo cannot be empty.");
        }
        String taskDescription = fullCommand.substring(5);
        System.out.println("Got it. I've added this task:");
        Todo todo = new Todo(taskDescription);
        System.out.println(todo);
        addList(todo,tasks, ui, storage);

    }

    private void handleDeadline(TaskList tasks,Ui ui,Storage storage) throws BotException{
        String[] parts = fullCommand.split(" /by ");
        if (parts.length < 2) {
            throw new BotException("The deadline date of a deadline cannot be empty. (in format: /by xxx )");
        }
        try{
            String taskDescription = parts[0].substring(9);
            String by = parts[1];
            Deadline deadline = new Deadline(taskDescription, by);
            ui.printLine();
            System.out.println("Got it. I've added this task:");
            addList(deadline,tasks, ui, storage);
            System.out.println(deadline);
        } catch(IndexOutOfBoundsException e){
            ui.printLine();
            System.out.println("Opps! The content of deadline is missing.");
            ui.printLine();
        }

    }


    private void handleFind(TaskList tasks,Ui ui,Storage storage) throws  BotException{
        ui.printLine();
        String[] parts = fullCommand.split(" ");

        if (parts.length < 2) {
            throw new BotException("The keyword cannot be empty.");
        }
        String keyword = parts[1];

        System.out.println("Here are the matching tasks in your list:");

        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(keyword)) {
                System.out.println((i + 1) + ". " + task);
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No tasks found with the keyword '" + keyword + "'.");
        }

        ui.printLine();

    }
    private void deleteTask(int index,TaskList tasks,Ui ui,Storage storage) throws BotException {
    if (index < 0 || index >= tasks.size()) {
        throw new BotException("Invalid task index.");
    }
    Task removedTask = tasks.taskList.remove(index);
    ui.printLine();
    System.out.println("Noted. I've removed this task:");
    System.out.println("  " + removedTask);
    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    ui.printLine();
    storage.saveTasksToFile(tasks.taskList);
    }
}
