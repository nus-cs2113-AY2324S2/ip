public class List {
    protected Task[] tasks;
    protected int counter = 0;

    public void generateSizeOfList(int elements){
        tasks = new Task[elements];
    }

    public void insertTask(Task task){
        tasks[counter] = task;
        System.out.println("Got it. I've added this task");
        System.out.println(task.getDescription());
        System.out.println("Now you have " + (counter + 1) + " tasks in the list.");
        counter += 1;
    }

    public void listTasks(){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < counter; i += 1){
            System.out.println((i +1) + ". "+ tasks[i].getDescription());
        }
    }

    public void markIndex(int index){
        System.out.println("Nice Ive marked this task as completed ");
        tasks[index - 1].markTask();
    }

    public void unmarkIndex(int index){
        System.out.println("OK, I have unchecked this task");
        tasks[index - 1].unmarkTask();
    }


}
