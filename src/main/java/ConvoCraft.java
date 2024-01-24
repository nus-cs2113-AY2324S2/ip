public class ConvoCraft {
    private static int LIST_SIZE = 100;
    public static void main(String[] args) {
        ListKeeper listKeeper = new ListKeeper(LIST_SIZE);
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
