public class Binks {
    public static void greetUser(){
        System.out.println("Hello! I'm Binks.");
        System.out.println("What can I do for you?");
    }

    public static void lineSpacing(){
        System.out.println("____________________________________________________________");
    }
    public static void main(String[] args) {
        lineSpacing();
        greetUser();
        lineSpacing();
        Echo.main(args);
    }
}
