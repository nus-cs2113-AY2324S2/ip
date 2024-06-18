# YOJ User Guide


## Overview
YOJ is a CLI-based application that allows users to track their tasks. Users can
add and delete tasks from a list, mark them as Done or undone and view all current tasks. This user guide aims to
help users get familiarized with the different commands available in the application.


## Quick Start 
1. Download Java11.
2. Download the latest JAR file from [here](https://github.com/liuzehui03/ip/releases/tag/A-Release).
3. Move the JAR file into an empty folder.
4. In the command terminal, use the `cd` command to switch to the
   folder with the JAR file.
5. Enter `java -jar ip.jar` in the command terminal to run this application.
6. Refer to the features below for details of the various commands and how to use YOJ.


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
AGENDA ✉
♡ All the best!! 💕 
_____________
1. [T][ ] add tasks
2. [D][ ] finish user guide (by: monday)
3. [E][X] project meeting (from: Mon 2pm to: 4pm)
```

### Adding a ToDo task: `todo`

Adds a ToDo task to the task list.

Format: `todo [description]`

Example of usage:

```
todo exercise
```

Expected output:
```
okii task added :
[T][ ] exercise
Now you have 1 tasks in the list
```

### Adding a Deadline task: `deadline`

Adds a Deadline task to the task list.

Format: `deadline [description] /by [deadline]`

Example of usage:

```
deadline return file /by monday
```

Expected output:
```
okii task added :
[D][ ] return file (by: monday)
Now you have 2 tasks in the list.
```

### Adding an Event task: `event`

Adds an Event task to the task list.

Format: `event [description] /from [eventStart] /to [eventEnd]`

Example of usage:

```
event project meeting /from Mon 2pm /to 4pm
```

Expected output:
```
okii task added :
[E][ ] project meeting (from: Mon 2pm to: 4pm)
Now you have 3 tasks in the list.
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
okiee! amazing!! I've marked this task as done: 
[T][X] exercise
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
alright I've marked this task as not done yet, do rmb to do it soon: 
[T][ ] exercise
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
okiee i've deleted the task
[T][ ] exercise
Now you have 2 tasks in the list.
```

### Finding a task: `find`

Returns all tasks in the task list that contains the keyword.

Format: `find [keyword]`

Example of usage:

```
find assignment
```

Expected output:
```
🔍 here are the tasks found with the keyword!
1. [T][ ] assignment 3
2. [T][ ] review assignment 2
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
bye bye!! hope to see u soon :) 😊
```
Make sure to say bye to ensure your data is saved properly, otherwise YOJ might not remember the tasks

### Data Management
Data would be saved locally on your device upon usage of `bye`
