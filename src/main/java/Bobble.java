public class Bobble {

    //Greets user
    public static void start(){
        System.out.println("____________________________________________________________\n" +
                "Hello! I'm Bobble.\n" +
                "What can I do for you?");
    }

    //Exits
    public static void goodbye() {
        System.out.println("____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }

    public static void main(String[] args) {
        start();
        goodbye();
    }
}
