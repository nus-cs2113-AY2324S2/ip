# User Guide

## Feauture List
- ### Task Logging
    Wongster allows you to log tasks into a task list using three categories:
    + **todo**: General tasks
    + **deadline**: Tasks with a specific deadline
    + **event**: Events with a specified time range
  
- ### Task Listing and Searching
  Wongster will show you the list you currently have, as well as search for items:
  + **list**: prints out the full list, as well as its completion status
  + **find xxx**: xxx is the keyword, for eg book, and Wongster will find all
    entries with the keyword book
  
- ### Task Management
    Wongster allows you to mark and unmark tasks, as well as delete unwanted tasks:
    + **mark X**: mark the task as done
    + **unmark X** mark the task as not done
    + **delete X**: removes the task from the list
    where X represents its position in the list

## Usage

+ ### Adding a todo task
  + **Example of usage**: 
  ````
  todo borrow book
  ````
  + **Expected outcome**:
  ````
  Got it. I've added this task:
  [T][ ] borrow book
  Now you have 2 tasks in the list.
  ````
+ ### Adding a deadline task
  + **Example of usage**:
  ````
  deadline return book /by Sunday
  ````
  + **Expected outcome**:
  ````
  Got it. I've added this task:
  [D][ ] return book (by: Sunday)
  Now you have 3 tasks in the list.
  ````
+ ### Adding an event task
  + **Example of usage**:
  ````
  event project meeting /from Mon 2pm /to 4pm
  ````
  + **Expected outcome**:
  ````
  Got it. I've added this task:
  [E][ ] project meeting (from: Mon 2pm to: 4pm)
  Now you have 4 tasks in the list.
  ````
+ ### Printing out the entire list
  + **Example of usage**:
  ````
  list
  ````
  + **Expected outcome**:
  ````
  Here are tasks in your list:
  
  1.[T][X] join soccer club
  2.[T][ ] borrow book
  3.[D][ ] return book (by: Sunday)
  4.[E][X] project meeting (from: Mon 2pm to: 4pm)
  ````
+ ### Searching for tasks with keywords
  + **Example of usage**:
  ````
  find book
  ````
  + **Expected outcome**:
  ````
  Found tasks matching "book":
  1. [T][ ] borrow book
  2. [D][ ] return book (by: Sunday)
  ````
+ ### Marking a task as done
  + **Example of usage**:
  ````
  mark 2
  ````
  + **Expected outcome**:
  ````
  Nice! I've marked this task as done: 
  [X] borrow book
  ````
+ ### Marking a task as not done
  _Although the default status of the task is not done, users may sometimes
accidentally mark the task as done, so they can reverse this action._
  + **Example of usage**:
  ````
  unmark 4
  ````
  + **Expected outcome**:
  ````
  OK, I've marked this task as not done yet: 
  [ ] project meeting (from: Mon 2pm to: 4pm)
  ````
+ ### Deleting a task
  + **Example of usage**:
  ````
  delete 3
  ````
  + **Expected outcome**:
  ````
  Noted. I've removed this task: 
    [D][ ] return book (by: Sunday)
  Now you have 3 tasks in the list.
  ````
+ ### Error handling 
  _handles errors such as improper user input, eg without descriptions_
  + **Example of usage**:
  ````
  todo
  ````
  + **Expected outcome**:
  ````
  Please input a proper task!
  Help: Tasks start with todo, deadline or event.
  ````
+ ### Goodbye
  _exits the chatbot after this command, and user data is saved in a text file_
  + **Example of usage**:
  ````
  bye
  ````
  + **Expected outcome**:
  ````
  Bye. Hope to see you again soon!
  ````