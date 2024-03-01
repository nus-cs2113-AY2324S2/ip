# User Guide of Charlie, a Task-managing Chatbot

## Quick start
1. Ensure you have Java 11 installed in your Computer.
2. Download the latest `ip.jar` from [here](https://github.com/Hws2209/ip/releases).
3. Open a command terminal, `cd` into the folder you put the jar file in, and use the 
   `java -jar ip.jar` command to run the application.
4. Type the command in the command box and press Enter to execute it.

## Features
> For the command format, words in UPPER_CASE are the parameters to be supplied by the user.

<br>

### Adding a todo: `todo`
Adds a task of 'todo' type to the list of tasks.

Format: `todo DESCRIPTION`

Example of usage:
`todo read book` assuming list is currently empty

Expected outcome:

```
New task added: [T][ ] read book
Current number of tasks: 1
```

<br>

### Adding a deadline: `deadline`
Adds a task of 'deadline' type to the list of tasks. 

<br>

### Adding an event: `event`
Adds a task of 'event' type to the list of tasks.

<br>

### Listing all tasks: `list`

<br>

### Searching tasks by keyword: `find`

<br>

### Marking a task as completed: `mark`

<br>

### Un-marking a task as not completed: `unmark`

<br>

### Deleting a task: `delete`

<br>

### Exiting the program: `bye`

<br>

### Saving the data
Data are saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually.