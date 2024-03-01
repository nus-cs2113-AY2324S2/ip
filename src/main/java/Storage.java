import com.sun.jdi.ArrayReference;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    static ArrayList<Todo> todolist = new ArrayList<>();
    static ArrayList ReadFile() throws FileNotFoundException {
        File file = new File("list.txt");
        if (!file.exists()) {
            return todolist;
        } else {
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            try {
                String line;
                while ((line = reader.readLine()) != null) {// process every line
                    Parser.ProcessSavedLine(line, ListManager.todolist);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            return todolist;

        }
    }

    static void SaveList(ArrayList todolist) throws IOException {
        File file = new File("list.txt");

        if (file.exists()) {
            System.out.println("file exists, overwriting file");
        } else {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter("list.txt");
        for (Object t : todolist) {
            if (t == null) {
                continue;
            }
            String content = (todolist.indexOf(t) + 1) + t.toString() + "\n";
            fw.write(content.replace("[", "|").replace("]", "|"));
        }
        fw.close();


    }
}
