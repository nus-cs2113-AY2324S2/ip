# User Guide
Porato is a **desktop app for managing tasks using a Command Line Interface**.
* [Quick start](#Quick-start)
* [Features](#Features)
  * [Viewing help: `help`](#Viewing-help--help)
  * [Adding a todo task : `todo`](#Adding-a-todo-task--todo)
  * [Adding a deadline task : `deadline`](#Adding-a-deadline-task--deadline)
  * [Adding a event task : `event`](#Adding-a-event-task--event)
  * [Deleting a task: `delete`](#Deleting-a-task--delete)
  * [Listing all the tasks : `list`](#Listing-all-the-tasks--list)
  * [Locating a specific task : `find`](#Locating-a-specific-task--find)
  * [Marking a task : `mark`](#Marking-a-task--mark)
  * [Unmarking a task : `unmark`](#Unmarking-a-task--unmark)
  * [Exiting the program : `bye`](#Exiting-the-program--bye)
  * [Saving the data](#Saving-the-data)
  * [Loading the data](#Loading-the-data)
  * [Editing the data file](#Editing-the-data-file)
* [Command summary](#Command-summary)

## Quick start
1. Ensure you have java 11 installed in your computer.
2. Download the ip.jar file from [here](https://github.com/daryltay415/ip/releases/tag/A-Release).
3. Copy the file to the folder you want to use as the home folder for the Porato.
4. Open a command terminal, `cd` into the same folder that you the jar file in, and use the `jave -jar ip.jar` command to run the application.
5. Type the command into the command prompt and press Enter to execute it. e.g. typing `help` and then pressing enter will list out all the available commands the user can input into the command prompt.
6. Refer to the Features below for details of each command
## Features

> [!NOTE] about the command format :  
> * The words that are in `UPPER_CASE` represents the parameters that the users are required to input
> e.g. `todo TASK`, `TASK` is a parameter which can be used as `todo add me`.
> * Commands such as `help`, `list` and `bye` do not require additional parameters. Hence, any extra parameters will be ignored. e.g. `help 123` will just be intepreted as `help`.
### Viewing help : `help`

Shows a list of commands the user can input

Format : `help`

Example of usage :

`help`

Expected outcome: 

```
    ____________________________________________________________
    These are the commands available:
    list
    mark INDEX
    unmark INDEX
    todo TASK
    deadline TASK /by DATE
    event TASK /from START_DATE /to END_DATE
    delete INDEX
    help
    ____________________________________________________________
```

### Adding a todo task : `todo`

Adds a todo task into the task list

Format : `todo TASK`

Examples of usage : 

* `todo add me`
* `todo finish project`

Expected outcome : 

```
    ____________________________________________________________
     Got it. I've added this task:
       [T][ ] add me
     Now you have 1 tasks in the list.
    ____________________________________________________________
```

### Adding a deadline task : `deadline`

Adds a deadline task into the task list

Format : `deadline TASK /by DATE`

Examples of usage : 

* `deadline fix computer /by Friday 6pm`
* `deadline fix the ladder /by Monday 6pm`

Expected outcome : 

```
    ____________________________________________________________
     Got it. I've added this task:
       [D][ ] fix computer (by: Friday 6pm)
     Now you have 1 tasks in the list.
    ____________________________________________________________

```

### Adding a event task : `event`

Adds an event task into the task list

Format : `event TASK /from START_DATE /to END_DATE`

Examples of usage : 

* `event go for meeting /from Monday 2pm /to 4pm`
* `event go for comic con /from Friday 2pm /to 7pm`

Expected outcome : 
```
    ____________________________________________________________
     Got it. I've added this task:
       [E][ ] go for meeting (from: Monday 2pm to: 4pm)
     Now you have 1 tasks in the list.
    ____________________________________________________________
```

### Deleting a task : `delete`

Deletes a task from the task list

Format : `delete INDEX`
* Deletes the task at the specified `INDEX`
* The index refers to the number shown in the task list
* The index numbers are all positive integers

Examples of usage : 

* `delete 1`
* `delete 2`

Expected outcomes : 

```
    ____________________________________________________________
     Fine! I've removed this task:
      [T][ ] add me
     Now you have 0 tasks in the list
    ____________________________________________________________
```

### Listing all the tasks : `list`

Lists all the tasks in the task list

Format : `list`

Examples of usage : 

`list`

Expected outcome : 

```
    ____________________________________________________________
     Here are the tasks in your list:
     1.[D][X] add her (by: 6pm)
     2.[T][ ] hit me
     3.[D][ ] fix computer (by: Friday 6pm)
     4.[E][ ] go for meeting (from: Monday 2pm to: 4pm)
    ____________________________________________________________
```

### Locating a specific task : `find`

Finds a task in the task list

Format : `find TASK`

Examples of usage : 

* `find fix computer`
* `find go for meeting`

Expected outcome :

```
    ____________________________________________________________
     Here are the relevant tasks in your list:
     1.[T][ ] fix computer
    ____________________________________________________________
```
### Marking a task : `mark`

Marks a task in the task list

Format : `mark INDEX`

Examples of usage : 

* `mark 1`
* `mark 4`

Expected outcome :

```
    ____________________________________________________________
     Nice! I've marked this task as done:
      [T][X] hit me
    ____________________________________________________________
```

### Unmarking a task : `unmark`

Unmarks a task in the task list

Format : `unmark INDEX`

Examples of usage :

* `unmark 1`
* `unmark 2`

Expected outcome : 

```
    ____________________________________________________________
     OK, I've marked this task as not done yet:
      [T][ ] hit me
    ____________________________________________________________
```

### Exiting the program : `bye`

Exits the program

Format : `bye`

Examples of usage :

`bye`

Expected outcome :

```
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

### Saving the data

The Porato data are saved in the hard disk automatically after any command that changes the data.
    
### Loading the data

When the program starts, saved data loads from `[JAR file location]/porato.txt`. If it does not exist then a `porato.txt` will be created in the same directory as the jar file.

### Editing the data file
hello
The Porato data are automatically saved as a text file `[JAR file location]/porato.txt`. Users can edit directly from the text file as well.

## Command summary

| Action | Format, Examples |
|---|---|
| help | `help` |
| todo | `todo TASK`<br>e.g., `todo finish project` |
| deadline | `deadline TASK /by DATE`<br>e.g., `deadline build house /by Friday 6pm` |
| event | `event TASK /from START_DATE /to END_DATE`<br>e.g., `event go meeting /from Monday 5pm /to 7pm` |
| delete | `delete INDEX`<br>e.g., `delete 1` |
| list | `list` |
| find | `find TASK`<br>e.g., `find meeting` |
| mark | `mark INDEX`<br>e.g., `mark 1` |
| unmark | `unmark INDEX`<br>e.g., `unmark 1` |
| bye | `bye` |                                                                              |
