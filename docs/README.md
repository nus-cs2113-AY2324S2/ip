# User Guide
Joe is a chatbot for managing a task list optimized for use via Command Line Interface (CLI).

## Quick Start
1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest joe.jar release [here](https://github.com/rismm/ip/releases).
3. Copy the file to a folder you want as the home directory of the file.
4. From the command terminal, `cd` into the home directory of the jar file and run the command `java -jar joe.jar` to launch the application.
5. Type a command in the command box and press Enter to execute it. Refer to **Features** below for details of each available command.

## Features 

> Notes on command and tasks format
> - Words between two dashes `--` are parameters to be input by the user
>   - e.g. in `todo --TASK--` TASK is the parameter to be input by the user used as `todo finish assignment` 
> - DATETIME parameters must include both dates and times in the format `dd-mm-yyyy HHmm`
>   - `dd-mm-yyyy` indicates day-month-year e.g. 1 January 2001 is `01-01-2001`
>   - `HHmm` indicates the 24-hour clock time e.g. 9pm is `2100`
> - Every necessary parameter input cannot be empty or whitespace
> - All tasks are displayed in the format `INDEX_NUMBER.[TASK_TYPE][MARKED_STATUS] TASK_DESCRIPTION` e.g.
>   - Todo: `21.[T][ ] Go home`
>   - Deadline: `5.[D][ ] CS2113 iP (by: 8 Mar 2024 11:59PM)`
>   - Event: `10.[E][X] IVE concert in Singapore (from: 24 Feb 2024 06:00PM to: 24 Feb 2024 08:00PM)`

### Adding a Todo task: `todo`
Adds a Todo task to the task list\
Format: `todo --TASK--`\
Example: `todo CS2113 Week 7 Lecture Quiz`

### Adding a Deadline task: `deadline`
Adds a Deadline task to the task list\
Format: `deadline --TASK-- /by --DATETIME--`\
Example: `deadline CS2113 iP /by 08-03-2024 2359` 

### Adding an Event task: `event`
Adds an Event task to the task list\
Format: `event --TASK-- /from --START DATETIME-- /to --END DATETIME--`\
Example: `event IVE concert in Singapore /from 24-02-2024 1800 /to 24-02-2024 2000`

### Listing all tasks: `list`
Shows a list of all tasks currently in the task list\
Format: `list`
- Additional parameters cannot be included in the command for it to execute
    - e.g. `list my tasks please` is not interpreted as `list`

### Setting a task's status to be done: `mark`
Marks a task as completed\
Format: `mark --INDEX--`
- `INDEX` must be a positive integer less than or equals to the current number of tasks in the task list
- To get a task's `INDEX`, `list` or `find` can be used to look for the index 
- Task will be marked with a `X` to indicate completion
- If task selected is already marked, it will remain marked

### Unsetting a task's done status: `unmark`
Removes a task's mark status\
Format: `unmark --INDEX--`
- `INDEX` must be a positive integer less than or equals to the current number of tasks in the task list
- To get a task's `INDEX`, `list` or `find` can be used to look for the index
- If task selected is already unmarked, it will remain unmarked

### Deleting a task: `delete`
Deletes a task from the task list\
Format: `delete --INDEX--`
- `INDEX` must be a positive integer less than or equals to the current number of tasks in the task list
- To get a task's `INDEX`, `list` or `find` can be used to look for the index

### Finding a task: `find`
Lists all tasks that matches or contains a keyword\
Format: `find --KEYWORD--`\
- `KEYWORD` is case-sensitive, e.g. `homework` does not match `Homework`

Example: `find assignment`

### Exiting the program: `bye`
Exits the program\
Format: `bye`
- Additional parameters cannot be included in the command for it to execute
  - e.g. `bye bye joe` is not interpreted as `bye`

### Saving the task list
The task list is saved automatically by the program everytime it is updated or changed. There is no need to save manually.

## Usage

### _An example of the interface when using Joe_
Output on program start:
```
____________________________________________________________
HI I'M JOE
WHAT CAN I DO FOR YOU
____________________________________________________________
```
Input: `deadline CS2113 iP /by 08-03-2024 2359`
```
____________________________________________________________
OKAY I'VE ADDED THIS TASK:
  [D][ ] CS2113 iP (by: 8 Mar 2024 11:59PM)
NUMBER OF TASKS CURRENTLY IN LIST: 1
____________________________________________________________
```
Input: `list`
```
____________________________________________________________
HERE'S YOUR TASKS:
1.[D][ ] CS2113 iP (by: 8 Mar 2024 11:59PM)
____________________________________________________________
```
Input: `mark 1`
```
____________________________________________________________
GOOD JOB BRO. I'VE MARKED IT AS DONE:
  [D][X] CS2113 iP (by: 8 Mar 2024 11:59PM)
____________________________________________________________
```
### _Data Saving_
Data will be saved in a text file created in the same home directory as `joe.jar` in the path `./data/tasklist.txt`. 
You can transfer the text files to another computer with joe.jar and Joe will read and load the data it accordingly.