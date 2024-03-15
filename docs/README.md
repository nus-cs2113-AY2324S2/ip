# Hailey User Guide

Hailey is a command-line chatbot application designed to help you manage your tasks efficiently.

## Getting Started

1. Make sure you have Java installed on your computer.
2. Clone or download the Hailey repository to your local machine.
3. Open a terminal in the directory where you saved the Hailey files.
4. Compile the Java files using the following command:
    ```
    javac *.java
    ```
5. Run the application using the following command:
    ```
    java Hailey
    ```

## Quick Reference

| Command    | Parameters                         | Explanation                            |
|------------|------------------------------------|----------------------------------------|
| `list`     | None                               | Displays the list of tasks.            |
| `todo`     | <task description>                 | Adds a todo task to the list.          |
| `deadline` | <task description> /by <due date>  | Adds a deadline task to the list.      |
| `event`    | <task description> /from <start> /to <end> | Adds an event task to the list. |
| `delete`   | <index>                            | Deletes the task at the specified index. |
| `find`     | <keyword>                          | Searches for tasks containing the specified keyword. |
| `bye`      | None                               | Exits the application.                 |

## Features

### Add Tasks

Hailey allows you to add different types of tasks:
- Todo tasks
- Deadline tasks
- Event tasks

### List Tasks

You can view all your tasks by typing `list`. Hailey will display the tasks along with their descriptions and statuses.

### Delete Tasks

To remove a task from your list, use the `delete` command followed by the index of the task you wish to delete.

### Find Tasks

If you need to find a specific task, use the `find` command followed by a keyword. Hailey will display all tasks containing the keyword.

### Exiting Hailey

To exit Hailey, simply type `bye`. Hailey will bid you farewell and close the application.

## Usage

### Adding a Todo Task 