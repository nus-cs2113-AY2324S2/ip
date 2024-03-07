# N.chatbot - User Guide
## Overview
Want to keep track of the tasks that you have to do? 
N.chatbot allows users to interact with and create 
their task list directly from the command line, making 
task management straightforward and accessible.

## Features 

### Adding a Task
> [!NOTE]
> There are 3 types of tasks:
> - `todo` specifies a basic task
> - `deadline` specifies a task that has to be completed 
> by a certain deadline
> - `event` specifies a task with a specified duration
> 
> each task has a specific input format and display format 
> in the tasklist.

#### Adding a Basic Task: `todo`
Adds a basic task to the tasklist.

Format: `todo [task description]`

Example:
```
todo wash dishes
    ____________________________________________________________

    Got it, ToDo task has been added:
    1. [T] [ ] wash dishes
    Now you have 1 task in the list
    ____________________________________________________________
```

#### Adding a Task with a Deadline: `deadline`
Adds a task with a deadline to the tasklist.

Format: `deadline [task description] /by [deadline]`

Example:
```
deadline wash dishes /by 12am
    ____________________________________________________________

    Got it, Deadline task has been added:
    2. [D] [ ] wash dishes(by: 12am)
    Now you have 2 tasks in the list
    ____________________________________________________________
```

#### Adding a Task with a Time Period: `event`
Adds a task with a specified duration to the tasklist.

Format: `event [task description] /from [start time] /to [endtime]`

Example:
```
event wash dishes /from 2pm /to 4pm
    ____________________________________________________________

    Got it, Event task has been added:
    3. [E] [ ] wash dishes (from: 2pm to: 4pm)
    Now you have 3 tasks in the list
    ____________________________________________________________
```

### Viewing the Current Task List: `list`
Allows the user to view the tasks that are currently in 
the task list.

Format: `list`

Example:
```
list
    ____________________________________________________________

    Here are the tasks in your list:
    1. [T] [ ] wash dishes
    2. [D] [ ] wash dishes(by: 12am)
    3. [E] [ ] wash dishes (from: 2pm to: 4pm)
    ____________________________________________________________
```

### Deleting a Task: `delete`
Deletes a task that is currently in the task list.

The CLI output message will also inform the user on the number of
tasks remaining.

Format: `delete [task index]`

Example:
```
delete 2
    ____________________________________________________________

    Noted, I have removed the following task:
    2. [D] [ ] wash dishes(by: 12am)
    Number of Tasks Remaining: 2
    ____________________________________________________________

```
### Marking / Unmarking Tasks

#### Marking a Task as Done: `mark`
Marks a task as done in the task list. 

Format: `mark [task index]`

Example:
```
mark 1
    ____________________________________________________________

    Task 1 marked done, yay! :)
    ____________________________________________________________
```

#### Unmarking a Task to Undone: `unmark`
Unmarks a task that has been marked as done previously.

Format: `unmark [task index]`

Example:
```
unmark 1
    ____________________________________________________________

    Okay, task 1 unmarked, let's complete it soon ~.o.~
    ____________________________________________________________
```

> [!IMPORTANT]
> For `delete`, `mark`, `unmark`:
> - The task index provided **must be a positive integer**
>    1, 2, 3, ...
> - The task index provided **must exist**! (i.e. if there
>  are only 3 tasks in the list, the task index provided
>  should not go beyond 3)

### Finding a Task: `find`
Searches through the task list to find tasks that contain 
the keyword specified by the user.

Format: `find [keyword]`
- Keyword **must** be provided by the user.

Example:
```
find dishes
    ____________________________________________________________

    Here are the tasks I found in your list:
    1. [T] [ ] wash dishes
    2. [E] [ ] wash dishes (from: 2pm to: 4pm)
    ____________________________________________________________
```

### Saving the Current Task List: `save`
Saves the current task list to a text file _n.txt_.

Format: `save`

Expected output:
```
    ____________________________________________________________

    Your Task List has been saved, find it at .\data\n.txt
    ____________________________________________________________
```

### Exiting the Program: `bye`
Exits the program.

Format: `bye`

Expected Output:
```
    ____________________________________________________________

    Bye. Hope to see you again soon!
    ____________________________________________________________
```

## Command Summary

| Command    | Format                                                       |
|------------|--------------------------------------------------------------|
| `todo`     | `todo [task description]`                                    |
| `deadline` | `deadline [task description] /by [deadline]`                 |
| `event`    | `event [task description] /from [start time] /to [end time]` |
| `list`     | `list`                                                       |
| `delete`   | `delete [task index]`                                        |
| `mark`     | `mark [task index]`                                          |
| `unmark`   | `unmark [task index]`                                        |
| `find`     | `find`                                                       |
| `save`     | `save`                                                       |
| `bye`       | `bye`                                                         |

