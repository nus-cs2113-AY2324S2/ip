# Task Manager Alpaca - User Guide
This is a user manual for Task Manager Alpaca, a Command Line Interface desktop application.

## Table of Contents
- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Features](#features)
  - [Adding a task](#adding-a-task)
  - [Listing all tasks](#listing-all-tasks)
  - [Marking a task as done](#marking-a-task-as-done)
  - [Deleting a task](#deleting-a-task)
  - [Finding a task by keyword](#finding-a-task-by-keyword)
  - [Exiting the program](#exiting-the-program)
  - [Viewing help](#viewing-help)
- [Appendix](#Date-and-Time-Format)

## Introduction
Welcome to Task Manager Alpaca, your personal CLI-based task organizer! Designed with simplicity and efficiency in mind, Alpaca helps you keep track of todos, deadlines, and events, ensuring you stay on top of your tasks without getting overwhelmed. Whether you're a student, a professional, or anyone in between looking to streamline their task management, Alpaca is here to assist!


## Quick Start
1. Ensure Java 11 or later is installed on your computer.
2. Download the `alpaca.jar` file to a convenient location.
3. Open a terminal or command prompt.
4. Navigate to the folder containing `alpaca.jar`.
5. Run `java -jar alpaca.jar` to start the application. You should be greeted by Alpaca's welcome message.

```
 Hello from
           _      _____        _____          
     /\   | |    |  __ \ /\   / ____|   /\    
    /  \  | |    | |__) /  \ | |       /  \   
   / /\ \ | |    |  ___/ /\ \| |      / /\ \  
  / ____ \| |____| |  / ____ \ |____ / ____ \ 
 /_/    \_\______|_| /_/    \_\_____/_/    \_\
   
```


## Features

Task Manager Alpaca offers a variety of features to help you manage your tasks effectively:

### Adding a Task
Create todos, deadlines, and events with ease, specifying details such as descriptions and due dates.

#### Usage
- `todo <description>`: Adds a todo task.
- `deadline <description> /by <date>`: Adds a task with a specific deadline.
- `event <description> /from <start date> /to <end date>`: Schedules an event within a specified time frame.

### Listing All Tasks
View all your current tasks in one place, giving you a clear overview of what's on your plate.

#### Usage
- `list`: Displays all tasks.

(Continue with the rest of the features in a similar format)

#### Example of Usage and Expected Outcome
```
____________________________________________________________

todo read book
____________________________________________________________

Got it. I've added this task:
[T][ ] read book
Now you have 3 tasks in the list.
____________________________________________________________

deadline charge phone /by 2025-03-30 18:00
____________________________________________________________

Got it. I've added this task:
[D][ ] charge phone (by: 30 Mar 2025 18:00)
Now you have 4 tasks in the list.
____________________________________________________________

event project meeting /from 2025-02-10 12:00 /to 2025-03-10 11:00
____________________________________________________________

Got it. I've added this task:
[E][ ] project meeting (from: 10 Feb 2025 12:00 to: 10 Mar 2025 11:00)
Now you have 5 tasks in the list.
____________________________________________________________

list
____________________________________________________________

Here are the tasks in your list:
1.[D][X] buy book (by: 15 Oct 2024 18:00)
2.[T][ ] fix
3.[T][ ] read book
4.[D][ ] charge phone (by: 30 Mar 2025 18:00)
5.[E][ ] project meeting (from: 10 Feb 2025 12:00 to: 10 Mar 2025 11:00)
____________________________________________________________
```


## Appendix: Date and Time Format
Task Manager Alpaca supports the following date and time formats for deadlines and events:
- Full Date and Time: `YYYY-MM-DD HH:MM` (e.g., `2024-03-30 18:00`)
- Date Only: `YYYY-MM-DD` (e.g., `2024-03-30`)



