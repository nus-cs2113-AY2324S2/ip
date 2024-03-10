public class Ui {
    public void showWelcome() {
        System.out.println("Hello! I'm Hailey");
        System.out.println("What can I do for you?");
        printLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showError(String message) {
        System.out.println("OOPS!!! " + message);
        printLine();
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks. Starting with an empty task list.");
    }

    private void printLine() {
        System.out.println("____________________________________________________________");
    }
}