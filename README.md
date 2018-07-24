#**Demo-Consuming-Rest**

A simple command-line program that will print out a list of food trucks, given a source of food truck data from the San Francisco government’s API.
The JAVA program prints out a list of food trucks that are open at the current date, when the program is being run.

The program display the name and address of the trucks and sort the output alphabetically by name.  It displays the results in pages of 10 trucks. 
That is: if there are more than 10 food trucks open, the program should display the first 10, then wait for input from the user before (1) displaying the
next 10 (or fewer if there are fewer than 10 remaining), and so on until there are no more food trucks to display, or (2) exiting the program early.

The program also displays the operation hours and status (Open) for testing purpose.

Public data: https://data.sfgov.org/resource/bbb8-hzi6.json
---

## Install dependencies and Build the program
Make sure the pom.xml file is attached with the project.  The project is built using SpringBoot, which is designed to run with minimal upfront configuration

- In Netbeans: select Build with Dependencies
- In Intellij: make sure Maven is set to AutoImport


## Run the file
1. JDK 8 or later
2. On the command line, go to the location of the jar file, named demo-0.0.1-SNAPSHOT.jar
3. Run this command: java -jar demo-0.0.1-SNAPSHOT.jar. 

## Potential for a full-scale web application
With some few modifications, such as adding GET/POST mapping, The program is 'almost' web-ready for a restful web service.  A View can also be added to display the results.

