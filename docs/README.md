# Dross User Guide

## Overview
Dross is a CLI-based application that allows users to track their tasks. Users can
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
All tasks:
1.[E][x] gareth attend cs2113 lecture friday 23 feb 2024 (from: 4 to: 6pm)
2.[T][x] testing
3.[T][ ] gareth
4.[T][x] garethsome
5.[T][ ] gareth things
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
Last added task: [T][ ] read book
```

### Adding a Deadline task: `deadline`

Adds a Deadline task to the task list.

Format: `deadline [description] /by [deadline]`

Example of usage:

```
deadline sleep /by tonight
```

Expected output:
```
Last added task: [D][ ] sleep (by: tonight)
```

### Adding an Event task: `event`

Adds an Event task to the task list.

Format: `event [description] /from [eventStart] /to [eventEnd]`

Example of usage:

```
event class /from 2 /to 4
```

Expected output:
```
Last added task: [E][ ] class (from: 2 to: 4)
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
mark 1
_______________________________________________________
All tasks:
1.[D][x] sleep (by: tonight)
2.[E][ ] class (from: 2 to: 4)
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
unmark 1
_______________________________________________________
All tasks:
1.[D][ ] sleep (by: tonight)
2.[E][ ] class (from: 2 to: 4)
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
delete 1
Task 1 successfully deleted boss
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
find book
_______________________________________________________
These stuff have the same description of what you searched for or whatever
1.[T][ ] read book
2.[T][ ] write book
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
