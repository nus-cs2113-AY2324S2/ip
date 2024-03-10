# SUNNY BOT USER GUIDE

Welcome to Sunny Bot, your personal task manager! Below are the features and usage guidelines to help you make the most of Sunny Bot.

## Features 

### Feature 1 - Adding of tasks

Sunny Bot allows you to add different types of tasks, such as Todo, Deadline, and Event.

Usage

**'todo' - Add a Todo task**

Add a new task without any specific date or time.

Example of usage:

`todo Buy groceries`

Expected outcome:
Sunny Bot will add a new Todo task and confirm the addition.

`Got it! I have successfully added:
[T][ ] Buy groceries
Now you have 1 task in the list.`


**'deadline' - Add a Deadline task**

Add a task with a specific deadline.

Example of usage:

`deadline Submit report /by mon`

Expected outcome:
Sunny Bot will add a new Deadline task and confirm the addition.

`Got it! I have successfully added:
[D][ ] Submit report (by:mon)
Now you have 2 tasks in the list.`


**event - Add an Event task**

Add a task with a specific start and end time.

Example of usage:

`event Team meeting /from 12pm /to 2pm`

Expected outcome:
Sunny Bot will add a new Event task and confirm the addition.

`Got it! I have successfully added:
[E][ ] Team meeting (from:12pm to:2pm)
Now you have 3 tasks in the list.`

Feel free to explore and try different variations with optional arguments!

### Feature 2 - Viewing Task List

Sunny Bot allows you to view the list of tasks currently in your task manager.

Usage

**list - Display the list of tasks**

View all tasks currently in your task manager.

Example of usage:

`list`

Expected outcome:
Sunny Bot will display the list of tasks.

`Here are the tasks in your list:
1. [T][ ] Buy groceries
2. [D][ ] Submit report (by:mon)
3. [E][ ] Team meeting (from:12pm to:2pm)`


### Feature 3 - Mark/Unmark Task as Done
Mark a task as done or undo the mark to indicate completion status.

Usage

**mark - Mark a task as done**

Mark a specific task as completed.

Example of usage:

`mark 2`

Expected outcome:
Sunny Bot will mark the specified task as done and confirm the action.

`Nice! I've marked this task as done!
[D][X] Submit report (by:mon)`


**unmark - Unmark a task as done**

Undo the completion status of a task.

Example of usage:

`unmark 2`

Expected outcome:
Sunny Bot will undo the completion status of the specified task and confirm the action.

`OK, I've marked this task as not done yet.
[D][ ] Submit report (by:mon)`

### Feature 4 - Find Tasks
Search for tasks containing specific keywords.

Usage
**find - Find tasks based on keywords**

Find tasks that match specific keywords.

Example of usage:

`find report`

Expected outcome:
Sunny Bot will display tasks containing the specified keyword.

`Here are the matching tasks in your list:
1.[D][ ] Submit report (by:mon)`

Feel free to experiment with different keywords to locate the tasks you need. Sunny Bot's flexible search functionality makes it easy to find and manage your tasks effectively.

##Feature 5 - Load, Save, and Display Data
Sunny Bot provides the ability to load tasks from a file, save tasks to a file, and display them again when the program runs.

Usage
**Loading Tasks from File:**
Sunny Bot automatically loads tasks from a file when you start the program.

Example:

`Tasks loaded from file:
Read from file: [T][ ] buy groceries
Read from file: [D][ ] Submit report (by:mon)
Read from file: [E][ ] Team meeting (from:12pm to:2pm)
Tasks loaded successfully!`


**Saving Tasks to File:**
Sunny Bot will automatically save your tasks in the file for future use.

Every time a change is made, the program displays:

`Saving tasks to file...
Tasks saved successfully!`


**Displaying Tasks:**
Sunny Bot displays the loaded tasks when you run the program. If you have saved tasks, they will be loaded and presented in the task list.

Example:

`Here are the tasks in your list:
1. [T][ ] buy groceries
2. [D][ ] Submit report (by:mon)
3. [E][ ] Team meeting (from:12pm to:2pm)`



## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
