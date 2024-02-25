package com.erii.user;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDetails {
    private String userName = "";
    private String userBirthday = "";
    private String userGender = "";
    private Scanner scanner = new Scanner(System.in);

    public UserDetails() {
        // Constructor to initialize the UserDetails instance
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getUserBirthday() {
        return this.userBirthday;
    }

    public String getUserGender() {
        return this.userGender;
    }

    public void inputName() {
        System.out.println("Please enter your full name (First Name Last Name): ");
        String name;

        while (true) {
            name = scanner.nextLine().trim();
            if (name.split("\\s+").length >= 2) {
                this.userName = name;
                break;
            } else {
                System.out.println("Invalid name. Please enter both your first name and last name.");
            }
        }
    }

    public void inputBirthday() {
        System.out.println("Please enter your birthday (DD/MM/YYYY): ");
        String birthday;
        Pattern datePattern = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$");

        while (true) {
            birthday = scanner.nextLine().trim();
            Matcher matcher = datePattern.matcher(birthday);
            if (matcher.matches()) {
                this.userBirthday = birthday;
                break;
            } else {
                System.out.println("Invalid format. Please enter your birthday in DD/MM/YYYY format.");
            }
        }
    }

    public void inputGender() {
        System.out.println("Please enter your gender: ");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.println("3. Other");
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
            this.userGender = gender;
            break;
        }
    }
}
