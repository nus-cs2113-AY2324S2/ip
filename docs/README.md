# Chatbot User Guide

## Features 

### Showing a list of commands: `help`
Displays a list of commands and their corresponding format

Format: `help` 

**Example:**

Input: `help`

Output:
```
Here are the commands you can use:
    todo <description> - Add a todo task
    deadline <description> /by <date> - Add a deadline task
    event <description> /from <date> /to <date> - Add an event task
    list - List all the tasks
    done <task number> - Mark a task as done
    delete <task number> - Delete a task
    find <keyword> - Find tasks with the keyword
    bye - Exit the program
```
### Adding a todo task: `todo`

Add a todo task

Format: `todo DESCRIPTION`

**Example:**

Input: `todo run 5 km`

Output:
```
----------------------------------------------------
    Awesome! Something to do without deadline hehe
      [T][ ] run 5 km
    You better not procrastinate... or maybe you should
----------------------------------------------------
```

### Adding a deadline task: `deadline`

add a deadline task with end time

Format: `deadline DESCRIPTION /by DEADLINE`

**Example:**

Input: `deadline do CS2113 assignment /by 8 March`

Output:
```
----------------------------------------------------
    Oh wow... a deadline, how exciting :)
      [D][ ] do CS2113 assignment (by: 8 March)
    A deadline a day keeps the sanity away.
----------------------------------------------------
```

### Adding an event task: `event`

add an event task with start and end time

Format: `event DESCRIPTION /from START /to END`

**Example:**

Input: `event vacation to Japan /from 01 Dec /to 20 Dec`

Output:
```
----------------------------------------------------
    Event... yeay.
      [E][ ] vacation to Japan (from: 01 Dec to: 20 Dec)
    Can it BE any more fun?
----------------------------------------------------
```

### Displaying the lists in the task list: `list`

list all the tasks (todo, deadline and event) stored in the task list 

Format: `list`

**Example:**

Input: `list`

Output:
```
----------------------------------------------------
Here are the tasks in your list:
    1.[D][ ] do CS2113 assignment (by: 8 March)
    2.[E][ ] vacation to Japan (from: 01 Dec to: 20 Dec)
----------------------------------------------------
```

### Marking a task as done: `mark`

mark a task as completed

Format: `mark TASK_INDEX`

**Example:**

Input: `mark 1`

Output:
```
----------------------------------------------------
    Nice! I've marked this task as done:
    [D][X] do CS2113 assignment (by: 8 March)
----------------------------------------------------
```

### Marking a task as not done: `unmark`

mark a task as not completed

Format: `unmark TASK_INDEX`

**Example:**

Input: `unmark 1`

Output:
```
----------------------------------------------------
    Ok, I've marked this task as not done yet:
    [D][ ] do CS2113 assignment (by: 8 March)
----------------------------------------------------
```

### Deleting a task: `delete`

remove the specified task from the task list

Format: `delete TASK_INDEX`

**Example:**

Input: `delete 1`

Output:
```
----------------------------------------------------
    YES, less things to do! I've removed this task:
     [D][ ] do CS2113 assignment (by: 8 March)
    Now you have 1 tasks in the list.
----------------------------------------------------
```

### Finding tasks with keyword: `find`

find the tasks that contain the specified keyword

Format: `find KEYWORD`

**Example:**

Input:`find buy`

Output:
```
----------------------------------------------------
I'm hopeless and awkward and desperate for love!
I mean... here are the matching tasks in your list.
    1.[T][ ] buy clothes
    2.[T][ ] buy new clothes
You have 2 matching tasks in the list.
----------------------------------------------------
```
### Exiting the programme: `bye`

exit from the programme

Format: `bye`

**Example:**

Input:`bye`

Output:
```
----------------------------------------------------
Bye. Meeting you was okay..
----------------------------------------------------
```

## Command Summary

| Action             | Format, Examples                                                                           |
|--------------------|--------------------------------------------------------------------------------------------|
| Add todo task      | `todo DESCRIPTION`<br/>e.g. `todo maths assignment`                                        |
| Add event task     | `event DESCRIPTION /from START /to END`<br/>e.g. `event graduation /from 10 am /to 5 pm`   |
| Add deadline task  | `deadline DESCRIPTION /by DEADLINE`<br/>e.g. `deadline essay submission /by 15th of March` |
| Show task lists    | `list`                                                                                     |
| Mark a task        | `mark TASK_INDEX`<br/>e.g. `mark 2`                                                        |
| Unmark a task      | `unmark TASK_INDEX`<br/>e.g. `unmark 7`                                                    |
| Delete a task      | `delete TASK_INDEX`<br/>e.g. `delete 5`                                                    |
| Find a task        | `find KEYWORD`<br/>e.g. `find buy`                                                         |
| Exit the programme | `bye`                                                                                      |

