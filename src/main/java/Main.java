public class Main {

    /**
     * The main method for starting the JingHao chatbot
     * Creates a new JingHao object and runs the JingHao chatbot
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        JingHao chatBot = new JingHao("data/tasklist.txt");
        chatBot.start();
    }
}
