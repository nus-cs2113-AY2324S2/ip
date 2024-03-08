# MIKU CHATBOT
Miku is a chatbot who can help you keep track of all your upcoming tasks!


### Interface example
```
______________________
Here are your list items!
1. [T][X]  CS2113 homework
2. [E][ ]  CS2113 lecture (from: 2pm to: 4pm)
3. [D][X]  CS2113 iP (by: 3rd Jan) 
4. [T][X]  Cook dinner
5. [D][ ]  Find internship (by: 20th Jan)
______________________
```

## Usage

### `todo` - adds a new todo task to your list

Add a new task todo to your list. This task does not have any time constraints.

Format: `time [NAME]`

Example of usage:
`todo CS2113 homework`

Expected outcome:
```
Got it! I've added this task:
[T][ ]  CS2113 homework
Now you have 1 tasks in the list!
```
###
###

### `event` - adds a new event task to your list

Add a new task event to your list. The event has two time parameters of "/from" and "/to", 
which are when the event starts and ends.

Format: `event [NAME] /from [TIME] /to [TIME]`

Example of usage:
`event CS2113 lecture /from 2pm /to 4pm`

Expected outcome:
```
Got it! I've added this task:
[E][ ]  CS2113 lecture (from: 2pm to: 4pm)
Now you have 2 tasks in the list!
```
### 

### `deadline` - adds a new deadline task to your list

Add a new task deadline to your list. The deadline has the time parameter of "/by", which is when the deadline is due by.

Format: `deadline [NAME] /by [TIME]`

Example of usage:
`deadline CS2113 iP /by 3rd Jan`

Expected outcome:
```
Got it! I've added this task:
[D][ ]  CS2113 iP (by: 3rd Jan)
Now you have 3 tasks in the list!
```

### 

### `mark` - marks a task as done

Marks a task as complete in your list. The index is the index of the task you want to mark.
It must be positive and exists in the list.

Format: `mark [INDEX]`

Example of usage:
`mark 1`

Expected outcome:
```
Good job~! I've marked it as done
[X]  CS2113 homework
```

### 
### `unmark` - unmarks a task from its done status

Unmarks a task from being complete in your list. The index is the index of the task you want to unmark.
It must be positive and exists in the list.

Format: `unmark [INDEX]`

Example of usage:
`unmark 2`

Expected outcome:
```
Aww... I've marked it as undone.
[ ]  CS2113 lecture 
```

### 

### `list` - lists all the tasks you currently have

The list function will display all your current upcoming tasks as well as if you have completed them or not.

Format: `list`

Example of usage:
`list`

Expected outcome:
```
Here are your list items!
1. [T][X]  CS2113 homework
2. [E][ ]  CS2113 lecture (from: 2pm to: 4pm)
3. [D][X]  CS2113 iP (by: 3rd Jan)
```

### 
### `delete` - deletes a task

Deletes a task from your list. The index is the index of the task you want to delete.
It must be positive and exists in the list.

Format: `delete [INDEX]`

Example of usage:
`delete 2`

Expected outcome:
```
Okay, I'm deleting this task:
[ ]  CS2113 lecture
Now you have 2 items in your list!
```

### 

### `find` - finds a keyword

Finds all tasks in your list that fit a provided keyword.

Format: `find [KEYWORD]`

Example of usage:
`find homework`

Expected outcome:
```
Here are the matching tasks in your list:
1. [T][ ]  CS2113 homework
```

