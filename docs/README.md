# Yuki - your personal chatbot and evil twin.

This is a chatbot that can track your list of tasks, and prompt you to complete them. Given below are instructions on how to use it.

## Features

### 1. Task Management
- **Add Tasks:** Add new tasks through the command line by providing a task description.
- **View Tasks:** List all pending tasks to get an overview of your workload.
- **Mark as Done or not Done:** Mark tasks as complete or not complete.
- **Delete Tasks:** Delete Tasks from current list.
- **Find Tasks:** Find Tasks using a keyword.

### 2. Command-Line Interface
- **User-Friendly Commands:** Intuitive and easy-to-use commands for seamless interaction.

## Getting Started

Follow these steps to get started with Yuki.

1. **Download the latest JAR File:**
    - Download the latest release from https://github.com/yuki-zmstr/ip

2. **Run the JAR File:**
    - Execute the JAR file to launch the chatbot.

3. **Interact with the Chatbot:**
    - Follow the on-screen instructions to add tasks and manage your to-do list.

## Commands

### 1. Viewing help : `help`
Shows instructions to use the chatbot

Format: `help`

### 2. Adding a todo : `todo`
Adds todo with a description.

Format: `todo read book`

Example:

```plaintext
> todo clean house
[Command entered: todo clean house]
---------------------------------------------
new todo for you:
[ ][T] clean house
Now you have 1 tasks in the list.
---------------------------------------------
```

### 3. Adding a deadline : `deadline`
Adds a deadline with a to-complete date.

Format: `deadline wash clothes /by Sunday`

Example:
```plaintext
> deadline homework /by 7pm
[Command entered: deadline homework /by 7pm]
---------------------------------------------
new todo for you:
[ ][D] homework  (by:7pm)
Now you have 2 tasks in the list.
---------------------------------------------
```

### 4. Adding an event : `event`
Adds an event with a start date and en date.

Format: `event birthday party /from 1 Jan /to 4 Jan`

Example:
```plaintext
> event trip to US /from 1 Jan /to 8 Jan
[Command entered: event trip to US /from 1 Jan /to 8 Jan]
---------------------------------------------
new todo for you:
[ ][E] trip to US  (from: 1 Jan  to: 8 Jan)
Now you have 3 tasks in the list.
---------------------------------------------
```

### 5. Marking a task as done : `mark`
Marks a task of a given index as done.

Format: `mark 1`

Example:
```plaintext
> mark 2
[Command entered: mark 2]
---------------------------------------------
Good job.
[X][D] homework  (by:7pm)
---------------------------------------------
```

### 6. Marking a task as not done : `unmark`
Marks a task of a given index as not done.

Format: `unmark 1`

### 7. Deleting a task : `delete`
Deleting a task of a given index.

Format: `delete 1`

### 8. Finding tasks : `find`
Finds tasks that contain a given keyword.

Format: `find homework`

### 9. List tasks : `list`
Lists all tasks.

Format: `list`

### 10. Exit program : `exit`
Exits program.

Format: `exit`

## Example usage
```plaintext
$ java -jar ip.jar

---------------------------------------------
I am Yuki, your personal chat bot and your evil twin.
Time to get grinding.
---------------------------------------------
Enter command: 

> todo clean house
[Command entered: todo clean house]
---------------------------------------------
new todo for you:
[ ][T] clean house
Now you have 1 tasks in the list.
---------------------------------------------
Enter command: 

> deadline homework /by 7pm
[Command entered: deadline homework /by 7pm]
---------------------------------------------
new todo for you:
[ ][D] homework  (by:7pm)
Now you have 2 tasks in the list.
---------------------------------------------
Enter command: 

> mark 2
[Command entered: mark 2]
---------------------------------------------
Good job.
[X][D] homework  (by:7pm)
---------------------------------------------
Enter command: 

> list
[Command entered: list]
---------------------------------------------
Wake up your idea and do these tasks:
1.[ ] [T] clean house
2.[X] [D] homework  (by:7pm)
Now you have 2 tasks in the list.
---------------------------------------------
Enter command: 

> exit
[Command entered: exit]
Found data file.
Successfully written into data file.
---------------------------------------------
Breaks are only for the weak.
---------------------------------------------
```

More instructions can also be found using the `help` command.

Have fun with the chatbot!
