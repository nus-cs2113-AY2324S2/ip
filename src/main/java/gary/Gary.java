package gary;

import gary.task.Task;
import gary.ui.Ui;
import gary.storage.Storage;
import gary.parser.Parser;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Gary {
    public static ArrayList<Task> todos = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Ui.greetings();

        File file = Storage.createFile();
        todos = Storage.readFileStorage(file);

        String line;
        line = in.nextLine();

        // Handle all user commands in loop until exit
        Parser.runCommand(line, file, in, todos);
    }
}
