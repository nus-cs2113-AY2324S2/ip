# Hiko

Hiko is a chatbot that has many functionalities.
Join and discover more uses of Hiko !!!

# Quick Start

1.Download the release package hiko.jar.
2.Run by right-clicking and selecting “open in terminal”. 
3.Type in the following: java -jar hiko.jar
4.Strart using Hiko !

## Features
Hiko has following Features

### 1.Store "ToDo" tasks
- By typing (todo + task descriptions), hiko will auto record down the task that you have to do.

### 2.Store "Event" tasks
- By typing (event + event name + /from start time + /to end time) ,hiko will auto record down the event with start and end time.

### 3.Store "DeadLine" tasks
- By typing (deadline + task name + /by end time) ,hiko will auto record down the task with its deadline.

### 4.Auto write in.
- Hiko will auto record down the tasks that you have input in the .txt file under /docs folder.

## 1. Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Hiko.java` file, right-click it, and choose `Run Hiko.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from 
```
 __ __  ____  __  _   ___  
|  |  ||    ||  |/ ] /   \
|  |  | |  | |  ' / |     |
|  _  | |  | |    \ |  O  |
|  |  | |  | |     \|     |
|  |  | |  | |  .  ||     |
|__|__||____||__|\_| \___/

   ```

# Updates

31/01/2024: Updated new function 'task recording'. Now whatever you type will be recorded as task..
