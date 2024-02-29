# User Guide
ConvoCraft is a CLI application to help users manage their tasks. 
- [Quick Start](#quick-start)
- [Features](#features)
    - [Listing all tasks: `list`](#listing-all-tasks-list)
    - [Adding a todo task: `todo`](#adding-a-todo-task-todo)
    - [Adding a deadline task: `deadline`](#adding-a-deadline-task-deadline)
    - [Adding an event task: `event`](#adding-an-event-task-event)
    - [Marking a task as done: `mark`](#marking-a-task-as-done-mark)
    - [Marking a task as not done: `unmark`](#marking-a-task-as-not-done-unmark)
    - [Deleting a task: `delete`](#deleting-a-task-delete)
    - [Finding a task: `find`](#finding-a-task-find)
    - [Exiting the application: `bye`](#exiting-the-application-bye)
    - [Saving the data](#saving-the-data)
- [Command Summary](#command-summary)

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest `ConvoCraft.jar` from [here](https://github.com/STeng618/ip/releases)
3. Copy the file to the folder you want to use as the home folder for your ConvoCraft.
4. Run the jar file using the following command:
   ```
   java -jar ConvoCraft.jar
   ```

## Features

> [TIP] How to read this user guide
> * Words in **bold** are the commands.
> * Words in `UPPER_CASE` are the parameters.
> * Items in square brackets are optional.

### Listing all tasks: `list`

- Shows a list of all tasks in the task list.
- Format: `list`

### Adding a todo task: `todo`

- Adds a todo task to the task list.
- Format: `todo DESCRIPTION`
- Example: `todo read book`
> [WARNING]
> * The description of the task cannot be empty.

### Adding a deadline task: `deadline`

- Adds a deadline task to the task list.
- Format: `deadline DESCRIPTION /by DATE`
- Example: `deadline return book /by 2021-09-30`
> [WARNING]
> * The description of the task cannot be empty.
> * The date must be specified

### Adding an event task: `event`

- Adds an event task to the task list.
- Format: `event DESCRIPTION /from START_DATE /to END_DATE`
- Example: `event project meeting /from 2021-10-01 /to 2021-10-02`
> [WARNING]
> * The description of the task cannot be empty.
> * The start date and end date must be specified.

### Marking a task as done: `mark`

- Marks a task as done in the task list.
- Format: `mark INDEX`
- Example: `mark 2`

### Marking a task as not done: `unmark`

- Marks a task as not done in the task list.
- Format: `unmark INDEX`
- Example: `unmark 2`

### Deleting a task: `delete`

- Deletes a task from the task list.
- Format: `delete INDEX`
- Example: `delete 2`

> [TIP] Specifying a task index
> * Wherever the task index is required, it should be a non-negative integer.
> * The task index refers to the index number shown in the displayed task list.
> * Get the task index by using [list](#listing-all-tasks-list) or [find](#finding-a-task-find)

### Finding a task: `find`

- Finds a task from the task list based on keywords.
- A task is considered a match if at least one of the keywords is found in the task description.
- Format: `find KEYWORD [MORE_KEYWORDS]`
- Example: `find book`

### Exiting the application: `bye`

- Exits the application.
- Format: `bye`

### Saving the data

- ConvoCraft data are saved in the hard disk if the application is closed properly using the `bye` command.
> [CAUTION]
> * The application will not be able to save your data if you force close the application.
> * Please use the `bye` command to exit the application.
> * Do not modify the data file manually.

## Command Summary

| Action       | Format, Examples                                                                                                    |
|--------------|---------------------------------------------------------------------------------------------------------------------|
| **List**     | `list`                                                                                                              |
| **Todo**     | `todo DESCRIPTION` <br> e.g. `todo read book`                                                                       |
| **Deadline** | `deadline DESCRIPTION /by DATE` <br> e.g. `deadline return book /by 2021-09-30`                                     |
| **Event**    | `event DESCRIPTION /from START_DATE /to END_DATE` <br> e.g. `event project meeting /from 2021-10-01 /to 2021-10-02` |
| **Mark**     | `mark INDEX` <br> e.g. `mark 2`                                                                                     |
| **Unmark**   | `unmark INDEX` <br> e.g. `unmark 2`                                                                                 |
| **Delete**   | `delete INDEX` <br> e.g. `delete 2`                                                                                 |
| **Find**     | `find KEYWORD [MORE_KEYWORDS]` <br> e.g. `find book`                                                                |
| **Bye**      | `bye`                                                                                                               |
