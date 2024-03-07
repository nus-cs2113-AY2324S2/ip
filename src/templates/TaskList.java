package templates;

import java.util.ArrayList;
import templates.task.Task;
import templates.task.Deadline;
import templates.task.Event;

public class TaskList{
    ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<Task>();
    }

    public String addTask(Task args) throws Exception {
        try {
            for(Task task : list){
                if(task.getTaskString().equals(args.getTaskString()) && task.getClass() == args.getClass()){
                    throw new Exception("Duplicate task detected!");
                }
            }
            this.list.add(args);
            return (String.format("Got it. I've added this task:\n%s\n%s", args.toString(),
                    this.stringNumberTask()));
        } catch (Exception e) {
            throw e;
        }
    }
    public Integer getLength(){return this.list.size();}
    public String markTask(Integer args) {
        if (args > this.list.size()) {
            throw new ArrayIndexOutOfBoundsException(
                    String.format("Unrecognised input index! Recognised range: 1 to %d", this.list.size()));
        }
        if(this.list.get(args - 1).getCompleted()){
            return ("Opps! This task has already been marked as completed!");
        }
        this.list.get(args - 1).setCompleted(true);
        return ("Nice! I've marked this task as done:\n" + this.list.get(args - 1).toString());
    }

    public String unmarkTask(Integer args) throws ArrayIndexOutOfBoundsException {
        if (args > this.list.size()) {
            throw new ArrayIndexOutOfBoundsException(
                    String.format("Unrecognised input index! Recognised range: 1 to %d", this.list.size()));
        }
        if(!this.list.get(args - 1).getCompleted()){
            return ("Opps! This task has already been marked as not completed!");
        }
        this.list.get(args - 1).setCompleted(false);
        return ("OK, I've marked this task as not done yet:\n" + this.list.get(args - 1).toString());
    }

    public Task getTask(Integer args) {
        if (args > this.list.size()) {
            return null;
        }
        return list.get(args - 1);
    }

    public String deleteTask(Integer args) throws ArrayIndexOutOfBoundsException {
        if (args > this.list.size()) {
            throw new ArrayIndexOutOfBoundsException(
                    String.format("Unrecognised input index! Recognised range: 1 to %d", this.list.size()));
        }
        Task deletedTask = this.list.get(args - 1);
        this.list.remove(args - 1);
        return (String.format("Noted. I've removed this task:\n%s\n%s", deletedTask.toString(),
                stringNumberTask()));
    }

    public String stringNumberTask() {
        return String.format("Now you have %d %s in your list.\n", this.list.size(),
                (this.list.size() > 1 ? "tasks" : "task"));
    }

    public String findTask(String keyword) throws Exception {
        int i = 1;
        String resultString = "Here are the matching tasks in your list:\n";
        for (Task task : this.list) {
            if (task.getTaskString().contains(keyword)) {
                resultString += String.format("%d. %s\n", i, task.toString());
                i += 1;
            }
        }
        if (i == 1) {
            throw new Exception("Unable to find keyword!");
        }
        return resultString;
    }

    public String findDate(BaseDate otherDate) throws Exception {
        int i = 1;
        String resultString = "Here are the matching tasks in your list:\n";
        for (Task task : this.list) {
            if (task instanceof Deadline) {
                if (((Deadline) task).getDeadlineDateTime().equals(otherDate)) {
                    resultString += String.format("%d. %s\n", i, task.toString());
                    i += 1;
                }
            } else if (task instanceof Event) {
                if (((Event) task).getStartDateTime().equals(otherDate)) {
                    resultString += String.format("%d. %s\n", i, task.toString());
                    i += 1;
                }
            } else {
                continue;
            }

        }
        if (i == 1) {
            throw new Exception("Unable to find keyword!");
        }
        return resultString;
    }

    @Override
    public String toString() {
        if (this.list.isEmpty()) {
            return "List is empty";
        } else {
            int i = 1;
            String listHeader = "Here are the tasks in your list:\n";
            for (Task task : list) {
                listHeader += i + ". " + task.toString() + "\n";
                i += 1;
            }
            return listHeader;
        }
    }

}
