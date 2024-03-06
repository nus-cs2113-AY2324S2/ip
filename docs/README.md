# Steffy, the chatbot
````
---------------------------------------------------
 _______    ___           _____   _____ ___    ___
|   ____\___\  \___    __/   __| /   __|\  \__/  /
\   \   \___  ____\__|__    ___|    ___|\_   __/
\   \     |  | /  _  \|   |    |  |     /  /
__\   \    |  ||    __/|   |    |  |  __/  /
/_______|   |__| \_____||___|    |__| |____/

---------------------------------------------------
````

# User Guide

## Features 

### Add tasks

- Todo task
  - eg. `Todo {name of task}`
- Deadline task
  - eg. `Deadline {name of task} /by {deadline period}`
- Event task
  - eg. `Event {name of task} /from {start of event} /to {end of event}`

Expected outcome:

```
Todo Chores
--------------------------------------
Got it! I've added this task:
[T] [ ] Todo Chores
--------------------------------------
Deadline CS2113 ip /by Friday 2359H
--------------------------------------
Got it! I've added this task:
[D] [ ] Deadline CS2113 ip (by: Friday 2359H)
--------------------------------------
Event Dinner and Dance (Raffles Hall) /from Sat 7pm /to 11pm
--------------------------------------
Got it! I've added this task:
[E] [ ] Event Dinner and Dance (Raffles Hall) (from: Sat 7pm to: 11pm)
--------------------------------------
```

### List tasks

- To see full task list, `list` command
- To search for specified tasks, `find {keyword}` command

Expected outcome:

```
list
--------------------------------------
Here are the tasks in your lists:
1.[T] [ ] Todo Chores
2.[D] [ ] Deadline CS2113 ip (by: Friday 2359H)
3.[E] [ ] Event Dinner and Dance (Raffles Hall) (from: Sat 7pm to: 11pm)
--------------------------------------
find Chores
--------------------------------------
Here are the matching tasks in your lists:
1.[T] [ ] Todo Chores
--------------------------------------
```

### Mark and unmark tasks

- To mark a task as completed, `mark {index of task}`
- To unmark a task, `unmark {index of task]`

Expected outcome:

```
mark 1
--------------------------------------
Nice! I've marked this task as done:
[X] Todo Chores
--------------------------------------
unmark 1
--------------------------------------
OK, I've marked this task as not done yet:
[ ] Todo Chores
--------------------------------------
```

### Delete

- Delete a task, `delete {index of task}`

Expected outcome:

```
delete 1
--------------------------------------
Noted, I have removed this task:
[T] [ ] Todo Chores
Now you have 2 tasks left
--------------------------------------
list
--------------------------------------
Here are the tasks in your lists:
1.[D] [ ] Deadline CS2113 ip (by: Friday 2359H)
2.[E] [ ] Event Dinner and Dance (Raffles Hall) (from: Sat 7pm to: 11pm)
--------------------------------------
```

### Exit

- To exit program, reply `bye` to Steffy.

Expected outcome:

```
bye
--------------------------------------
Bye. Hope to see you again soon!
--------------------------------------

Process finished with exit code 0
```
