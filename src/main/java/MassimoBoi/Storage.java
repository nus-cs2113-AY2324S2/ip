package MassimoBoi;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Storage {

    public List<Task> loadList() throws FileNotFoundException {
        List<Task> tasks = new ArrayList<>();
        File f = new File("./src/main/java/list.txt");
        Scanner s = new Scanner(f);
        while(s.hasNext()){
            Task newTask;
            String line = s.nextLine();

            String[] lineArray = line.split("\\|");
            if(line.contains("[T]")){
                String todoDescription = lineArray[0];
                newTask = new ToDo(todoDescription);
            } else if(line.contains("[D]")){
                String deadlineDescription = lineArray[0];
                String deadlineDueDate = lineArray[1];
                newTask = new Deadline(deadlineDescription, deadlineDueDate);
            } else if (line.contains("[E]")){
                String eventDescription = lineArray[0];
                String from = lineArray[1];
                String to = lineArray[2];
                newTask = new Event(eventDescription, from, to);
            } else {
                continue;
            }

            if(line.contains("[X]")){
                newTask.markAsDone();
            }
            tasks.add(newTask);

        }
        return tasks;
    }

    public void addToFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter("./src/main/java/list.txt");
        String list = "";
        for(int i = 0; i < tasks.size(); i++){
            if(Objects.equals(tasks.get(i).taskType(), "[D]")){
                String[] deadlineDescription = tasks.get(i).getDescription().split("\\(by:");
                String[] extractDeadlineDate = deadlineDescription[1].split("\\)");
                list += deadlineDescription[0] + "|" +
                        extractDeadlineDate[0] + "|" + tasks.get(i).taskType() + "|" + tasks.get(i).getStatus() + "\n";
            } else if (Objects.equals(tasks.get(i).taskType(),"[E]")){
                String[] eventDescription = tasks.get(i).getDescription().split("\\(from:");
                String[] extractFromDate = eventDescription[1].split("to:");
                String[] extractToDate = extractFromDate[1].split("\\)");
                list += eventDescription[0] + "|" + extractFromDate[0] + "|" + extractToDate[0] + "|"
                        + tasks.get(i).taskType() + "|" + tasks.get(i).getStatus() + "\n";
            }
            else {
                list += tasks.get(i).getDescription() + "|" + tasks.get(i).taskType() + "|" + tasks.get(i).getStatus() + "\n";
            }
        }
        fw.write(list);
        fw.close();
    }

}
