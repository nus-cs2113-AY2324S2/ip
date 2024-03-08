# Mavis - Your Personal Task Management Assistant!
# User Guide

### Greeting Message
```
Greetings.
                           z$$$$P
                           d$$$$"
                         .$$$$$"
                         z$$$$$"
                        z$$$$P
                      d$$$$$$$$$$"
                     *******$$$"
                          .$$$"
                          .$$"
                         4$P"
                        z$"
                        zP
                       z"
                      / 
                     ^ 
      ___           ___                                    ___     
     /__/\         /  /\          ___        ___          /  /\    
    |  |::\       /  /::\        /__/\      /  /\        /  /:/_   
    |  |:|:\     /  /:/\:\       \  \:\    /  /:/       /  /:/ /\  
  __|__|:|\:\   /  /:/~/::\       \  \:\  /__/::\      /  /:/ /::\ 
 /__/::::| \:\ /__/:/ /:/\:\  ___  \__\:\ \__\/\:\__  /__/:/ /:/\:\
 \  \:\~~\__\/ \  \:\/:/__\/ /__/\ |  |:|    \  \:\/\ \  \:\/:/~/:/
  \  \:\        \  \::/      \  \:\|  |:|     \__\::/  \  \::/ /:/ 
   \  \:\        \  \:\       \  \:\__|:|     /__/:/    \__\/ /:/  
    \  \:\        \  \:\       \__\::::/      \__\/       /__/:/   
     \__\/         \__\/           ~~~~                   \__\/    
____________________________________________________________
Greetings. I am Mavis.
What would you command of me?

┌───────────────────────────────────────────────┐
│ Please enter a recognized command from the    │
│ following list to maintain temporal coherence:│
│                                               │
│  1. todo <task name>                          │
│  2. deadline <task name> /by:<date/time>      │
│  3. event <task name> /from:<date> /to:<date> │
│  4. mark <task number>                        │
│  5. unmark <task number>                      │
│  6. list                                      │
│  7. find <description keywords>               │
│  8. delete <task number>                      │
│  9. bye                                       │
└───────────────────────────────────────────────┘

```

## Features

### Adding Tasks

Mavis allows you to add various types of tasks:

*   **Todo**: Simple tasks without specific deadlines.
*   **Deadline**: Tasks with specific end dates.
*   **Event**: Tasks that span a specific period.

### Marking Tasks

*   You can mark tasks as done or undone to track your progress effectively.

### Deleting Tasks

*   Easily delete tasks from your list when they are no longer relevant.

### Finding Tasks

*   Quickly search for tasks containing specific keywords to locate what you need.

### List Tasks

*   View all your tasks at a glance to stay organized and focused.

### Exiting Application

*   Close the application while ensuring any changes are saved.


## Usage

### Adding a Todo
```bash
todo <task_description>
```
- **Example**: `todo Run an errand`
- **Expected Outcome**: Adds a todo task with the description "Run an errand" to the task list.

#### Example Output:
```
____________________________________________________________
A new task is etched upon the sands of time:
[T][ ]Run an errand
Your roster now bears 5 endeavors.
____________________________________________________________
```

### Adding a Deadline
```bash
deadline <description> /by: <deadline>
```
- **Example**: `deadline submit iP /by: 2024-03-08 23:59`
- **Expected Outcome**: Adds a deadline task with the description "submit iP" and the deadline "2024-03-08 23:59" to the task list.

#### Example Output:
```
____________________________________________________________
A new task is etched upon the sands of time:
[D][ ]submit iP (by: 2024-03-08 23:59)
Your roster now bears 6 endeavors.
____________________________________________________________
```

### Adding an Event
```bash
event <description> /from <start_date> /to <end_date>
```
- **Example**: `event Taylor Swift Concert /from: 2024-04-15 1pm /to: 8pm
- **Expected Outcome**: Adds an event task with the description "Project meeting", start date "2024-03-05", and end date "2024-03-07" to the task list.

#### Example Output:
```
____________________________________________________________
A new task is etched upon the sands of time:
[E][ ]Taylor Swift Concert (from: 2024-04-15 1pm to: 8pm)
Your roster now bears 7 endeavors.
____________________________________________________________
```

### Marking a Task
```bash
mark <task_number>
```
- **Example**: `mark 2`
- **Expected Outcome**: Marks the task with index 2 as done.

#### Example Output:
```
Your command has been executed.Behold the task, now marked as completed:
[T][X] 2. hello
```

### Unmarking a Task
```bash
unmark <task_number>
```
- **Example**: `unmark 3`
- **Expected Outcome**: Marks the task with index 3 as undone.

#### Example Output:
```
Reversing the flow of space and time to undo the task...
Here is the task you just marked as not completed:
[D][ ] 3. submit iP (by:tdy 2359)

```

### Deleting a Task
```bash
delete <task_number>
```
- **Example**: `delete 1`
- **Expected Outcome**: Deletes the task with index 1 from the task list.

#### Example Output:
```
Time erodes all, and hence this task has been erased too:
[D][X] 1. finish studying for 2023 (by:Saturday)
Your roster now bears 6 endeavors.

```

### Finding Tasks
```bash
find <keyword>
```
- **Example**: `find submit`
- **Expected Outcome**: Displays a list of tasks containing the keyword "submit".

#### Example Output:
```
____________________________________________________________
Searching the annals of time for tasks matching your query...
[D][ ] 2. submit iP (by:tdy 2359)
[D][ ] 5. submit iP (by: 2024-03-08 23:59)
[D][ ] 7. submit journal entry for GESS1029 (by:2359)
____________________________________________________________
```


### Displaying Tasks
```bash
list
```
- **Example**: `list`
- **Expected Outcome**: Displays a list of tasks along with their completion status.

#### Example Output:
```
____________________________________________________________
Herein lies the catalog of your labors: 
[T][X] 1. hello
[D][ ] 2. submit iP (by:tdy 2359)
[T][ ] 3. AAKASH
[T][ ] 4. Run an errand
[D][ ] 5. submit iP (by: 2024-03-08 23:59)
[E][ ] 6. Taylor Swift Concert (from: 2024-04-15 1pm to: 8pm)
[D][ ] 7. submit journal entry for GESS1029 (by:2359)
____________________________________________________________
```

### Exiting Application
```bash
bye
```
- **Example**: `bye`
- **Expected Outcome**: Displays a farewell message indicating the application will be closed.

#### Example Output:
```
____________________________________________________________
Time's tide ebbs, and so must I. Farewell, traveler.
____________________________________________________________

```