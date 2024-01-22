public class Hachi {
    public static void greet(){
        String logo ="._. ._.  ._____.  ._____.  ._. ._.  ._.\n"
                + "| | | |  | ._. |  |  ___|  | | | |  | |\n"
                + "| |_| |  | |_| |  | |      | |_| |  | |\n"
                + "| ._. |  | ._. |  | |___   |  _  |  | |\n"
                + "|_| |_|  |_| |_|  |_____|  |_| |_|  |_|\n";
        System.out.println("Hey, Hachi Here!\n" + logo
                            + "How can I assist you today?\n");
        spacerInsert("medium");
    }
    public static void spacerInsert(String length){
        String spacer;
        switch (length) {
            case "small":
                spacer = "~~~~~~~~~~~~~~~~~~~~";
                break;
            case "medium":
            default:
                spacer = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
                break;
            case "large":
                spacer = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
                break;
        }
        System.out.println(spacer);
    }
    public static void goodbye(){
        System.out.println("Goodbye! Hope you have a marvelous day.");
        spacerInsert("medium");
    }
    public static void main(String[] args) {
        spacerInsert("medium");
        greet();
        goodbye();
    }
}
