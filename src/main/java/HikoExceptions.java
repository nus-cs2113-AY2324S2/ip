public class HikoExceptions extends Exception {
    public HikoExceptions(String message) {
        String  break_line = "----------------------------------------";
        System.out.println(break_line);
        System.out.println("Sorry, Hiko don't understand that command.");
        System.out.println("Type 'help' for a list of valid commands.");
        System.out.println(break_line);
    }

}