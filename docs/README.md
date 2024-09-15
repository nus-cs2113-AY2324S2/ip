# Winter User Guide

Winter is a chatbot that helps the user keep track of tasks which can take the form ot todos, deadlines or events.

## Quick Start
1. Ensure you have Java 11 or later installed on your computer.
2. Download the latest release Winter.jar from [Latest release](https://github.com/Zhengwinter/ip/releases/tag/A-Release)
3. Copy the file to a folder where you wish to launch Winter
4. Open a command prompt from the folder or navigate to the folder with te JAR file using `cd`
5. Enter `java -jar Winter.jar` into the terminal to run the program

## Features 

### Feature-Add task

Allows user to add tasks of types Todo, Deadline or Event. These tasks have specific
formats that contains information about the task.

### Feature-Remove task

Allows user to delete a task from the task list by specifying the number 
corresponding to the task as seen from the task list.

### Feature-Exit

Allows user to exit the program.

### Feature-List

Allows user to see the task list and their order, after addition/removal of tasks (if any).


### Feature-Mark

Allows user to mark a certain task as complete.

### Feature-Unmark

Allows user to mark a certain task as incomplete.

### Feature-Find

Allows user to search for a certain keyword within the names of all the tasks currently present in the task list.

## Usage

### `todo` - Adds a todo task

Adds a todo task to the task list.

Example of usage: 

`todo homework`

Expected outcome:

Shows a confirmation message of the todo task that was added.

```
Awesome! I've added this task!
    [T][ ] homework
    Now, you have 1 tasks in your list.
```

### `Deadline` - Adds a deadline task

Adds a deadline task to the task list where it has a deadline time. The deadline must be specified in the form of yyyy-mm-dd HH:mm.

Example of usage:

`deadline CS2113 team project /by 2024-03-29 23:59`

Expected outcome:

Shows a confirmation message of the deadline task that was added.

```
Awesome! I've added this task!
    [D][ ] CS2113 team project (by: MAR 3 2024 23:59)
    Now, you have 2 tasks in your list.
```

### `Event` - Adds an event task

Adds a event task to the task list after specifying the starting and ending time, together with the event name.

Example of usage:

`event Basketball practice /from Mon 2pm /to 4pm`

Expected outcome:
Shows a confirmation message of the event task that was added.


```
Awesome! I've added this task!
    [E][ ] Basketball practice (from: Mon 2pm to: 4pm)
    Now, you have 3 tasks in your list.
```
### `List` - Displays the task list

Shows to the user the task list after tasks have been added / removed. The tasks are arranged in the order of addition and labeled accordingly.

Example of usage:

`list`

Expected outcome:

Displays the task list

```
    1. [T][ ] homework
    2. [D][ ] CS2113 team project (by: MAR 3 2024 23:59)
    3. [E][ ] Basketball practice (from: Mon 2pm to: 4pm)
```
### `Mark` - Marks a task as complete

Marks a task in the task list as complete after specifying the number corresponding to the task in the task list.

Example of usage:

`mark 1`

Expected outcome:

Shows a confirmation message that the specified task in the task list is marked as complete, as indicated by the cross in the box next to the task name.

```
    Woohoo! I've marked this task as done:
        [T][X] homework
```
### `Unmark` - Marks a task as incomplete

Marks a task in the task list as incomplete after specifying the number corresponding to the task in the task list.

Example of usage:

`unmark 1`

Expected outcome:

Shows a confirmation message that the specified task in the task list is marked as incomplete, as indicated by the cross in the box next to the task name.

```
    Alright! I've marked this task as incomplete:
        [T][ ] homework
```


### `delete` - Removes a task from the task list

Deletes a task from the task list by specifying the number corresponding to the task.

Example of usage:

`delete 2`

Expected outcome:

Shows a confirmation message that the specified task in the task list is deleted, by showing the details of the deleted task.

```
    No problemo, I've removed this task: 
        [D][ ] CS2113 team project (by: MAR 3 2024 23:59)
    Now, you have 2 tasks in your list.
```
### `find` - Search for a keyword

Looks for a specified keyword in the task names of the tasks in the task list.

Example of usage:

`find Basketball`

Expected outcome:

Shows a list containing all the matches with the keyword, if present.

```
Hola! These are the matching tasks in your list:
    1. [E][ ] Basketball practice (from: Mon 2pm to: 4pm)
```
Example of usage:

`find elephant`

Expected outcome:

Shows a message saying the task is not found.

```
Sorry! There were no task that matches your keyword, try another keyword?
```

### `bye` - Exit the program

Exits the program. Other strings like BYE or Bye are also accepted.

Example of usage:

`bye`

Expected outcome:

Shows a farewell message and terminates the program.

```

Farewell. Hope to see you again soon!
```

