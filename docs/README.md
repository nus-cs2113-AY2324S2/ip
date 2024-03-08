# EDITH User Guide

## EDITH's Features 

### Create & add three different tasks types

With EDITH, you can keep track of your daily tasks.
You can categorise your tasks into "todo"s, "deadline"s & "event"s.

**Todo** - Tasks without any specific deadline.

Usage: `todo <description>`

Expected outcome: 

```
____________________________________________________________
todo buy flowers
Got it. I've added this task:
[T][ ] buy flowers
Now you have 1 tasks in the list.
____________________________________________________________
```

**Deadlines** - Task with a specific deadline.

Usage: `deadline <description> /by <date>`

Expected outcome:
```
____________________________________________________________
deadline CS2113 IP by 8 Mar 1159
Got it. I've added this task:
   [D][ ] CS2113 IP (by: 8 Mar 1159)
   Now you have 2 tasks in the list.
____________________________________________________________
```
**Events** - Tasks with a specific start and end time.

Usage: `event <description> /from <start> /to <end>`

Expected outcome:

```
____________________________________________________________
event Taylor Swift Eras Tour concert from 1700 to 2230
Got it. I've added this task:
   [E][ ] Taylor Swift Eras Tour concert (from: 1700 to: 2230)
   Now you have 3 tasks in the list.
____________________________________________________________
```


### Delete tasks

Delete a task from your task list.

Usage: `delete <task number>`

Expected outcome: 
```
list
 Here are the tasks in your list:
 1. [T][ ] buy flowers
 2. [D][ ] CS2113 IP (by: 8 Mar 1159)
 3. [E][ ] Taylor Swift Eras Tour concert (from: 1700 to: 2230)
 4. [T][ ] test delete fucntion
____________________________________________________________
delete 4
Noted. I've removed this task:
   [T][ ] test delete fucntion
   Now you have 3 tasks in the list.
____________________________________________________________
list
 Here are the tasks in your list:
 1. [T][ ] buy flowers
 2. [D][ ] CS2113 IP (by: 8 Mar 1159)
 3. [E][ ] Taylor Swift Eras Tour concert (from: 1700 to: 2230)
____________________________________________________________
```

### Mark tasks 

Mark a task as done in your task list.

Usage: `mark <task number>`

Expected outcome:
```
____________________________________________________________
mark 2
Nice! I've marked this task as done:
     [D][X] CS2113 IP (by: 8 Mar 1159)
____________________________________________________________
list
 Here are the tasks in your list:
 1. [T][ ] buy flowers
 2. [D][X] CS2113 IP (by: 8 Mar 1159)
 3. [E][ ] Taylor Swift Eras Tour concert (from: 1700 to: 2230)
____________________________________________________________
```
### Unmark tasks

Mark a task as not done in your task list.

Usage: `unmark <task number>`

Expected outcome:
```
____________________________________________________________
unmark 2
OK, I've marked this task as not done yet:
     [D][ ] CS2113 IP (by: 8 Mar 1159)
____________________________________________________________
list
 Here are the tasks in your list:
 1. [T][ ] buy flowers
 2. [D][ ] CS2113 IP (by: 8 Mar 1159)
 3. [E][ ] Taylor Swift Eras Tour concert (from: 1700 to: 2230)
____________________________________________________________
```

### Find tasks 

Find tasks in your task list containing a specific keyword.

Usage: `find <keyword>`

Expected outcome:
```
____________________________________________________________
find Swift
Here are the matching tasks in your list:
3. [E][ ] Taylor Swift Eras Tour concert (from: 1700 to: 2230)
____________________________________________________________
```
### List your tasks

List all tasks in your task list.

Usage: `list`

Expected outcome:
```
____________________________________________________________
list
 Here are the tasks in your list:
 1. [T][ ] buy flowers
 2. [D][ ] CS2113 IP (by: 8 Mar 1159)
 3. [E][ ] Taylor Swift Eras Tour concert (from: 1700 to: 2230)
____________________________________________________________

```
### Exit from EDITH 

Exit from the ChatBot.

Usage: `bye`

Expected outcome:
```
____________________________________________________________
bye
  Bye. Hope to see you again soon!
____________________________________________________________
```
