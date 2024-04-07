# User Guide

## Features 

### Add and delete a task

adds a Todo, Deadline, or Event task to the task list or deletes a task from the task list

### Mark and unmark a task

marks task as complete or incomplete

### Find task

finds tasks that contains the given keyword in its description

### Load from and save to disk

loads task list from hard disk when chatbot starts up and saves task list to hard disk when chatbot terminates

## Usage

### `todo` - adds a Todo task to task list

Parses user input, creates a Todo object, appends it to the task list, and displays a confirmation message

Example of usage: 

`todo read book`

Expected outcome:

The todo is added to the list and the following confirmation message is displayed

```
Got it. I've added this task:
[T][ ] read book
Now you have 1 tasks in the list.
```
### `deadline` - adds a Deadline task to task list

Parses user input, creates a Deadline object, appends it to the task list, and displays a confirmation message

Example of usage: 

`deadline cs homework /by Tue 11:59 pm`

Expected outcome:

The deadline is added to the list and the following confirmation message is displayed

```
Got it. I've added this task:
[D][ ] cs homework (by: Tue 11:59 pm)
Now you have 2 tasks in the list.
```
### `event` - adds a Event task to task list

Parses user input, creates a Event object, appends it to the task list, and displays a confirmation message

Example of usage: 

`event cs lecture /from Fri 4pm /to Fri 6pm`

Expected outcome:

The event is added to the list and the following confirmation message is displayed

```
Got it. I've added this task:
[E][ ] cs lecture (from: Fri 4pm to: Fri 6pm)
Now you have 3 tasks in the list.
```
### `list` - lists all tasks

Lists all the tasks in the task list and their current status

Example of usage: 

`list`

Expected outcome:

All tasks currently in the task list are displayed as following:

```
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] cs homework (by: Tue 11:59 pm)
3. [E][ ] cs lecture (from: Fri 4pm to: Fri 6pm)

```
### `mark` - mark completed

marks a task in the tasklist as completed

Example of usage: 

`mark 2`

Expected outcome:

The second task is marked as complete and the following confirmation is shown:

```
Nice! I've marked this task as done: 
[X] cs homework (by: Tue 11:59 pm)
```
### `unmark` - mark incompleted

unmarks a previously marked task

Example of usage: 

`unmark 2`

Expected outcome:

The second task is unmarked and the following confirmation is shown:

```
Ok, I've marked this task as not done yet:
[ ] cs homework (by: Tue 11:59 pm)
```
### `delete` - delete task

deletes a task from the task list

Example of usage: 

`delete 1`

Expected outcome:

The first task is deleted and the following confirmation message is shown:

```
Now you have 2 tasks in the list.
```
### `find` - finds task

find tasks that match a certain keyword/phrase

Example of usage: 

`find cs`

Expected outcome:

The two tasks containing the word 'cs' will be displayed as following:

```
Here are the matching tasks in your list:
[D][ ] cs homework (by: Tue 11:59 pm)
[E][ ] cs lecture (from: Fri 4pm to: Fri 6pm)
```
### `bye` - terminates program

terminates the chatbot and the current task list is saved to disk

Example of usage: 

`bye`

Expected outcome:

Chatbot terminates after showing the following message:

```
Bye. Hope to see you again soon!
```
