public class TaskManager {
    private int numItems;
    private Task[] taskList;

    public TaskManager() {
        this.numItems = 0;
        this.taskList = new Task[100];
    }

    public void addListContents(String userInput) {
        Parser myParser = new Parser();
        String[] taskInfomation = myParser.processTaskInformation(userInput);
        switch (taskInfomation[0]) {
        case ("todo"):
            this.taskList[numItems] = new Todo(numItems, taskInfomation[1], false);
            break;

        case ("deadline"):
            this.taskList[numItems] = new Deadline(numItems, taskInfomation[1], false, taskInfomation[2]);
            break;

        case ("event"):
            this.taskList[numItems] = new Event(numItems, taskInfomation[1], false, taskInfomation[2],
                    taskInfomation[3]);
            break;

        case ("error"):
            System.out.println("Please give a proper input");
            break;

        default:
            break;
        }
        System.out.println(taskList[numItems]);
        this.numItems += 1;
    }

    public void showListContents() {
        if (this.numItems == 0) {
            System.out.println("List is empty. Please enter something first.");
        }
        for (int i = 0; i < numItems; i += 1) {
            System.out.print(taskList[i].getId() + 1 + ". ");
            System.out.println(taskList[i]);
        }
    }

    public static boolean isStringInteger(String number ){
        try{
            Integer.parseInt(number);
        }catch(Exception e ){
            return false;
        }
        return true;
    }

    public void changeTaskStatus(String userInput) {
        userInput = userInput.toLowerCase();
        String[] wordArray = userInput.split(" ");
        // need to check if the 1st index of the array is a valid number and an actual number
        if (wordArray.length != 2 || !isStringInteger(wordArray[1])) {
            System.out.println("Please give a command in the structure of \"mark x\" or \"unmark x\"" +
                    "where x is the task number");
            return;
        }

        int id = Integer.parseInt(wordArray[1]) - 1;
        if (userInput.contains("unmark")) {
            taskList[id].setDone(false);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[ ] " + taskList[id].getContent());
        }
        // must contain mark at this point
        else {
            taskList[id].setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[X] " + taskList[id].getContent());
        }
    }


}
