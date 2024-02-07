import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * The UserDetails class provides methods to retrieve user details such as name, birthday, and gender.
 */
public class UserDetails {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Pattern datePattern = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$");

    /**
     * Prompts the user to enter their full name and validates the input.
     *
     * @return the user's full name
     */
    public static String getName() {
        System.out.println("Please enter your full name (First Name Last Name): ");
        String name;
        while (true) {
            name = scanner.nextLine().trim();
            // Simple check to ensure the input contains at least two words for first and last name
            if (name.split("\\s+").length >= 2) {
                break;
            } else {
                System.out.println("Invalid name. Please enter both your first name and last name.");
            }
        }
        return name;
    }

    /**
     * Prompts the user to enter their birthday in DD/MM/YYYY format and validates the input.
     *
     * @return the user's birthday
     */
    public static String getBirthday() {
        System.out.println("Please enter your birthday (DD/MM/YYYY): ");
        String birthday;
        while (true) {
            birthday = scanner.nextLine().trim();
            Matcher matcher = datePattern.matcher(birthday);
            if (matcher.matches()) {
                break;
            } else {
                System.out.println("Invalid format. Please enter your birthday in DD/MM/YYYY format.");
            }
        }
        return birthday;
    }

    /**
     * Prompts the user to select their gender and returns the selected gender.
     *
     * @return the user's selected gender
     */
    public static String getGenderSelection() {
        System.out.println("Please enter your gender: ");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.println("3. Other");
        System.out.print("Enter the number corresponding to your choice: ");

        String gender = "";
        while (true) {
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    gender = "Male";
                    break;
                case "2":
                    gender = "Female";
                    break;
                case "3":
                    gender = "Other";
                    break;
                default:
                    System.out.println("Invalid option selected. Please enter 1, 2, or 3.");
                    continue;
            }
            break; // Exit the loop once a valid input is received.
        }

        return gender; // Return the selected gender.
    }
}
