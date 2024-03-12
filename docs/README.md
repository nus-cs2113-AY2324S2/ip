# Asuka Task Manager

Asuka is a simple yet powerful command-line task manager application written in Java. It provides users with the ability to efficiently organise and track their tasks using a straightforward interface.

## Features

- **Task Management:** Add, mark as done, delete, and list tasks effortlessly.
- **Task Types:** Supports various types of tasks, including todo, deadline, and event tasks.
- **Search Functionality:** Find specific tasks quickly using keywords.
- **Persistence:** Save tasks to a file for future reference and load them when needed.
- **Easy-to-Use Interface:** Simple command-line interface for seamless task management.

## Getting Started

To run and improve Asuka on your system, ensure you have Java installed.

1. **Clone the Repository:** Clone this repository to your local machine using Git.
    ```bash
    git clone https://github.com/Luo-Z-Y/ip.git
    ```

2. **Compile the Source Code:** Navigate to the cloned directory and compile the `Asuka.java` file using `javac`.
    ```bash
    cd ./src/main/java
    javac Asuka.java
    ```

3. **Run the Application:** Execute the compiled Java file to start the Asuka Task Manager.
    ```bash
    java Asuka
    ```

Alternatively, you can download the jar file from the release page and **run it using the following command in the correct directory**:
```bash
java -jar asuka.jar
```

## Usage

Once Asuka is running, you can interact with it using the command-line interface. Here are the available commands and their usage:

- `todo [TASK_DESCRIPTION]`: Adds a todo task to the task list.
- `deadline [TASK_DESCRIPTION] /by [DEADLINE]`: Adds a task with a deadline to the task list.
- `event [TASK_DESCRIPTION] /from [START_DATE] /to [END_DATE]`: Adds an event task to the task list.
- `mark [TASK_NUMBER]`: Marks the task with the specified number as done.
- `unmark [TASK_NUMBER]`: Marks the task with the specified number as incomplete.
- `delete [TASK_NUMBER]`: Deletes the task with the specified number from the task list.
- `list`: Lists all tasks currently in the task list.
- `find [KEYWORD]`: Searches for tasks containing the specified keyword.
- `bye`: Exits the application, saving the current task list.

## Example Usage

Here are additional examples of how you can use Asuka to manage your tasks effectively:

1. **Adding Tasks:**
    - Add a shopping task:
      ```bash
      todo Buy groceries
      ```

    - Add a deadline task for submitting a report:
      ```bash
      deadline Submit quarterly report /by 2024-04-15
      ```

    - Add an event task for a friend's birthday party:
      ```bash
      event Friends birthday party /from 2024-05-20 /to 2024-05-21
      ```

2. **Viewing Tasks:**
    - List all tasks:
      ```bash
      list
      ```

    - View upcoming deadlines:
      ```bash
      find deadline
      ```

3. **Marking Tasks:**
    - Mark the first task as completed:
      ```bash
      mark 1
      ```

    - Mark an event task as completed:
      ```bash
      mark 3
      ```

4. **Searching for Tasks:**
    - Find tasks related to work:
      ```bash
      find work
      ```

    - Find all tasks related to shopping:
      ```bash
      find shopping
      ```

5. **Deleting Tasks:**
    - Delete a completed task:
      ```bash
      delete 1
      ```

    - Delete a specific deadline task:
      ```bash
      delete 2
      ```

6. **Exiting the Application:**
    - Save tasks and exit the application:
      ```bash
      bye
      ```

These examples demonstrate various ways to interact with Asuka for task management purposes. You can mix and match commands as needed to suit your workflow.
