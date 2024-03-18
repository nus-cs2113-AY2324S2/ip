# Ruby's User Guide

## About Ruby

Ruby is a command-line app created for everyone to manage their tasks.

## Quick start

1. Ensure you have Java `11` or above installed on your computer.
1. Download the latest `IP.jar`
1. Copy the file to the folder you want to use as the _home folder_ for your STM.
1. Open a command terminal, `cd` into the folder you put the JAR file in, and use the `java -jar ip.jar` command to run the application.

   A welcome message as below should appear:
    ```
    -----RUBY-----
    Hi, I am here. Greets from Ruby.
    What can I do for you?
    --------------
    ```
## Features

:information_source: **Notes about the command format:**

* Words in `UPPER_CASE` are the parameters to be supplied by the user.

  For example, in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo Add a brief User Guide`.

### Add a todo task: `todo`

* Adds a todo to the task list.

* Format: `todo DESCRIPTION`

* Example: `todo Read diary`

### Add a deadline task: `deadline`

* Adds a deadline to the task list.

* Format: `deadline DESCRIPTION /by TIME`

  :information_source: `TIME` must follow the Format `yyyy-MM-dd`.

* Example: `deadline Submit project 2024-03-05`

### Add an event task: `event`

* Adds an event to the task list.

* Format: `event DESCRIPTION /from START_TIME /to END_TIME`

  :information_source: `START_TIME` and `END_TIME` must follow the Format `yyyy-MM-dd`.

* Example: `event Trip to Hong Kong /from 2024-05-01 /to 2024-05-03`

### Delete a task: `delete`

* Deletes a task from the task list.

* Format: `delete TASK_ID`

  :information_source: `TASK_ID` refers to the index number shown in the displayed task list.

* Example: `delete 2`

### Mark task as completed: `mark`

* Marks a task as completed in the task list.

* Format: `mark TASK_ID`

  :information_source: `TASK_ID` refers to the index number shown in the displayed task list.

* Example: `mark 3`

### Mark task as uncompleted: `unmark`

* Marks a task as uncompleted in the task list.

* Format: `unmark TASK_ID`

  :information_source: `TASK_ID` refers to the index number shown in the displayed task list.

* Example: `unmark 1`

### List tasks: `list`

* Lists tasks in the task list.

* Format: `list`

### Find tasks by keyword: `find`

* Lists tasks whose description contains the keyword. The search is case-insensitive.

* Format: `find KEYWORD`

* Example: `find final`

### Exit App: `bye`
* Exit the programme.

* Format: `bye`
