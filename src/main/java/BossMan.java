public class BossMan {

    private final String sep = "____________________________________________________________";

    public void greetUser(){
        String greet = "Hello! I'm BossMan";
        String offerService = "What can I do for you?";
        System.out.println(sep + "\n" + greet);
        System.out.println(offerService + "\n" + sep);
    }

    public void endChat(){
        String exit = "Bye. Hope to see you again soon!";
        System.out.println(exit + "\n" + sep);
    }
}

