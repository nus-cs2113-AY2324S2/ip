public class Ui {
    public void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__\n" +
                "    },_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void printFormat() {
        for(int i = 0; i < 50; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public void sayHello() {
        printFormat();
        System.out.println("Hello! I'm Atlas");
        System.out.println("What can I do for you?");
        printFormat();
    }

    public void sayBye() {
        printFormat();
        System.out.println("Bye. Hope to see you again soon!");
        printFormat();
    }

    public void printNumTasks(int numTasks) {
        if(numTasks == 1) {
            System.out.println("Now you have " + numTasks + " task in the list.");
        } else {
            System.out.println("Now you have " + numTasks + " tasks in the list.");
        }
    }

    public void printError(String message) {
        printFormat();
        System.out.println(message);
        printFormat();
    }

}

