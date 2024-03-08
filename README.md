# John ChatBot project template

This is the IP for John Nguyen for CS2113 at National University of Singapore. This Porject is a Commandline based chatbot that performs tasks catered towards todolist.

## Quick Start

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Download the ip.jar file found in the repo at out/artifacts/ip_jar/ip.jar
2. Move the jar file to your desired folder.
3. Open your terminal or command line and `cd` into the folder you put the ip.jar file.
4. Run the command `java -jar ip.jar`
5. Now you should be able to Interact with John the ChatBot!

## Features
### Add a Todo task: `Todo`
* Expected Format: `todo [description]`
* Example Usage: `todo walk the dog`
* Example Output:
* ```
  ______________________________________
  Got it. I've added this task:
  [T][ ] walk the dog
  Now you have 1 tasks in the list
  ```

### Add a Deadline task: `Deadline`
* Expected Format: `deadline [description] /by [date]`
* Example Usage: `deadline walk the dog /by tomorrow`
* Example Output:
* ```
  ______________________________________
  Got it. I've added this task:
  [D][ ] walk the dog (by: tomorrow)
  Now you have 1 tasks in the list
  ```

### Add a Event task: `Event`
* Expected Format: `event [description] /from [start time] /to [end time]`
* Example Usage: `event walk the dog /from today /to tomorrow`
* Example Output:
* ```
  ______________________________________
  Got it. I've added this task:
  [E][ ] walk the dog (from: today to: tomorrow)
  Now you have 1 tasks in the list
  ```

### Mark a task as complete: `Mark`
* Expected Format: `mark [number]` (One indexed)
* Example Usage: `mark 1`
* Example Output:
* ```
  OK, I've marked this task done:
  [T][X] walk the dog
  ```

### Mark a task as incomplete: `Unmark`
* Expected Format: `unmark [number]` (One indexed)
* Example Usage: `unmark 1`
* Example Output:
* ```
  OK, I've marked this task as not done yet:
  [T][ ] walk the dog
  ```

### Delete a task: `Delete`
* Expected Format: `delete [number]` (One indexed)
* Example Usage: `delete 1`
* Example Output:
* ```
  Noted. I've removed this task:
    [T][ ] walk the dog
  Now you have 0 tasks in the list.
  ```

### Display your Todolist: `List`
* Expected Format: `list`
* Example Usage: `list`
* Example Output:
* ```
  1. [T][ ] walk the dog
  2. [D][ ] walk the cat (by: tomorrow)
  3. [E][ ] walk the monkey (from: today to: tomorrow)
  ```

### Exit the ChatBot: `Bye`
* Expected Format: `bye`
* Example Usage: `bye`
* Example Output:
* ```
  ______________________________________
  Hope to see you soon Bruda.
  ______________________________________
  ```
  
## Saving the Data

John Chatbot automatically saves the current state of your todolist to a `data.txt` that will exist in the same folder as your ip.jar file.
