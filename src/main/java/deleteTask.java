public class deleteTask {

    public static void deleteTask(Task[] list, int taskNumber, int count) {
        if (taskNumber >= 0 && taskNumber < count) {
            // Print the task being deleted
            System.out.println(Echo.break_line);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + list[taskNumber].toString());

            // Shift all elements to the left to delete the task
            for (int i = taskNumber; i < count - 1; i++) {
                list[i] = list[i + 1];
            }

            System.out.println("Now you have " + (count - 1) + " tasks in the list.");
            System.out.println(Echo.break_line);
        } else {
            System.out.println("Oops. Seems like you have input an invalid task number!");
            System.out.println(Echo.break_line);
        }
    }
}