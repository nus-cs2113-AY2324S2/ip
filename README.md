# Incy - The British Task Manager

Incy is a British command-line task management application! It allows you to add, mark, unmark, delete, and list tasks of various types: ToDos, Deadlines, and Events.

## Features

- Add ToDos, Deadlines, and Events tasks
- Mark tasks as done
- Unmark tasks as not done
- Delete tasks
- List all tasks
- Automatically save and load tasks from a file

## Installation

1. Make sure you have Java installed on your system.
2. Clone the repository or download the source code.

## Usage

1. Navigate to the project directory in your terminal.
2. Compile the Java files using the following command:

```
javac *.java
```

3. Run the application with:

```
java Incy
```

4. Incy will greet you and prompt you to enter commands.

### Commands

- `todo [description]`: Add a new ToDo task with the given description.
- `deadline [description] /by [date/time]`: Add a new Deadline task with the given description and due date/time.
- `event [description] /from [start time] /to [end time]`: Add a new Event task with the given description, start time, and end time.
- `list`: List all tasks in the task list.
- `mark [index]`: Mark the task at the given index as done.
- `unmark [index]`: Mark the task at the given index as not done.
- `delete [index]`: Delete the task at the given index.
- `bye`: Exit the application.

**Note:** Indices for mark, unmark, and delete commands start from 1.

### Task Storage

Incy automatically saves your tasks to a file named `incy_tasks.txt` in the `data` folder within the project directory. If the `data` folder doesn't exist, it will be created automatically. If the `incy_tasks.txt` file doesn't exist, a new file will be created when you add your first task.

### File Format

The task data is stored in the `incy_tasks.txt` file using the following format:

```
T | 0 | read book
D | 1 | return book | June 6th
E | 0 | project meeting | Aug 6th 2-4pm | Aug 6th 6-8pm
```

- `T` represents a ToDo task, `D` represents a Deadline task, and `E` represents an Event task.
- The second value (`0` or `1`) indicates whether the task is done or not.
- The remaining values represent the task description and additional details (due date for Deadlines, start and end times for Events).

If the file becomes corrupted or contains invalid data, Incy will skip the corrupted lines and load the valid tasks.

## Code Structure

The application is structured into the following classes:

- `Incy`: The main class that handles user input and interaction.
- `TaskManager`: Manages the task list and handles commands related to tasks.
- `TaskFactory`: Responsible for creating tasks from user input and file data.
- `Task`: Abstract base class for different task types.
- `Todo`: Represents a ToDo task.
- `Deadline`: Represents a Deadline task.
- `Event`: Represents an Event task.
- `IncyException`: Custom exception class used for handling errors.
- `Constants`: Contains constant values used throughout the application.

## Dependencies

This application has no external dependencies and only uses the built-in Java libraries.

## Contributing

Contributions to Incy are welcome! If you find any bugs or have suggestions for improvements, please open an issue or submit a pull request.