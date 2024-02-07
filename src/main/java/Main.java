import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = UserDetails.getName();
        String birthday = UserDetails.getBirthday();
        String gender = UserDetails.getGenderSelection();


        String message = String.format(
                "Verification passed.\nOptions enabled.\n%s Born on %s\n%s\nID A.D.0013\n" +
                        "Rank 'S'\nListed in the Kassel Academy roster.\n" +
                        "Database access granted\nAccount activated\nCourse schedule generated\n" +
                        "I am Erii, the secretary of Kassel Academy, pleased to serve you.",
                name, birthday, gender);

        System.out.println(message);

        boolean continueReading = true;
        while (continueReading) {
            String command = scanner.nextLine().trim();
            continueReading = TaskManager.processCommand(command);
        }

        scanner.close();

    }
}

