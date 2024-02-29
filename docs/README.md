# Gary User Guide :book:

Gary is a interactive and easy to use Command Line Interface application to manage your available tasks.

___

## Table of Contents
- [Quick Start](#quick-start)
- [Features](#features)
   * [Adding a Task](#adding-a-task)
     - [Todo: `todo`](#1-todo-todo)
     - [Deadline: `deadline`](#2-deadline-deadline)
     - [Event: `event`](#3-event-event)
   * [Listing all Task: `list`](#listing-all-task-list)
   * [Marking a task: `mark`](#marking-a-task-mark)
   * [Unmarking a task: `unmark`](#unmarking-a-task-unmark)
   * [Deleting a task: `delete`](#deleting-a-task-delete)
   * [Finding tasks: `find`](#finding-tasks-find)
   * [Exiting the programme: `bye`](#exiting-the-programme-bye)
   * [Saving the data](#exiting-the-programme-bye)
- [FAQ](#faq)
- [Command Summary](#command-summary)

---

## Quick Start
1. Ensure you have Java `11` installed in your Computer.
2. Download the latest `gary.jar` from here.
3. Copy the file to the folder you want to use as the _home folder_.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar gary.jar` 
command to run the application.

    A command line display similar to below will appear:
    ```
    Hello! I'm Gary
                        0   0   ______
                        | v | /oooooooo\
                        |   |/OOOOOOOOOO\
                        ===================
    |   |   |   |   |   |   |   |   |   |   |   |
    ```
5. Type commands in the terminal and press Enter to execute it.
   
    Some example commands you can try:
   * `list`: list all tasks.
   * `todo do homework`: adds a do homework task with type todo.
   * `mark 1`: mark 1st task as done.
   * `bye`: exits the programme.
6. Refer to the Features below for details of each command.

---

## Features
### Adding a Task
There are 3 types of tasks that can be added:
### 1. Todo: `todo`
Adds todo task to the list of tasks.

Format: `todo TODO_DESCRIPTION`
- Adds the todo task specified in TODO_DESCRIPTION.
- Description of todo can be in more than 1 word.

Examples:
- `todo exercise`
- `todo read book`

### 2. Deadline: `deadline`
Adds deadline task to the list of tasks.

Format: `deadline DEADLINE_DESCRIPTION /by yyyy-mm-dd`
- Adds deadline task specified in `DEADLINE_DESCRIPTION`.
- `DEADLINE_DESCRIPTION` can be in more than 1 word.
- Format **must** include `/by`.
- Date specified after `/by` **must** follow the format of `yyyy-mm-dd`.
  * `yyyy`: year (example: `2024`)
  * `mm`: months (example: `01` for January)
  * `dd`: day (example: `15`)
- `DEADLINE_DESCRIPTION` and `yyyy-mm-dd` **cannot** be empty.

Examples:
- `deadline homework /by 2024-02-25`
- `deadline CS2113 project /by 2024-03-08`

### 3. Event: `event`
Adds event task to the list of tasks.

Format: `event EVENT_DESCRIPTION /from START_TIME /to END_TIME`
- Adds event task specified in `EVENT_DESCRIPTION`.
- `EVENT_DESCRIPTION`, `START_TIME`, and `END_TIME` can be in more than 1 word.
- Format **must** include `/from` and `/to`.
- `EVENT_DESCRIPTION`, `START_TIME`, and `END_TIME` **cannot** be empty.

Examples:
- `event lecture /from 4pm /to 6pm`
- `event play games /from morning /to afternoon` 

### Listing all Task: `list`
Shows a list of tasks that is available in the list of tasks.

Format: `list`

### Marking a task: `mark`
Marks a task from the list of tasks as done.

Format: `mark TASK_NUMBER`
- Marks the task represented by `TASK_NUMBER` as shown in the list as done.
- `TASK_NUMBER` **must** be in positive integer and cannot exceed the total number of tasks.

Example: 
- `mark 3`

### Unmarking a task: `unmark`
Unmarks a task from the list of tasks as not done.

Format: `unmark TASK_NUMBER`
- Unmarks the task represented by `TASK_NUMBER` as shown in the list as not done.
- `TASK_NUMBER` **must** be in positive integer and cannot exceed the total number of tasks.

Example:
- `unmark 8`

### Deleting a task: `delete`
Deletes a task from the list of tasks.

Format: `delete TASK_NUMBER`
- Delete the task represented by `TASK_NUMBER` as shown in the list.
- `TASK_NUMBER` **must** be in positive integer and cannot exceed the total number of tasks.

Example:
- `delete 2`

### Finding tasks: `find`
Shows all the tasks that contain the given keyword.

Format: `find KEYWORD`
- Filters and shows tasks containing the `KEYWORD`.
- `KEYWORD` takes in String and can be in more than 1 word.

Example:
- `find task`
- `find do homework`

### Exiting the programme: `bye`
Exits the programme.

Format: `bye`
- Programme exits as long as command contains `bye`.

example:
- `bye`
- `Byeee`

### Saving the data
List of tasks are saved in local hard disk automatically after any command that modify the data.

---

## FAQ
> Q: Can I access the tasks I previously added when I restart the programme?
>
> A: Yes, all data are saved in local hard disk, and all the data will be 
read and loaded everytime the programme is restarted. 

> Q: How do I transfer my data to another computer?
> 
> A: Copy the `/gary.txt` file to another computer and paste it in the same 
> folder inside `gary.jar`

---

## Command Summary
| Command  | Format                                                  |
|----------|---------------------------------------------------------|
| todo     | `todo TODO_DESCRIPTION`                                 |
| deadline | `deadline DEADLINE_DESCRIPTION /by yyyy-mm-dd`          |
| event    | `event EVENT_DESCRIPTION /from START_TIME /to END_TIME` |
| list     | `list`                                                  |
| mark     | `mark TASK_NUMBER`                                      |
| unmark   | `unmark TASK_NUMBER`                                    |
| delete   | `delete TASK_NUMBER`                                    |
| find     | `find KEYWORD`                                          |
| exit     | `bye`                                                   |