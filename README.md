# Blue

Blue is a chatbot that helps you manage your tasks.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
   - Click `Open`.
   - Select the project directory, and click `OK`.
   - If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/blue/Blue.java` file, right-click it, and choose `Run Blue.main()`. If the setup is correct, you should see something like this:
   ```
   -------------------------------------------------------------
    Welcome to Blue, a command line task assistant.
   -------------------------------------------------------------
   ```
