import java.util.Scanner;


public class Phoebe {
    public static void main(String[] args) {
        String greet = "\n" +
                "█▀█ █░█ █▀█ █▀▀ █▄▄ █▀▀\n" +
                "█▀▀ █▀█ █▄█ ██▄ █▄█ ██▄\n" + "HELLOOOO WHATCHA DOING???????";
        String exit = "byebye\n" + "ฅ^•ﻌ•^ฅ";
        System.out.println(greet);

        String echo;                                            ////////////////////////////////////////////
        Scanner in  = new Scanner(System.in);
        while (true) {
            echo = in.nextLine();

            if (echo.equalsIgnoreCase("bye")) {     ///// level1echo/////////
                System.out.println(exit);
                break;
            } else {
                System.out.println(echo + "\n");
            }
        }                                                       /////////////////////////////////////////////
    }
}


