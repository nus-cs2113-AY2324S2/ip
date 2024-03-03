# Sigma - Your own personal chatbot

This is a chatbot that can track your list of different tasks, as well as marking them when done. Given below are instructions on how to use it.

## Features

### 1. Task Management
- **Add Tasks:** Add a new task according to their type - todo, deadline or event
- **View Tasks:** List all tasks to get an overview of your workload.
- **Mark as Done or not Done:** Mark tasks as complete or not complete.
- **Delete Tasks:** Delete Tasks from the current list.
- **Find Tasks:** Find Tasks using a keyword.

### 2. Command-Line Interface
- **User-Friendly Commands:** Intuitive and easy-to-use commands for seamless interaction.

### 3. Time formatting
**For deadlines and events:** Formats the time as MM DD YYYY if input is in YYYY-MM-DD

## Getting Started

Follow these steps to get started.

1. **Compile the Code:**
   - Compile the Java source code using your preferred Java compiler.

2. **Run the JAR File:**
   - Execute the compiled JAR file to launch the chatbot.

3. **Interact with the Chatbot:**
   - Follow the on-screen instructions to add tasks and manage your to-do list.

## Example Usage

```plaintext
____________________________________________________________
Hello! I'm Sigma
What can I do for you?
____________________________________________________________

todo buy milk
____________________________________________________________
Okay, I've added:  buy milk
____________________________________________________________

deadline CS2113-exam /by 2024-03-28
____________________________________________________________
Okay, I've added:  CS2113-exam  (by: Mar 28 2024)
____________________________________________________________

event party /from 2024-04-01 /to 2024-04-02
____________________________________________________________
Okay, I've added:  party  (from: Apr 1 2024 to: Apr 2 2024)
____________________________________________________________

mark 1
Good job! I've marked task 1 as done
list
____________________________________________________________

Your current tasks
1. [T][X] buy milk
2. [D][ ] CS2113-exam  (by: Mar 28 2024)
3. [E][ ] party  (from: Apr 1 2024 to: Apr 2 2024)
____________________________________________________________
bye
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

Thank you for using Sigma!
