# Cody User Guide

Cody is a CLI (Command Line Interface) chatbot application that helps you manage your tasks efficiently. Below are the features available in Cody:

## Quick Start

1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest `cody.jar` from the [releases page](https://github.com/your-repo/cody/releases).
3. Copy the file to the folder you want to use as the home folder for Cody.
4. Open a command terminal, navigate to the folder containing `cody.jar`, and run the command `java -jar cody.jar` to start the application.

## Features

### Adding a Task
- Commands:
    - `todo DESCRIPTION` - Adds a todo task.
    - `deadline DESCRIPTION /by DATE` - Adds a deadline task.
    - `event DESCRIPTION /from START_DATE /to END_DATE` - Adds an event task.

### Listing All Tasks
- Command: `list`
- Description: Lists all tasks.

### Locating a Task by Name/Description
- Command: `find KEYWORD`
- Description: Locates tasks whose descriptions contain the given keyword.

### Deleting a Task
- Command: `delete INDEX`
- Description: Deletes the task at the specified index.

### Marking a Task as Done
- Command: `mark INDEX`
- Description: Marks the task at the specified index as done.

### Marking a Task as Not Done
- Command: `unmark INDEX`
- Description: Marks the task at the specified index as not done.

### Exiting the Program
- Command: `bye`
- Description: Exits the program.

### Saving the Data
- Cody automatically saves your task data to `tasks.txt` in the `data` folder after the program exits. There is no need to save manually.

## FAQ

- **Q:** How do I transfer my data to another computer?
    - **A:** Install Cody on the other computer and copy the `data` folder from your previous Cody home folder to the new one.
