### BossMan User Guide
BossMan is the individual project component for CS2113, AY23/24 Semester 2
BossMan is CLI task management assistant that allows users to track tasks like todos, deadlines and events.

## Table of Contents
- [BossMan User Guide](#bossman-user-guide)
    - [Starting BossMan](#starting-bossman)
    - [Features](#features)
        - [Task Creation](#task-creation)
        - [Task Management](#task-management)
        - [Persistent State](#persistent-state)
    - [Usage](#usage)
        - [`todo` - Add a TODO task](#todo---add-a-todo-task)
        - [`deadline` - Add a Deadline Task](#deadline---add-a-deadline-task)
        - [`event` - Add an Event Task](#event---add-an-event-task)
        - [`list` - Get your Task List](#list---get-your-task-list)
        - [`mark` - Tag task as done](#mark---tag-task-as-done)
        - [`unmark` - Tag task as undone](#unmark---tag-task-as-undone)
        - [`delete` - Remove a Task](#delete---remove-a-task)
        - [`find` - Query for a task](#find---query-for-a-task)
        - [`bye` - Exit BossMan](#bye---exit-bossman)


## Starting BossMan
To start `BossMan` Using the `jar` file, go to the containing folder for BossMan. Then, on your terminal of choice, run:
`-$ java -jar ip.jar`

## Features
BossMan comes with features for you to manage your daily tasks using CLI.

### Task Creation
Manage your work by creating tasks

BossMan provides three types of tasks for creation:
- `todo`, for general daily tasks
- `deadline`, for tasks that have deadlines
- `event`, for appointments and meetings

### Task Management
BossMan can help you `mark`, `unmark`, `list`, `delete` and `find` your tasks.
- mark allows you to tick off your todos, deadline and events so you can keep track of what tasks are done.
- unmark allows you to untick your tasks if you made an error or if the task is still not done.
- list can list all your tasks to get an overview of what you've done so far.
- delete can delete task specified in the user input
- find can filter the tasks you are looking for by using a keyword.

### Persistent State
BossMan automatically saves all your task data to a text file. When you start BossMan up again, it will load up your tasks from previous sessions automatically.

## Usage

### `todo` - Add a TODO task
Create a todo type of task for BossMan to track.

Syntax:
`todo <your_task_description>`

Example of usage:
`todo homework`

Expected outcome:
BossMan will read your todo description, and echo it back to you via console after adding it to your task list.
`Added:[T][ ] homework
Now you have 10 tasks in the list.`

#### `deadline` - Add a Deadline Task
Create a deadline type of task for BossMan to track.
Deadlines have a due date, a compulsory parameter that must be included.

Syntax:
`deadline <description> /by <YYYY-MM-dd>`

Example of usage:
`deadline assignment 3 /by 2024-06-15`

Expected outcome:
BossMan will read your deadline and due date, and echo it back to you via console after adding it to your task list.
`Added:[D][ ] assignment 3 (do by:2024-06-15)
Now you have 11 tasks in the list.`

### `event` - Add an Event Task
Create an event type of task for BossMan to track.
Events have a from and to time/ date. Unlike deadline, there is no restriction for the format for from and to parameters.
These are compulsory parameters that must be included.

Syntax:
`event <description> /from <from time/date> /to <to time/date>`

Example of usage:
`event hackathon /from tuesday 2pm /to wednesday 6pm`

Expected outcome:
BossMan will read your event, from and to dates, and will echo it back to you via console after adding it to your task list.
`Added:[E][ ] hackathon  (from: tuesday 2pm  to: wednesday 6pm)
Now you have 12 tasks in the list.`

### `list` - Get your Task List
Get your list of tasks.
The list contains an overview of each tasks' status.

Example of usage:
`list`

Expected outcome:
Returns your task list, with tasks sorted in chronological order when it was added.
`Todo List:
1. [D][ ] ppt slides (do by:2024-11-11)
2. [T][ ] homework
3. [E][ ] hackathon  (from: tuesday 2pm  to: wednesday 6pm)`

### `mark` - Tag task as done
Marks a task in the tasklist as done.
The task number is the number of the task from running list. If the task is already done, BossMan will still go ahead and mark it done again.

Syntax:
`mark <task_number>`

Example of usage:
`mark 2`

Expected outcome:
BossMan tags the task as done and echos it back to you.
`Nice! I've marked this task as done:
[T][x] homework`

### unmark - Tag task as undone
Marks a task in the tasklist as NOT done.
The task number is the number of the task from running list. If the task is already not done, BossMan will still go ahead and mark it as undone again.

Syntax:
`unmark <task_number>`

Example of usage:
`unmark 2`

Expected outcome:
BossMan tags the task as not done and echos it back to you.
`OK, I've marked this task as not done yet:
[T][ ] homework`

### `delete` - Remove a Task
Removes a task from your task list. The task number is the number of the task from running list.

Syntax:
`delete <task_number>`

Example of usage:
`delete 2`

Expected outcome:
BossMan removes the task matching the task number from your tasklist. After that, BossMan echoes the removed task back to you.
`Removed task: [T][ ] homework`

### find - Query for a task
Ask BossMan to find all tasks that contain search query keyword.

Syntax:
`find <query_keyword>`

Example of usage:
`find hackathon`

Expected outcome:
BossMan filters out all tasks whose descriptions contains query keyword.
`Matching tasks:
1. [E][ ] hackathon  (from: tuesday 2pm  to: wednesday 6pm)`

### bye - Exit BossMan
Exits BossMan application, returning you to your shell.

Syntax:
`bye`

Example of usage:
`bye`

Expected outcome:
BossMan says goodbye.
`Bye. Hope to see you again soon!`





