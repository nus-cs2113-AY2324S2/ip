public class Alice {
    public static void main(String[] args) {

        String line = "____________________________________________________________";

        System.out.println(line);
        System.out.println("Hello! I'm Alice");
        System.out.println("What can I do for you?");
        System.out.println(line);

        System.out.println(args[0]);

        String x = "bye";
        boolean isSame = x.equals(args[0]);

        if (isSame) {
            System.out.println(line);
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(line);
        } else {
            System.out.println(line);
            System.out.println(args[0]);
            System.out.println(line);
        }
    }
}
