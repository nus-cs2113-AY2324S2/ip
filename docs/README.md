# Kobot User Guide
Kobot is a desktop app for managing tasks via a Command Line Interface (CLI).
> Note: `task` refers to any combination of `todo`, `deadline` and `event`

- [Quick Start](#quick-start)
- [Features](#features)
  - [Add to-Do](#add-to-do-to-list-todo)
  - [Add deadline](#add-deadline-to-list-deadline)
  - [Add event](#add-event-to-list-event)
  - [List all tasks](#list-all-tasks-list)
  - [Mark task as completed](#mark-task-as-completed-mark)
  - [Mark task as not completed](#mark-task-as-not-completed-unmark)
  - [Locating task by description](#locating-task-by-description-find)
  - [Delete task](#delete-a-task-delete)
  - [Exiting the program](#exiting-the-program-exit-or-bye)
- [Command Summary](#command-summary)

## Quick Start
1. Ensure you have Java `11` or above installed in your computer.

2. Download the latest `kobot.jar` from [here](https://github.com/Kobot7/ip/releases).

3. Copy the file to the folder you want to use as the *home folder* for `Kobot`.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use `java -jar kobot.jar` command to run the application.

5. Refer to the [Features](#features) below for details of each command.

## Features

> ### Notes about the Command format
> - Words in `UPPER_CASE` are the parameters to be supplied by the user.
    <br>e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo homework`.
> - Parameters must be in specified order.
  <br>e.g. if the command specifies `/from START_DATETIME /to END_DATETIME`, `/to END_DATETIME /from START_DATETIME` will not be accepted.
> - Extraneous parameters for commands that do not take in parameters (such as `list`, `exit` and `bye`) will be ignored.
  <br>e.g. if the command specifies `list 123`, it will be interpreted as `list`.

### Add To-do to list: `todo`

Adds a new to-do task to the task list.

Format: `todo DESCRIPTION`

Examples:
- `todo Clean room`


### Add Deadline to list: `deadline`

Adds a new deadline to the task list.

Format: `deadline DESCRIPTION /by DATETIME`

Examples:
- `deadline CS2113T Quiz /by Sunday, 2359`


### Add Event to list: `event`

Adds a new event to the task list.

Format: `event DESCRIPTION /from START_DATETIME /to END_DATETIME`

Examples:
- `event CS2113T Lecture /from Friday 4pm /to Friday 6pm`


### List all tasks: `list`

Shows a list of all tasks in the task list.

Format: `list`

### Mark task as completed: `mark`

Marks a specific task as completed.

Format: `mark INDEX`
- Marks the task at the specified `INDEX` as completed.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3...

Examples:
- `mark 1` marks the 1st task in the task list as completed.

### Mark task as not completed: `unmark`

Marks a specific task as not completed.

Format: `unmark INDEX`
- Marks the task at the specified `INDEX` as not completed.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3...

Examples:
- `unmark 2` marks the 2nd task in the task list as not completed.


### Locating task by description: `find`

Finds tasks which contain the given keywords.
- The search is case-sensitive. e.g. `Homework` will not match `homework`
- Only the description is searched.
- Full and partial words will be matched. e.g. `work` will match `Homework` and `work`

Format: `find KEYWORD`

Examples: 
- `find assignment` returns `CS2113T assignment` and `CS3235 assignment 1`.

### Delete a task: `delete`

Delete a specific task from the task list.

Format: `delete INDEX`
- Deletes the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3...

Examples:
- `delete 3` deletes the 3rd task in the task list.


### Exiting the program: `exit` or  `bye`
Exits the program.

Format: `exit`

Format: `bye`


## Command Summary

| Action       | Format, Examples                                                                                                           |
|--------------|----------------------------------------------------------------------------------------------------------------------------|
| Add to-do    | `todo DESCRIPTION`<br/>e.g. `todo Clean room`                                                                              |
| Add deadline | `deadline DESCRIPTION /by DATETIME`<br/>e.g. `deadline CS2113T Quiz /by Sunday, 2359`                                      |
| Add event    | `event DESCRIPTION /from START_DATETIME /to END_DATETIME`<br/>e.g. `event CS2113T Lecture /from Friday 4pm /to Friday 6pm` |
| List         | `list`                                                                                                                     | 
| Mark         | `mark INDEX`<br/>e.g. `mark 1`                                                                                             |
| Unmark       | `umark INDEX`<br/>e.g. `unmark 2`                                                                                          |
| Find         | `find INDEX`<br/>e.g. `find assignment`                                                                                    | 
| Delete       | `delete INDEX`<br/>e.g. `delete 3`                                                                                         |
| Exit         | `exit`<br/>`bye`                                                                                                           |


