public class Greet {

    public void printHyphen() {
        for(int i = 0; i < 50; i++) {
            System.out.print("-");
        }
    }
    public void sayHello() {
        printHyphen();
        System.out.println();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printHyphen();
        System.out.println();
    }

    public void sayBye() {
        printHyphen();
        System.out.println();
        System.out.println("Bye. Hope to see you again soon!");
        printHyphen();
    }

    public void printList(String[] list, int length) {
        printHyphen();
        System.out.println();
        for(int i = 0; i < length; i++) {
            System.out.print(i+1 + ". ");
            System.out.println(list[i]);
        }
        printHyphen();
        System.out.println();
    }
}
