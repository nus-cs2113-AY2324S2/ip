public class UI {
    protected boolean ifExit; //exits program if true

    public UI (){
        this.ifExit = false;
    }

    public void logo () {
        String logo
                = " _______    ___           _____   _____ ___    ___\n"
                + "|   ____\\___\\  \\___    __/   __| /   __|\\  \\__/  /\n"
                + " \\   \\   \\___  ____\\__|__    ___|    ___|\\_   __/\n"
                + "  \\   \\     |  | /  _  \\|   |    |  |     /  / \n"
                + " __\\   \\    |  ||    __/|   |    |  |  __/  / \n"
                + "/_______|   |__| \\_____||___|    |__| |____/ \n";
        System.out.println("--------------------------------------");
        System.out.println(logo);
        System.out.println("--------------------------------------");
    }

    public void greeting () {
        System.out.println("Hello! I'm Steffy");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------------------");
    }

    public void printBye () {
        System.out.println("--------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("--------------------------------------");
        ifExit = true;
    }

   //public void printList () {
   // }
}
