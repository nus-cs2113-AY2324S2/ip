## Command Summary

<table>
<thead>
<tr>
<th><strong>Command</strong></th>
<th><strong>Usage</strong></th>
<th><strong>Description</strong></th>
</tr>
</thead>
<tbody>
<tr>
<td><strong>add</strong></td>
<td>
<ul>
<li><code>add -re -n ITEM_NAME -d ITEM_DESCRIPTION [-q ITEM_QUANTITY] -e EXPIRY_DATE -s SALE_PRICE -c COST_PRICE [-t THRESHOLD]</code> </li>
<li><code>add -op -n ITEM_NAME -d ITEM_DESCRIPTION [-q ITEM_QUANTITY] -e EXPIRY_DATE -c COST_PRICE [-t THRESHOLD]</code></li>
</ul>
</td>
<td>Adds a new item to the inventory.</td>
</tr>
<tr>
<td><strong>search</strong></td>
<td><code>search [-n NAME_QUERY] [-d DESCRIPTION_QUERY] [-q QUANTITY_RANGE] [-c COST_PRICE_RANGE] [-s SALE_PRICE_RANGE] [-e EXPIRY_DATE_RANGE] [-l NUMBER_OF_RESULTS]</code></td>
<td>Searches for items in the inventory based on various criteria. At least one of the optional flags must be specified. <code>-l</code> must be used with other flags.</td>
</tr>
<tr>
<td><strong>list</strong></td>
<td><ul>
<li><code>list</code></li>
<li><code>list -c</code></li>
<li><code>list -s</code></li>
<li><code>list -e</code></li>
<li><code>list -p</code></li>
</ul></td>
<td>Lists all items in the inventory, with optional sorting based on cost price, sale price, expiry date or profits.</td>
</tr>
<tr>
<td><strong>delete</strong></td>
<td><ul>
<li><code>delete -i ITEM_INDEX</code></li>
<li><code>delete -n ITEM_NAME</code></li>
</ul></td>
<td>Deletes an item from the inventory.</td>
</tr>
<tr>
<td><strong>sell</strong></td>
<td><ul>
<li><code>sell -n ITEM_NAME -q ITEM_QUANTITY</code></li>
<li><code>sell -i ITEM_INDEX -q ITEM_QUANTITY</code></li>
</ul></td>
<td>Decreases the quantity of an item after a sale.</td>
</tr>
<tr>
<td><strong>restock</strong></td>
<td><ul>
<li><code>restock -n ITEM_NAME -q ITEM_QUANTITY</code></li>
<li><code>restock -i ITEM_INDEX -q ITEM_QUANTITY</code></li>
</ul></td>
<td>Increases the quantity of an item after restocking.</td>
</tr>
<tr>
<td><strong>update</strong></td>
<td><ul>
<li><code>update -n ITEM_NAME [-d ITEM_DESCRIPTION] [-q ITEM_QUANTITY] [-e EXPIRY_DATE] [-s SALE_PRICE] [-c COST_PRICE] [-t THRESHOLD]</code></li>
<li><code>update -i ITEM_INDEX [-d ITEM_DESCRIPTION] [-q ITEM_QUANTITY] [-e EXPIRY_DATE] [-s SALE_PRICE] [-c COST_PRICE] [-t THRESHOLD]</code></li>
</ul></td>
<td>Updates the details of an existing item in the inventory depending on which fields are specified.  At least one of the optional flags must be specified.</td>
</tr>
<tr>
<td><strong>profit</strong></td>
<td><code>profit</code></td>
<td>Displays the total profit earned from the inventory.</td>
</tr>
<tr>
<td><strong>quote</strong></td>
<td><code>quote</code></td>
<td>Displays a random quote on the screen.</td>
</tr>
<tr>
<td><strong>bye</strong></td>
<td><ul>
<li><code>bye</code></li>
<li><code>exit</code></li>
<li><code>quit</code></li>
</ul></td>
<td>Exits the application.</td>
</tr>
</tbody>
</table>

<div id="infoCallout" style="padding: 1em; border: 0 solid #9ec1cf;border-left-width: 4px;border-radius: 6px; margin-top: 1rem; margin-bottom: 1rem; padding: 1em; border-radius: 4px; color: #293132; background-color: #eef9fc;">
ℹ️ <strong>Note:</strong>
<li>Only one item type flag can be specified for each item. This means that you can only use either <code>-re</code> or <code>-op</code> but not both at the same time. </li> 
<li>The <code>-e</code> flag should be provided if the item that you are adding is a Perishable item. That is to say, it will expire by the provided expiry date.</li>
<li>The <code>-s</code> flag should be provided if the item that you are adding is a Retail item. This means that this item is meant to be sold.</li>
<li>The flags can be placed in any order. There is no specific order that you have to abide by.</li>
<li>Words in <code>UPPER_CASE</code> are the arguments that are meant to be supplied by you.<br>For example, in <code>-n ITEM_NAME</code>, <code>ITEM_NAME</code> would represent the name of the item you are adding (e.g., <code>add -n apple</code>).</li>
<li>For some commands, if flags and arguments are wrapped in square brackets, they are optional.<br>For example, <code>add [-q ITEM_QUANTITY]</code> signify that the <code>-q</code> flag, as well as its argument, are optional for the command.</li>
<li>For a better understanding of these commands, refer to the <a href="#features">features</a> section.</li>
</div>

[Back to table of contents](#table-of-contents)

---
<div style="page-break-after: always;"></div>

## FAQ

**Q**: How do I know if I have Java `11` installed on my computer? <br>
**A**: Using the terminal/command prompt, type in `java -version`. If Java `11` is installed, you should see a result that is similar to this:
```bash
$ java -version
openjdk version "11.0.22" 2024-01-16
OpenJDK Runtime Environment ... (build ...)
OpenJDK 64-Bit Server VM ... (build ...)
```
If not, refer to Oracle's [guide](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A) on installing Java `11` for your operating system.

**Q**: Can I move my BinBash data to another computer? <br>
**A**: Absolutely! Here's a step-by-step guide on how you can do this:
1. On your current computer, locate the BinBash save file. The save file can be found at `<Location of binbash.jar>/data/items.txt`. Make a copy of this file.
2. Ensure that BinBash has been installed on the other computer. Refer to [this section](#getting-started) for more details.
3. On the other computer, create the `/data` folder in the BinBash install location if it does not exist.
4. Then, paste the copied save file in this folder. If an existing save file already exists, choose to overwrite it.
5. Start up BinBash, and execute the [`list` command](#listing-current-inventory-list) to check that your data has been loaded successfully.

**Q**: Do I need an Internet connection to use BinBash? <br>
**A**: You do not need an Internet connection. BinBash can be used offline.

**Q**: I want to modify my `items.txt` file. How should I format my items?<br>
**A**: Firstly, we recommend that you take a backup of your current `items.txt` file before editing it.

Then, open the file in any text editor of your choice (you can use `Notepad` on Windows). Feel free to add, modify or remove rows, but do ensure that they adhere to this format:
```text
TYPE|NAME|DESCRIPTION|QUANTITY|COST_PRICE|UNITS_PURCHASED|THRESHOLD|EXPIRATION_DATE|SALE_PRICE|UNITS_SOLD|
```

If your item does not contain a certain attribute (e.g, no `ITEM_SALE_PRICE`), replace its value with a whitespace.

[Back to table of contents](#table-of-contents)

---
<div style="page-break-after: always;"></div>

## Glossary

### Bash
A computer program that provides a text-based interface and environment for user input. Also, the name of a programming language commonly used for scripting and operating system job control.

### Command Prompt / Command Line / Terminal
A means of interacting with a computer through keyboard typed lines of text, also known as commands. This is in contrast to the currently more popular graphical user interface (GUI), which uses visual elements that users can directly manipulate to perform their desired actions.

### Java
Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible.
