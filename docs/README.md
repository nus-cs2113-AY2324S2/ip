# Serf, your very own medieval servant User Guide
Serf is a medieval styled chat bot. Users are able to store and retrieve tasks of todo, deadline and event format with Serf.

## Feature list and format
Here is a list of command in their respective format you can use with Serf

1) Todo: 'todo (Task)'
2) Deadline: 'deadline (Task) /by (due date)'
3) Event: 'event (Task) /from (start date) /to (end date)'
4) List: 'list'
5) Delete: 'delete (task)'
6) Mark: 'mark (task position in task list)'
7) Unmark: 'unmark (task position in task list)'
8) Find: 'find (task)'
9) Bye: 'bye'
10) Load from save file: executed automatically at the start of the program
11) Save to save file: executed automatically at the end of the program

Dont worry if you get your commands wrong Serf will remind you of the appropriate formats when you make a typo

### Feature-Todo

Commands Serf to take down a Todo task in the task list.
Stores only the task description when first added to the task list
A Todo command comes in the format 'todo (Task)'

Example: todo CS2113

### Feature-Deadline

Commands Serf to take down a Deadline task in the task list.
Stores only the task description and due date when first added to the task list
A Deadline command comes in the format 'deadline (Task) /by (due date)'

Example: deadline CS2113 /by 2359hrs

### Feature-Event

Commands Serf to take down an Event task in the task list.
Stores only the task description, start date and end date when first added to the tasklist
A Event command comes in the format 'deadline (Task) /by (due date)'

Example: event CS2113 /from 6pm /to 8pm

### Feature-List

Commands Serf to show all the tasks currently in the task list, their position in the task list, task type, task status, task description, task start date and task end date (if applicable).
The List command comes in the format 'list'

Example: list

### Feature-Delete

Commands Serf to remove a task from the task list
The Delete command comes in the format 'delete (task position in task list)'

Example: delete 11

### Feature-Mark

Commands Serf to mark a task as complete
The Mark command comes in the format 'mark (task position in task list)'

Example: mark 11

### Feature-Unmark

Commands Serf to mark a task as incomplete
The Unmark command comes in the format 'unmark (task position in task list)'

Example: unmark 11

### Feature-Find

Commands Serf to find a task from the task list based on a keyword
The Find command comes in the format 'find (keyword)'

Example: find CS2113

### Feature-Bye

Exits the program
The Bye command comes in the format 'bye'

Example: bye

### Sample program and output
Here is a run through of the commands and their expected output when using the program. Initially the program contains 3 tasks

    ____________________________________________________________
     Good day my lord! I'm Serf
     How shall I serve thee?
    ____________________________________________________________

    ____________________________________________________________
     Got it sire. I've added this task:
       [T][ ] sleep
     Now you have 1 tasks in the list.
    ____________________________________________________________

    ____________________________________________________________
     Got it sire. I've added this task:
       [E][ ] sleep (from: 10pm to: 6pm)
     Now you have 2 tasks in the list.
    ____________________________________________________________

    ____________________________________________________________
     Got it sire. I've added this task:
       [D][ ] wake up (by: 8am)
     Now you have 3 tasks in the list.
    ____________________________________________________________

todo CS2113 week 7
    ____________________________________________________________
     Got it sire. I've added this task:
       [T][ ] CS2113 week 7
     Now you have 4 tasks in the list.
    ____________________________________________________________

deadline CS2113 assignment /by 2359hrs
    ____________________________________________________________
     Got it sire. I've added this task:
       [D][ ] CS2113 assignment (by: 2359hrs)
     Now you have 5 tasks in the list.
    ____________________________________________________________

event CS2113 tutorial /from 12pm /to 1pm
    ____________________________________________________________
     Got it sire. I've added this task:
       [E][ ] CS2113 tutorial (from: 12pm to: 1pm)
     Now you have 6 tasks in the list.
    ____________________________________________________________

list
    ____________________________________________________________
     My lord, here are the tasks as recorded in thy list:
     1.[T][ ] sleep
     2.[E][ ] sleep (from: 10pm to: 6pm)
     3.[D][ ] wake up (by: 8am)
     4.[T][ ] CS2113 week 7
     5.[D][ ] CS2113 assignment (by: 2359hrs)
     6.[E][ ] CS2113 tutorial (from: 12pm to: 1pm)
    ____________________________________________________________

delete 1
    ____________________________________________________________
     Noted sire. I've removed this task:
       [T][ ] sleep
     Now you have 5 tasks in the list.
    ____________________________________________________________

mark 3
    ____________________________________________________________
     Acknowledged sire, I've marked this task as complete:
       [T][X] CS2113 week 7
    ____________________________________________________________

mark 4
    ____________________________________________________________
     Acknowledged sire, I've marked this task as complete:
       [D][X] CS2113 assignment
    ____________________________________________________________

list
    ____________________________________________________________
     My lord, here are the tasks as recorded in thy list:
     1.[E][ ] sleep (from: 10pm to: 6pm)
     2.[D][ ] wake up (by: 8am)
     3.[T][X] CS2113 week 7
     4.[D][X] CS2113 assignment (by: 2359hrs)
     5.[E][ ] CS2113 tutorial (from: 12pm to: 1pm)
    ____________________________________________________________

unmark 4
    ____________________________________________________________
     Acknowledged sire, I've marked this task as incomplete:
       [D][ ] CS2113 assignment
    ____________________________________________________________

list
    ____________________________________________________________
     My lord, here are the tasks as recorded in thy list:
     1.[E][ ] sleep (from: 10pm to: 6pm)
     2.[D][ ] wake up (by: 8am)
     3.[T][X] CS2113 week 7
     4.[D][ ] CS2113 assignment (by: 2359hrs)
     5.[E][ ] CS2113 tutorial (from: 12pm to: 1pm)
    ____________________________________________________________

find CS2113
    ____________________________________________________________
     Sire here are the task I have found as per thy request
     1.[T][X] CS2113 week 7
     2.[D][ ] CS2113 assignment (by: 2359hrs)
     3.[E][ ] CS2113 tutorial (from: 12pm to: 1pm)
    ____________________________________________________________

bye
    ____________________________________________________________
     Farewell, my lord.
    ____________________________________________________________


