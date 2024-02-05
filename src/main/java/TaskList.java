public class TaskList {
    public final Task [] taskList= new Task[100];
    public int taskNo = 0;

    public void addTask(String userInput){
        switch (userInput.split(" ")[0]){
        case "todo":
            taskList[taskNo] = new Todo(userInput.substring(5),taskNo+1);
            break;
        case "deadline":
            String name = userInput.split(" /")[0].substring(9);
            String by = userInput.split(" /")[1].substring(3);
            taskList[taskNo] = new Deadline(name, taskNo+1, by);
            break;
        case "event":
            name = userInput.split(" /")[0].substring(6);
            String from = userInput.split(" /")[1].substring(5);
            String to = userInput.split(" /")[2].substring(3);
//            System.out.println("from: "+from +" to: " +to);
            taskList[taskNo]=new Event(name,taskNo+1, from, to);
            break;
        }

        System.out.println("    " + "--------------");
        System.out.println("    Got it. I've added this task:");
        System.out.print("      ");
        taskList[taskNo].printTask();
        taskNo++;
        System.out.println("    Now you have " + taskNo + " tasks in the list.");
        System.out.println("    " + "--------------");
    }

    public void markTask (int n){
        if (n >= taskNo){
            print("Sorry, task unfound.");
        }else{
            taskList[n].markedTask();
        }
    }

    public void unmarkTask (int n){
        if (n >= taskNo){
            print("Sorry, task unfound.");
        }else{
            taskList[n].unmarkedTask();
        }
    }

    public void showTaskList() {
        System.out.println("    " + "--------------");
        System.out.println("    " + "Here are the tasks in your list:");
        for (int i=0; i < taskNo; i++){
            System.out.print("    " + (i+1) +".");
            taskList[i].printTask();
        }
        System.out.println("    " + "--------------");
    }

    private static void print(String thingToPrint){
        System.out.println("    " + "--------------");
        System.out.println("    " + thingToPrint);
        System.out.println("    " + "--------------");
    }
}
