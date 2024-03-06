# Byte User Guide
Byte is a user-friendly task management app designed to help you organize and manage your tasks efficiently in your hectic life!
## Features
Byte can track several types of tasks, such as:
1. ToDos
2. Deadlines
3. Events
   
You may also keep track of your progress through the utility functions:

1. List: to see all the tasks that you have
2. Mark: to mark a task as complete
3. Unmark: to mark a task as incomplete
4. Find: to search for a specific task through a keyword
5. Delete: to remove tasks from your list
6. Save: export your data into a file to sync your progress across multiple instances and sessions!

## Usage
Running Byte
- You are required to install Java 11 onto your computer
- Download the [latest release](https://github.com/V4Vern/ip/releases) from the releases page
- Run the program in your preferred terminal using the command: java -jar Byte.jar
- You should be greeted with a prompt on the first start-up asking for your name

### ToDos
ToDos are simple tasks that you wish to complete, without a deadline or a time frame to meet.
```
todo <task name [string]>
```

Example of usage: 
```
todo borrow book
```

Expected outcome:
```
Got it. I've added this task:
 [T][ ] borrow book
```
### Deadlines
Deadlines are tasks that have a deadline to be met.
```
deadline <task name [string]> /by <datetime [LocalDate]>
```

Example of usage: 
```
deadline return book /by 2024-02-03
```

Expected outcome:
```
Got it. I've added this task:
 [D][ ] return book  (by: Feb 3 2024)
```
### Events
Events are tasks that have a start and end datetime.
```
event <task name [string]> /from <datetime [string]> /to <datetime [string]>
```

Example of usage: 
```
event project meeting /from Mon 2pm /to 4pm
```

Expected outcome:
```
Got it. I've added this task:
 [E][ ] project meeting (from: Mon 2pm to: 4pm)
```
### Listing Tasks
You may list existing tasks by using the `list` command.

Example of usage: 
```
list
```

Expected outcome:
```
Here are the tasks in your list:
1. [T][ ] borrow book
2. [D][ ] return book  (by: Feb 3 2024)
3. [E][ ] project meeting (from: Mon 2pm to: 4pm)
```
### Marking Tasks
You may mark tasks as complete by using either the `mark`.
```
mark <item index on list [number]> 
```

Example of usage: 
```
mark 2
```

Expected outcome:
```
Nice! I've marked this task as done:
[D][X] return book  (by: Feb 3 2024)
```
### Unmarking Tasks
You may unmark tasks as incomplete by using either the `unmark`.
```
unmark <item index on list [number]> 
```

Example of usage: 
```
unmark 2
```

Expected outcome:
```
OK, I've marked this task as not done yet:
[D][ ] return book  (by: Feb 3 2024)
```
### Finding Tasks
You may find tasks using keywords through the `find` command. 
```
find <keyword [string]>
```

Example of usage: 
```
find book
```

Expected outcome:
```
Here are the matching tasks in your list:
1. [T][ ] borrow book
2. [D][ ] return book  (by: Feb 3 2024)
```
### Saving Progress
The task will be automatically saved whenever the task list changes and when you exit the program. The data will be loaded from `/data/byte.txt` when the chatbot starts up. If the directory or file is not created, it will automatically be created for you.

```
bye
```

Example of usage: 
```
bye 
Run the chatbot again
```

Expected outcome:
```
Here are the tasks in your list:
1. [T][ ] borrow book
2. [D][ ] return book  (by: Feb 3 2024)
3. [E][ ] project meeting (from: Mon 2pm to: 4pm)
____________________________________________________________
Hello! I'm Byte, your friendly chat assistant!
What can I do for you?
```
### Exit the program
You may exit the program using keywords through the `bye` command. 
Example of usage: 
```
bye
```

Expected outcome:
```
Bye. Hope to see you again soon!
```
