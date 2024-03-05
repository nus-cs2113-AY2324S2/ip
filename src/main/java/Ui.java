    import java.util.List;
    import java.util.Scanner;

    public class Ui {

        private static final String welcome = "No place to store your to-dos? Avril (not the singer), your personal helper is here to help you!!";
        private static final String instructions =
                """
                --------------------------------------------------
                Adhere to the syntax below:
                - Todos: todo <taskDescription>
                - Deadlines: deadline <taskDescription> /by <date> <time>
                - Events: event <taskDescription> /from <date> /to <date>
                - Plus point if you can spell my name right!
                - MORE plus points if you can pronounce it correctly :)
                --------------------------------------------------
                """;
        private static final String farewell = "Bye. Hope to see you again!";

        Ui() {
            System.out.println(welcome);
        }

        void printInstructions() {
            System.out.println(instructions);
        }

        void printFarewell() {
            System.out.println(farewell);
        }
    }
