# Multi-IDE-Java-Template

A template Java project using Maven, capable being opened in VS Code, NetBeans, and Eclipse.

Why? Because I like using Eclipse, I have a teacher that insists on NetBeans, and I want to be able to edit collaboartively using VS Code's Live Share extension.

This took a whole day to set up myself, so I hope I made someone's life easier.

Note: I have only tested this on Ubuntu Linux.

## Prerequisites

You will need [Java](https://www.java.com/en/download/manual.jsp) 8 or higher, [Apache Maven](https://maven.apache.org/download.cgi), and [Apache Ant](https://ant.apache.org/bindownload.cgi).

On Ubuntu, you can install these with this command: `sudo apt install default-jdk maven ant`

## How to use

### [NetBeans](https://netbeans.apache.org/download/index.html)

Just open up the project folder. NetBeans will automatically download any required libs.

### [VS Code](https://code.visualstudio.com/download)

First, make sure that the [Java Extension Pack](vscode:extension/vscjava.vscode-java-pack) is installed.

Simply open the folder in VS Code. You can use Ctrl+Shift+B to build and run the project.

I also highly recommend [VS Live Share](vscode:extension/MS-vsliveshare.vsliveshare) and [XML Tools](vscode:extension/DotJoshJohnson.xml).

### [Eclipse](https://www.eclipse.org/downloads/)

Right-click the package explorer and click Import. Under the "Maven" folder, select "Existing Maven Projects". Do NOT import from the expanded-by-default "General" folder.

You might need to fiddle around with the build path. If you have issues with missing keywords, try deleting "JRE Standard Library" and then re-adding it.

## Other

If you aren't able to get this template project working with these instructions, please open an issue.

Once you get it working, you can do a bulk find/replace to change "testing123" to your project's name. Remember to change file paths and within files, and make sure the casing is correct.


