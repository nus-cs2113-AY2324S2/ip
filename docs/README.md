# Baron User Guide

## Overview
Baron is a CLI-based application that allows users to track their tasks. Users can 
add and delete tasks from a list, mark them and view all current tasks. This user guide aims to
help users get familiarized with the different commands available in the application.

## Getting Started
1. Download Java11.
2. Download the latest JAR file from GitHub.
3. Move the JAR file into an empty folder.
4. In the command terminal, use the `cd` command to switch to the
   folder with the JAR file.
5. Enter `java -jar ip.jar` in the command terminal to run the application.
6. Refer to the features below for details of the various commands and how to use Baron.

## Features

### Viewing all tasks: `list`

Shows a list of all the current tasks, along with their 
descriptions and status. 

Format: `list`

Example of usage:

```
list
```

Expected output:
```
Here are the tasks in your list:
1.[T][X] read book
2.[D][ ] return book (by: sunday)
3.[E][ ] project meeting (from: mon 2pm  to: 4pm)
4.[T][X] join sports club
5.[T][ ] borrow book
```

### Adding a ToDo task: `todo`

Adds a ToDo task to the task list.

Format: `todo [description]`

Example of usage:

```
todo read book
```

Expected output:
```
Got it. I've added this task:
    [T][ ] read book
Now you have 1 task in the list.
```
 
### Adding a Deadline task: `deadline`

Adds a Deadline task to the task list.

Format: `deadline [description] /by [deadline]`

Example of usage:

```
deadline return book /by sunday
```

Expected output:
```
Got it. I've added this task:
   [D][ ] return book (by: sunday)
Now you have 1 task in the list.
```

### Adding an Event task: `event`

Adds an Event task to the task list.

Format: `event [description] /from [eventStart] /to [eventEnd]`

Example of usage:

```
deadline project meeting /from mon 2pm /to 4pm
```

Expected output:
```
Got it. I've added this task:
   [E][ ] project meeting (from: mon 2pm  to: 4pm)
Now you have 1 task in the list.
```

### Marking a task as done: `mark`

Changes the status of the selected task to Done.

Format: `mark [index]`

Example of usage:

```
mark 1
```

Expected output:
```
Nice! I've marked this task as done:
   [E][X] project meeting  (from: mon 2pm  to: 4pm)
```

### Marking a task as undone: `unmark`

Changes the status of the selected task to Undone.

Format: `unmark [index]`

Example of usage:

```
unmark 1
```

Expected output:
```
OK, I've marked this task as not done yet:
   [E][ ] project meeting  (from: mon 2pm  to: 4pm)
```

### Removing a task: `delete`

Removes a specific task from the task list.

Format: `delete [index]`

Example of usage:

```
delete 1
```

Expected output:
```
Noted. I've removed this task:
   [E][ ] project meeting  (from: mon 2pm  to: 4pm)
Now you have 0 tasks in the list.
```

### Finding a task: `find`

Returns all tasks in the task list that contains the keyword.

Format: `find [keyword]`

Example of usage:

```
find book
```

Expected output:
```
Here are the matching tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: sunday)
```

### Exiting the program: `bye`

Exits the application and saves all tasks in a file.

Format: `bye`

Example of usage:

```
bye
```

Expected output:
```
Bye. Hope to see you again soon!
```

## Saving and Loading Data
All data entered is automatically saved by the program and does not
require any commands from the user.

Upon running the file for the first time, the following output would be displayed
on the command line. This is to notify the user that the `tasks.txt` file has been
created successfully in the `data` folder. This data folder will be in the same location
as the folder with the JAR file.
```
Data folder created successfully.

Data file created successfully.
```

Upon exiting the program with the `bye` command, the following output would be displayed,
indicating that the data entered by the user during the session has been saved to the file.
Note that `tasks.txt` will be overwritten with new the data.
```
Writing successful.
```

The next time the program is run, any previously saved data is automatically loaded from `tasks.txt`
and printed on the command line.

```
Retrieving file details.
T | 1 | read book
D | 0 | return book | sunday
E | 0 | project meeting | mon 2pm  4pm
T | 1 | join sports club
T | 0 | borrow book
```
