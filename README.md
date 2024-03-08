# Incy - The British Task Manager

## Introduction
Welcome to Incy, your friendly neighborhood task manager! Incy is a command-line application designed to help you stay organized and manage your tasks with ease. Whether you need to keep track of simple to-dos, important deadlines, or upcoming events, Incy has got you covered.

## Getting Started
### Prerequisites
Before you start using Incy, make sure you have the following:
- Java Development Kit (JDK) installed on your computer
- Basic understanding of command-line interfaces

### Installation
1. Visit the Incy repository on GitHub: [Incy Repository](https://github.com/IncyBot/ip)
2. Click on the "Code" button and select "Download ZIP" to download the source code as a ZIP file.
3. Extract the ZIP file to a directory of your choice.
4. Open a terminal or command prompt and navigate to the directory where you extracted the source code.

### Running Incy
1. In the terminal, compile the Java source files by running the following command:
   ```
   javac *.java
   ```
2. Once the compilation is successful, you can run Incy using the following command:
   ```
   java Incy
   ```
3. Incy will greet you with a friendly message and display the command prompt, ready for you to start managing your tasks.

## Features
Incy offers a range of features to help you stay on top of your tasks:

### Adding Tasks
Incy supports three types of tasks: to-dos, deadlines, and events. Here's how you can add each type of task:

#### To-Do
To add a to-do task, use the following command:
```
todo [description]
```
Replace `[description]` with a brief description of your to-do task.

Example:
```
todo Buy groceries
```

#### Deadline
To add a deadline task, use the following command:
```
deadline [description] /by [date/time]
```
Replace `[description]` with a brief description of your deadline task, and `[date/time]` with the due date and time of the deadline.

Example:
```
deadline Submit project report /by 2023-06-30 17:00
```

#### Event
To add an event task, use the following command:
```
event [description] /from [start time] /to [end time]
```
Replace `[description]` with a brief description of your event task, `[start time]` with the start date and time of the event, and `[end time]` with the end date and time of the event.

Example:
```
event Team meeting /from 2023-07-01 14:00 /to 2023-07-01 16:00
```

### Listing Tasks
To view all your tasks, simply use the `list` command:
```
list
```
Incy will display a numbered list of all your tasks, along with their details such as task type, description, and due date/time (if applicable).

### Marking Tasks as Done
When you complete a task, you can mark it as done using the `mark` command followed by the task index:
```
mark [index]
```
Replace `[index]` with the number corresponding to the task you want to mark as done.

Example:
```
mark 2
```

### Unmarking Tasks
If you accidentally marked a task as done or need to reopen a task, you can use the `unmark` command followed by the task index:
```
unmark [index]
```
Replace `[index]` with the number corresponding to the task you want to unmark.

Example:
```
unmark 3
```

### Deleting Tasks
To remove a task from your list, use the `delete` command followed by the task index:
```
delete [index]
```
Replace `[index]` with the number corresponding to the task you want to delete.

Example:
```
delete 1
```

### Finding Tasks
If you have a long list of tasks and want to quickly find specific tasks, you can use the `find` command followed by a keyword:
```
find [keyword]
```
Replace `[keyword]` with the word or phrase you want to search for in your task descriptions.

Example:
```
find project
```
Incy will display a list of tasks that contain the specified keyword in their descriptions.

### Listing Tasks by Date
To view tasks scheduled for a specific date, use the `list by` command followed by the date:
```
list by [date]
```
Replace `[date]` with the desired date in the format `yyyy-MM-dd`.

Example:
```
list by 2023-06-30
```
Incy will display a list of tasks scheduled for the specified date.

### Exiting Incy
When you're done managing your tasks and want to exit Incy, simply type `bye`:
```
bye
```
Incy will save your tasks and bid you farewell.

## Data Persistence
Incy automatically saves your tasks to a file named `incy_tasks.txt` located in the `data` folder within the project directory. This ensures that your tasks are preserved even after you exit the application.

When you start Incy, it loads the tasks from the `incy_tasks.txt` file, allowing you to pick up where you left off.

If the `data` folder or the `incy_tasks.txt` file doesn't exist, Incy will create them automatically when you add your first task.

## Error Handling
Incy is designed to handle common errors gracefully. If you enter an invalid command or provide incorrect input, Incy will display an appropriate error message and prompt you to try again.

If the `incy_tasks.txt` file becomes corrupted or contains invalid data, Incy will skip the corrupted lines and load only the valid tasks.

## Customization
Incy's source code is well-structured and modular, making it easy to customize and extend its functionality. The main classes in the project are:

- `Incy`: The entry point of the application, handling user input and interaction.
- `TaskManager`: Manages the task list and provides methods for adding, marking, unmarking, deleting, and listing tasks.
- `TaskFactory`: Responsible for creating task objects based on user input and file data.
- `Task`: An abstract base class representing a generic task.
- `Todo`, `Deadline`, `Event`: Concrete classes extending the `Task` class, representing specific types of tasks.
- `IncyException`: A custom exception class for handling Incy-specific errors.
- `Constants`: Contains constant values used throughout the application.

Feel free to explore the source code and make modifications to suit your specific needs.

## Troubleshooting
If you encounter any issues while using Incy, consider the following troubleshooting steps:

1. Make sure you have Java Development Kit (JDK) installed and properly set up on your computer.
2. Verify that you are running Incy from the correct directory where the source code files are located.
3. Check that you have the necessary permissions to read from and write to the `data` folder and `incy_tasks.txt` file.
4. If you encounter any error messages, carefully read the message and refer to the relevant section in this user guide for guidance.

If the issue persists, feel free to open an issue on the Incy GitHub repository, providing detailed information about the problem you encountered.

## Conclusion
Incy is a user-friendly and efficient task manager designed to simplify your life. With its intuitive commands and seamless data persistence, Incy helps you stay organized and on top of your tasks.

So, go ahead and give Incy a try! Let it be your trusted companion in tackling your tasks and achieving your goals.

Happy task managing with Incy!