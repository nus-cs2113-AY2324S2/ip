# MimiChat User Guide

## Features 
1. [add a ToDo task](#add-todo-task)
2. [add a Deadline task](#add-deadline-task)
3. [add an Event task](#add-event-task)
4. [list all tasks](#list-all-task)
5. [mark a task as done](#mark-as-done)
6. [unmark a task as done](#unmark)
7. [delete a task](#delete)
8. [find tasks by keyword](#find)
9. [exit the program](#bye)
10. [troubleshoot](#troubleshoot)

## Usage
1. In order for the program to work, you need to have Java 11 installed on your computer.
2. Download the latest release of MimiChat from https://github.com/JustinSoh/ip/releases
3. Run the program using the command `java -jar MimiChat.jar` in the terminal.
4. Make sure that there is a /data folder in the root of the program directory. This is where the data will be stored.
5. You can now start using the program.


## Add ToDo Task

### `todo` - Adds a new Todo task

Adds a new ToDo task into the Task List.

Parameters:

``taskName (String)``

Example of usage: 

`todo <taskName>`

Expected outcome:
```
Got it. I've added this task:
    [T][X] read book
You now have 1 tasks in the list.
```

## Add Deadline Task

### `deadline` - Adds a new Deadline task

Adds a new Deadline task into the Task List.

Parameters:

``taskName (String)``
``deadline (String)``

Example of usage:

`deadline <taskName> /by <deadline>`

Expected outcome:
```
Got it. I've added this task:
    [D][X] return book (by: 2nd of December 2020)
You now have 2 tasks in the list.

```

## Add Event Task

### `event` - Adds a new Event task

Adds a new Event task into the Task List.

Parameters:

``taskName (String)``
``startTime (String)``
``endTime (String)``

Example of usage:

```event <taskName> /from <startTime> /to <endTime>```

Expected outcome:
```
Got it. I've added this task:
    [E][X] project meeting (from: Mon 3pm to: Mon 5pm)
You now have 3 tasks in the list.
```


## List All Task

### `list` - Lists all tasks

List all tasks in the Task List and whether they are marked. 

Example of usage:

`list`

Expected outcome:
```
Here are the tasks in your list:
1. [T][X] read book
2. [D][X] return book (by: 2nd of December 2020)
3. [E][X] project meeting (from: Mon 3pm to: Mon 5pm)
```

## Mark as Done

### `mark` - Marks a task as done

Marks a task as done in the Task List. 

Parameters:

``Mark <itemNumber>``

Example of usage:
`mark 2`

Expected outcome:
``` 
OK, I've marked this task as done:
2. [D][X] return book (by: 2nd of December 2020)
```

## Unmark


### `unmark` - Unmarks a task as done

Unmarks a task as done in the Task List.

Parameters: 

``Unmark <itemNumber>``

Example of usage:
`unmark 2`

Expected outcome:
```
OK, I've marked this task as not done yet
2. [D][ ] return book (by: 2nd of December 2020)
```

## Delete

### `delete` - Deletes a task

Deletes a task from the Task List.

Parameters:

``Delete <itemNumber>``

Example of usage:
`delete 2`

Expected outcome:
```
Noted. I've removed this task:
        2. [D][ ] return book (by: 2nd of December 2020)
Now  you have 2 tasks in the list.
```

## Find

### `find` - Finds tasks by keyword

Finds tasks in the Task List which contains the keyword.

Parameters:

``Find <keyword>``

Example of usage:
`find book`

Expected outcome:
```
Here are the matching tasks in your list:
1. [T][X] read book
```

Expected outcome:
```
Here are the matching tasks in your list:
You have no matching tasks in your list.
```

## Bye

### `bye` - Exits the program

Exits the program.

Example of usage:

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```

## Troubleshoot
### 1. I keep receiving the `Error: Unable to load file`
- Please ensure /data folder exists in the root directory
  ```
  directory
  |--- mimichat.jar
  |--- /data 
  ```
### 2. I have already created the `/data` folder, why do I still see the error?
- Please ensure that the `/data` folder is in the same directory as the `mimichat.jar` file.
- If so, then you can ignore the error as the system will automatically create the mimi.logs file once you enter a new task.




