# User Guide
Hello! This is a **ChatBot** named **Altria** for you to manage your own task and stuffs.
## Features 

### Memory of ChatBot
The Memory of the ChatBot is stores in **data/saver.txt**. The bot will create
the data automatically when the first you start using it.

### Add tasks
The ChatBot can add the task you want to do to its memory by typing on commandline
you may refer to usage for a detail explanation.

### Manage tasks
You can also manage the tasks with some simple command like `delete` or view all the tasks with
command `list`

## Usage
Type the following command in command line to use the **ChatBot** :
### `list` - List all the tasks

Example of usage: 
`list`
Expected outcome:

```
------------------------------------------------------------------
	1.[T][X] book
	2.[T][ ] a++
	3.[D][ ] finish homework (by: Feb 26 2024)
	
------------------------------------------------------------------
```
<br/>

### `delete` - Delete a Task with it's index
Example of usage:`delete 3`
Expected outcome:
```
------------------------------------------------------------------
	I see, I will remove the task 3

	[D][ ] finish homework (by: Feb 26 2024)
	Now you have 2 tasks in my memory 
------------------------------------------------------------------
```
<br/>

### `mark` - Mark a Task as Down
Example of usage:`mark 3`
Expected outcome:
```
------------------------------------------------------------------
	Nice! I've marked this task as done:
	[D][X] finish homework (by: Feb 26 2024)
------------------------------------------------------------------
```
<br/>

### `unmark` - Unmark a Task
Example of usage:`unmark 3`
Expected outcome:
```
------------------------------------------------------------------
	OK, I've marked this task as not done yet:
	[D][ ] finish homework (by: Feb 26 2024)
------------------------------------------------------------------
```
<br/>

### `find` - Find the task with a given name
Example of usage:`find homework`
Expected outcome:
```
------------------------------------------------------------------
	Here are the matching tasks in your list:
	1.[D][ ] finish homework (by: Feb 26 2024)
	2.[T][ ] give the homework to cindy
------------------------------------------------------------------
```
<br/>

### `todo` - Add the todo task to the list
Example of usage:`todo sleep`
Expected outcome:
```
------------------------------------------------------------------
	Got it. I've added this task:
	[T][ ] sleep

	Now you have 1 tasks in the list.
------------------------------------------------------------------
```
<br/>

### `deadline` - Add the Deadline task to the list
Example of usage:`deadline finish the IP /by 2024-03-08`
Expected outcome:
```
------------------------------------------------------------------
	Got it. I've added this task:
	[D][ ] finish the ip (by: Mar 8 2024)

	Now you have 2 tasks in the list.
------------------------------------------------------------------
```   
<br/>

### `event` - Add the event task to the list
Example of usage:`deadline finish the IP /by 2024-03-08`
Expected outcome:
```
------------------------------------------------------------------
	You can also use date format like 2023-07-30
	Got it. I've added this task:
	[E][ ] project meeting (from: mon 2pm to 4pm)

	Now you have 3 tasks in the list.
------------------------------------------------------------------
```
<br/>

### `bye` - Exit the mean program

## QA
If you encounter any problems or want to contact with me, feel free to contact me at
**yzhanglp0730@gmail.com**. You can also visit my [personal website](yzhanglp.com) for my information!