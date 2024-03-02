# Sinep User Guide
A CLI Chatbot to track and manage tasks.
***

## Features
***

### 1. Add a Todo Task

Adds a task of type TODO to the list. This is for tasks that do not have a deadline.

Format : ``` todo DESCRIPTION ```

Example command: `todo study tonight` <br>
Example output:
```
Added to Task List: 
[T][ ] study tonight
```
<br>

### 2. Add a Deadline Task

Adds a task of type DEADLINE to the list. This is for tasks that has one deadline.

Format : ``` deadline DESCRIPTION /by DEADLINE ```

Example command: `deadline study /by tonight` <br>
Example output:
```
Added to Task List: 
[D][ ] study (by: tonight)
```
<br>

### 3. Add an Event Task

Adds a task of type EVENT to the list. This is for tasks that has a time period.

Format : ``` event DESCRIPTION /from START_DATE /to END_DATE ```

Example command: `event study /from 22-03-24 /to 23-03-24` <br>
Example output:
```
Added to Task List: 
[E][ ] study (from: 22-03-24 to: 23-03-24)
```
<br>

### 4. Mark Task

Marks a task as done.

Format : ``` mark INDEX ```

Example command: `mark 1` <br>
Example output:
```
Got it! Task 1 marked as done:
[X] study
```
<br>

### 5. Unmark Task

Marks a task as undone.

Format : ``` unmark INDEX ```

Example command: `unmark 1` <br>
Example output:
```
Got it! Task 1 unmarked as done:
[ ] study
```
<br>

### 6. Delete Task

Delete a task from the list.

Format : ``` delete INDEX ```

Example command: `delete 1` <br>
Example output:
```
Noted. I have removed this task:
[T][0] study
Now you have 7 tasks left.
```
<br>

### 7. Find Task

Find a task from the list.

Format : ``` find KEYWORD ```

Example command: `Find study` <br>
Example output:
```
Here are the matching tasks in your list:
1.[T][ ] study
```
<br>

### 8. List

Gives a list of current tasks

Format : ``` list ```

Example command: `list` <br>
Example output:
```
Here are the current tasks in your list:
1.[T][ ] study
```
<br>

### 9. Exit Programme

Exit the Programme

Format : ``` byer ```

Example command: `bye` <br>
Example output:
```
Bye. Hope to see you again soon!
```
<br>
