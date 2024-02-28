# Uwunzhe User Guide

Uwunzhe is a Command Line Interface (CLI) application which helps to keep track of upcoming tasks.
Consists of flavour text based on a certain special individual.

## Getting Started

1. Make sure you have Java 11 installed on your computer. Note that it does not work on newer versions of Java.
2. Download the .jar file from [here](https://github.com/1simjustin/ip/releases).
3. Open a terminal in the directory which you saved the .jar file, then run the following:
```
java -jar earl.jar
```

## Quick Reference
|Command                                    |Parameters                             |
|-------------------------------------------|---------------------------------------|
|[`list`](#printing-todo-list-list)         |None                                   |
|[`todo`](#adding-to-dos-todo)              |`<task name>`                          |
|[`deadline`](#adding-deadlines-deadline)   |`<task name> /by <deadline>`           |
|[`event`](#adding-events-event)            |`<task name> /from <start> /to <end>`  |
|[`mark`](#mark-completion-mark)            |`<index>`                              |
|[`unmark`](#unmark-completion-unmark)      |`<index>`                              |
|[`delete`](#deleting-a-task-delete)        |`<index>`                              |
|[`find`](#finding-a-task-find)             |`<description experession>`            |
|[`date`](#find-a-date-date)                |`<date>`                               |
|[`bye`](#exiting-the-application-bye)      |None                                   |

## Features 

### Add Tasks

Uwunzhe allows for adding of tasks of the following types:
- To Do
- Deadline (Consists of due date)
- Event (Consists of start date and end date)

### Various Commands

Uwunzhe consists of many commands which allows users to manipulate their to-do list.
Commands are explained [below](#Usage).

### Local Storage

Uwunzhe saves past data locally, allowing users to access past data even after restarting the application.
Data is saved to `./data/uwunzhe.txt` in the same directory as the `uwunzhe.jar` file.

## Usage

### Printing Todo List: `list`

Prints the list of tasks.
Prints flavour text if the list is currently empty.

Format: `list`

Expected outcome:
The current list of tasks is printed in order of being added.
Each task is shown in the format:
- S/N
- Task Type
- Completion Status
- Description
- Deadline/Start Date (for deadline/event respectively)
- End Date (for event)

Example outcome:
```
Yay! List!
1.[T][X] Coursemology Exercises
2.[T][X] Canvas Quizzes
3.[D][ ] IP Submission (by: 07 Mar 2024)
4.[E][ ] TP (from: 07 Mar 2024 to: 11 Apr 2024)
```

### Exiting the application: `bye`
Gracefully shuts the application down.

Format: `bye`

Expected outcome:
The program exits after printing flavour text.

### Adding To-Dos: `todo`

Create a to do task and add it to the list.

Format: `todo <task description>`

Example of usage: `todo Coursemology Exercises`

Expected outcome:
The following message indicates that the task has successfully been added to the list.
The task is then saved to storage.
```
Okey dokey here we go
 [T][ ] <task description>
We only have uhhhh <list size> more things left to go!
```

### Adding Deadlines: `deadline`

Create a task with a deadline and add it to the list.
Deadline is to be in the format YYYY-MM-DD.

Format: `deadline <task description> /by <deadline>`

Example of usage: `deadline IP Submission /by 2024-03-07`

Expected outcome:
The following message indicates that the task has successfully been added to the list.
The task is then saved to storage.
```
Okey dokey here we go
 [D][ ] <task description> (by: <deadline>)
We only have uhhhh <list size> more things left to go!
```

### Adding Events: `event`

Create an event with a start date and end date and add it to the list.
Start and end dates are to be in the format YYYY-MM-DD.

Format: `event <task description> /from <start date> /to <end date>`

Example of usage: `event TP /from 2024-03-07 /to 2024-04-11`

Expected outcome:
The following message indicates that the task has successfully been added to the list.
The task is then saved to storage.
```
Okey dokey here we go
 [E][ ] <task description> (from: <start date> to: <end date>)
We only have uhhhh <list size> more things left to go!
```

### Mark Completion: `mark`

Mark a task, based on its index in the list, as completed.
When printing, this is denoted as an X.

Format: `mark <index>`

Example of usage: `mark 1`

Expected outcome:
The following message indicates that the task has successfully been marked as complete.
The completion status is then saved to storage.
```
Good job team! We did it!
 [<task type>][X] <task description>
```

### Unmark Completion: `unmark`

Mark a task, based on its index in the list, as incomplete.
When printing, this is denoted as a blank space.

Format: `unmark <index>`

Example of usage: `unmark 1`

Expected outcome:
The following message indicates that the task has successfully been marked as incomplete.
The completion status is then saved to storage.
```
Good job team! We did it!
 [<task type>][ ] <task description>
```

### Deleting a Task: `delete`

Removes a task, based on its index in the list, from the list.

Format: `delete <index>`

Example of usage: `delete 1`

Expected outcome:
The following message indicates that the task has successfully been removed.
The updated task list is then saved to storage.
```
There goes that task!
 [<task type>][<completion>] <task description>
We only have uhhhh <list size> more things left to go!
```

### Finding a task: `find`

Searches for tasks with description containing or matching the input expression.

Format: `find <expression>`

Example if usage: `find Coursemology`

Expected outcome:
Prints a list of the tasks which contains or matches the input expression, in order of adding into the list.
```
1.[<task type>][<completion>] <task description>
```

### Find a date: `date`

Searches for tasks which occur on a specified date.
Can only search for deadlines and events.
Date must be of format YYYY-MM-DD

Format: `data <date>`

Example of usage: `date 2024-03-06`

Expected outcome:
Prints a list of the tasks which fall on the specified date, in order of adding into the list.
```
1.[<task type>][<completion>] <task description>
```