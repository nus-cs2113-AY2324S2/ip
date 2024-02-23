# GAB chatbot User Guide

Gab is a **Command Line Interface (CLI)** desktop app for managing your tasks (todo, deadline and event). 

## Features 
* [Adding a new todo task: `todo`](#Adding-a-todo-task)
* [Adding a new deadline task: `deadline`](#Adding-a-deadline-task)
* [Adding a new event task: `event`](#Adding-a-event-task)
* [Listing all task: `list`](#List-all-tasks)
* [Mark task as done: `mark`](#Mark-task-as-done)
* [Mark task as not done: `unmark`](#Mark-task-as-not-done)
* [Find task that contains keyword: `find`](#Find-task)
* [Delete task: `delete`](#Delete-task)
* [Exit program: `exit`](#Exit-program)
### Feature-ABC

Description of the feature.

### Feature-XYZ

Description of the feature.

## Usage

### Adding a todo task: `todo` 
Create a todo task with just the task name.

Format: `todo TASK_NAME`

Examples:
* todo training
* todo create jar file

Expected outcome: 
```
_________________________________________
Oh no! One new task added...
[T][ ] training
Now you have 1 task(s)
_________________________________________

```
### Adding a deadline task: `deadline`
Create a deadline task with a name and deadline.

Format: `deadline TASK_NAME /by DEADLINE`

Examples:
* deadline training /by tmr
* deadline create jar file /by week 7

Expected outcome:
```
_________________________________________
Ok! Watch the deadline!
[D][ ] training (by: tmr)
Now you have 2 task(s)
_________________________________________

```

### Adding a deadline task: `event`
Create a deadline task with a name, start and end date.

Format: `event TASK_NAME /from START_DATE /to END_DATE`

Examples:
* event training /from saturday 9am /to 11am
* event attend class /from now /to tmr

Expected outcome:
```
_________________________________________
Weehoo! Enjoy the event!
[E][ ] training (from: saturday 9am to: 11am)
Now you have 3 task(s)
_________________________________________

```

### List all tasks: `list`
List all the task in a numbered list from the task list.

Format: `list`

Examples:
* list

Expected outcome:
```
_________________________________________
All your tasks are here
1.[D][ ] training 
2.[D][ ] training (by: tmr)
3.[E][ ] training (from: saturday 9am to: 11am)
_________________________________________

```

### Mark task as done: `mark`
Mark task with a cross to show that it is done

Format: `mark TASK_INDEX`
* Mark task of the TASK_INDEX as done with a cross
* TASK_INDEX **must be positive integer** 1, 2, 3, ...

Examples:
* mark 1

Expected outcome:
```
_________________________________________
One done!
[T][X] training
_________________________________________

```

### Mark task as not done: `unmark`
Un-mark task to show that it is not done

Format: `unmark TASK_INDEX`
* Un-mark task of the TASK_INDEX 
* TASK_INDEX **must be positive integer** 1, 2, 3, ...

Examples:
* unmark 1

Expected outcome:
```
_________________________________________
Oh no!
[T][ ] training
_________________________________________

```

### Find task: `find`
Un-mark task to show that it is not done

Format: `find KEYWORD`
* KEYWORD is the word you want to find 
* KEYWORD is not case sensitive

Examples:
* find floorball
* find training

Expected outcome:
```
_________________________________________
Yay! No task found...
_________________________________________

```
```
_________________________________________
1. [T][ ] training
2. [D][ ] training (by: tmr)
3. [E][ ] training (from: saturday 9am to: 11am)
_________________________________________

```

### Delete task: `delete`
Delete task from task list

Format: `delete TASK_INDEX`
* Delete task of the TASK_INDEX
* TASK_INDEX **must be positive integer** 1, 2, 3, ...

Examples:
* delete 1

Expected outcome:
```
_________________________________________
Ok task deleted!
	[T][ ] training
Now you have 2 task(s)
_________________________________________

```

### Mark task as done: `bye`
Exits the program

Format: `exit`

Examples:
* exit

Expected outcome:
```
_________________________________________
All the best!
_________________________________________

```


