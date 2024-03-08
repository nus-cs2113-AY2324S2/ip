# Ms Chatty User Guide
Ms Chatty is a desktop app for **managing tasks, optimized for use via a Command Line Interface** (CLI).

* [Quick start](#quick-start)
* [Features](#features-)
    * [Adding a todo task: `todo`](#adding-a-todo-task-todo)
    * [Adding a deadline task: `deadline`](#adding-a-deadline-task-deadline)
    * [Adding an event task: `event`](#adding-an-event-task-event)
    * [Listing all tasks: `list`](#listing-all-tasks-list)
    * [Marking task as done: `mark`](#marking-task-as-done-mark)
    * [Marking task as not done: `unmark`](#marking-task-as-not-done-unmark)
    * [Finding task by keyword: `find`](#finding-task-by-keyword-find)
    * [Deleting task: `delete`](#deleting-task-delete)
    * [Exiting the program: `bye`](#exiting-the-program-bye)
    * [Saving data](#saving-data)
* [FAQ](#faq)
* [Command summary](#command-summary-)

---

## Quick start
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `MsChatty.jar` from [here](https://github.com/j013n3/ip/releases/tag/A-Release).
3. Copy the file to the folder you want to use as the home folder for your chatbot.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar MsChatty.jar` command to run the chatbot.
5. Refer to the [Features](#features-) below for details of each command.

---

## Features 

### Adding a todo task: `todo`

Adds a todo task to the task list. A todo task contains the description of the task.

Format: `todo DESCRIPTION`

Examples: 

* todo Complete assignment 
* todo Buy movie tickets

Usage: 
```
todo Complete assignment
Got it. I've added this task:
[T][ ] Complete assignment
Now you have 1 task(s) in the list. 
```

### Adding a deadline task: `deadline`

Adds a deadline task to the task list with a deadline to be completed by. 

Format: `deadline DESCRIPTION /by DEADLINE`

Examples: 
* deadline CS2113T assignment /by 4 March 
* deadline Sign up for Pilates /by 10pm 

Usage: 
```
deadline CS2113T assignment /by 4 March
Got it. I've added this task:
[D][ ] CS2113T assignment (by: 4 March)
Now you have 2 task(s) in the list. 
```

### Adding an event task: `event`

Adds an event task to the task list with a start and an end time. 

Format: `event DESCRIPTION /from START_TIME /to END_TIME`

Examples:
* event Workshop /from 1 March 2pm /to 4pm 
* event Project meeting /from 10pm /to 12am

Usage:
```
event Workshop /from 1 March 2pm /to 4pm 
Got it. I've added this task:
[E][ ] Workshop (from: 1 March 2pm to: 4pm)
Now you have 3 task(s) in the list. 
```

### Listing all tasks: `list`

List all the tasks in the task list. 

Format: `list`

Usage: 
```
list
Here are the tasks in your list: 
1.[T][ ] Complete assignment
2.[D][ ] CS2113T assignment (by: 4 March)
3.[E][ ] Workshop (from: 1 March 2pm to: 4pm)
```
### Marking task as done: `mark`

Marks a task as done. 

Format: `mark TASK_NUMBER`

### Marking task as not done: `unmark`

Marks a task as undone.

Format: `unmark TASK_NUMBER`

### Finding task by keyword: `find`

Finds tasks with descriptions containing the given keyword.

Format: `find KEYWORD`

Usage: 
```
find assignment
Here are the tasks in your list:
1.[T][ ] Complete assignment
2.[D][ ] CS2113T assignment (by: 4 March)
```

### Deleting task: `delete`

Deletes a task from the task list. 

Format: `delete TASK_NUMBER`

Usage: 
```
delete 1
[T][ ] Complete assignment
Item above has been removed!
Now you have 2 task(s) in the list.
```

### Exiting the program: `bye`

Exits the chatbot. 

Format: `bye`

Usage: 
```
bye 
Tasks saved successfully.
Bye! Hope to see you again :)
```

### Saving data
The task list will be saved automatically right before the `bye` command executes. There is no need to save manually. 

---

## FAQ
**Q:** How do I transfer my data to another computer? 

**A:**: Install the chatbot on the other computer and overwrite the empty data file it creates with the file that 
contains the data. 

---

## Command summary 


| **Commands** | **Format, Examples**                                                                             |
|--------------|--------------------------------------------------------------------------------------------------|
| **todo**     | `todo DESCRIPTION` <br/> e.g., `todo Complete assignment`                                        |
| **deadline** | `deadline DESCRIPTION /by DEADLINE` <br/> e.g., `deadline CS2113T assignment /by 4 March`        |
| **event**    | `event DESCRIPTION /from START_TIME /to END_TIME` <br/> e.g., `event Workshop /from 2pm /to 4pm` |
| **list**     | `list`                                                                                           |
| **mark**     | `mark TASK_NUMBER` <br/> e.g., `mark 1`                                                          |
| **unmark**   | `unmark TASK_NUMBER` <br/> e.g., `unmark 1`                                                      |
| **find**     | `find KEYWORD` <br/> e.g., `find assignment`                                                     |
| **delete**   | `delete TASK_NUMBER` <br/> e.g., `delete 1`                                                      |
| **bye**      | `bye`                                                                                            |
