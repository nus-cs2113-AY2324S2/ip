public class ConvoCraft {
    private static int MAX_NUM_OF_TASKS = 100;
    public static void main(String[] args) {
        ListKeeper listKeeper = new ListKeeper(MAX_NUM_OF_TASKS);
        HorizontalGenerator.printHorizontal();
        System.out.println("Hello! I'm ConvoCraft");
        System.out.println("What can I do for you?");
        HorizontalGenerator.printHorizontal();
        listKeeper.manageList();
        HorizontalGenerator.printHorizontal();
        System.out.println("Bye. Hope to see you again soon!");
        HorizontalGenerator.printHorizontal();
    }
}
