public class Winter {
    public static void main(String[] args) {
        String logo = "  __      __.__        __                \n" +
                "/  \\    /  \\__| _____/  |_  ___________ \n" +
                "\\   \\/\\/   /  |/    \\   __\\/ __ \\_  __ \\\n" +
                " \\        /|  |   |  \\  | \\  ___/|  | \\/\n" +
                "  \\__/\\  / |__|___|  /__|  \\___  >__|   \n" +
                "       \\/          \\/          \\/    ";
        System.out.println("Hello from\n" + logo);
        hiandbye();
    }

    /* Function for greeting and farewell messages*/
    private static void hiandbye() {
        String line = "-----------------------------------\n";
        String greet = "Hello! I'm Winter!\nWhat can I do for you?\n";
        String farewell = "Bye. Hope to see you again soon!\n";
        System.out.print(line);
        System.out.println(greet);
        System.out.print(line);
        System.out.println(farewell);
        System.out.print(line);

    }

}
