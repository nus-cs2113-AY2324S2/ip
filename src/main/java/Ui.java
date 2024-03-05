public class Ui {

    public static void printGreeting() {
        String greet = "\n" +
                "█▀█ █░█ █▀█ █▀▀ █▄▄ █▀▀\n" +
                "█▀▀ █▀█ █▄█ ██▄ █▄█ ██▄\n" + "HELLOOOO WHATCHA DOING???????";
        System.out.println(greet);
    }

    public static void printExit() {
        String exit = "byebye\n" + "ฅ^•ﻌ•^ฅ";
        System.out.println(exit);
    }

    public static void printFileNotFound() {
        System.out.println("No saved tasks found. Starting fresh.");
    }

    public static void printCorrupted() {
        System.out.println("Error loading tasks. Data file might be corrupted.");
    }

    public static void printTodoNotSpecified() {
        System.out.println("hello fool how can you todo nothing??????");
    }

    public static void printEventNotSpecified() {
        System.out.println("you tell me empty event for what");
    }

    public static void printEventUnclear() {
        System.out.println("event no (/from and /to)??? then what time u can go home?");
    }

    public static void printDeadlineNotSpecified() {
        System.out.println("if never tell me /by how I know the deadline??? im ded.");
    }

    public static void printUserIsStupid() {
        System.out.println("you dont make any sense");
    }

    public static void printTaskAdded(Task newTask, int size) {
        System.out.println("OKIE I MEMORISED FOR U:\n  " + newTask);
        System.out.println("You have " + size + " remaining things to dododo.");
    }


    public static void printTaskDeleted(Task removedTask, int size) {
        System.out.println("Just now you said do but now say don't, so I forgot this:\n  " + removedTask);
        System.out.println("Now you have " + size + " remaining things to dododo.");
    }

    public static void printDeleteTaskNotFound() {
        System.out.println("Which one is that? You never tell me this before:");
    }

    public static void printDeleteTaskError() {
        System.out.println("Eh, use numbers to tell me which one to forget, can?");
    }

    public static void printDisplayEmptyTasks() {
        System.out.println("U never tell me anything how I know");
    }

    public static void printDisplayTasks() {
        System.out.println("Every time need me to remind you...");
    }

    public static void printMarkUnmarkMissingTask() {
        System.out.println("u stupid u never tell me this before:");
    }

    public static void printMarkDoneTask(Task tasks) {
        System.out.println("YAY GOOD JOB\n  " + tasks);
    }

    public static void printMarkUndoneTask(Task tasks) {
        System.out.println("Just now say do alr now never do\n  " + tasks);
    }

    public static void printErrorSaving() {
        System.out.println("An error occurred while saving tasks.");
    }

    public static void printError() { System.out.println("Error occurred");}
}
