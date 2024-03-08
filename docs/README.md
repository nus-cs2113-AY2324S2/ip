# User Guide for NyanBot

> NyanBot is a CLI chatbot programme for task management. By default, it stores the user's tasks in **./data/nyan.txt** in the **same directory as the jar file**.

### Running the chatbot

Prerequisites: Have java *(verson >= 11)* installed.

> Create an empty folder
> Put the provided jar file inside folder
> Run programme with terminal using:
```
java -jar ./nyan.jar
```

### Task types

- ToDo: Task with ```Description```
- Deadline: Task with ```Description``` and ```Deadline```
- Event: Task with ```Description```, ```From``` and ```To```

# Features

## - Add a ToDo task ```todo```

Adds ToDo task to task list.

## - Add an Event task ```event```

Adds Event task to task list.

## - Add a Deadline task ```deadline```

Adds Deadline task to task list.

## - Remove a task ```delete```

Removes a task from task list.

## - Mark/Unmark a task ```mark``` / ```unmark```

Marks task in task list as complete or incomplete.

## - List all tasks ```list```

Lists all stored tasks in task list.

## - Find certain tasks ```find```

Find and list out tasks with description containing keyword.


# Usage

## Adding a task

Add task of specific type, description and time information.

**Format**:
- Event: ```event [description] //[time] //[time]```
- Deadline: ```deadline [description] //[time]```
- Todo: ```todo [description]```

*Example usage*:

- ```todo wake up for lecture```
- ```deadline submit IP //12pm this friday```
- ```event 2113 lecture //friday 4pm /friday 6pm```

*Expected outcome*:

With proper arguments, task will be added and success message will be printed in console.
Else, error message will be printed or exception will be thrown.

## Mark task as complete or incomplete

Mark task of specified index in task list as done (or undone using unmark).

**Format**:
- Mark: ```mark [index]```
- Unmark: ```unmark [index]```

*Example usage*:

- ```mark 3```
- ```unmark 5```

*Expected outcome*:

With proper arguments, task will be marked/unmarked and success message will be printed in console.
Else, error message will be printed or exception will be thrown.

## List: ```list```: List tasks

List all tasks currently existing in task list.

**Format**:
- ```list```

*Example usage*:

- ```list```

*Expected outcome*:

All tasks will be printed out in the console.
Else, error message will be printed or exception will be thrown.

### Find: ```find``` : Finding tasks

Find all tasks in task list with descriptions containing keyword then list all found tasks.

**Format**:
- ```find [keyword]```

*Example usage*:

- ```find lecture```

*Expected outcome*:

All tasks containing the keyword in description will be printed out in console.
Else, error message will be printed or exception will be thrown.

### Delete: ```delete``` : Deleting a task

Delete task of specified index in task list irreversibly.

**Format**:
- ```delete NUMBER```

*Example usage*:

- ```delete 3```

*Expected outcome*:

Task of specified index will be deleted irreversibly and success message will be printed in console.
Else, error message will be printed or exception will be thrown.

### ```bye``` : Exit the program:

Stops program and exits.

**Format**:
- ```bye```

*Expected outcome*:

Data file is updated with current task list then program is exited.

# Known Issues
-Cringe warning


