import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list = new ArrayList<Task>();
    private int size = 0;

    /**
    * Marks a task as done or not done.
    * 
    * @param index The index of the task in the list.
    * @param status The status of the task.
    * @return None
    */
    public void setItemStatus(int index, boolean status) {
        try {
            list.get(index - 1).setStatus(status);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Huhhhhhhh? I cannot find!");
        }
    }

    /**
     * Returns the size of the list.
     * 
     * @param None
     * @return The size of the list.
     */
    public int getSize() {
        return size;
    }

    /**
     * Prints the size of the list with flavour text.
     * 
     * @param None
     * @return None
     */
    public void printSize() {
        int size = this.getSize();
        System.out.println("We only have uhhhh " + size
                + " more thing" + (size > 1 ? "s" : "") + " left to go!");
    }   
   
    /**
     * Prints the list.
     * 
     * @param None
     * @return None
     */
    public void printList() {
        // Check if list is empty
        if (size == 0) {
            System.out.println("You KAIBAI-ing");
            return;
        }

        System.out.println("Yay! List!");
        for (int i = 0; i < size; i++) {
            Task task = list.get(i);
            System.out.print(i + 1 + ".");
            System.out.println(task);
        }
    }

    /**
     * Prints error message for invalid addItem command.
     * 
     * @param None
     * @return None
     */
    public void addItemError() {
        System.out.println("ACKSHUALLY you are missing something...");
    }

    /**
     * Adds a task to the list.
     * 
     * @param taskName The name of the task.
     * @return None
     */
    public void addItem(String taskName) {
        // Error if empty string
        if (taskName.equals("")) {
            this.addItemError();
            return;
        }

        // Create new task object, add to list and update size
        Task task = new Task(taskName);
        list.add(task);
        size++;

        // Echo the input
        System.out.println("Okey dokey here we go");
        System.out.print(" ");
        System.out.println(list.get(size - 1));

        this.printSize();
    }

    /**
     * Adds a task of specified type to the list.
     * Overloaded method.
     * 
     * @param taskName
     * @param type
     * @return None
     */
    public void addItem(String description, TaskType type) {
        // Error if empty string
        if (description.equals("")) {
            this.addItemError();
            return;
        }

        String taskName, taskStart, taskEnd;
        boolean isValid = true;

        try {
            switch (type) {
            case TODO:
                taskName = description;
                list.add(new Todo(taskName));
                break;

            case DEADLINE:
                String[] nameEnd = description.split(" /by ", 2);
                taskName = nameEnd[0];
                taskEnd = nameEnd[1];

                list.add(new Deadline(taskName, taskEnd));
                break;

            case EVENT:
                String[] nameTimes = description.split(" /from ", 2);
                String times = nameTimes[1];

                String[] startEnd = times.split(" /to ", 2);
                taskName = nameTimes[0];
                taskStart = startEnd[0];
                taskEnd = startEnd[1];

                list.add(new Event(taskName, taskStart, taskEnd));
                break;
            
            default:
                isValid = false;
                System.out.println("HUHHH? What is this even?");
                break;
            }
            
            if (isValid) {
                size++;
                System.out.println("Okey dokey here we go");
                System.out.print(" ");
                System.out.println(list.get(size - 1));
                this.printSize();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            this.addItemError();
        }
    }
}
