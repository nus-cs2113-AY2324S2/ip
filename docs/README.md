# TaskManager

TaskManager is a simple yet powerful Java application designed to help users manage their tasks efficiently. It allows users to add, delete, mark tasks as done or not done, and find tasks by searching for keywords.

## Features

- **Add Tasks**: Users can add three types of tasks - todos, deadlines, and events.
- **Delete Tasks**: Users can delete tasks they no longer need.
- **Mark Tasks**: Users can mark tasks as done or not done.
- **Find Tasks**: Users can search for tasks by keywords.
- **List Tasks**: Users can view all their tasks at any time.

## Getting Started
## Adding a task
### Type of task
- Todo
- Deadline
- Event

### Prompt
- Todo: todo {task description}

  _e.g. adding task todo `read book`_
    ```
    todo read book
    ```

- Deadline: deadline {task description} /by {date}

  _e.g. adding task deadline `return book` before `2021-09-17`_
    ```
    deadline return book /by 2021-09-17
    ```

- Event: event {task description} from {date} to {date}

  _e.g. adding task event `project meeting` from `2021-09-17` to `2021-09-18`_
    ```
    event project meeting from 2021-09-17 to 2021-09-18
    ```
### Expected output for all adding tasks
    Got it. I've added this task:
        [D][ ] return book (by: 2021-09-17)
> Note: the following e.g. for each command will be using the output of the above prompt

## Listing all tasks
### Prompt
- list

  _e.g. listing all tasks_
    ```
    list
    ```
### Expected output

    Here are the tasks in your list:
    1. [T][ ] read book
    2. [D][ ] return book (by: Sep 17 2021)
    3. [E][ ] project meeting (from: Sep 17 2021 to: Sep 18 2021)


## Deleting a task
### prompt
- delete {task number}

  _e.g. deleting 3_
    ```
    delete 3
    ```
### Expected output

    Noted. I've removed this task: {task number}
    this can be verified by listing all tasks:

    list
    Here are the tasks in your list:
    1. [T][ ] read book
    2. [D][ ] return book (by: Sep 17 2021)


## Marking a task as done
### Prompt
- mark {task name}

  _e.g. marking task 1 as done_
    ```
    mark read book
    ```
### Expected output

    Nice! I've marked this task as done: {task name}
    this can be verified by listing or finding tasks:
  ```
  list
  Here are the tasks in your list:
  1. [T][X] read book
  2. [D][ ] return book (by: Sep 17 2021)
  3. [E][ ] project meeting (from: Sep 17 2021 to: Sep 18 2021)
  ```

- unmark {task name}

  _e.g. unmarking read book_
  ```
  unmark read book
  ```
### Expected output
  ```
  unmarked 
  I've unmarked this task as done: {task name}
  ```
this can be verified by listing all tasks:
  ```
  list
  Here are the tasks in your list:
  1. [T][ ] read book
  2. [D][ ] return book (by: Sep 17 2021)
  3. [E][ ] project meeting (from: Sep 17 2021 to: Sep 18 2021)
  ```

## Finding a task
### prompt
- find {keyword}

  e.g. finding tasks related to `book`
    ```
    find book
    ```
### Expected output
    Here are the matching tasks in your list:
    1. [T][ ] read book
    2. [D][ ] return book (by: Sep 17 2021)

## Exit Program
### prompt
- bye

### Expected output
  ```
  Bye. Hope to see you again soon!
  ```
### Prerequisites

- Java 11 or above.