import java.util.Scanner;
public class Hiko {

    public static void main(String[] args) {
        String logo =


        "  \n" +
                " __ __  ____  __  _   ___  \n" +
                "|  |  ||    ||  |/ ] /   \\ \n" +
                "|  |  | |  | |  ' / |     |\n" +
                "|  _  | |  | |    \\ |  O  |\n" +
                "|  |  | |  | |     \\|     |\n" +
                "|  |  | |  | |  .  ||     |\n" +
                "|__|__||____||__|\\_| \\___/ \n" +
                "                           \n";
        System.out.println("Hello from  \n" + logo);
        // create an instance of Echo and start echoing
        Echo echo = new Echo();
        echo.startEchoing();

    }
}
