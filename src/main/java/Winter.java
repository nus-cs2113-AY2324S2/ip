import java.util.Scanner;
public class Winter {
    public static void main(String[] args) {
        String logo = "  __      __.__        __                \n" +
                "/  \\    /  \\__| _____/  |_  ___________ \n" +
                "\\   \\/\\/   /  |/    \\   __\\/ __ \\_  __ \\\n" +
                " \\        /|  |   |  \\  | \\  ___/|  | \\/\n" +
                "  \\__/\\  / |__|___|  /__|  \\___  >__|   \n" +
                "       \\/          \\/          \\/    ";
        System.out.println("Hello from\n" + logo);
        sayHi();
        //echo();
        Manager.acceptInput();

    }
    // Method for greeting message
    private static void sayHi() {
        String line = "-----------------------------------\n";
        String greet = "Hello! I'm Winter!\nWhat can I do for you?";
        System.out.print(line);
        System.out.println(greet);
        System.out.print(line);
    }

}