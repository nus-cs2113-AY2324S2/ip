package Binks;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Files {
    public static void loadFileContents(List list, String file) throws FileNotFoundException {
        java.io.File f = new java.io.File(file);
        Scanner s = new Scanner(f);
        String description;
        for (int i = 1; s.hasNext(); i++) {
            String line = s.nextLine();
            String taskType = line.substring(1, 2);
            switch (taskType.toLowerCase()) {
            case "t":
                list.addTodo(line.substring(7));
                if (line.charAt(4) == 'X') {
                    list.markAsDone(i);
                }
                break;
            case "d":
                description = line.substring(7, line.indexOf(" (by:"));
                String deadline = line.substring(line.indexOf("(by: ") + 5, line.indexOf(")"));
                list.addDeadline(description + " (by: " + deadline + ")");
                if (line.charAt(4) == 'X') {
                    list.markAsDone(i);
                }
                break;
            case "e":
                description = line.substring(7, line.indexOf(" (from:"));
                String startTime = line.substring(line.indexOf("(from: ") + 7, line.indexOf(" to:"));
                String endTime = line.substring(line.indexOf("to:") + 4, line.indexOf(")"));
                list.addEvent(description + " (from: " + startTime + " to: " + endTime + ")");
                if (line.charAt(4) == 'X'){
                    list.markAsDone(i);
                }
                break;
            }
        }
        System.out.println("I have added all the tasks that you have saved!");
        System.out.println("Is there anything more tasks I can list for you?");
        Binks.createLineSpacing();
    }
    public static void readFile(List list) {
        try {
            loadFileContents(list,"binkslist.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
