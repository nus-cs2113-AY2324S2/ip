package MassimoBoi;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class FileClass {

    public static void loadList(List<Task> taskList) throws FileNotFoundException {
        File f = new File("./src/main/java/list.txt");
        Scanner s = new Scanner(f);
        while(s.hasNext()){
            Task newTask;
            String line = s.nextLine();

            String[] lineArray = line.split("\\|");
            if(line.contains("[T]")){
                newTask = new ToDo(lineArray[0]);
            } else if(line.contains("[D]")){
                newTask = new Deadline(lineArray[0], lineArray[1]);
            } else if (line.contains("[E]")){
                newTask = new Event(lineArray[0], lineArray[1], lineArray[2]);
            } else {
                continue;
            }

            if(line.contains("[X]")){
                newTask.markAsDone();
            }
            taskList.add(newTask);

        }
    }

    public static void addToFile(List<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter("./src/main/java/list.txt");
        String list = "";
        for(Task task : taskList){
            if(Objects.equals(task.taskType(), "[D]")){
                String[] deadlineDescription = task.getDescription().split("\\(by:");
                String[] extractDeadline = deadlineDescription[1].split("\\)");
                list += Objects.toString(deadlineDescription[0]+ "|" +
                        extractDeadline[0] + "|" + task.taskType()) + "|" + Objects.toString(task.getStatus())  + "\n";
            } else if (Objects.equals(task.taskType(),"[E]")){
                String[] eventDescription = task.getDescription().split("\\(from:");
                String[] extractFrom = eventDescription[1].split("to:");
                String[] extractTo = extractFrom[1].split("\\)");
                list += Objects.toString(eventDescription[0]+ "|" + extractFrom[0] + "|" + extractTo[0] + "|"
                        + task.taskType()) + "|" + Objects.toString(task.getStatus())  + "\n";
            }
            else {
                list += Objects.toString(Objects.toString(task.getDescription())+ "|" + task.taskType()) + "|" + Objects.toString(task.getStatus())  + "\n";
            }
        }
        fw.write(list);
        fw.close();
    }

}
