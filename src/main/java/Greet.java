public class Greet {
    public void printFormat() {
        for(int i = 0; i < 50; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public void sayHello() {
        printFormat();
        System.out.println("Hello! I'm Duke");
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

    public void printInvalidDescription() {
        printFormat();
        System.out.println("Hey, the description is invalid!");
        printFormat();
    }

    public boolean isWithinBounds(int currentIndex, int index) {
        if(index < currentIndex && currentIndex > 0) {
            return true;
        }
        printFormat();
        if(currentIndex == 0) {
            System.out.println("Hey, you have no tasks added yet!");
        } else {
            System.out.println("Hey, you don't have that many tasks!");
        }
        printFormat();
        return false;
    }

}

