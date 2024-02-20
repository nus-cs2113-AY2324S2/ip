package humi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class FileProcessor {

    public static String textContent;

    public static void appendTextContent(String content) {
        textContent += (content + '\n');
    }

    public static ArrayList<String> readFile(String filePath) throws FileNotFoundException {
        ArrayList<String> input = new ArrayList<>();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            input.add(s.nextLine());
        }
        s.close();
        return input;
    }

    public static void writeFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static String convertCommandToText (String command) {
        String trimmedCommand = command.trim();
        String text = "";
        if (command.startsWith("todo")) {
            String description = trimmedCommand.substring(5);
            text = "T/0/" + description;
        } else if (command.startsWith("deadline")) {
            String[] splitArray = trimmedCommand.split("/");
            String description = splitArray[0].substring(9);
            String deadline = splitArray[1].substring(3);
            text = "D/0/" + description + "/" + deadline;
        } else if (command.startsWith("event")) {
            String[] splitArray = trimmedCommand.split("/");
            String description = splitArray[0].substring(6);
            String startDate = splitArray[1].substring(5);
            String endDate = splitArray[2].substring(3);
            text = "E/0/" + description + "/" + startDate + "/" + endDate;
        }
        return text;
    }

    public static void markText(boolean isDone, int index) {
        String[] textArray = textContent.split("\n");
        String mark = isDone ? "1" : "0";
        String newString = textArray[index-1].substring(0, 2) + mark + textArray[index-1].substring(3);
        textArray[index-1] = newString;
        textContent = "";
        for (int i = 0; i < textArray.length; i++) {
            FileProcessor.appendTextContent(textArray[i]);
        }
    }

    public static void deleteText(int index) {
        String[] textArray = textContent.split("\n");
        textContent = "";
        for (int i = 0; i < textArray.length; i++) {
            if (i != index - 1) {
                appendTextContent(textArray[i]);
            }
        }
    }
}
