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

## Example Usage

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

Other instructions can be found using the `help` command.

Have fun with the chatbot!
