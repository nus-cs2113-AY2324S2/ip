# User Guide
```
Hello from
 _____        _     _
|  __ \      | |   | |
| |  | | ___ | |__ | |__  _   _
| |  | |/ _ \| '_ \| '_ \| | | |
| |__| | |_| | |_) | |_) | |_| |
|_____/ \___/|_.__/|_.__/ \__, |
                           __/ |
                          |___/
```
Meet Dobby, your personal task manager to help you stay organised and on top of all your tasks

## Features 
#**Add Tasks**
Dobby allows you to add various types of tasks such as todo, deadline, and event.

#**Mark Tasks**
You can mark tasks as completed to keep track of your progress.

#**Delete Tasks**
Remove tasks that are no longer needed from your task list.

#**Find Tasks**
Search for tasks containing specific keywords to quickly locate relevant tasks.

#**List Tasks**
List all tasks in task list

## Usage

### `todo` - Add a todo Task

Adds a todo task to your task list.

format: todo < description >

Example of usage: 

`todo Buy lunch`
Expected outcome: <br />
![image](https://github.com/Mmaxx15/ip/assets/88656341/d7305494-ca6a-40ff-b86b-64240ca7c0c7)

### `deadline` - Add a Deadline Task 

Adds a task with a deadline to your task list.

format: deadline < description > /by < deadline >

Example of usage: 

`Deadline complete assignment /by today 9pm`
Expected outcome: <br />
![image](https://github.com/Mmaxx15/ip/assets/88656341/c7a1a728-5b3b-48bc-a0b0-7be0008d5462)

### `event` - Add a event Task

Adds a event to your task list.

format: event < description > /from < start time/date > /to < end time/date >

Example of usage: 

`event formula 1 race /from sunday 9pm /to 11pm`
Expected outcome: <br />
![image](https://github.com/Mmaxx15/ip/assets/88656341/fcbf896f-d622-4459-9dfe-f2c6f017f20a)

### `mark` - Mark a Task as Done

Marks a task as completed.

format: mark < task index >

Example of usage:

`mark 1`
Expected outcome: <br />
![image](https://github.com/Mmaxx15/ip/assets/88656341/774d69da-20f7-4d96-86f6-eff2eeef1f44)

### `unmark` - Mark a Task as Undone

Marks a completed task as undone.

format: unmark < task index >

Example of usage:

`unmark 1`
Expected outcome: <br />
![image](https://github.com/Mmaxx15/ip/assets/88656341/c7300cbe-cd01-4ec5-8efe-dfd3587e2c02)

### `delete` - Delete a Task

Removes a task from your task list.

format: delete < task index >

Example of usage:

`delete 1`
Expected outcome: <br />
![image](https://github.com/Mmaxx15/ip/assets/88656341/d0cb910d-e76b-448a-a19e-2f73deffaa94)

### `find` - Find Tasks by Keyword

Searches for tasks containing specific keywords. returns a list of tasks with matching keywords

format: find < keyword >

Example of usage:

`find formula`
Expected outcome: <br />
![image](https://github.com/Mmaxx15/ip/assets/88656341/1714c394-d026-4cc1-9ea6-8b11828f0eeb)

### `list` - Lists all Tasks

Lists all tasks in task list including current status and task type

format: list

Example of usage:

`list`
Expected outcome: <br />
![image](https://github.com/Mmaxx15/ip/assets/88656341/1714c394-d026-4cc1-9ea6-8b11828f0eeb)

### `bye` - exits dobby

ends the program

format: bye
 





