# User Guide
Welcome to the comprehensive User Guide for your application. This guide will walk you through the features and usage instructions to help you get the most out of your application.

## Features
### Feature-Greeting
**Description:**
This feature initiates a user-friendly interaction by prompting new users to enter their name, birthday, and gender directly through the console. After receiving the inputs, it generates a personalized welcome message that incorporates the user's provided details, simulating a verification and enrollment process to an exclusive roster - in this case, the fictional Kassel Academy. This introductory procedure not only greets the user but also seamlessly collects essential information for a customized experience.

### Feature-Read in and Save Basic Info of the User
**Description:**
This feature allows the application to collect and store basic information about the user, such as their name, birthday, gender, and preferences. This personalized information helps in tailoring the application's functionality and responses to fit the user's needs, enhancing the overall user experience.

## Usage
### 'inputName' - Enter User Name
**Description:**
Prompts the user to input their full name (First Name Last Name). The application validates the input to ensure it includes both names, enhancing the personalized interaction.

**Example of usage:**
Please enter your full name (First Name Last Name): John Doe
**Expected outcome:**
  The user's name is stored and utilized for personalized greetings and interactions within the application.
Welcome, John Doe! Your name has been recorded.

### inputBirthday - Enter Birthday
**Description:**
Ask the user to input their birthday in a specific format (DD/MM/YYYY). This information is crucial for personalized messages and functionalities related to the user's age or significant dates.

**Example of usage:**

Please enter your birthday (DD/MM/YYYY): 01/01/1990
**Expected outcome:**
The application stores the user's birthday for age-related functionalities and personalized greetings.
+ Your birthday, 01/01/1990, has been saved successfully.

### inputGender - Select Gender
**Description:**
The user is prompted to select their gender from predefined options. This feature allows for more tailored interactions and can be used to personalize the user experience further.

**Example of usage:**

Please enter your gender: 1. Male 2. Female 3. Other
**Expected outcome:**

The application records the user's gender selection, which can be used for personalized communication and functionalities.

Your gender, Male/Female/Other, has been recorded.

### addTask - Add a New Task
**Description:**
Allows users to add tasks to their list by entering a description and selecting a priority level. This feature is central to the task management functionality of the application.

**Example of usage:**

"addTask Complete the project report /S"
**Expected outcome:**

The task is added to the user's list with the specified priority, enhancing their productivity and task management.

! Got it. I've added this task: Complete the project report <S>
! Now you have [number] tasks in the list.

### listTasks - List All Tasks
**Description:**
Displays all tasks currently in the user's list, providing a comprehensive overview of pending, completed, and scheduled tasks.

**Example of usage:**

listTasks
**Expected outcome:**

A list of all tasks, along with their details such as status, description, and priority, is displayed to the user.

Here are the tasks in your list:
1. [T][ ] Complete the project report <S>

### markTaskAsDone - Mark a Task as Done
**Description:**
Enables users to mark a task as completed by specifying the task number. This feature helps users keep track of their progress.

**Example of usage:**

markTaskAsDone 1
**Expected outcome:**

The specified task is marked as completed, and its status is updated in the task list.

- Task 1 marked as done.
This guide should help you navigate through the application's features and functionalities. Enjoy managing your tasks and personalizing your experience with our application!
