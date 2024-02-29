# Carrot  User Guide
***
Carrot is a **task management application**, designed for use through a **command-line interface (CLI)**

- [Quick Start](#quick-start)
- [Features](#features)
    - Add a task
        - [todo task: `todo`](#todo---add-a-todo-task)
        - [deadline task: `deadline`](#deadline---add-a-deadline-task)
        - [event task: `event`](#event---add-an-event-task)
    - Mark task completion
        - [mark completed: `mark`](#mark---mark-a-task-as-completed)
        - [mark uncompleted: `unmark`](#unmark---mark-a-task-as-not-completed)
    - [Delete a task: `delete`](#delete---delete-a-task-item-from-the-list)
    - [Locate task by name: `find`](#find---locate-a-recorded-task-by-word)
    - [Show all recorded tasks: `list`](#list---shows-all-recorded-task)
    - [View command list: `help`](#help---shows-list-of-commands-in-cli)
    - [Exit program: `exit`](#bye---exits-the-application)
- [Saving Data](#saving-data)
- [Command Summary](#command-summary)

***

## Quick Start
1. Ensure you have `java 11` or above installed on your computer
2. Download the latest `carrot.jar` from [here](https://github.com/sxfoo/ip/releases)
3. Copy the file `carrot.jar` to a new empty folder
4. Open a command terminal, `cd` to the path of the folder, and use `java -jar carrot.jar` to run the application
5. Type commands into the CLI and press enter to execute them. 
   - Some example commands are:
        - `todo homework`: Adds a todo task for homework
        - `list`: Shows all the recorded task so far
        - `bye`: Exits the application
6. Refer to [**Command Summary**](#command-summary) for the list of commands available. 
   - Alternatively, head to **Features** right below for more details on each command.
---
## Features
> [!NOTE]
> - Words in `UPPER_CASE` are the parameters to be supplied by the user.
> 
>   e.g. in`event DESCRIPTION /FROM /TO`, `DESCRIPTION`, `FROM`, `TO` are the parameters 
> required from the user
>
> 
> - Parameters are to be given in a fixed order. 
> 
> 
> - Some commands require using the `/` key as a delimitter
> 
> 
> - To prevent potential formatting issues, avoid using `/` when typing your own parameters.

### `todo` - Add a todo task

Adds a todo task to the task list. 
Todo tasks are tasks which only requires a task description.

Format: `todo DESCRIPTION`

Example of usage: `todo homework`, `todo fold clothes`

Example outcome: 
```
Got it. I've added this task:
	[T][ ] homework
Now you have 1 task(s) in the list.
```

### `deadline` - Add a deadline task

Adds a deadline task to the task list. 
Deadline tasks are tasks which requires 
a task description as well as a deadline time/date (in string format).

Format: `deadline DESCRIPTION /BY`

Example of usage: `deadline cook dinner /6pm`, `deadline return book /23 March 2024`

Example outcome:
```
Got it. I've added this task:
	[D][ ] cook dinner (by: 6pm)
Now you have 2 task(s) in the list.
```

### `event` - Add an event task

Adds an event task to the task list.
Event tasks are tasks which requires
a task description, a start and an end date/time (in string format).

Format: `event DESCRIPTION /FROM /TO`

Example of usage: `event birthday party /23|03|24 9am /23|03|24 4pm`, `event basketball game /6pm /8pm`

Example outcome:
```
Got it. I've added this task:
	[E][ ] birthday party (from: 23|03|24 9am || to: 23|03|24 4pm)
Now you have 3 task(s) in the list.
```

### `mark` - Mark a task as completed

Puts a checkmark on a specified task item to indicate its completed status.
`TASKINDEX` refers to the position order of the task item in the list. 
This can be viewed using `list` command

Format: `mark TASKINDEX`

Example of usage: `mark 1`, `mark 2`

Example outcome:
```
Nice! I've marked this task as done:
	[T][X] homework
```

### `unmark` - Mark a task as not completed

Puts a checkmark on a specified task item to indicate its uncompleted status.
`TASKINDEX` refers to the position order of the task item in the list.
This can be viewed using `list` command

Format: `unmark TASKINDEX`

Example of usage: `unmark 1`, `unmark 2`

Example outcome:
```
OK, I've marked this task as not done yet:
	[T][ ] homework
```

### `delete` - Delete a task item from the list

Removes a specified task item from the list of recorded task. 
`TASKINDEX` refers to the position order of the task item in the list.
This can be viewed using `list` command

Format: `delete TASKINDEX`

Example of usage: `delete 1`, `delete 2`

Example outcome:
```
Noted. I've removed this task:
	[T][ ] homework
Now you have 2 task(s) in the list.
```

### `find` - Locate a recorded task by word

Search and show recorded tasks that contains the specified `WORD`.
This command searches in all the recorded tasks

Format: `find WORD`

Example of usage: `find dinner`, `find book`

Example outcome:
```
Here are the task(s) that contains "dinner" in your list:
1.[D][ ] cook dinner (by: 6pm)
```

### `list` - Shows all recorded task

Displays all tasks that was recorded by the user.

Format: `list`

Example of usage: `list`

Example outcome:
```
Here are the task(s) in your list:
1.[D][ ] cook dinner (by: 6pm)
2.[E][ ] birthday party (from: 23|03|24 9am || to: 23|03|24 4pm)
```

### `help` - Shows list of commands in CLI

Displays the available commands that can be used in the Command Line Interface (CLI).

Format: `help`

Example of usage: `help`

Example outcome:
```
Available Commands:
1. todo <taskdescription> - Add a new todo task
2. deadline <taskdescription> /<by> - Add a new task with a deadline <by>
3. event <taskdescription> /<from> /<to> - Add a new task that starts <from> and ends <to>
4. mark <taskindex> - Mark a task as done
5. unmark <taskindex> - Mark a task as not done
6. delete <taskindex> - delete a task from the list
7. find <word> - search a task from the list that contains <word>
8. list - List all tasks recorded
9. bye - Exit the program
10. help - Show available commands
```

### `bye` - Exits the application

Terminates the program

Format: `bye`

Example of usage: `bye`

Example outcome:
```
Bye. Hope to see you again soon!
```
***
## Saving Data

Task data are automatically saved to a text file located at
`data/listOfTasks.txt` (with reference to jar file path)

In the event where the app is unable to start as the text file data is corrupted, 
please delete the text file and rerun the program.

***
## Command Summary
| Command   | Description                           | Format                       |
|-----------|---------------------------------------|------------------------------|
| `todo`    | Adds a todo task to the task list.    | `todo DESCRIPTION`           |
| `deadline`| Adds a deadline task to the task list.| `deadline DESCRIPTION /BY`   |
| `event`   | Adds an event task to the task list.  | `event DESCRIPTION /FROM /TO`|
| `mark`    | Mark a task as completed              | `mark TASKINDEX`             |
| `unmark`  | Mark a task as not completed          | `unmark TASKINDEX`           |
| `delete`  | Delete a task item from the list      | `delete TASKINDEX`           |
| `find`    | Locate a recorded task by word        | `find WORD`                  |
| `list`    | Shows all recorded task               | `list`                       |
| `help`    | Shows list of commands in CLI         | `help`                       |
| `bye`     | Exits the application                 | `bye`                        |
