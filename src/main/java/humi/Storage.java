package humi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    public String textContent;

    Storage() {
        textContent = "";
    }

    public void appendTextContent(String content) {
        textContent += (content + '\n');
    }

    public ArrayList<String> readFile(String filePath) throws FileNotFoundException {
        ArrayList<String> input = new ArrayList<>();
        File file = new File(filePath);
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            input.add(s.nextLine());
        }
        s.close();
        return input;
    }

    public void writeFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public void markText(boolean isDone, int index) {
        String[] textArray = textContent.split("\n");
        String mark = isDone ? "1" : "0";
        String newString = textArray[index-1].substring(0, 2) + mark + textArray[index-1].substring(3);
        textArray[index-1] = newString;
        textContent = "";
        for (int i = 0; i < textArray.length; i++) {
            appendTextContent(textArray[i]);
        }
    }

    public void deleteText(int index) {
        String[] textArray = textContent.split("\n");
        textContent = "";
        for (int i = 0; i < textArray.length; i++) {
            if (i != index - 1) {
                appendTextContent(textArray[i]);
            }
        }
    }
}
